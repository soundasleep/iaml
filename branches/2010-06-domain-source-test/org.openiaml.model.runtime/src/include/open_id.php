<?php

/**
 * OpenID support.
 *
 */

/**
 * Has the given URL been authenticated against the current session?
 */
function openid_is_authenticated($url) {
	$done = isset($_SESSION["openid_authenticated"]) ? $_SESSION["openid_authenticated"] : array();
	
	return isset($done[$url]);
}

/**
 * Mark the given URL as authenticated against our current session.
 */
function openid_mark_authenticated($url) {
	if (!isset($_SESSION["openid_authenticated"])) {
		$_SESSION["openid_authenticated"] = array();
	}
	$_SESSION["openid_authenticated"][$url] = $url;
}

function get_openid_links($url) {

	log_message("[openid] Retrieving $url");
	$url = new DownloadURL($url);
	$remote = $url->fetch();
	if (!$url->passed()) {
		log_message("[openid] Downloading URL failed: " . $url->getError());
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
	
	$identity = (isset($delegate) && $delegate) ? $delegate : $url;
	
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
function openid_ajax_callback($return, $id) {
	
	$url = require_get("url");
	$final = require_get("final");
	
	$result = openid_retrieve($url, $return, $final);
	if ($result === false) {
		echo "openid_fail " . escape_parameter_string($id) . " " . escape_parameter_string("Could not load authentication from $url");
		//echo "alert " . escape_parameter_string("Could not load authentication from $url");
	} else if ($result === true) {
		echo "ok\nopenid_pass " . escape_parameter_string($id);
	} else {
		echo "redirect " . escape_parameter_string($result);
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
	
	$identity = (isset($delegate) && $delegate) ? $delegate : $url;

	// now construct the URL to redirect the user to
	// this needs to be provided as POST, not GET
	$url = $server;

	// we MUST copy over all keys of 'openid_'
	$data = array();
	foreach ($_GET as $key => $value) {
		if (substr($key, 0, 7) == "openid_") {
			$data["openid." . substr($key, 7)] = require_get($key);
		}
	}

	// replace openid.mode
	$data["openid.mode"] = "check_authentication";
	print_r($data);
	
	log_message("[openid] Asking for confirmation: $url, data = " . print_r($data, true));
	
	$request = new DownloadURL($url, "POST", $data);
	$response = $request->fetch();
	if (!$request->passed()) {
		log_message("[openid] Downloading URL failed: " . $request->getError());
		return false;
	}
	if (!$response) {
		throw new IamlRuntimeException("Could not parse url '$url'");
	}
	
	log_message("[openid] Response: $response");
	if (preg_match("/is_valid:true/", $response)) {
		// ok
		openid_mark_authenticated($identity);
		
		// redirect
		log_message("[openid] Success: Redirecting to $final");
		
		// we are not in AJAX mode; we are actually redirecting the entire client
		server_redirect($final);
		die;
	} else {
		log_message("[openid] Failure");
		throw new IamlRuntimeException("Response from '$url' was not authenticated: $response");
	}
	
}
