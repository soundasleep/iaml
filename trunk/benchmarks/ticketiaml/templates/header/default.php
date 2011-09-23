<?php queue_log_messages(true); /* disable log messages */ ?>

<body id="<?php echo $body_id; ?>" class="frame_<?php echo $body_name; ?>" onLoad="loadStoredEvents()">
<div style="float: right; width: 240px; font-size: small; text-align: right;">
	<a href="clear_session.php">clear session</a> - <a href="sitemap.html">sitemap</a>
</div>

<?php /* add ticket 2.0 manual navigation */ ?>
<div class="navigation" id="user_navigation">
<ul>
	<li class="nav_home"><a href="index.php">Home</a></li>
	<li class="xnav_browse disabled"><a href="all_events.php">Browse Events</a></li>
	<?php if (condition_user_logged_in()) { ?>
	<li class="nav_tickets"><a href="user_tickets.php">Your Tickets</a></li>
	<!-- <li class="xnav_profile disabled">Your Profile</li> -->
	<li class="disabled">Your Friends</li>
	<li class="xnav_rec disabled">Recommended Events</li>
	<li class="nav_logout"><a href="user_logout.php">Logout</a></li>
	<?php } else { ?>
	<li class="nav_signup"><a href="signup.php">Signup</a></li>
	<li class="nav_login"><a href="user_login.php">Login</a></li>
	<?php } ?>
</ul>
</div>
<?php if (condition_user_logged_in() && condition_manager_logged_in()) { ?>
<div class="navigation" id="manager_navigation">
Manager tools:
<ul>
	<li class="nav_all"><a href="events_list.php">All Events</a></li>
	<li class="nav_new"><a href="event_new.php">Create New Event</a></li>
</ul>
</div>
<?php } ?>

<h1><?php echo htmlspecialchars($body_title); ?></h1>
<div id="runtime_errors"><!-- any runtime errors go into here --></div>

<?php
/* expand any fail error messages */
if (has_get("fail")) { ?>
	<div class="error"><p>
		An exception occured: <i><?php echo htmlspecialchars(require_get("fail")); ?></i>
	</p></div>
<?php } ?>
