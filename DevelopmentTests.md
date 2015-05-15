# Introduction #

The IAML project comes with a comprehensive suite of unit, acceptance and integration test cases, which need to all pass before a new plugin version can be released.

There are currently two test suites, that must be run individually until [issue 31](http://code.google.com/p/iaml/issues/detail?id=31) is resolved:

  1. [AllTests.java](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/AllTests.java): code generation, inference, model quality, release quality
  1. [EclipseTestsSuite.java](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/eclipse/EclipseTestsSuite.java): run-time plugin tests

All new tests will be added to one of the test suites above. In the future testing will be added as an [automated testing script](http://code.google.com/p/iaml/issues/detail?id=26).