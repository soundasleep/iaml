<body id="<?php echo $body_id; ?>" class="frame_<?php echo $body_name; ?>" onLoad="loadStoredEvents()">
  <div style="font-size:small; text-align: right;"><a href="clear_session.php">clear
  session</a> - <a href="sitemap.html">sitemap</a></div>
  <h1><?php echo htmlspecialchars($body_title); ?></h1>
  <div id="runtime_errors"><!-- any runtime errors go into here --></div><?php
    /* expand any fail error messages */
    if (has_get("fail")) { ?>
    <div class="error"><p>
      An exception occured: <i><?php echo htmlspecialchars(require_get("fail"));
        ?></i>
    </p></div><?php } ?>

  <label>Hello, world!</label>