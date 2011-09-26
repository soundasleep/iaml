<h1>Ticket Details</h1>

<form class="ticket_details view">
	<h2>Ticket Details</h2>

	<label class="label_event"><a href="<?php echo url_for('event/show?id='.$Ticket->getEventId()) ?>"><?php echo $Ticket->getEvent() == null ? "<i>unknown event</i>" : $Ticket->getEvent()->getTitle(); ?></a></label>
	<label class="label_date"><?php echo $Ticket->getCreatedAt('r') ?></label>
	<label class="label_venue"><?php echo $Ticket->getEvent() == null ? "<i>unknown venue</i>" : $Ticket->getEvent()->getVenue(); ?></label>

</form>

<hr />

<a href="<?php echo url_for('ticket/index') ?>">List</a>
