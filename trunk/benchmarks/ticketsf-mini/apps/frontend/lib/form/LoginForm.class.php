<?php

class LoginForm extends sfForm
{
  public function configure()
  {
    $this->setWidgets(array(
      'email'  		=> new sfWidgetFormInputText(),
      'password'   	=> new sfWidgetFormInputPassword(),
    ));

    $this->setValidators(array(
      'email' 		=> new sfValidatorString(array('max_length' => 255)),
      'password'	=> new sfValidatorString(array('max_length' => 255)),
    ));
  }
}
