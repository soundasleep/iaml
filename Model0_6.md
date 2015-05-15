[< 0.5](Model0_5.md) | **0.6** | [0.7 >](Model0_7.md)



# Model 0.5.1 #

**Released [May 2010](http://journals.jevon.org/users/jevon-phd/entry/19835)**

This adds support for OpenID and RSS feeds. There has also been a lot of metamodel refactoring, to clean up the metamodel. Many [issues have been fixed](http://code.google.com/p/iaml/issues/list?can=1&q=label%3AMilestone-Release0.5.1).

## New Data Types ##

### iamlOpenID ###

A URL that is an OpenID URL. This is always valid on the client-side, but only valid on the server-side once the user has authenticated with the given URL. This follows the specifications in [OpenID v1.1](http://openid.net/specs/openid-authentication-1_1.html); for example, it supports delegating URLs.

When rendered as an [InputTextField](http://openiaml.org/model/InputTextField), an "Authenticate" button is provided, which will allow the server to authenticate the user.

## Changed Elements ##

### Frame ###

A [Frame](http://openiaml.org/model/Frame) can now have different rendering targets. By default, it will generate HTML.

If the rendering target is set to RSS2\_0, then the content will be rendered as a [RSS 2.0 feed](http://cyber.law.harvard.edu/rss/rss.html) instead. This is achieved by iterating over any [DomainIterators](http://openiaml.org/model/DomainIterator) in the frame.

### EntryGate ###

An [EntryGate](http://openiaml.org/model/EntryGate) can now require a Label to be set (and valid) within the same Scope. For example, if the Label is of type `iamlOpenIDURL`, then the user must provide and authenticate a valid OpenID URL before the Scope can be accessed.

## Changes ##

  1. Meta-model changes
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

# Model 0.5.2 #

**Released [July 2010](http://journals.jevon.org/users/jevon-phd/entry/19848)**

This minor release adds autocomplete wires, and refreshes the rendering of elements in the diagram editors. There is also a small amount of metamodel refactoring, along with other [issue fixes](http://code.google.com/p/iaml/issues/list?can=1&q=label%3AMilestone-Release0.5.2).

## New Elements ##

### Autocomplete Wire ###

An [Autocomplete Wire](http://openiaml.org/model/AutocompleteWire) allows for the easy creation of an autocompletion visual element.

## Changed Elements ##

### Visible Thing ###

All [VisibleThings](http://openiaml.org/model/VisibleThing) now support an additional property, rather than just [InputTextFields](http://openiaml.org/model/InputTextField): _currentInput_. If supported at runtime, this represents input that the user has provided but before it has been committed.

It also adds the [Event](http://openiaml.org/model/EventTrigger) _onInput_, which is fired whenever the _currentInput_ has changed. Not all model elements support _onInput_ and it is not guaranteed to fire consistently.

### Action Edge ###

To unify better with the desired ECA style, Actions have been renamed to [ActionEdges](http://openiaml.org/model/ActionEdge).

The differences between [RunAction](http://openiaml.org/model/RunAction) and [NavigateAction](http://openiaml.org/model/NavigateAction) have been removed, and the two elements have been unified into [ActionEdge](http://openiaml.org/model/ActionEdge).

## Unified Design ##

The style and design of model elements has been refreshed, and unified according to the _Physics of Notation_ by Moody ([issue 195](https://code.google.com/p/iaml/issues/detail?id=195)). The wide range of colours that were in use before have been unified down into seven; full details on this change will be in the thesis.

## Changes ##

  1. Meta-model changes
    * [DomainIterators](http://openiaml.org/model/DomainIterator) and [InputForm](http://openiaml.org/model/InputForm) no longer have _fieldValues_ created through model completion.
    * The `javascriptAlert` [PrimitiveOperation](http://openiaml.org/model/PrimitiveOperation) has been renamed to `alert` ([issue 196](https://code.google.com/p/iaml/issues/detail?id=196))
  1. Code generation
    * Duplicate extensions are now found and provided as warnings when developing templates ([issue 182](https://code.google.com/p/iaml/issues/detail?id=182))
  1. Interface
    * The diagram elements icons have been refreshed ([issue 18](https://code.google.com/p/iaml/issues/detail?id=18))
    * A new tool to generate new element IDs has been added; for example, an [InputTextField](http://openiaml.org/model/InputTextField) with an ID of `model.1a9b9019af.153` may be renamed to `text1` ([issue 86](https://code.google.com/p/iaml/issues/detail?id=86))
  1. Documentation
    * The element export script now also exports to SVG, as well as PNG ([issue 193](https://code.google.com/p/iaml/issues/detail?id=193))
    * The documentation has not been fully migrated yet, nor all of the example models migrated yet, but this will be completed before 0.6.

# Model 0.5.3 #

**Released [January 2011](http://journals.jevon.org/users/jevon-phd/entry/19886)**

This release is mostly bug fixes and other small changes. The metamodel may be changing significantly in the next release (as the thesis requires) so this is an interim release before these changes proceed. Many [improvements have been completed](http://code.google.com/p/iaml/issues/list?can=1&q=label%3AMilestone-Release0.5.3).

There has also been some performance improvements, such as [reducing the size of generated models](http://code.google.com/p/iaml/issues/detail?id=210), [improving Drools rule performance](http://code.google.com/p/iaml/source/detail?r=2494) and [improvements to Modeldoc](http://code.google.com/p/iaml/source/detail?r=2527).

## Changes ##

  1. Documentation
    * Wrote up a list of sample representations of user interface elements in [InterfaceElements](InterfaceElements.md)

# Model 0.6 #

**Released [September 2011](http://journals.jevon.org/users/jevon-phd/entry/19917)**

This is a major release that represents the final version of the IAML modelling environment, as documented in the corresponding [Thesis](Thesis.md) to the project. A lot of the metamodel changes are described by [issue 234](https://code.google.com/p/iaml/issues/detail?id=234), and includes many [issue fixes](http://code.google.com/p/iaml/issues/list?can=1&q=label%3AMilestone-Release0.6) summarised below.

## Changes ##

  1. Meta-model changes
    1. New elements
      * The underlying model of the language has been refactored to support first-order logic concepts. This includes the model elements of [Functions](http://openiaml.org/model/Function), [Predicates](http://openiaml.org/model/Predicate), [BooleanProperties](http://openiaml.org/model/BooleanProperty), [BuiltinProperties](http://openiaml.org/model/BuiltinProperty) and [ComplexTerms](http://openiaml.org/model/ComplexTerm) ([issue 244](https://code.google.com/p/iaml/issues/detail?id=244), [issue 245](https://code.google.com/p/iaml/issues/detail?id=245), [issue 247](https://code.google.com/p/iaml/issues/detail?id=247)).
      * XQuery functions and predicates are now supported through [XQueryFunctions](http://openiaml.org/model/XQueryFunction) and [XQueryPredicates](http://openiaml.org/model/XQueryPredicates) ([issue 246](https://code.google.com/p/iaml/issues/detail?id=246)).
      * The underlying metamodel for domain modelling has been changed to reuse the Ecore metamodel through EMF ([issue 242](https://code.google.com/p/iaml/issues/detail?id=242)).
      * The logic behind the "set" [BuiltinOperation](http://openiaml.org/model/BuiltinOperation) has been moved to a new model element within activity modelling, [SetNode](http://openiaml.org/model/SetNode).
    1. Changed elements
      * The [Arithmetic](http://openiaml.org/model/Arithmetic) model element is now deprecated, and should be replaced with a [XQueryFunction](http://openiaml.org/model/XQueryFunction) ([issue 220](https://code.google.com/p/iaml/issues/detail?id=220)).
      * The Property element has been renamed to [Value](http://openiaml.org/model/Value). StaticValues are now merged with [Values](http://openiaml.org/model/Value) ([issue 179](https://code.google.com/p/iaml/issues/detail?id=179)).
      * The Hidden visual element has been merged into hidden [Labels](http://openiaml.org/model/Label) ([issue 224](https://code.google.com/p/iaml/issues/detail?id=224)).
      * The EntryGate and ExitGate elements have been merged into a single [Gate](http://openiaml.org/model/Gate) element ([issue 254](https://code.google.com/p/iaml/issues/detail?id=254)).
      * [DomainIterators](http://openiaml.org/model/DomainIterator) now contain a single [DomainInstance](http://openiaml.org/model/DomainInstance) ([issue 241](https://code.google.com/p/iaml/issues/detail?id=241)).
      * A [DomainIterator](http://openiaml.org/model/DomainIterator) now has a new value, 'current pointer' ([issue 219](https://code.google.com/p/iaml/issues/detail?id=219)).
      * A [VisibleThing](http://openiaml.org/model/VisibleThing) now has a new value, 'visible' ([issue 223](https://code.google.com/p/iaml/issues/detail?id=223)).
    1. Renamed elements
      * DomainSchema is now [DomainType](http://openiaml.org/model/DomainType) ([issue 263](https://code.google.com/p/iaml/issues/detail?id=263)).
      * CompositeOperation is now [ActivityOperation](http://openiaml.org/model/ActivityOperation) ([issue 248](https://code.google.com/p/iaml/issues/detail?id=248)).
      * CompositeFunction is now [ActivityPredicate](http://openiaml.org/model/ActivityPredicate) ([issue 268](https://code.google.com/p/iaml/issues/detail?id=268)).
      * PrimitiveOperation is now [BuiltinOperation](http://openiaml.org/model/BuiltinOperation).
    1. Removed elements
      * The DynamicApplicationElementSet element has been removed ([issue 253](https://code.google.com/p/iaml/issues/detail?id=253)).
      * The abstract type ApplicationElement element has been removed ([issue 257](https://code.google.com/p/iaml/issues/detail?id=257)).
      * The ExternalValueEdge element has been removed ([issue 274](https://code.google.com/p/iaml/issues/detail?id=274), [issue 277](https://code.google.com/p/iaml/issues/detail?id=277)).
  1. Code generation
    * The [DomainIterator](http://openiaml.org/model/DomainIterator) query now supports the `now()` function ([issue 203](https://code.google.com/p/iaml/issues/detail?id=203)).
  1. Interface
    * The visual representation of metamodel elements is now consistent across all elements, and generated by [SimpleGMF](SimpleGMF.md) ([issue 233](https://code.google.com/p/iaml/issues/detail?id=233)).
    * Names obtained from ENamedElement, rather than [NamedElement](http://openiaml.org/model/NamedElement), will now be correctly displayed ([issue 282](https://code.google.com/p/iaml/issues/detail?id=282)).
  1. Documentation
    * All metamodel elements are now documented through [ModelDoc](ModelDoc.md).
    * All model completion rules are now documented through [ModelDoc](ModelDoc.md) ([issue 240](https://code.google.com/p/iaml/issues/detail?id=240)).
    * The [HelloWorld](HelloWorld.md) documentation has been updated ([issue 138](https://code.google.com/p/iaml/issues/detail?id=138)).
  1. Verification
    * OCL constraints on the metamodel have been implemented through the EMF Validation Framework ([issue 235](https://code.google.com/p/iaml/issues/detail?id=235)).
    * The NuSMV verification plugin has been released through the update site ([issue 236](https://code.google.com/p/iaml/issues/detail?id=236)).
    * The CrocoPat verification plugin has been released through the update site ([issue 237](https://code.google.com/p/iaml/issues/detail?id=237)).
  1. Performance
    * Initialising new IAML diagrams should now be faster, as an unnecessary HTTP request was removed ([issue 252](https://code.google.com/p/iaml/issues/detail?id=252)).
    * Unused model completion rules have been identified and removed ([issue 261](https://code.google.com/p/iaml/issues/detail?id=261)).
    * Unused metamodel constructs and references have been removed ([issue 259](https://code.google.com/p/iaml/issues/detail?id=259)).