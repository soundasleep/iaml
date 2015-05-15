# Introduction #

Starting in [r701](https://code.google.com/p/iaml/source/detail?r=701), there has been some experimental support for code coverage from the model.

# Instructions #

  1. Execute [RunInstrumentation](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.codegen.oaw/src/org/openiaml/model/codegen/oaw/coverage/RunInstrumentation.java) as a Java application; this will add instrumentation data to all the templates
  1. Force a refresh of your local workspace (so that the templates can be rebuilt through XPT)
  1. Make sure that you have the [OutputInstrumentation](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.codegen.oaw/src/org/openiaml/model/codegen/oaw/coverage/OutputInstrumentation.java) instrumenter enabled as part of the IACleanerBeautifier: [r903](https://code.google.com/p/iaml/source/detail?r=903)
  1. Execute a test case

Generating the instrumentation results:

  1. Execute [RevertInstrumentation](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.codegen.oaw/src/org/openiaml/model/codegen/oaw/coverage/RevertInstrumentation.java) to remove the instrumentation code from the templates
  1. Execute [OutputInstrumentation](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.codegen.oaw/src/org/openiaml/model/codegen/oaw/coverage/OutputInstrumentation.java) as a Java application to generate the [instrumentation results](http://iaml.googlecode.com/svn/trunk/org.openiaml.model.codegen.oaw/instrument/output/index.html)
  1. Note that successive test case executions will append information to the existing instrumentation dumps; you will have to delete `instrument/*.dump` manually otherwise

Once finished:

  1. Revert the changes to the IACleanerBeautifier
  1. Execute [RevertInstrumentation](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.codegen.oaw/src/org/openiaml/model/codegen/oaw/coverage/RevertInstrumentation.java) as a Java application
  1. Force a refresh of your local workspace

# Sample Results #
[See the latest output instrumentation results on SVN](http://iaml.googlecode.com/svn/trunk/org.openiaml.model.codegen.oaw/instrument/output/index.html)

# Overview #
![http://iaml.googlecode.com/svn/trunk/org.openiaml.model.codegen.oaw/src/org/openiaml/model/codegen/oaw/coverage/templates/overview.png](http://iaml.googlecode.com/svn/trunk/org.openiaml.model.codegen.oaw/src/org/openiaml/model/codegen/oaw/coverage/templates/overview.png)