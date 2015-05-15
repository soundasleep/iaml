[< Development](Development.md)

# Introduction #

This is a brief technical guideline on what may be the best approach to implementing a metamodel refactoring, such as the major refactoring done to satisfy [issue 234](https://code.google.com/p/iaml/issues/detail?id=234). Each step is illustrated with an example revision from the implementation of [issue 241](https://code.google.com/p/iaml/issues/detail?id=241).

  1. Update the Ecore metamodel itself ([r2700](https://code.google.com/p/iaml/source/detail?r=2700)).
  1. Update diagram editors as in [CreatingANewVisualEditor](CreatingANewVisualEditor.md) ([r2706](https://code.google.com/p/iaml/source/detail?r=2706)).
  1. Execute [AllReleaseTests](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/release/AllReleaseTests.java?r=2315) ensure the quality of the diagram editors.
  1. Test creating a new instance of the metamodel using the new diagram editors.
  1. Implement the metamodel changes in a model migrator ([r2712](https://code.google.com/p/iaml/source/detail?r=2712)).
  1. Migrate all test model instances ([r2713](https://code.google.com/p/iaml/source/detail?r=2713)).
  1. Fix compile errors in test cases ([r2714](https://code.google.com/p/iaml/source/detail?r=2714)).
  1. Clean and rebuild the inference plugin to identify build errors.
  1. Update inference rules to new metamodel ([r2716](https://code.google.com/p/iaml/source/detail?r=2716)).
  1. Run a quick inference smoke test, such as [AllModel0\_4InferenceTests](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/inference/model0_4/AllModel0_4InferenceTests.java?r=2710).
  1. Execute [AllReleaseTests](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/release/AllReleaseTests.java?r=2315) to synchronise the OAW version of the IAML metamodel.
  1. Clean and rebuild the codegen plugin to identify build errors.
  1. Update codegen templates to new metamodel ([r2718](https://code.google.com/p/iaml/source/detail?r=2718)).
  1. Run a quick codegen smoke test, such as [AllModel0\_6CodegenTests](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/codegen/model0_6/AllModel0_6CodegenTests.java?r=2710).
  1. Run [AllTests](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/AllTests.java?r=2710), identify failing test cases, fix errors ([r2723](https://code.google.com/p/iaml/source/detail?r=2723)).