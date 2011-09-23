<h1>Your Tickets</h1>

<?php if ($sf_user->hasFlash('error')): ?>
  <div class="error"><?php echo $sf_user->getFlash('error') ?></div>
<?php endif ?>

<?php if ($sf_user->hasFlash('notice')): ?>
  <div class="notice"><?php echo $sf_user->getFlash('notice') ?></div>
<?php endif ?>

<table>
  <thead>
    <tr>
      <th>Event</th>
      <th>Ticket Purchased</th>
    </tr>
  </thead>
  <tbody>
    <?php foreach ($Tickets as $Ticket): ?>
    <tr>
      <td>
    	<a href="<?php echo url_for('ticket/show?id='.$Ticket->getId()) ?>">
    	<?php echo $Ticket->getEvent() == null ? "<i>unknown event</i>" : $Ticket->getEvent()->getTitle(); ?>
    	</a>
      </td>
      <td>
    	<?php echo $Ticket->getCreatedAt('r'); ?>
      </td>
    </tr>
    <?php endforeach; ?>
    <?php if (count($Tickets) == 0) { ?>
    <tr>
    	<td colspan="2"><i>You have not purchased any tickets.</i></td>
    </tr>
    <?php } ?>
  </tbody>
</table>
