# Introduction #

Composite Operations are operations which are composed of [smaller operations](IamlOperations.md). IAML defines a number of pre-defined composite operations which automatically generate their contents (unless [the generation is disabled](IamlInference.md)). Their intent is based on their _name_, and we summarise these operations below.

The current implementation of these operations are implemented in the Drools rule files  [operations.drl](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.drools/rules/operations.drl#52). They are also implemented in [sessions.drl](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.drools/rules/sessions.drl) temporarily, but this needs to be refactored out (TODO).

**Also see** [IamlPrimitiveOperations](IamlPrimitiveOperations.md) for an overview of the pre-defined operation components.

## update, refresh, init ##

| **Sample model** | [SyncWireTestCase.iaml](http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/inference/SyncWireTestCase.iaml) |
|:-----------------|:----------------------------------------------------------------------------------------------------------------------------------------------------|
| **Runtime tests** | [SyncWireTestCase.java](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/inference/SyncWireTestCase.java) |
| **Added in** | [Model 0.1](Model0_1.md) |

This will set the [\_fieldValue\_ of the containing object](IamlApplicationElementProperties.md) to a particular value (the incoming parameter).

### Default operation contents ###
![http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/inference/composite_operations/images/UpdateOperation.png](http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/inference/composite_operations/images/UpdateOperation.png)

### Sample Usage ###
![http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/inference/composite_operations/images/UpdateOperationUsage.png](http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/inference/composite_operations/images/UpdateOperationUsage.png)