<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <head>
    <?php include_http_metas() ?>
    <?php include_metas() ?>
	<?php include_slot('links'); ?>
    <?php include_title() ?>
    <link rel="shortcut icon" href="/favicon.ico" />
    <?php include_stylesheets() ?>

  	<?php use_javascript('jquery-1.6.2.min.js') ?>
  	<?php use_javascript('search.js') ?>
  	<?php use_javascript('default.js') ?>
    <?php include_javascripts() ?>
  </head>
  <body>
  	<div id="user_navigation" class="navigation">
	  	<ul>
	  		<li class="nav_home"><?php echo link_to("Home", "home/index"); ?></li>
	  		<li class="nav_browse"><?php echo link_to("Browse Events", "event/index"); ?></li>
	  		<?php if ($sf_user->isLoggedIn()) { ?>
	  		<?php if ($sf_user->hasCredential('manager')) { ?>
	  		<li class="nav_event_all"><?php echo link_to("All Events", "event/allEvents"); ?></li>
	  		<li class="nav_event_create"><?php echo link_to("New Event", "event/new"); ?></li>
	  		<?php } ?>
	  		<li class="nav_logout"><?php echo link_to("Your Tickets", "ticket/index"); ?></li>
	  		<li class="nav_logout"><?php echo link_to("Logout", "user/logout"); ?></li>
	  		<?php } else { ?>
	  		<li class="nav_signup"><?php echo link_to("Signup", "user/new"); ?></li>
	  		<li class="nav_login"><?php echo link_to("Login", "user/login"); ?></li>
	  		<?php } ?>
  		</ul>
  	</div>

    <?php echo $sf_content ?>
  </body>
</html>
