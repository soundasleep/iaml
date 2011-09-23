<?php

/**
 * Ticket filter form base class.
 *
 * @package    ticketsf-mini
 * @subpackage filter
 * @author     Your name here
 */
abstract class BaseTicketFormFilter extends BaseFormFilterPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'event_id'   => new sfWidgetFormPropelChoice(array('model' => 'Event', 'add_empty' => true)),
      'user_id'    => new sfWidgetFormPropelChoice(array('model' => 'User', 'add_empty' => true)),
      'created_at' => new sfWidgetFormFilterDate(array('from_date' => new sfWidgetFormDate(), 'to_date' => new sfWidgetFormDate())),
    ));

    $this->setValidators(array(
      'event_id'   => new sfValidatorPropelChoice(array('required' => false, 'model' => 'Event', 'column' => 'id')),
      'user_id'    => new sfValidatorPropelChoice(array('required' => false, 'model' => 'User', 'column' => 'id')),
      'created_at' => new sfValidatorDateRange(array('required' => false, 'from_date' => new sfValidatorDate(array('required' => false)), 'to_date' => new sfValidatorDate(array('required' => false)))),
    ));

    $this->widgetSchema->setNameFormat('ticket_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Ticket';
  }

  public function getFields()
  {
    return array(
      'id'         => 'Number',
      'event_id'   => 'ForeignKey',
      'user_id'    => 'ForeignKey',
      'created_at' => 'Date',
    );
  }
}
