<?php

/**
 * event actions.
 *
 * @package    ticketsf-mini
 * @subpackage event
 * @author     Your name here
 */
class eventActions extends sfActions
{
  public function executeIndex(sfWebRequest $request)
  {
	// select all events that haven't yet expired
	$c = new Criteria();
	$c->add(EventPeer::DATE, time(), Criteria::GREATER_EQUAL);
	$c->addAscendingOrderByColumn(EventPeer::DATE);

	$query = trim($request->getParameter("q", ""));
	if ($query) {
		$cs1 = $c->getNewCriterion(EventPeer::TITLE, '%'.$query.'%', Criteria::LIKE);
		$cs2 = $c->getNewCriterion(EventPeer::DESCRIPTION, '%'.$query.'%', Criteria::LIKE);
		$cs1->addOr($cs2);
		$cs3 = $c->getNewCriterion(EventPeer::VENUE, '%'.$query.'%', Criteria::LIKE);
		$cs1->addOr($cs3);
		$c->add($cs1);
	}

    $this->Events = EventPeer::doSelect($c);

    $this->searchForm = new EventSearchForm();
	$this->searchForm->bind(array("q" => $query));

	// if ajax, only include partial
	if ($request->isXmlHttpRequest()) {
		return $this->renderPartial("results", array("Events" => $this->Events));
	}

	$this->add_link("event/atom", "alternate", "application/atom+xml", "Upcoming Events (Atom)");

  }

  public function executeAllEvents(sfWebRequest $request)
  {
	// select all events that haven't yet expired
	$c = new Criteria();
	$c->addAscendingOrderByColumn(EventPeer::DATE);

	$query = trim($request->getParameter("q", ""));
	if ($query) {
		$cs1 = $c->getNewCriterion(EventPeer::TITLE, '%'.$query.'%', Criteria::LIKE);
		$cs2 = $c->getNewCriterion(EventPeer::DESCRIPTION, '%'.$query.'%', Criteria::LIKE);
		$cs1->addOr($cs2);
		$cs3 = $c->getNewCriterion(EventPeer::VENUE, '%'.$query.'%', Criteria::LIKE);
		$cs1->addOr($cs3);
		$c->add($cs1);
	}

    $this->Events = EventPeer::doSelect($c);

    $this->searchForm = new EventSearchForm();
	$this->searchForm->bind(array("q" => $query));

	// if ajax, only include partial
	if ($request->isXmlHttpRequest()) {
		return $this->renderPartial("results", array("Events" => $this->Events));
	}

	$this->add_link("event/atom", "alternate", "application/atom+xml", "Upcoming Events (Atom)");

  }

	/**
 	 * Add link tag to slot 'links'.
 	 * from http://jnotes.jonasfischer.net/2009/10/symfony-link-tags-e-g-canonical/
 	 *
	 * @param String $href [Absolute or internat URI]
	 * @param String $rel [value for 'rel' attribtue, e.g. 'canonical']
	 *
	 * @return void
	 */
	private function add_link($href, $rel, $type = false, $title = false)
	{
		$this->loadHelper('Url');	// for url_for
		$this->loadHelper('Partial');	// for url_for

		$slot = get_slot('links');
		try {
			$href = url_for($href, true);
			$slot .= "\n<link rel=\"$rel\" href=\"$href\"";
			if ($type) {
				$slot .= " type=\"".htmlspecialchars($type)."\"";
			}
			if ($title) {
				$slot .= " title=\"".htmlspecialchars($title)."\"";
			}
			$slot .= " />";
		} catch (InvalidArgumentException $e) {
			$slot .= "\n<!-- Could not add Link '$href': Only absolute or internal URIs allowed -->";
		}
		slot('links', $slot);
	}

	private function loadHelper($helper) {
		sfContext::getInstance()->getConfiguration()->loadHelpers($helper);
	}

    public function executeAtom(sfWebRequest $request)
    {
		// we need the url helper for url_for
		$this->loadHelper('Url');

		// uses the sfFeed2Plugin symfony plugin
		$feed = new sfAtom1Feed();

		$feed->setTitle("Ticket 2.0 Upcoming Events");
		$feed->setLink(url_for("event/index", true /* absolute URI */));
		$feed->setAuthorEmail("support@openiaml.org");
		$feed->setAuthorName("Ticket 2.0");

		// select all events that haven't yet expired
		$c = new Criteria();
		$c->add(EventPeer::DATE, time(), Criteria::GREATER_EQUAL);
		$c->addAscendingOrderByColumn(EventPeer::DATE);

    	$this->Events = EventPeer::doSelect($c);

    	foreach ($this->Events as $event) {
			$item = new sfFeedItem();

			$item->setTitle($event->getTitle());
			$item->setLink(url_for("event/show?id=".$event->getId()));
			//$item->setPubdate($event->getCreatedAt('U'));
			$item->setPubdate($event->getDate('U'));
			$item->setUniqueId($item->getLink());
			$item->setDescription($event->getDescription());

			$feed->addItem($item);
		}

		$this->feed = $feed;
  }

  public function executeShow(sfWebRequest $request)
  {
    $this->Event = EventPeer::retrieveByPk($request->getParameter('id'));
    $this->forward404Unless($this->Event);
  }

  public function executeNew(sfWebRequest $request)
  {
    $this->form = new EventForm();
  }

  public function executeCreate(sfWebRequest $request)
  {
    $this->forward404Unless($request->isMethod(sfRequest::POST));

    $this->form = new EventForm();

    $this->processForm($request, $this->form);

    $this->setTemplate('new');
  }

  public function executeEdit(sfWebRequest $request)
  {
    $this->forward404Unless($Event = EventPeer::retrieveByPk($request->getParameter('id')), sprintf('Object Event does not exist (%s).', $request->getParameter('id')));
    $this->form = new EventForm($Event);
  }

  public function executeUpdate(sfWebRequest $request)
  {
    $this->forward404Unless($request->isMethod(sfRequest::POST) || $request->isMethod(sfRequest::PUT));
    $this->forward404Unless($Event = EventPeer::retrieveByPk($request->getParameter('id')), sprintf('Object Event does not exist (%s).', $request->getParameter('id')));
    $this->form = new EventForm($Event);

    $this->processForm($request, $this->form);

    $this->setTemplate('edit');
  }

  public function executeDelete(sfWebRequest $request)
  {
    $request->checkCSRFProtection();

    $this->forward404Unless($Event = EventPeer::retrieveByPk($request->getParameter('id')), sprintf('Object Event does not exist (%s).', $request->getParameter('id')));
    $Event->delete();

    $this->redirect('event/index');
  }

  protected function processForm(sfWebRequest $request, sfForm $form)
  {
    $form->bind($request->getParameter($form->getName()), $request->getFiles($form->getName()));
    if ($form->isValid())
    {
      $Event = $form->save();

      $this->redirect('event/edit?id='.$Event->getId());
    }
  }
}
