<?php

class EventSearchForm extends sfForm
{
  public function configure()
  {
    $this->setWidgets(array(
      'q'   => new sfWidgetFormInputText(),
    ));

    $this->setValidators(array(
      'q' => new sfValidatorString(array('max_length' => 255)),
    ));
  }
}
