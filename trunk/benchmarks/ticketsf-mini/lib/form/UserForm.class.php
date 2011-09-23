<?php

/**
 * User form.
 *
 * @package    ticketsf-mini
 * @subpackage form
 * @author     Your name here
 */
class UserForm extends BaseUserForm
{
  public function configure()
  {
	  // to disable form fields, unset() will remove both the widget and the validator
	  // http://www.symfony-project.org/jobeet/1_2/Propel/en/10

	  unset($this['is_admin']);
	  unset($this['is_manager']);
	  unset($this['created_at']);
	  unset($this['updated_at']);

	  // email should be validated
	  $this->validatorSchema['email'] = new sfValidatorAnd(array(
		$this->validatorSchema['email'],
		new sfValidatorEmail(),
	  ));

	  // emails should not be duplicated
	  $this->validatorSchema['email'] = new sfValidatorAnd(array(
		$this->validatorSchema['email'],
		new sfValidatorCallback(array("callback" => array($this, 'checkEmail'))),
	  ));

	  // password should be a password field
	  $this->setWidget("password", new sfWidgetFormInputPassword());
  }

  public function checkEmail($validator, $email) {
	  $c = new Criteria();
	  $c->add(UserPeer::EMAIL, $email);
	  $existing = UserPeer::doSelectOne($c);

	  if ($existing)
	  	throw new sfValidatorError($validator, "Email '$email' already exists.");

	  return $email;
  }
}
