<?php

/**
 * Helper methods for sending emails.
 *
 */

// use html2text implementation
require("html2text/html2text.php");

/**
 * Send an email. The addresses, subject and content are in the provided
 * array.
 *
 * @return true on pass, false on failure
 */
function send_email($args) {

	if (!defined('EMAIL_HANDLER'))
		throw new IamlRuntimeException('EMAIL_HANDLER is not defined');
	log_message("[mail] send mail: handler=" . EMAIL_HANDLER);

	switch (EMAIL_HANDLER) {
		case "phpmailer":
			return send_email_phpmailer($args);

		case "file":
			return send_email_file($args);

		default:
			throw new IamlRuntimeException("Unknown EMAIL_HANDLER '" . EMAIL_HANDLER . "'");
	}

}

/**
 * Send an e-mail using PHPmailer.
 */
function send_email_phpmailer($args) {
	require_once(EMAIL_HANDLER_PHPMAILER_INCLUDE . "class.phpmailer.php");

	$mail = new PHPMailer();
	$mail->AddReplyTo($args["from"], $args["from_name"] == null ? false : $args["from_name"]);
	$mail->SetFrom($args["from"], $args["from_name"] == null ? false : $args["from_name"]);
	$mail->AddAddress($args["to"], $args["to_name"] == null ? false : $args["to_name"]);
	$mail->Subject = $args["subject"];
	$mail->AltBody = convert_html_to_text($args["content"]);
	$mail->MsgHTML($args["content"]);

	log_message("[phpmailer] Preparing to send email with subject '" . $args["subject"] . "'");
	if ($mail->Send()) {
		// passed
		log_message("[phpmailer] Mail sent OK");
		return true;
	} else {
		// failed
		log_message("[phpmailer] Mail error: " . $mail->ErrorInfo);
		return false;
	}
}

/**
 * Log the e-mail to disk.
 */
function send_email_file($args) {

	if (!defined('EMAIL_HANDLER_FILE_DESTINATION')) {
		throw new IamlRuntimeException("EMAIL_HANDLER_FILE_DESTINATION was not specified");
	}

	$file = EMAIL_HANDLER_FILE_DESTINATION;
	$properties = load_properties($file);
	$size = get_property($properties, "size", 0);

	// set all the properties
	$properties = set_property($file, $properties, "email.$size.from", $args["from"]);
	$properties = set_property($file, $properties, "email.$size.to", $args["to"]);
	$properties = set_property($file, $properties, "email.$size.fromName", $args["from_name"]);
	$properties = set_property($file, $properties, "email.$size.toName", $args["to_name"]);
	$properties = set_property($file, $properties, "email.$size.subject", $args["subject"]);
	$properties = set_property($file, $properties, "email.$size.content", $args["content"]);

	// increment and save the size
	$properties = set_property($file, $properties, "size", $size + 1);

	// done! always passes
	return true;

}
