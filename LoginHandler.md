# Introduction #

A _Login Handler_ is a component which implements basic security for a Session. Any page within a Session that also contains a Login Handler will be secured with the handler. Simply place the _Login Handler_ within a _Session_ for it to take effect.

Incoming _Parameter Wires_ will represent the necessary information for logging in.

The "login" outgoing wire will represent what to do when a login is successful; for example, this may navigate to a "login is successful" page.

The "logout" outgoing wire will represent what to do when a logout is successful; for example, this may navigate back to a home page, or a "logout is successful" page.

The outgoing _Set Wire_ represents what model element will be updated with the current session.

## Technical Details ##

A _Login_ page will be generated, which will contain a form with all the fields that the user may need to complete in order to access the session. For example, a _secret key_ handler will generate a login form with a single text field (the key); a _domain instance_ handler will generate text fields for each parameter connected towards it.

A _Logout_ page will be generated, which will invalidate the current login handler session information.

Any Page that contains a Login Handler will have its _access_ EventTrigger connected to a _check key_ or _check instance_ operation. This operation will make sure that the current session has a valid key or instance; if not, it will redirect to the _login_ page.

## Secret Key ##
| **Description** | Controls authentication and access to pages, authenticated with a single key. |
|:----------------|:------------------------------------------------------------------------------|
| **Model** | [LoginHandlerKey.iaml](http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/codegen/model0_1/LoginHandlerKey.iaml) |
| **Inference Tests** | [LoginHandlerKey.java](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/inference/model0_4/LoginHandlerKey.java) |
| **Runtime Tests** | [LoginHandlerKey.java](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/codegen/model0_1/LoginHandlerKey.java) |

The incoming _Parameter Wire_ can be any data source, such as a static value or an _ApplicationElementProperty_. The _Set Wire_ should connect to an _ApplicationElementProperty_, which will represent the current sessions' secret key.

Only one incoming secret key parameter is supported at the moment.

![http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/codegen/model0_1/images/LoginHandlerKey-1.png](http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/codegen/model0_1/images/LoginHandlerKey-1.png)

## Domain Instance ##
| **Description** | Controls authentication and access to pages, authenticated with a domain instance. |
|:----------------|:-----------------------------------------------------------------------------------|
| **Model** | [LoginHandlerInstanceComplete.iaml](http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/codegen/model0_4/LoginHandlerInstanceComplete.iaml) |
| **Inference Tests** | [LoginHandlerInstanceMultiple.java](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/inference/model0_4/LoginHandlerInstanceMultiple.java) |
| **Runtime Tests** | [LoginHandlerInstanceComplete.java](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/codegen/model0_4/LoginHandlerInstanceComplete.java) |

Authentication with this login handler will only be true if the given _DomainInstance_ exists. This is an easy way to select for users in a database.

The incoming _Parameter Wire_ can be any attribute in a _Domain Object_, although they should all belong to the same object (not yet enforced). The _Set Wire_ should connect to a _Domain Object Instance_, which will represent the current sessions' selected instance.

Any number of incoming attribute parameters are supported.

![http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/codegen/model0_4/images/LoginHandlerInstanceComplete-2.png](http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/codegen/model0_4/images/LoginHandlerInstanceComplete-2.png)