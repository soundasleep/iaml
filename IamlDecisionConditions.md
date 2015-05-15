# Introduction #

**[Decision Conditions](http://openiaml.org/model/DecisionCondition)** are the fundamental building blocks of conditional blocks. Their intent is based on their _name_, and we summarise these operations below.

Decision conditions have a fixed semantics and cannot be changed (without modifying IAML inference and code generation logic).

**Also see** [IamlCompositeOperations](IamlCompositeOperations.md) for an overview of pre-defined composite operations, and [IamlPrimitiveOperations](IamlPrimitiveOperations.md) for an overview of the fundamental operations.

## xpathMatch ##

> _TODO Incomplete documentation_

## emailAddress ##

> _TODO Incomplete documentation_

## is set? ##

| **Sample model** | TODO |
|:-----------------|:-----|
| **Runtime tests** | TODO |
| **Added in** | [Model 0.4.1](Model0_5.md) |

This condition will be **true** if the incoming data flow variable has been set, **false** if not. For example, incoming properties stored within a session that have not been modified will return **false**. It only accepts incoming [ApplicationElementProperty](ApplicationElementProperty.md)s.

## equal? ##

> _TODO Incomplete documentation: See documentation in [IamlPrimitiveOperations](http://code.google.com/p/iaml/wiki/IamlPrimitiveOperations#equal?)_

## true? ##

> _TODO Incomplete documentation: See documentation in [IamlPrimitiveOperations](http://code.google.com/p/iaml/wiki/IamlPrimitiveOperations#true?)_

> 