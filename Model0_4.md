[< 0.3](Model0_3.md) | **0.4** | [0.5 >](Model0_5.md)

# Model 0.4 #



# Introduction #

This model release adds a lot of important model elements to the modelling language. In particular, there is now better support for the instancing of domain objects and [role-based access control](http://openiaml.org/model/Role). Domain objects now support [inheritance](http://openiaml.org/model/ExtendsWire). Users are now [first-class objects](http://openiaml.org/model/UserInstance) and can be created just like domain objects.

## Documentation ##

There is now an experimental documentation source for model elements, available online at http://openiaml.org/model and described briefly at [ModelDoc](ModelDoc.md). Model elements now have documentation ([issue 39](https://code.google.com/p/iaml/issues/detail?id=39)) and this documentation is generated automatically. In the future, previous model versions will be migrated to this new format.

An [installation guide](Installation.md) has been written ([issue 2](https://code.google.com/p/iaml/issues/detail?id=2)) and a [simple tutorial](HelloWorld.md) has also been written ([issue 1](https://code.google.com/p/iaml/issues/detail?id=1)), along with screenshots ([issue 97](https://code.google.com/p/iaml/issues/detail?id=97)).

There is now a rich suite of generated example models, available on the documentation site: http://openiaml.org/model/examples

## Usability Features ##

The differences between [primitive operations](IamlPrimitiveOperations.md) and [composite operations](IamlCompositeOperations.md) have been clarified. If an unrecognised primitive operation is specified, a warning will show ([issue 136](https://code.google.com/p/iaml/issues/detail?id=136)).

A new [session](http://openiaml.org/model/Session) editor has been created ([issue 102](https://code.google.com/p/iaml/issues/detail?id=102)).

The model instance will be checked for validity before code generation is executed; if there is an error, a message box will be displayed.

Debug information is no longer [displayed by default](IamlProjectProperties.md) ([issue 115](https://code.google.com/p/iaml/issues/detail?id=115)).

## Bug Fixes ##

There were [many bug fixes](http://code.google.com/p/iaml/issues/list?can=1&q=label%3AMilestone-Release0.4), but the most important ones included:

  * Generated elements no longer showing derived '/' marker ([issue 92](https://code.google.com/p/iaml/issues/detail?id=92))
  * Elements can no longer be included accidentally within more than one containment feature ([issue 96](https://code.google.com/p/iaml/issues/detail?id=96))
  * Constraints not being executed on model save ([issue 135](https://code.google.com/p/iaml/issues/detail?id=135))
  * A [SelectWire](http://openiaml.org/model/SelectWire) will now synchronise attributes between objects and instances ([issue 68](https://code.google.com/p/iaml/issues/detail?id=68))
  * Incoming [ParameterWires](http://openiaml.org/model/ParameterWire) from shortcuts are now rendered correctly ([issue 69](https://code.google.com/p/iaml/issues/detail?id=69))

## Development ##

  * PHP and Javascript libraries have been refactored out into a separate runtime bundle ([issue 75](https://code.google.com/p/iaml/issues/detail?id=75), [issue 76](https://code.google.com/p/iaml/issues/detail?id=76))
  * A significant portion of the code generation templates have been refactored ([issue 78](https://code.google.com/p/iaml/issues/detail?id=78), [issue 79](https://code.google.com/p/iaml/issues/detail?id=79), [issue 80](https://code.google.com/p/iaml/issues/detail?id=80), [issue 95](https://code.google.com/p/iaml/issues/detail?id=95))
  * Diagram tests have been refactored out into a separate bundle ([issue 109](https://code.google.com/p/iaml/issues/detail?id=109))

# New Elements #

## Login Handler (Domain Instance) ##

A [login handler](http://openiaml.org/model/LoginHandler) can now be connected to login [domain object instances](http://openiaml.org/model/DomainObjectInstance). This will login or logout the current user based on the existance of a valid domain instance in the [domain store](http://openiaml.org/model/DomainStore).

> _See http://openiaml.org/model/LoginHandler_

## Login Handler (User Instance) ##

A [login handler](http://openiaml.org/model/LoginHandler) can now be connected to login [user instances](http://openiaml.org/model/UserInstance). This will login or logout the current user based on the existance of a valid user in the [user store](http://openiaml.org/model/UserStore).

> _See http://openiaml.org/model/LoginHandler_

## Join Node, Split Node ##

A Split Node will split the execution of an operation; a Join Node is required to unify the execution back.

> _See http://openiaml.org/model/SplitNode, http://openiaml.org/model/JoinNode_

## Operation Call Node ##

A virtual [operation](http://openiaml.org/model/Operation) call; the outgoing [Run Instance Wire](http://openiaml.org/model/RunInstanceWire) will be executed.

> _See http://openiaml.org/model/OperationCallNode_

## Query Parameter ##

Represents a parameter taken from the current request query.

> _See http://openiaml.org/model/QueryParameter_

## User Store ##

Contains the definition of users; in particular, [roles](http://openiaml.org/model/Role) and [permissions](http://openiaml.org/model/Permission).

> _See http://openiaml.org/model/UserStore_

## User Instance ##

Represents an instance of a user, most commonly the currently [logged in user](http://openiaml.org/model/LoginHandler).

> _See http://openiaml.org/model/UserInstance_

## Role ##

User instances can have one or many roles, which can be inherited. Roles can also provide [permissions](http://openiaml.org/model/Permission). This is an implementation of Role-Based Access Control.

> _See http://openiaml.org/model/Role_

## Permission ##

User instances can have any number of permissions, which can be added or removed at any time.

> _See http://openiaml.org/model/Permission_

## Access Control Handler ##

Restricts access to a Page or Session based on the required Roles or Permissions.

> _See http://openiaml.org/model/AccessControlHandler_

# New Wires #
## Set Wire ##

Keeps a target value up-to-date with a source value in a unidirectional fashion.

> _See http://openiaml.org/model/SetWire_

## Extends Wire ##

Allows for [domain objects](http://openiaml.org/model/DomainObject) and [roles](http://openiaml.org/model/Role) to inherit each other. Supports limited multiple inheritance.

> _See http://openiaml.org/model/ExtendsWire_

## Provides Wire ##

Indicates that the given [user role](http://openiaml.org/model/Role) by default will provide the connected [permissions](http://openiaml.org/model/Permission).

> _See http://openiaml.org/model/ProvidesWire_

## Requires Wire ##

Indicates that the given [access control handler](http://openiaml.org/model/AccessControlHandler) requires the given [role](http://openiaml.org/model/Role) or [permission](http://openiaml.org/model/Permission).

> _See http://openiaml.org/model/RequiresWire_

## Constraint Wire ##

Allows for the complex construction of [requirements](http://openiaml.org/model/RequiresWire) for an [access control handler](http://openiaml.org/model/AccessControlHandler).

In the future this will allow the complex construction of [conditions](http://openiaml.org/model/ConditionWire) ([issue 134](http://code.google.com/p/iaml/issues/detail?id=134)).

> _See http://openiaml.org/model/ConstraintWire_