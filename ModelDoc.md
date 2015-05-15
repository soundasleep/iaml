

# Introduction #

**Modeldoc** is an experimental way of adding documentation for a modelling language within the actual implementation itself. This will be discussed later in more detail. The essential tags are:

| **Tag** | **Description** | **Parser** |
|:--------|:----------------|:-----------|
| `@operational` | Operational semantics | [HandleOperationalTag.java](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.docs/src/org/openiaml/docs/generation/semantics/HandleOperationalTag.java) |
| `@inference` | Inference semantics | [HandleInferenceTag.java](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.docs/src/org/openiaml/docs/generation/semantics/HandleInferenceTag.java) |
| `@implementation` | Implementation notes | [HandleImplementationTag.java](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.docs/src/org/openiaml/docs/generation/semantics/HandleImplementationTag.java) |
| `@example` | Examples | [HandleExampleTag.java](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.docs/src/org/openiaml/docs/generation/semantics/HandleExampleTag.java) |

You can see the most recently-generated model documentation at http://openiaml.org/model/.