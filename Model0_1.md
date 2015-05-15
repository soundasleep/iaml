0.0 | **0.1** | [0.2 >](Model0_2.md)

# Model 0.1 #

  * [Model .ecore](http://iaml.googlecode.com/svn/tags/version-0.1.0/org.openiaml.model/model/iaml.ecore)
  * [Inference tests](http://iaml.googlecode.com/svn/tags/version-0.1.0/org.openiaml.model.tests/src/org/openiaml/model/tests/inference/)
  * [Runtime tests](http://iaml.googlecode.com/svn/tags/version-0.1.0/org.openiaml.model.tests/src/org/openiaml/model/tests/codegen/)

# Introduction #

As the first major release of the model, this structure aims to set the potential final structure of the model.

# Elements #

  * **Page**: an abstract representation of a single "Page" in the application.
  * **SyncWire**: designed to sync different elements automatically.
    * Works for elements on the same page, or on different pages.
    * Only works for InputTextField and Page.