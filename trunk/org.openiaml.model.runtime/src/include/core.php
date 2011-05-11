<?php

/**
 * Core runtime classes and functions. It must be possible to include
 * this file without having to define any additional runtime parameters,
 * e.g. global.php's ROOT_PATH.
 */

/**
 * Similar to a Java's RuntimeException.
 * We can also pass along additional information.
 */
class IamlRuntimeException extends Exception {
  var $more_info;

  public function __construct($message = "", $more_info = "") {
    parent::__construct($message);
    $this->more_info = $more_info;
  }
}

/**
 * Similar to a Java's IllegalArgumentException.
 * We can also pass along additional information.
 */
class IamlIllegalArgumentException extends IamlRuntimeException {
  public function __construct($message = "", $more_info = "") {
    parent::__construct($message, $more_info);
  }
}

/**
 * A specific runtime exception to say that data from the current
 * session seems invalid. Perhaps the user needs to reset their
 * session?
 */
class IamlInvalidSessionException extends IamlRuntimeException {
  public function __construct($message = "", $more_info = "") {
    parent::__construct($message, $more_info);
  }
}
