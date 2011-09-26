<h1>All Events</h1>

<!-- search input will go here -->
<div class="event_search">
<form action="<?php echo url_for("event/allEvents"); ?>" method="GET">
  <label class="event_search">Event Search
    <?php
    echo $searchForm["q"]->render(array(
      'id' => 'event_search_q',
      'class' => 'event_search_query',
    ));
?>
  </label>
</form>
</div>

<?php include_partial("results", array("Events" => $Events)) ?>
