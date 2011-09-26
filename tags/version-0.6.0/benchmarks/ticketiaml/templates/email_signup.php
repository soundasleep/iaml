<html>
<body>
<p>Hi <?php echo htmlspecialchars($property["toName"]); ?>,</p>

<p>You have recently created a new Ticket 2.0 account, using the e-mail address
<a href="mailto:<?php echo htmlspecialchars($property["to"]); ?>"><?php echo htmlspecialchars($property["to"]); ?></a>. Enjoy!</p>

<p>- <?php echo htmlspecialchars($property["fromName"]); ?> &lt;<a href="mailto:<?php echo htmlspecialchars($property["from"]); ?>"><?php echo htmlspecialchars($property["from"]); ?></a>&gt;</p>
</body>
</html>
