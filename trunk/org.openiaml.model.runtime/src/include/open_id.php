<?php

/**
 * OpenID support.
 *
 */

/**
 * Has the given URL been authenticated against the current session?
 */
function is_authenticated($url) {
	$done = isset($_SESSION["openid_authenticated"]) ? $_SESSION["openid_authenticated"] : array();
	
	return isset($done[$url]);
}

function get_openid_links($url) {

	log_message("[openid] Retrieving $url");
	$remote = @file_get_contents($url);
	if ($remote === false) {
		log_message("[openid] file_get_contents() failed");
		return false;
	}
	
	// find all <link>s in the page
	// I don't know of a reliable way to parse a broken HTML document into an XML
	// DOM, so we will just use a regular expression
	preg_match_all('/<link.*?\s+rel\s*=\s*[\'"]([^\'"]+)[\'"]\s*.*?href\s*=\s*[\'"]([^\'"]+)[\'"].*?>/im', $remote, $links_1, PREG_SET_ORDER);
	preg_match_all('/<link.*?\s+href\s*=\s*[\'"]([^\'"]+)[\'"]\s*.*?rel\s*=\s*[\'"]([^\'"]+)[\'"].*?>/im', $remote, $links_2, PREG_SET_ORDER);
	
	$links = array();
	foreach ($links_1 as $result) {
		$links[$result[1]] = $result[2];
	}
	foreach ($links_2 as $result) {
		$links[$result[2]] = $result[1];
	}
	
	log_message("[openid] Found links = " . print_r($links, true));
	$server = null;
	$delegate = null;
	foreach ($links as $key => $value) {
		if (strtolower($key) == "openid.server") {
			$server = $value;
		}
		if (strtolower($key) == "openid.delegate") {
			$delegate = $value;
		}
	}
	
	if (!$server) {
		log_message("[openid] No server found");
		return false;
	}
	return array("server" => $server, "delegate" => $delegate);

}

/**
 * Perform OpenID authentication against the given $url; if we
 * need to return via callback, goto $return; then finally,
 * go to the URL $final.
 * 
 * $return will call openid_callback(), and that is all.
 *
 * May redirect the user away.
 *
 * @returns false if it failed; true if it passed; or a String indicating
 *		where the user must be redirected
 */
function openid_retrieve($url, $return, $final) {

	$parse = get_openid_links($url);
	if ($parse === false)
		return false;
	$server = $parse["server"];
	$delegate = $parse["delegate"];

	log_message("[openid] Found server: $server, delegate: $delegate");
	
	$identity = isset($delegate) ? $delegate : $url;
	
	// first, establish a secret
	$_SESSION["openid_secret"] = sprintf("%04x%04x%04x%04x", rand(0,0xffff), rand(0,0xffff), rand(0,0xffff), rand(0,0xffff));
	$_SESSION["openid_final"] = $final;
	
	$args = array(
		"secret" => $_SESSION["openid_secret"],
	);
	$return_added = url_add($return, $args);
	
	// now construct the URL to redirect the user to
	$url = $server;
	$args = array(
		"openid.mode" => "checkid_setup",
		"openid.identity" => $identity,
		"openid.return_to" => $return_added,
	);
	$url = url_add($url, $args);
	
	// currently we don't use any AJAX calls; we just use a simple redirect	
	return $url;
}

/**
 * Add the arguments in the $args array to the given url.
 * Return the new url.
 */
function url_add($url, $args) {
	foreach ($args as $key => $value) {
		if (strpos($url, "?") === false) {
			$url .= "?";
		} else {
			$url .= "&";
		}
		$url .= urlencode($key) . "=" . urlencode($value);
	}
	return $url;
}

/**
 * Called as part of the AJAX callback.
 * Either outputs:
 *  - 'alert X': something bad happened
 *  - 'ok': has been validated
 *	- 'redirect URL': redirect to the given URL
 */
function openid_ajax_callback($return) {
	
	$url = require_get("url");
	$final = require_get("final");
	
	$result = openid_retrieve($url, $return, $final);
	if ($result === false) {
		echo "alert " . urlencode("Could not load authentication from $url");
	} else if ($result === true) {
		echo "ok";
	} else {
		echo "redirect " . urlencode($result);
	}
	
	die;	
}

/**
 * This method is called on an OpenID callback page.
 */
function openid_callback() {
	
	$secret = require_session("openid_secret");
	$final = require_session("openid_final");
	
	// check we have the right secret
	$their_secret = require_get("secret");
	if ($secret != $their_secret) {
		throw new IamlRuntimeException("Invalid secret");
	}
	
	$url = require_get("openid_identity");
	$parse = get_openid_links($url);
	if ($parse === false) {
		throw new IamlRuntimeException("Could not parse url '$url'");
	}
	$server = $parse["server"];
	$delegate = $parse["delegate"];
	log_message("[openid] Found server: $server, delegate: $delegate");

	// now construct the URL to redirect the user to
	$url = $server;
	$args = array(
		"openid.mode" => "check_authentication",
		"openid.assoc_handle" => require_get("openid_assoc_handle", ""),
		"openid.sig" => require_get("openid_sig", ""),
		"openid.signed" => require_get("openid_signed", ""),
		"openid.response_nonce" => require_get("openid_response_nonce", ""),
		"openid.identity" => require_get("openid_identity", ""),
		"openid.return_to" => require_get("openid_return_to", ""),
		"openid.invalidate_handle" => "123456",
	);
	$url = url_add($url, $args);
	
	log_message("[openid] Asking for confirmation: $url");
	$response = file_get_contents($url);
	if ($response === false) {
		throw new IamlRuntimeException("Could not parse url '$url'");
	}
	
	log_message("[openid] Response: $response");
	if (preg_match("/is_valid:true/", $response)) {
		// ok
		// redirect
		log_message("[openid] Success: Redirecting to $final");
		echo "redirect " . urlencode($final);
	} else {
		log_message("[openid] Failure");
		throw new IamlRuntimeException("Response from '$url' was not authenticated");
	}
	
}


