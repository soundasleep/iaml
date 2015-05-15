# Introduction #

Platform-specific project properties can be specified in an IAML project; a detailed editor will be provided with [issue 87](https://code.google.com/p/iaml/issues/detail?id=87).

The creation of these properties will be detailed later, but essentially an `iaml.properties` file, that is in the same folder as the `.iaml` model, will be read at generation time.

For properties that are not specified, the default properties are specified in [DefaultRuntimeProperties.java](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model/src/org/openiaml/model/codegen/DefaultRuntimeProperties.java).

## include\_runtime ##

If this is set to **true** (the default), the runtime libraries will be included directly in the generated code output. It will also modify _config\_runtime_ and _config\_web_ properties to reflect these new properties.

This was added in [issue 81](https://code.google.com/p/iaml/issues/detail?id=81) and implemented in the extension [OawCodeGeneratorWithRuntime.java](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.codegen.oaw/src/org/openiaml/model/codegen/oaw/OawCodeGeneratorWithRuntime.java?spec=svn889&r=889).

## config\_runtime ##

A string pointing to the server-side runtime libraries: [issue 75](https://code.google.com/p/iaml/issues/detail?id=75). This is implemented at runtime by creating a _config.php_ configuration file at runtime, and referencing it as follows ([example](http://code.google.com/p/iaml/source/detail?r=894)):

```
<?php require(IAML_RUNTIME_ROOT."global.php"); ?>
```

## config\_web ##

A string pointing to the client-side runtime libraries: [issue 76](https://code.google.com/p/iaml/issues/detail?id=76). This is implemented at runtime through:

```
<script language="Javascript" type="text/javascript" src="<?php echo IAML_RUNTIME_WEB; ?>js/default.js"></script>
```

## debug ##

If set to "true", debug information will be rendered as part of the generate code. Until [issue 137](https://code.google.com/p/iaml/issues/detail?id=137) is resolved, this only affects the rendering of debug code; it is still generated, just not displayed.

```
<?php if (DEBUG) { ... } ?>
```

## email\_handler ##

The handler used to send [e-mail](http://openiaml.org/model/Email). In the future, this will include configuration for `mail()`, PHPMailer, direct SMTP, etc. Currently, the following e-mail handlers are supported:

  * `phpmailer`: Use [PHPMailer](PHPMailer.md) **(default)**
  * `file`: Send to a text file

If using [PHPMailer](PHPMailer.md), then the default `mail` implementation is used (usually `mail()`).

## email\_handler\_phpmailer\_include ##

Location of the [PHPMailer](PHPMailer.md) include files.

## email\_handler\_file\_destination ##

If `email_handler` is set to `file`, this is the destination text file that [e-mails](http://openiaml.org/model/Email) will be written to.

The target file will be a Java Properties file, in the following format:

```
size=5
email.0.from=source@openiaml.org
email.0.to=to@openiaml.org.
email.0.subject=subject
email.0.content=e-mail content
email.1.from=source@openiaml.org
...
```

## map\_handler ##

The handler used to render [Maps](http://openiaml.org/model/Map). Currently, the following map handlers are supported:

  * `googlemaps`: Use [Google Maps API](http://code.google.com/apis/maps/) **(default)**
  * `mock`: Generate a mocked-up map

## google\_maps\_api\_key ##

If using the `googlemaps` `map_handler` option, you need to provide an API key for your application. No default key is provided with IAML, you will have to [generate your own](http://code.google.com/apis/maps/signup.html) (which is tied to your Google account), and agree with the terms and conditions provided.

## proxy\_host ##

When using [CURL](http://php.net/curl), this is provided as the proxy host. For example, `proxy.example.com`. This is empty by default.

## proxy\_port ##

When using [CURL](http://php.net/curl), this is provided as the proxy port. For example, `8080`. This is empty by default.

## proxy\_userpass ##

When using [CURL](http://php.net/curl), this is provided as the proxy username and password. For example, `username:password`. This is empty by default.