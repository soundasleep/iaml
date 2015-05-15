# Introduction #

**[Primitive Operations](IamlPrimitiveOperations.md)** are the fundamental building blocks of application functionality. Their intent is based on their _name_, and we summarise these operations below.

The current implementation of these operations are implemented in the OAW templates [templates/js/Operations.xpt#expandOperationContents(model::PrimitiveOperation)](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.codegen.oaw/src/template/js/Operations.xpt?r=595#337) (for Javascript) and [templates/php/EventTrigger.xpt#expandOperationContents(model::PrimitiveOperation)](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.codegen.oaw/src/template/php/EventTrigger.xpt?r=595#150) (for PHP).

Primitive operations have a fixed semantics and cannot be changed (without modifying IAML inference and code generation logic).

**Also see** [IamlCompositeOperations](IamlCompositeOperations.md) for an overview of pre-defined composite operations.

## setPropertyToValue ##

| **Sample model** | [SetPropertyToValue.iaml](http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/examples/requirements/operations/SetPropertyToValue.iaml) |
|:-----------------|:-------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Runtime tests** | TODO |
| **Added in** | [Model 0.2](Model0_2.md) |

This will set an [ApplicationElementProperty](IamlApplicationElementProperties.md) (outgoing data edge) to a particular value (incoming data edge).

![http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/examples/requirements/operations/images/SetPropertyToValue-2.png](http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/examples/requirements/operations/images/SetPropertyToValue-2.png)

## set ##

| **Sample model** | [Set.iaml](http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/examples/requirements/operations/Set.iaml) |
|:-----------------|:-------------------------------------------------------------------------------------------------------------------------------------------|
| **Runtime tests** | TODO |
| **Added in** | [Model 0.3](Model0_3.md) |

This will set a TemporaryVariable (outgoing data edge) to a particular value (incoming data edge).

![http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/examples/requirements/operations/images/Set-2.png](http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/examples/requirements/operations/images/Set-2.png)

## javascriptAlert ##

| **Sample model** | [JavascriptAlert.iaml](http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/examples/requirements/operations/JavascriptAlert.iaml) |
|:-----------------|:-------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Runtime tests** | TODO |
| **Added in** | [Model 0.2](Model0_2.md) |

This will pop up a Javascript ''alert()'' box on the client, with the message provided by the incoming data edge.

If this operation executes on the server, a PHP exception will occur (more documentation needed, and probably some validation tests).

![http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/examples/requirements/operations/images/JavascriptAlert-2.png](http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/examples/requirements/operations/images/JavascriptAlert-2.png)

## save ##

> _TODO: Incomplete documentation._

Part of a domain object instance. Saves the current instance to the domain store.

## new ##

> _TODO: Incomplete documentation._

Part of a domain object instance. Creates a new instance of the object from the NewInstanceWire or SelectWire.

## exists? ##

> _TODO: Incomplete documentation._

Part of a domain object instance. Returns true if the current instance actually exists.

## equal? ##

> _TODO: Incomplete documentation._

Returns true if all incoming parameters have the same value.

## true? ##

Returns false if any incoming parameters can be considered false (i.e. only true when _all_ incoming parameters are true):

  * boolean false (although types aren't implemented yet)
  * an undefined variable, i.e. null
  * any string equal to "0", "" or "false"
  * the integer 0
  * the floating-point number 0.0

This is similiar to the [conversion of booleans in PHP](http://nz2.php.net/manual/en/language.types.boolean.php).

## add role ##

> _TODO: Incomplete documentation._

Part of a UserInstance. Adds the given Role (parameter) to the given UserInstance.

## add permission ##

> _TODO: Incomplete documentation._

Part of a UserInstance. Adds the given Permission (parameter) to the given UserInstance.

## remove role ##

> _TODO: Incomplete documentation._

Part of a UserInstance. Removes the given Role (parameter) from the given UserInstance.

## remove role ##

> _TODO: Incomplete documentation._

Part of a UserInstance. Removes the given Permission (parameter) from the given UserInstance.

## check permissions ##

> _TODO: Incomplete documentation._

Part of an AccessControlHandler. Checks that the current User Instance incoming to the AccessControlHandler has the correct permissions.

## hide ##

| **Added in** | [Model 0.4.4](http://code.google.com/p/iaml/wiki/Model0_5#Model_0.4.4) |
|:-------------|:-----------------------------------------------------------------------|

Hides the containing visible thing, if it is visible. The visibility state either persists across the containing Session, or the InternetApplication.

## show ##

| **Added in** | [Model 0.4.4](http://code.google.com/p/iaml/wiki/Model0_5#Model_0.4.4) |
|:-------------|:-----------------------------------------------------------------------|

Shows the containing visible thing, if it is hidden. The visibility state either persists across the containing Session, or the InternetApplication.