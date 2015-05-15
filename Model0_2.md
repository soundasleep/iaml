[< 0.1](Model0_1.md) | **0.2** | [0.3 >](Model0_3.md)

# Model 0.2 #

# Introduction #

This model version adds real support for persistency (Properties files and databases), support for sessions, and complex components for login/logout.

It also finalises the implementation of the _initial requirements_ with example models that are used in inference, code generation and runtime tests, as documented below.

The rest of the plugin adds [model quality tests](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/model/ModelTestsSuite.java?r=570), [release quality tests](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/release/ReleaseTestsSuite.java?r=570), and improved user interfaces with [icons](http://code.google.com/p/iaml/issues/detail?id=18) and [breadcrumbs](http://code.google.com/p/iaml/issues/detail?id=17).

## Form synchronisation, bidirectional wires ##
| **Description** | The value of elements (currently only text fields) will be synchronised whenever they are changed with elements connected by SyncWires. SyncWires are bidirectional. |
|:----------------|:---------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Model** | [requirements/1-sync\_wires.iaml](http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/examples/requirements/1-sync_wires.iaml) |
| **Inference Tests** | - |
| **Runtime Tests** | [Requirement1SyncWires.java](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/codegen/Requirement1SyncWires.java) |

![http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/examples/requirements/images/1-sync_wires-2.png](http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/examples/requirements/images/1-sync_wires-2.png)

## Static wire parameters ##
| **Description** | Parameters can be provided to RunInstanceWires (which run operations when an event is triggered) that are static values. |
|:----------------|:-------------------------------------------------------------------------------------------------------------------------|
| **Model** | [requirements/2-static\_params.iaml](http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/examples/requirements/2-static_params.iaml) |
| **Inference Tests** | - |
| **Runtime Tests** | [Requirement2StaticParams.java](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/codegen/Requirement2StaticParams.java) |

![http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/examples/requirements/images/2-static_params-2.png](http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/examples/requirements/images/2-static_params-2.png)

## Dynamic wire parameters ##
| **Description** | Parameters can be provided to RunInstanceWires (which run operations when an event is triggered) that are determined at runtime. In this case, the value of a text field (_fieldValue_). |
|:----------------|:-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Model** | [requirements/3-dynamic\_params.iaml](http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/examples/requirements/3-dynamic_params.iaml) |
| **Inference Tests** | - |
| **Runtime Tests** | [Requirement3DynamicParams.java](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/codegen/Requirement3DynamicParams.java) |

![http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/examples/requirements/images/3-dynamic_params-2.png](http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/examples/requirements/images/3-dynamic_params-2.png)

## Dynamic wire sources ##
| **Description** | Parameters can be provided to RunInstanceWires (which run operations when an event is triggered) that are determined at runtime. In this case, the value of a text field (_fieldValue_). |
|:----------------|:-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Model** | [requirements/4-dynamic\_sources.iaml](http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/examples/requirements/4-dynamic_sources.iaml) |
| **Inference Tests** | [Requirement4DynamicSources.java](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/inference/Requirement4DynamicSources.java) |
| **Runtime Tests** | [Requirement4DynamicSources.java](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/codegen/Requirement4DynamicSources.java) |

![http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/examples/requirements/images/4-dynamic_sources.png](http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/examples/requirements/images/4-dynamic_sources.png)

## Operations ##
| **Description** | We can compose operations out of primitives similar to UML activity diagrams. |
|:----------------|:------------------------------------------------------------------------------|
| **Model** | [requirements/5-operations.iaml](http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/examples/requirements/5-operations.iaml) |
| **Inference Tests** | - |
| **Runtime Tests** | [Requirement5Operations.java](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/codegen/Requirement5Operations.java) |

![http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/examples/requirements/images/5-operations-3.png](http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/examples/requirements/images/5-operations-3.png)

## Simple conditions ##
| **Description** | We can compose conditions out of primitives similar to UML activity diagrams. |
|:----------------|:------------------------------------------------------------------------------|
| **Model** | [requirements/6-conditions.iaml](http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/examples/requirements/6-conditions.iaml) |
| **Inference Tests** | - |
| **Runtime Tests** | [Requirement6Conditions.java](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/codegen/Requirement6Conditions.java) |

![http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/examples/requirements/images/6-conditions-1.png](http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/examples/requirements/images/6-conditions-1.png) ![http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/examples/requirements/images/6-conditions-2.png](http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/examples/requirements/images/6-conditions-2.png)

# New Elements #

  * **Session**: an abstract representation of a "Session". Any ApplicationElementProperties stored in this session will not be available outside of the current user.
  * **AbstractDomainObject**: DomainObjects are abstracted away, and we now have DomainObject for SimpleDB databases, and FileDomainObject for .properties files.
  * **LoginHandler**: a component which deals with login/logout. Must be placed in a Session.

These elements are all new elements introduced in Model 0.2, and will be documented (similarly to the initial requirements above) in [Model 0.3](Model0_3.md).