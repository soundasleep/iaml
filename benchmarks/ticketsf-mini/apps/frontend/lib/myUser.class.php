<?php

class myUser extends sfBasicSecurityUser
{

	public function isLoggedIn() {
		return $this->isAuthenticated() && $this->getAttribute("user") !== null;
	}

	public function getProfile() {
		return $this->getAttribute("user");
	}

	/**
	 * @returns true if login was successful, false otherwise
	 */
	public function login($email, $password) {

		$c = new Criteria();
		$c->add(UserPeer::EMAIL, $email);
		$c->add(UserPeer::PASSWORD, $password);

		$obj = UserPeer::doSelectOne($c);
		if (!$obj)
			return false;

		$this->setAttribute("user", $obj);
		$this->setAuthenticated(true);

		// update permissions
		$this->clearCredentials();
		if ($obj->getIsManager())
			$this->addCredential('manager');
		if ($obj->getIsAdmin())
			$this->addCredential('admin');

		return true;

	}

	public function logout() {
		$this->setAttribute("user", null);
		$this->setAuthenticated(false);
		$this->clearCredentials();
	}

}
