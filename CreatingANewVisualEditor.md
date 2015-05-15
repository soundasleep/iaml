[< Development](Development.md)

# Introduction #

**NOTE:** This page is outdated, as now all of the visual editors are generated through the [SimpleGMF](SimpleGMF.md) framework.

This is a brief technical guideline on how to add a new visual editor in the IAML framework. A more comprehensive guide will appear eventually. This assumes you are well versed in both EMF and GMF; this is just the steps required to integrate your new plugin with the rest of the framework.

Ideally most of this will be integrated into an automated script or an abstraction.

In this example, consider we are making a new editor "user\_store", for UserStore objects.

  1. Update the [iaml.gmfgraph](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model/model/iaml.gmfgraph) if necessary
  1. Create a new `user_store.gmftool`
  1. Create a new `user_store.gmfmap` and map the tools to the graph elements
  1. Generate the `user_store.gmfgen`
  1. Set the following properties in the new `gmfgen` file:
    * _Gen Expression Provider_ > _Gen Java Expression Provider_
      * _Inject Expression Body_ to **true** (until [issue 164](https://code.google.com/p/iaml/issues/detail?id=164) is resolved)
  1. The following properties should be replaced automatically when [AllReleaseTests](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/release/AllReleaseTests.java?r=2315) are executed:
    * _Gen Editor Generator_
      * _Domain File Extension_ to "iaml\_user\_store" (will update _Diagram File Extension_ automatically)
      * _Dynamic Templates_ to "true"
      * _Model ID_ to "Iaml\_UserStore"
      * _Package Name Prefix_ to "org.openiaml.model.diagram.user\_store"
      * _Template Directory_ to "/org.openiaml.model/templates/"
    * _Gen Diagram_
      * _Validation Decorators_ to "true"
      * _Validation Enabled_ to "true"
      * _Contains Shortcuts To_ and _Shortcuts Provided For_ to all domain file extensions in the model (checked with [PluginsTestCase](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/release/PluginsTestCase.java?r=1006&spec=svn1006#181))
    * _Gen Plugin_
      * _ID_ to "org.openiaml.model.diagram.user\_store"
      * _Name_ to "Iaml Diagram Plugin (User Store)"
      * _Provider_ to "www.openiaml.org"
      * _Version_ to the latest version (e.g. "0.3.1.qualified")
    * You will still need to add _OpenDiagramBehaviors_ to any elements that require it (checked with [GmfGenTestCase](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/release/GmfGenTestCase.java?r=1006#329))
  1. Generate the plug-in code and execute the Release test cases twice (once to replace broken values; a second time to test)