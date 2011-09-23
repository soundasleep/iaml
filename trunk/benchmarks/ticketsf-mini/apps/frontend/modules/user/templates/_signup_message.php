Hi <?php echo $user->getEmail(); ?>,

Welcome to Ticket 2.0! You can now login via <?php $url = url_for("user/login", array("absolute" => true)); echo link_to($url, $url); ?>.

- Ticket 2.0