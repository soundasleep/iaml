# Introduction #



In order to browse generated applications, you need to install a platform in which to serve generated web applications.

Currently, only the PHP platform is supported; however since IAML is a platform-independent modelling language, future work will cover implementation of the model in other languages.

Essentially, to serve web applications on your machine, you need:

  * A web server, such as Apache Tomcat;
  * And a scripting language, such as PHP.

This installation process will cover the installation of Apache and PHP on Windows, but the implementation of IAML is by no means limited to this combination of technology.

# Installing Apache #

> _Download the latest version of [Apache httpd 2.2](http://httpd.apache.org/download.cgi#apache22), and follow the [installation tutorial](http://httpd.apache.org/docs/2.2/platform/windows.html) on the Apache website._

# Installing PHP #

> _Download the latest version of the [PHP 5.x installer](http://www.php.net/downloads.php), and follow the [installation tutorial](http://www.php.net/manual/en/install.windows.installer.msi.php) on the PHP website._

IAML currently requires [PHP 5.2 or higher](http://php.net/manual/en/function.error-get-last.php).

When installing PHP, make sure that you have the following extensions enabled:

  * PDO
  * SQLite over PDO
  * CURL

In Windows, this is achieved by selecting the following configuration directives in your `php.ini`:

```
extension=php_pdo.dll
extension=php_pdo_sqlite.dll
extension=php_curl.dll
```

# Configure Apache #

> _Follow the [PHP/Apache installation notes](http://www.php.net/manual/en/install.windows.apache2.php) on the PHP website._

# Testing #

In order to test your PHP install, we will use the _phpinfo_ test. Simply create a new text file as so:

```
<?php phpinfo(); ?>
```

Save this file to the root of your web server as **phpinfo.php**. If you are using Apache 2.2 on Windows, this location will be `C:\Program Files\Apache Software Foundation\Apache2.2\htdocs`.

If you then browse to http://localhost:8080/phpinfo.php, you should get a successful page describing your current PHP install.

# Troubleshooting #

Troubleshooting the installation of Apache/PHP is far beyond the scope of this project; if you have difficulty, just check the Internet for [guides on installing Apache/PHP](http://www.google.co.nz/search?q=how+to+install+apache+and+php&ie=utf-8&oe=utf-8).

# What Next? #

Now that you have [installed the IAML Eclipse plugin](Installation.md) and a platform in which to execute the generated web applications, you can now create your [very first Hello World application](HelloWorld.md).

> _For more information, see other [End User documentation](http://code.google.com/p/iaml/w/list?q=label:EndUser)._