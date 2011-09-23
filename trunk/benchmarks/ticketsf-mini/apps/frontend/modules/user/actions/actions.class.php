<?php

/**
 * user actions.
 *
 * @package    ticketsf-mini
 * @subpackage user
 * @author     Your name here
 */
class userActions extends sfActions
{
	// executeIndex not allowed

	public function executeLogin(sfWebRequest $request) {
		$this->form = new LoginForm();
	}

	public function executeLogout(sfWebRequest $request) {
		$this->getUser()->logout();
		$this->getUser()->setFlash("notice", "Successfully logged out.");
		$this->redirect("user/login");
	}

	// perform login
	public function executeLoginDo(sfWebRequest $request) {
		$this->forward404Unless($request->isMethod(sfRequest::POST));

		$this->form = new LoginForm();

		$this->form->bind($request->getPostParameters());
		if (!$this->form->isValid()) {
			$this->getUser()->setFlash("error", "Invalid input.");
			$this->redirect('user/login');
		}

		// try login
		$login = $this->getUser()->login($this->form["email"]->getValue(), $this->form["password"]->getValue());
		if (!$login) {
			$this->getUser()->setFlash("error", "Incorrect credentials.");
			$this->redirect('user/login');
		}

		$this->getUser()->setFlash("notice", "Successfully logged in.");
		$this->redirect("ticket/index");		// todo redirect to your ticket page

	}

  public function executeNew(sfWebRequest $request)
  {
    $this->form = new UserForm();
  }

  public function executeCreate(sfWebRequest $request)
  {
    $this->forward404Unless($request->isMethod(sfRequest::POST), $request->getMethod());

    $this->form = new UserForm();

    $this->form->bind($request->getParameter($this->form->getName()), $request->getFiles($this->form->getName()));
	if ($this->form->isValid())
	{
	  $User = $this->form->save();

	  	// send an email
	  	$mailer = sfContext::getInstance()->getMailer();
	  	$message = Swift_Message::newInstance();
	  	$message->setFrom('ticket20@openiaml.org');
	  	$message->setTo($User->getEmail());
	  	$message->setSubject('New Ticket 2.0 account');
	  	$message->setBody($this->getPartial("signup_message", array("user" => $User)));
	  	$mailer->send($message);

		// they can now log in
		$this->getUser()->setFlash("notice", "Account successfully created. You may now login.");

	  $this->redirect('user/login');
    }

    $this->setTemplate('new');
  }

	// no edit
	// no update
	// no delete

}
