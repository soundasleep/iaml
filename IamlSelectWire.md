# Introduction #

To select a _DomainObjectInstance_ from a _DomainObject_, or to select a _DomainAttributeInstance_ from a _DomainAttribute_, we can either use a _NewInstanceWire_ to select a new instance, or, we can use a _SelectWire_ to select a previously existing value.

These were added in [Model version 0.3](Model0_3.md).



## Selecting Any ##

| **Model** | [SelectField.iaml](http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/codegen/SelectField.iaml) |
|:----------|:----------------------------------------------------------------------------------------------------------------------------------------|
| **Inference Test** | - |
| **Runtime Test** | [SelectField.java](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/codegen/SelectField.java) |

![http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/codegen/model0_3/images/SelectField-1.png](http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/codegen/model0_3/images/SelectField-1.png)

By default, a query will select "any", i.e. select the first result found. This is not usually that helpful.

Alternatively, if we set the query to a query string such as "id = 42", this will search for a given _DomainObject_ which has an _id_ of 42.

## Dynamic Selection ##

| **Model** | [SelectFieldFromDynamicObject.iaml](http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/codegen/SelectFieldFromDynamicObject.iaml) |
|:----------|:--------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Inference Test** | - |
| **Runtime Test** | [SelectFieldFromDynamicObject.java](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/codegen/SelectFieldFromDynamicObject.java) |

![http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/codegen/model0_3/images/SelectFieldFromDynamicObject-1.png](http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/codegen/model0_3/images/SelectFieldFromDynamicObject-1.png)

More interestingly is selecting an instance from another value; in this case above, we are selecting a _User_ with an _email_ equal to the value in the text field "another value". We can use any data source as a parameter source, such as text fields or static values.

## Autosave ##

| **Model** | [SelectFieldFromDynamicQueryAutosave.iaml](http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/codegen/SelectFieldFromDynamicQueryAutosave.iaml) |
|:----------|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Inference Test** | - |
| **Runtime Test** | [SelectFieldFromDynamicQueryAutosave.java](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/codegen/SelectFieldFromDynamicQueryAutosave.java) |

![http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/codegen/model0_3/images/SelectFieldFromDynamicQueryAutosave-1.png](http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/codegen/model0_3/images/SelectFieldFromDynamicQueryAutosave-1.png)

If "auto save" is set to false on the _DomainObjectInstance_, changes to the instance will not be saved in the database automatically. In this case, the "save" operation contained within the instance must be called.

## No Result Found ##

| **Model** | [SelectMissing.iaml](http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/codegen/SelectMissing.iaml) |
|:----------|:--------------------------------------------------------------------------------------------------------------------------------------------|
| **Inference Test** | - |
| **Runtime Test** | [SelectMissing.java](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/codegen/SelectMissing.java) |

![http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/codegen/model0_3/images/SelectMissingExpected-1.png](http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/codegen/model0_3/images/SelectMissingExpected-1.png)

By default, if no result is found, an error is thrown which has to be caught by something. If the error is not caught by anything, the Internet Application will display a 500 error.

| **Model** | [SelectMissingExpected.iaml](http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/codegen/SelectMissingExpected.iaml) |
|:----------|:------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Inference Test** | - |
| **Runtime Test** | [SelectMissingExpected.java](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/codegen/SelectMissingExpected.java) |

![http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/codegen/model0_3/images/SelectMissingExpected.png](http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/codegen/model0_3/images/SelectMissingExpected.png)

The example above has a _NavigateWire_ connecting the "container" page to an alternative error page, "an expected failure". In this case, the application will go to this page when trying to access the failing select wire.