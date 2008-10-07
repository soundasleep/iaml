<?php

// TODO: move to PHPUnit?
class GmfTestRunner {
  public function runTest(GmfTestCase $test) {
    $test->setup($this);
    $this->startNew(get_class($test));
    $test->run();
  }
  public function printStats() {
    $total_pass = 0;
    $total_tests = 0;
    foreach ($this->test_cases as $i => $case) {
      if ($case["pass"] != $case["tests"]) {
        warn("[$i] " . number_format($case["tests"]-$case["pass"]) . " failures");
      }
      $total_pass += $case["pass"];
      $total_tests += $case["tests"];
    }
    info("Total tests run: " . number_format($total_pass) . ", fails: " . number_format($total_tests - $total_pass));
  }

  var $test_cases = array();
  var $current_test_case = false;

  // called by TestCase

  public function startNew($key) {
    $this->current_test_case = $key;
    $this->test_cases[$this->current_test_case] = array("pass" => 0, "tests" => 0);
  }
  public function pass($message) {
    $this->test_cases[$this->current_test_case]["pass"]++;
    $this->test_cases[$this->current_test_case]["tests"]++;
    // no output
  }
  public function fail($message) {
    $this->test_cases[$this->current_test_case]["tests"]++;
    warn($message);
  }
}