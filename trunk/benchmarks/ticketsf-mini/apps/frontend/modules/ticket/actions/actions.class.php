<?php

/**
 * ticket actions.
 *
 * @package    ticketsf-mini
 * @subpackage ticket
 * @author     Your name here
 */
class ticketActions extends sfActions
{
  public function executeIndex(sfWebRequest $request)
  {
	  $c = new Criteria();
	  $c->add(TicketPeer::USER_ID, $this->getUser()->getProfile()->getId());
	  $c->addDescendingOrderByColumn(TicketPeer::CREATED_AT);
    $this->Tickets = TicketPeer::doSelect($c);
  }

  public function executeShow(sfWebRequest $request)
  {
	  $c = new Criteria();
	  $c->add(TicketPeer::USER_ID, $this->getUser()->getProfile()->getId());
	  $c->add(TicketPeer::ID, $request->getParameter('id'));
    $this->Ticket = TicketPeer::doSelectOne($c);
    $this->forward404Unless($this->Ticket);
  }

  // disable new
  // disable create
  // disable edit
  // disable delete

}
