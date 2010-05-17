<?php

/**
 * Definitions for the can_cast() and do_cast() type methods.
 *
 */

/**
 * Make the given value into a boolean, according to a set of
 * specified conversions: http://code.google.com/p/iaml/wiki/IamlPrimitiveOperations#true?
 */
function make_into_boolean($value) {
	if ($value === "0" || $value === "false" || $value === "" || $value === null)
		return false;
	if ($value === 0 || $value === 0.0 || $value === false)
		return false;
	return true;
}

/**
 * Get the regexp to check e-mail types for.
 * TODO this should be synchronised with the XSD type.
 */
function get_email_datatype_regexp() {
	return "#^([0-9a-zA-Z]([-.\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\w]*[0-9a-zA-Z]\.)+[a-zA-Z]{2,9})$#";
}

/**
 * Returns true if the given value (any type) can be successfully cast into the given
 * XSD type.
 *
 * <p>See also the documentation for {@model CastNode}.
 */
function can_cast($value, $type) {
	log_message("can_cast('" . print_r($value, true) . "', '$type')");
	switch ($type) {
		// casting to string
		case "":
		case "http://openiaml.org/model/datatypes#iamlString":
		case "http://openiaml.org/model/datatypes#iamlAddress":
		case "http://openiaml.org/model/datatypes#iamlURL":
			// can always convert anything to a String
			return true;

		case "http://openiaml.org/model/datatypes#iamlOpenIDURL":
			// OpenID urls can only be created if they have been authenticated
			// through OpenID
			if (openid_is_authenticated($value)) {
				return true;
			}
			return false;

		// casting to email
		case "http://openiaml.org/model/datatypes#iamlEmail":
			// can only convert strings
			if (is_string($value)) {
				if ($value === "")
					return true;	// can match empty string

				return preg_match(get_email_datatype_regexp(), $value);
			}

			// everything else fails
			return false;

		// casting to dateTime
		case "http://openiaml.org/model/datatypes#iamlDateTime":
			if ($value instanceof DateTime) {
				// already a datetime
				return true;
			}

			if (is_int($value)) {
				// PHP's ints are always constrained to min/max
				return true;
			}

			// a string
			if (is_string($value)) {
				// check it can be converted
				if (strtotime($value) === false) {
					// failed
					return false;
				}
				return true;
			}

			// everything else fails
			return false;

		// casting to integer
		case "http://openiaml.org/model/datatypes#iamlInteger":
			if (is_int($value))
				return true;	// already an integer

			// a string
			if (is_string($value)) {
				// maximum values
				if ($value === "-2147483648")
					return true;
				if ($value === "2147483647")
					return true;

				// convert it to an int
				$int = intval($value);

				// is it one of the maximums? if so, we have an overflow
				if ($int == -2147483648 || $int == 2147483647)
					return false;

				// "" = 0
				if ($int == 0 && $value == "") {
					return true;
				}

				// is the string representation of this intval equal
				// to the original string?
				if (strval($int) === $value) {
					// successfully converted
					return true;
				}

				// otherwise, this string has a floating point (etc), so
				// we fail
				return false;

			}

			// a date/time (PHP class)
			if ($value instanceof DateTime) {
				// a date/time

				$ts = $value->getTimestamp();

				// maximum values
				if ($ts == -2147483648) {
					// NOTE as far as i can tell, DateTime->format() always returns values in English
					return $value->format(DateTime::RFC2822) == "Fri, 13 Dec 1901 20:45:52 +0000";
				}
				if ($ts == 2147483647) {
					return $value->format(DateTime::RFC2822) == "Tue, 19 Jan 2038 03:14:07 +0000";
				}

				// otherwise, we can definitely return as an integer
				return true;

			}

			// don't know how to deal with this otherwise
			return false;

		default:
			throw new IamlRuntimeException("Unknown cast type '$type'");
	}

	throw new IamlRuntimeException("Unexpectedly fell out of can_cast check");
}

/**
 * Cast the given value to the given type, as best as possible.
 *
 * <p>See also the documentation for {@model CastNode}.
 */
function do_cast($value, $type) {
	log_message("do_cast('" . print_r($value, true) . "', '$type')");
	switch ($type) {
		// casting to string
		case "":
		case "http://openiaml.org/model/datatypes#iamlString":
		case "http://openiaml.org/model/datatypes#iamlAddress":
		case "http://openiaml.org/model/datatypes#iamlURL":
			// a date?
			if ($value instanceof DateTime) {
				// change to UTC first
				$value->setTimezone(new DateTimeZone("UTC"));

				// format as RFC 2822
				return $value->format(DateTime::RFC2822);
			}

			return (string) $value;

		case "http://openiaml.org/model/datatypes#iamlOpenIDURL":
			// if it's already been authenticated, pass
			if (can_cast($value, $type)) {
				return $value;
			}
			// otherwise, fails
			return "";

		// casting to email
		case "http://openiaml.org/model/datatypes#iamlEmail":
			// can only convert strings
			if (is_string($value)) {
				// a successful e-mail?
				if (can_cast($value, $type)) {
					return $value;
				}

				// otherwise, fails
				return "";
			}

			// everything else fails
			return "";

		// casting to dateTime
		case "http://openiaml.org/model/datatypes#iamlDateTime":
			if ($value instanceof DateTime) {
				// same thing
				return $value;
			}

			if (is_int($value)) {
				// if it can't be returned, just return the unix epoch
				if (!can_cast($value, $type))
					return new DateTime("@0");

				// convert timestamp to a date, and then convert to an object
				// "to create DateTime from timestamp, use the @ keyword"
				return new DateTime("@$value");
			}

			// a string
			if (is_string($value)) {
				// if it can't be returned, just return the unix epoch
				if (!can_cast($value, $type))
					return new DateTime("@0");

				// use DateTime to create object
				return new DateTime($value);
			}

			// everything else fails
			return new DateTime("@0");

		// casting to integer
		case "http://openiaml.org/model/datatypes#iamlInteger":
			if (is_int($value))
				return $value;	// return copy

			// a string
			if (is_string($value)) {
				// return best guess
				return (int) $value;

			}

			// a date/time (PHP class)
			if ($value instanceof DateTime) {
				// change to UTC first
				$value->setTimezone(new DateTimeZone("UTC"));

				// return timestamp
				return $value->getTimestamp();
			}

			// don't know how to deal with this otherwise
			return 0;

		default:
			throw new IamlRuntimeException("Unknown cast type '$type'");
	}

	throw new IamlRuntimeException("Unexpectedly fell out of do_cast");
}
