[< 0.4](Model0_4.md) | **0.5** | [0.6 >](Model0_6.md)



# Model 0.4.1 #

**Released [December 2009](http://journals.jevon.org/users/jevon-phd/entry/19777)**

This is a minor release which changes the approach used in the code generation of operations. Instead of generating operations as an exhaustive structured program, each instruction is generated as a state, and the execution flow is executed based on a call stack. In particular, this means that loops in the execution flow will no longer cause code generation to fail.

Advanced verification with CrocoPat is also provided as an _experimental_ feature plugin. Currently this only detects [infinite redirection loops](http://code.google.com/p/iaml/source/browse/branches/2009-08-owl/org.openiaml.verification.crocopat/rules/infinite_redirection.rml?spec=svn1301&r=1301).

## New Elements ##

### Arithmetic ###

Allows for inline arithmetic expressions.

> _See http://openiaml.org/model/Arithmetic_

## Changes ##

[ExecutionEdges](http://openiaml.org/model/ExecutionEdge) can now have names; a [[DecisionCondition](http://openiaml.org/model/DecisionCondition) will use the labels 'yes' or 'no' (also 'pass' or 'fail') in order to calculate the correct execution flow.

All [ApplicationElementProperty](ApplicationElementProperty.md)s now also create a condition called "X is set" in the container for the property. This is used in [SyncWires](SyncWires.md) to ensure that init/access does not overwrite the default (or existing) value if there is no value to replace it with. This is powered by a new [decision condition](IamlDecisionConditions.md) "is set?"

Added documentation on [IamlDecisionConditions](IamlDecisionConditions.md).

# Model 0.4.2 #

**Released [February 2010](http://journals.jevon.org/users/jevon-phd/entry/19798)**

This minor release adds the concept of Entry Gates and Exit Gates, preventing access inside or outside a session without first co-operating with a given requirement (currently only other pages). [url=http://iaml.googlecode.com/svn-history/r1497/trunk/examples/GateDisclaimer/GateDisclaimer-4.html]This example[/url] demonstrates using an Entry Gate to prevent viewing pages until a disclaimer page has been viewed; and [url=http://iaml.googlecode.com/svn-history/r1497/trunk/examples/ExitGateAdSimple/ExitGateAdSimple-1.html]this example[/url] illustrates an advertisement being displayed before the user can exit out of the session.

Conditional modelling is now also generated into code the same way as operational modelling in [0.4.1](#Model_0.4.1.md).

## New Elements ##

### EntryGate ###

Restricts access within a [Scope](http://openiaml.org/model/Scope) until the incoming condition has been satisfied.

> _See http://openiaml.org/model/EntryGate_

### ExitGate ###

Restricts access outside of a currently-visited [Scope](http://openiaml.org/model/Scope) until the incoming condition has been satisfied.

> _See http://openiaml.org/model/ExitGate_

## Changes ##

**Pages** have been renamed into [Frames](http://openiaml.org/model/Frame). In the future, this will allow Frames to recursively contain other Frames, which may be replaced using particular [wires](http://openiaml.org/model/WireEdge) (the exact specifics have not been decided yet).

# Model 0.4.3 #

**Released [February 2010](http://journals.jevon.org/users/jevon-phd/entry/19803)**

This minor release adds the [Label](http://openiaml.org/model/Label), which allows a static field that cannot be edited by a user, yet can still be changed (for example, on events).

This release also marks the start of some significant metamodel refactoring, which should hopefully be transparent to (any) end users.

## New Elements ##

### Label ###

Represents a label which cannot be edited by the user; however, the value of the label _can_ change at runtime, by changing the Label.fieldValue.

> _See http://openiaml.org/model/Label_

## Changes ##

A [SetWire](http://openiaml.org/model/SetWire) connecting from a [DomainObjectInstance](http://openiaml.org/model/DomainObjectInstance) to a [InputForm](http://openiaml.org/model/InputForm) will now generate [Labels](http://openiaml.org/model/Label); compare this to [SyncWires](http://openiaml.org/model/SyncWire) creating [InputTextFields](http://openiaml.org/model/InputTextField).

[DomainObjectInstance](http://openiaml.org/model/DomainObjectInstance).autosave and [DomainAttributeInstance](http://openiaml.org/model/DomainAttributeInstance).autosave now default to "false".

ParameterWires are now known as [ParameterEdges](http://openiaml.org/model/ParameterEdge). They are no longer [Wires](http://openiaml.org/model/Wire).

The modelling language is currently undergoing a major meta-model refactoring, which should hopefully be transparent to end-users. In particular:

  * ParameterWire is now [ParameterEdge](http://openiaml.org/model/ParameterEdge)
  * ExtendsWire is now [ExtendsEdge](http://openiaml.org/model/ExtendsEdge)
  * RequiresWire is now [RequiresEdge](http://openiaml.org/model/RequiresEdge)
  * ProvidesWire is now [ProvidesEdge](http://openiaml.org/model/ProvidesEdge)
  * ConstraintWire is now [ConstraintEdge](http://openiaml.org/model/ConstraintEdge)
  * ConditionWire is now [ConditionEdge](http://openiaml.org/model/ConditionEdge)

# Model 0.4.4 #

**Released [March 2010](http://journals.jevon.org/users/jevon-phd/entry/19812)**

This adds the significant modelling concept of data types, and allows values to be cast between different types (although not completely extensible at this point in time).

## New Elements ##

### CastNode ###

A [CastNode](http://openiaml.org/model/CastNode) allows one data value to be cast to a different type. The potential result of the cast can be checked by a [DecisionOperation](http://openiaml.org/model/DecisionOperation) called 'can cast?', allowing for input validation to occur.

## Changes ##

Data types are implemented using XSD data types, which in the future allowing for user-defined data types to be defined by third parties. Different values can be [cast to different types](http://openiaml.org/model/CastNode).

[InputTextFields](http://openiaml.org/model/InputTextField) now automatically create an internal [Label](http://openiaml.org/model/Label) called "Warning", which allows for validation to occur. Labels can also be shown and hidden using the 'show' and 'hide' [PrimitiveOperations](http://openiaml.org/model/PrimitiveOperation).

Internally, I finally fixed a bug where [AJAX calls were not being synchronised correctly](http://code.google.com/p/iaml/issues/detail?id=43). As a result, the test cases are much more reliable and faster to run.

Operations and conditions are [no longer generated on every page](http://code.google.com/p/iaml/source/detail?r=1714); rather, they are generated into separate include files. As a result, code generation and testing is faster, but actually loading the generated pages using a browser is much slower. This should be fixed in the future.

The [Milestones](Milestones.md) have also been updated, and after a weeks worth of research writing, the focus will be on implementing [RIA-specific modelling features](http://code.google.com/p/iaml/wiki/Milestones#Release_0.5).

The meta-model refactoring has continued; for example:

  * RunInstanceWire and NavigateWire are now [Actions](http://openiaml.org/model/Action)

# Model 0.5.0 #

**Released [April 2010](http://journals.jevon.org/users/jevon-phd/entry/19824)**

This adds a lot of new modelling elements, as well as fixes [some important issues](http://code.google.com/p/iaml/issues/list?can=1&q=label%3AMilestone-Release0.5).

## New Elements ##

### Map ###

A [Map](http://openiaml.org/model/Map) is a browsable map, which can be generated into Google Maps, Bing Maps, or likewise. They can contain any number of [MapPoints](http://openiaml.org/model/MapPoint).

### Map ###

A [MapPoint](http://openiaml.org/model/MapPoint) is a location that should be rendered within a map. This point can optionally be contained within a [Map](http://openiaml.org/model/Map).

### Email ###

[E-mails](http://openiaml.org/model/Email) can now be composed and sent. Two events, _onSent_ and _onFailure_, can be used to register when the e-mail is sent and if it ever fails to get to its recipient.

### PrimitiveCondition ###

Like a [PrimitiveOperation](http://openiaml.org/model/PrimitiveOperation), allows for the definition of primitive conditions for particular model elements, even though these can be modified later.

## Changed Elements ##

### DomainObjectInstance ###

[DomainObjectInstances](http://openiaml.org/model/DomainObjectInstance) can now be used for navigation. A [SelectWire](http://openiaml.org/model/SelectWire) can be used to select the instances and has a limit that is not 1.

In particular, connecting a [DomainObjectInstances](http://openiaml.org/model/DomainObjectInstance) to an [InputForm](http://openiaml.org/model/InputForm) with a [SetWire](http://openiaml.org/model/SetWire) will automatically populate the form with buttons necessary to navigate through the result set.

> _Example: [SelectWireManyPaginate](http://iaml.googlecode.com/svn/trunk/examples/SelectWireManyPaginate/SelectWireManyPaginate.html)_

## Changes ##

The semantics of [generated elements](http://openiaml.org/model/GeneratesElements) have now been refined: see [ModelCompletion](ModelCompletion.md).

  * Added [an action](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.actions/src/org/openiaml/model/custom/actions/ExportDotGraphAction.java?spec=svn1756&r=1756) to export a given IAML model to DOT format.
  * Added [an action](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.actions/src/org/openiaml/model/custom/actions/ExportInferredDotGraphAction.java?spec=svn1756&r=1756) to export the initial, and completed, model to DOT format.
  * [Events](http://openiaml.org/model/EventTrigger) now render as actual events, not just boxes.
  * [Overridden elements](ModelCompletion.md) are now displayed in **bold**.
  * Various code generation optimisations; in particular, operations and conditions that are never used are no longer generated ([issue 169](https://code.google.com/p/iaml/issues/detail?id=169)).
  * The model namespace is now `http://openiaml.org/model/0.5`. Migrators have been updated.