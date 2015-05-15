# Introduction #
The development of this [IAML Eclipse plugin](Development.md) is based on an iterative approach. In particular, large iterations (minor versions) which are then broken down into smaller iterations (revisions, spikes). These iterations are also designed to be synchronised with my [PhD](http://journals.jevon.org/users/jevon-phd).

I hope to also use [Test Driven Development](http://c2.com/cgi/wiki?TestDrivenDevelopment) significantly in order to test and validate the majority of the plugin. Though I'm not yet sure how to test the usability of Eclipse :)



# Release 0.1 #
([Resolved issues](http://code.google.com/p/iaml/issues/list?can=1&q=label%3AMilestone-Release0.1))
([Released](http://journals.jevon.org/users/jevon-phd/entry/16760) November 2008)
  1. [Initial IAML model](Model0_1.md) that can describe initial requirements
  1. Usability improvements
    * Display shortcut elements for referenced elements
  1. Look into rule engines for element inference
  1. Testing
    * Initial testing of code generation
    * Initial testing of element inference
    * Testing of plugin structure
  1. Code generation
    * Basic HTML/PHP genereation
  1. Set up [update site](Installation.md)

# Release 0.2 #
([Resolved issues](http://code.google.com/p/iaml/issues/list?can=1&q=label%3AMilestone-Release0.2))
([Released](http://journals.jevon.org/users/jevon-phd/entry/19062) April 2009)
  1. [Publish milestones](Milestones.md)
  1. [Expand IAML model](Model0_2.md)
    * All basic model elements can be placed in a diagram
    * Inference and code generation tests
  1. Code generation
    * Clean up structure of code generation plugin
    * Operation deep chaining ([r563](http://code.google.com/p/iaml/source/detail?r=563))
    * Can generate all elements of the [initial requirements](Model0_2.md)
  1. Testing
    * Test operational modelling and generation
    * Test cases for code generation of all modelling elements

# Release 0.3 #
([Resolved issues](http://code.google.com/p/iaml/issues/list?can=1&q=label%3AMilestone-Release0.3))
([Released](http://journals.jevon.org/users/jevon-phd/entry/19718) July 2009)
  1. [Expand IAML model](Model0_3.md)
    * [Document existing atomic operations](IamlOperations.md)
    * [Document existing composite operations](IamlCompositeOperations.md)
  1. Inference
    * [Queue-based knowledge inference](ModelInference.md) - to be detailed in upcoming paper
    * Infer directly-contained model elements
    * [Inference actions](http://code.google.com/p/iaml/wiki/Model0_3#Usability_Features) available through right-click
  1. Code generation
    * Session persistence within multiple remote calls ([r586](http://code.google.com/p/iaml/source/detail?r=586))
    * Generated code is cleaned with the [iacleaner](http://code.google.com/p/iacleaner/) formatter ([issue 13](http://code.google.com/p/iaml/issues/detail?id=13))
  1. [Testing and Documentation](Model0_3.md)
    * Sessions
    * Buttons
    * Operation modelling
    * Condition modelling
    * Domain objects, domain object instances
    * [Login handlers (secret key)](LoginHandler.md)
    * Select wires
    * New instance wires
  1. Editor
    * Create edges from shortcut elements not directly contained ([issue 34](http://code.google.com/p/iaml/issues/detail?id=34))
    * Render shortcut elements and edges not directly contained ([issue 47](http://code.google.com/p/iaml/issues/detail?id=47))
  1. Usability improvements
    * [Can migrate models between different versions](ModelMigration.md)
    * Render model diagram to PNG images ([issue 9](http://code.google.com/p/iaml/issues/detail?id=9))
    * Progress monitors for inference and code generation ([issue 23](http://code.google.com/p/iaml/issues/detail?id=23))
    * [Deletion of generated elements semantics](http://code.google.com/p/iaml/wiki/Model0_3#Generated_Elements) ([[issue 29](http://code.google.com/p/iaml/issues/detail?id=29))
  1. Development
    * [Investigations into model-driven code coverage](http://code.google.com/p/iaml/source/browse/#svn/trunk/org.openiaml.model.codegen.oaw/instrument/output)

## Release 0.3.1 ##
([Resolved issues](http://code.google.com/p/iaml/issues/list?can=1&q=label%3AMilestone-Release0.3.1))
([Released](http://journals.jevon.org/users/jevon-phd/entry/19722) July 2009)
  1. Upgrade editor to Eclipse 3.5/GMF 2.2 ([issue 50](http://code.google.com/p/iaml/issues/detail?id=50))
  1. Usability improvements
    * More icons for editor elements ([issue 18](http://code.google.com/p/iaml/issues/detail?id=18))
    * Model element creation tools are now rendered in groups

# Release 0.4 #
([Resolved issues](http://code.google.com/p/iaml/issues/list?can=1&q=label%3AMilestone-Release0.4))
([Released](http://journals.jevon.org/users/jevon-phd/entry/19760) October 2009)
  1. [Expand IAML model](Model0_4.md)
    * [Login Handlers](http://openiaml.org/model/LoginHandler) (domain instance and user)
    * [Clear the current session DomainObjectInstance](http://code.google.com/p/iaml/source/detail?r=973)
    * [Set Wire](http://openiaml.org/model/SetWire): a one-way _Sync Wire_
    * [Query Parameter](http://openiaml.org/model/QueryParameter) (_url?param=bar_)
    * [Users as first-class citizens](http://openiaml.org/model/Role)
    * [Join Node](http://openiaml.org/model/JoinNode), [Split Node](http://openiaml.org/model/SplitNode)
    * [...](Model0_4.md)
  1. Documentation
    * [Automatically generated documentation](http://openiaml.org/model)
    * [Installation guide](Installation.md)
    * ["Hello, World" tutorial](HelloWorld.md)
    * Rich suite of [example models](http://openiaml.org/model/examples)
  1. Usability improvements
    * Differences between [primitive operations](IamlPrimitiveOperations.md) and [composite operations](IamlCompositeOperations.md) have been clarified
    * Model instances will be checked for validity before code generation occurs
    * Debug information is no longer displayed by default ([issue 115](https://code.google.com/p/iaml/issues/detail?id=115))
  1. Development
    * PHP and Javascript libraries moved into a separate runtime bundle ([issue 75](https://code.google.com/p/iaml/issues/detail?id=75), [issue 76](https://code.google.com/p/iaml/issues/detail?id=76))
    * Refactoring of code generation templates
    * Diagram tests moved into separate bundle ([issue 109](https://code.google.com/p/iaml/issues/detail?id=109))

## Release 0.4.1 ##
([Resolved issues](http://code.google.com/p/iaml/issues/list?can=1&q=label%3AMilestone-Release0.4.1))
([Released](http://journals.jevon.org/users/jevon-phd/entry/19777) December 2009)
  1. [Expand IAML model](http://code.google.com/p/iaml/wiki/Model0_5#Model_0.4.1)
    * Arithmetic
  1. Code generation
    * Operational modelling is now implemented using a call stack, allowing for loops in the execution path

## Release 0.4.2 ##
([Resolved issues](http://code.google.com/p/iaml/issues/list?can=1&q=label%3AMilestone-Release0.4.2))
([Released](http://journals.jevon.org/users/jevon-phd/entry/19798) February 2010)
  1. [Expand IAML model](http://code.google.com/p/iaml/wiki/Model0_5#Model_0.4.2)
    * EntryGate: restricting entry through a given gate
    * ExitGate: restricting exit through a given gate
    * "Page" is now "Frame"
  1. Code generation
    * Conditional modelling is also now implemented using the same call stack in 0.4.1

## Release 0.4.3 ##
([Resolved issues](http://code.google.com/p/iaml/issues/list?can=1&q=label%3AMilestone-Release0.4.3))
([Released](http://journals.jevon.org/users/jevon-phd/entry/19803) February 2010)
  1. [Expand IAML model](http://code.google.com/p/iaml/wiki/Model0_5#Model_0.4.3)
    * Label: a static text field that cannot be edited by the user
    * An InputForm connected by a SetWire will be rendered with Labels, rather than InputTextFields ([r1511](https://code.google.com/p/iaml/source/detail?r=1511))
    * DomainObjectInstance autosave default should be false ([r1512](https://code.google.com/p/iaml/source/detail?r=1512))
    * Some metamodel refactoring: Not all edges are now wires

## Release 0.4.4 ##
([Resolved issues](http://code.google.com/p/iaml/issues/list?can=1&q=label%3AMilestone-Release0.4.4))
([Released](http://journals.jevon.org/users/jevon-phd/entry/19812) March 2010)
  1. [Expand IAML model](http://code.google.com/p/iaml/wiki/Model0_5#Model_0.4.4)
    * Data types using XML Schema (XSD)
    * CastNode: an operational modelling node that allows one data value to be cast to another type
  1. Inference
    * InputTextFields now automatically have a Label called "Warning"
  1. Code generation
    * Operations and conditions are now generated as individual include files
    * Test cases are now correctly synchronised with AJAX calls ([issue 43](https://code.google.com/p/iaml/issues/detail?id=43))

# Release 0.5 #
([Resolved issues](http://code.google.com/p/iaml/issues/list?can=1&q=label%3AMilestone-Release0.5))
([Released](http://journals.jevon.org/users/jevon-phd/entry/19824) April 2010)
  1. [Expand IAML model](http://code.google.com/p/iaml/wiki/Model0_5#Model_0.5)
    * Google Maps through [Map](http://openiaml.org/model/Map) and [MapPoint](http://openiaml.org/model/MapPoint)
    * E-mails through [Email](http://openiaml.org/model/Email)
    * Navigating through [DomainObjectInstance](http://openiaml.org/model/DomainObjectInstance) and [SelectWire](http://openiaml.org/model/SelectWire)
    * Added documentation for all model element classes ([issue 167](https://code.google.com/p/iaml/issues/detail?id=167))
  1. Inference
    * Wrote documentation about [ModelCompletion](ModelCompletion.md)
    * Automatically mark containers as overridden ([issue 130](https://code.google.com/p/iaml/issues/detail?id=130))
    * Overridden flag is ignored ([issue 131](https://code.google.com/p/iaml/issues/detail?id=131))
  1. Usability improvements
    * Highlight overridden elements in bold ([issue 162](https://code.google.com/p/iaml/issues/detail?id=162))
    * [Events](http://openiaml.org/model/EventTrigger) are now rendered as UML events ([issue 93](https://code.google.com/p/iaml/issues/detail?id=93))
  1. Code generation
    * Operations and conditions that are never used are no longer generated ([issue 169](https://code.google.com/p/iaml/issues/detail?id=169))
  1. Development
    * `generateID()` code is automatically inserted into model code using EMF Dynamic Templates ([issue 165](https://code.google.com/p/iaml/issues/detail?id=165))

## Release 0.5.1 ##
([Resolved issues](http://code.google.com/p/iaml/issues/list?can=1&q=label%3AMilestone-Release0.5.1))
([Released](http://journals.jevon.org/users/jevon-phd/entry/19835) May 2010)
  1. [Expand IAML model](http://code.google.com/p/iaml/wiki/Model0_6#Model_0.5.1)
    * OpenID is supported through a new data type
    * [EntryGate](http://openiaml.org/model/EntryGate) can require a provided OpenID value
    * RSS Feeds can be produced using a [Frame](http://openiaml.org/model/Frame)
    * RSS Feeds can be consumed using a [DomainSource](http://openiaml.org/model/DomainSource)
    * Refactoring of Domain Modelling elements ([issue 178](https://code.google.com/p/iaml/issues/detail?id=178))
    * DecisionOperation and DecisionCondition merged into DecisionNode ([issue 160](https://code.google.com/p/iaml/issues/detail?id=160))
    * CompositeWire and SingleWire removed ([issue 189](https://code.google.com/p/iaml/issues/detail?id=189))
    * FieldValue properties are now stored in an explicit containment reference ([issue 170](https://code.google.com/p/iaml/issues/detail?id=170))
  1. Code generation
    * A new domain modelling runtime framework ([issue 178](https://code.google.com/p/iaml/issues/detail?id=178)), which improves code generation time significantly
    * Various performance and size improvements to code generation
  1. Development
    * Diagram editors are now completely removed from version control ([issue 175](https://code.google.com/p/iaml/issues/detail?id=175))
  1. Documentation
    * The documentation has not been fully migrated yet, nor all of the example models migrated yet, but this will be completed before 0.6.

## Release 0.5.2 ##
([Resolved issues](http://code.google.com/p/iaml/issues/list?can=1&q=label%3AMilestone-Release0.5.2))
([Released](http://journals.jevon.org/users/jevon-phd/entry/19848) July 2010)
  1. [Expand IAML model](http://code.google.com/p/iaml/wiki/Model0_6#Model_0.5.2)
    * Added [Autocomplete Wire](http://openiaml.org/model/AutcompleteWire)
    * Added instant [SyncWires](http://openiaml.org/model/SyncWire) using the new _onInput_/_currentInput_ event and property
    * [DomainIterators](http://openiaml.org/model/DomainIterator) and [InputForm](http://openiaml.org/model/InputForm) no longer have _fieldValues_ created through model completion.
  1. Interface
    * The visual display of elements has been refreshed and unified, according to Moody's _Physics of Notation_ ([issue 195](https://code.google.com/p/iaml/issues/detail?id=195))
  1. Documentation
    * The element export script now also exports to SVG, as well as PNG ([issue 193](https://code.google.com/p/iaml/issues/detail?id=193))
    * The documentation has not been fully migrated yet, nor all of the example models migrated yet, but this will be completed before 0.6.

## Release 0.5.3 ##
([Resolved issues](http://code.google.com/p/iaml/issues/list?can=1&q=label%3AMilestone-Release0.5.3))
([Released](http://journals.jevon.org/users/jevon-phd/entry/19886) January 2011)
  1. [Various fixes](http://code.google.com/p/iaml/wiki/Model0_6#Model_0.5.3)
    * Various performance improvements
  1. Documentation
    * Wrote up a list of sample representations of user interface elements in [InterfaceElements](InterfaceElements.md)

(Re-released March 2011)
  1. Resolved [issue 227](https://code.google.com/p/iaml/issues/detail?id=227).

# Release 0.6 #
([Outstanding issues](http://code.google.com/p/iaml/issues/list?can=2&q=label%3AMilestone-Release0.6))
  1. [Expand IAML model](Model0_6.md)
    * Instant SetWires
  1. Code generation
    * Improve performance of multiple include files on client-side
  1. Documentation
    * Update example models to refactored domain modelling
    * Generate completed models for all example models

# Release 0.7+ #
([Outstanding issues](http://code.google.com/p/iaml/issues/list?can=2&q=label%3AMilestone-Release0.7))
  1. [Expand IAML model](Model0_7.md)
    * Captchas
    * Rich Text
    * Use Web Services
    * Delete Domain Object

# Release 0.9 #
  1. Can implement [Ticket 2.0](http://openiaml.org)

# Release 1.0 #
  1. Focus on Usability
  1. Implement code generation in another language (JSP?)
  1. Extensibility