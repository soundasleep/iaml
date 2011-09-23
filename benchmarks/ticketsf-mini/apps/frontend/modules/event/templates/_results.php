<script type="text/javascript">
reset_map_results();
</script>

<div id="events_search_results">
<table class="list_events_list">
  <thead>
    <tr>
      <th class="list_heading_title">Title</th>
      <th class="list_heading_venue">Venue</th>
      <th class="list_heading_event_date">Date</th>
      <th class="list_heading_tickets_left">Tickets left</th>
    </tr>
  </thead>
  <tbody>
    <?php foreach ($Events as $Event): ?>
    <tr>
      <td><a href="<?php echo url_for('event/show?id='.$Event->getId()) ?>"><?php echo $Event->getTitle() ?></a></td>
      <td>
      	<?php echo $Event->getVenue() ?>
      	<script type="text/javascript">add_map_location("<?php echo htmlspecialchars($Event->getVenue()); ?>", "<?php echo htmlspecialchars($Event->getTitle()); ?>");</script>
      </td>
      <td><?php echo $Event->getDate() ?></td>
      <td><?php echo $Event->getTicketsLeft() ?></td>
    </tr>
    <?php endforeach; ?>
    <?php if (count($Events) == 0) { ?>
    <tr>
    	<td colspan="4"><i>No results found.</i>
    </tr>
    <?php } ?>
  </tbody>
</table>
</div>

