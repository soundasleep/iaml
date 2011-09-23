<?php

/**
 * Event form.
 *
 * @package    ticketsf-mini
 * @subpackage form
 * @author     Your name here
 */
class EventForm extends BaseEventForm
{
  public function configure()
  {
	  // disable fields
	  unset($this['created_at']);
	  unset($this['updated_at']);

  }
}
