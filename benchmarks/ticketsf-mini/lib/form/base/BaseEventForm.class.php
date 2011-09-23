<?php

/**
 * Event form base class.
 *
 * @method Event getObject() Returns the current form's model object
 *
 * @package    ticketsf-mini
 * @subpackage form
 * @author     Your name here
 */
abstract class BaseEventForm extends BaseFormPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'id'           => new sfWidgetFormInputHidden(),
      'title'        => new sfWidgetFormInputText(),
      'description'  => new sfWidgetFormTextarea(),
      'venue'        => new sfWidgetFormInputText(),
      'date'         => new sfWidgetFormDateTime(),
      'tickets_left' => new sfWidgetFormInputText(),
      'created_at'   => new sfWidgetFormDateTime(),
      'updated_at'   => new sfWidgetFormDateTime(),
    ));

    $this->setValidators(array(
      'id'           => new sfValidatorChoice(array('choices' => array($this->getObject()->getId()), 'empty_value' => $this->getObject()->getId(), 'required' => false)),
      'title'        => new sfValidatorString(array('max_length' => 255)),
      'description'  => new sfValidatorString(array('required' => false)),
      'venue'        => new sfValidatorString(array('max_length' => 255)),
      'date'         => new sfValidatorDateTime(),
      'tickets_left' => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647)),
      'created_at'   => new sfValidatorDateTime(array('required' => false)),
      'updated_at'   => new sfValidatorDateTime(array('required' => false)),
    ));

    $this->widgetSchema->setNameFormat('event[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Event';
  }


}
