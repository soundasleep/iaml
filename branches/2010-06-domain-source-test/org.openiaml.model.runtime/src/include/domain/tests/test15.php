<?php

// uses the iterator defined in run.php

echo "[test 15] ";
ob_start();
{
	$iterator = DomainIterator_News_ergqa3bdfbd::getInstance();

	// print out the contents
	$iterator->reset();
	printit4($iterator);
	while ($iterator->hasNext()) {
		$iterator->next();
		printit4($iterator);
	}

	// move iterator back to start, and reset all titles
	$iterator->reset();
	$counter = 42;
	$iterator->getAttributeInstance(DomainAttribute_News_Title::getInstance())->setValue("Counter " . $counter++);
	$iterator->save();
	while ($iterator->hasNext()) {
		$iterator->next();
		$iterator->getAttributeInstance(DomainAttribute_News_Title::getInstance())->setValue("Counter " . $counter++);

		// reset the iterator before saving (simulates a different network request)
		DomainIterator_News_ergqa3bdfbd::resetInstance();
		$iterator = DomainIterator_News_ergqa3bdfbd::getInstance();
		$iterator->save();
	}

	// print out the contents
	$iterator->reset();
	printit4($iterator);
	while ($iterator->hasNext()) {
		$iterator->next();
		printit4($iterator);
	}

}

$result = ob_get_contents();
ob_end_clean();

// check the results are as expected
compareTestResults($result, "expected15.txt");
