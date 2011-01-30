<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title><?php echo htmlspecialchars(strtoupper($body_title)); ?></title>
    <link rel="stylesheet" type="text/css" href="<?php echo IAML_RUNTIME_WEB; ?>css/default.css">
    <?php if (file_exists($body_id . ".css")) { ?>
      <link rel="stylesheet" type="text/css" href="<?php echo $body_id; ?>.css">
      <?php } ?> <?php if (file_exists(CUSTOM_WEB_PATH . "default.css")) { ?>
      <link rel="stylesheet" type="text/css" href="<?php echo CUSTOM_WEB_PATH; ?>default.css">
      <?php } ?>