# Introduction #

The IAML plugin is an Eclipse plugin that provides a visual editor for the modelling of Internet applications, as an implementation of our [research project](http://openiaml.org). Following our research emphasis on iterative development, we hope that regularly releasing our plugin will help us focus development and gain valuable feedback from the development community.



# Install Eclipse #

First you need to install a copy of [Eclipse](Eclipse.md). Go to the [download page for Eclipse](http://www.eclipse.org/downloads/) and download the latest version of _Eclipse Classic_.

Once downloaded, extract this archive to a folder, and start Eclipse.

You will need to select a workspace folder - usually you can just select the default.

# Install IAML #

Select the "Menu" item and select "Install New Software".

![http://iaml.googlecode.com/svn/wiki/install/menu-install.png](http://iaml.googlecode.com/svn/wiki/install/menu-install.png)

You will be provided with [an installation dialog box](http://iaml.googlecode.com/svn/wiki/install/software-empty.png) in which to select software artefacts to install.

Click the "Add" button and add the following update sites to your Eclipse installation. Eclipse will automatically contact these to download related and required plugins:

```
Name: OpenArchitectureWare update site 
URL:  http://www.openarchitectureware.org/updatesite/milestone/
```

```
Name: iacleaner update site 
URL:  http://iacleaner.googlecode.com/svn/trunk/org.openiaml.iacleaner.update/
```

Click the "Add" button and add the following update site to your Eclipse installation. All updates to the IAML project will be provided through this update site, and Eclipse includes functionality to automatically update your installation when new versions are released.

```
Name: IAML Modelling Platform update site
URL:  http://iaml.googlecode.com/svn/trunk/org.openiaml.update/
```

![http://iaml.googlecode.com/svn/wiki/install/update-site.png](http://iaml.googlecode.com/svn/wiki/install/update-site.png)

Once the update site has been added, you should be able to select the latest version of the IAML Modelling Platform to install. You will also need to install the **EMF XPath Support**.

![http://iaml.googlecode.com/svn/wiki/install/software-select.png](http://iaml.googlecode.com/svn/wiki/install/software-select.png)

> _If you receive the following error: "Only one of the following can be installed at once: Core Resource Management", then you [need to update your Eclipse installation itself](http://www.jevon.org/wiki/Only_one_of_the_following_can_be_installed_at_once%3A_Core_Resource_Management)._

Click "Next" and continue through the installation wizard. Eclipse will automatically contact relevant update sites and locate the required dependencies of the IAML plugin. This may take a significant period of time. Once all of these dependencies have been downloaded, the platform will be installed, and you will be asked to restart your platform (highly recommended).

# What Next? #

Once you have installed the Eclipse plugin, you should make sure that you have a [correct installation of PHP installed](InstallationPlatform.md). You can then create your [very first Hello World](HelloWorld.md) Rich Internet Application.

(Future plugin versions will support code generation to platforms other than PHP.)

## Note ##

If you need to set up a proxy for Eclipse to access the IAML update site, you may need to add the following lines to your _eclipse.ini_ (before the `-vmargs` section):

```
-Dhttp.proxyPort=1234
-Dhttp.proxyHost=proxy.name
```

This adds required proxy information for the Java runtime. To set up the proxy for Eclipse itself, you will need to set up the proxy as a _manual_ connection in _Window > Preferences_:

![http://iaml.googlecode.com/svn/wiki/install/proxy.png](http://iaml.googlecode.com/svn/wiki/install/proxy.png)

## Note ##

You may run out of memory when using the modelling platform within a Sun VM, in particular PermGen memory; this is mostly due to the Drools engine requiring a 7MB compiler in PermGen memory. It will depend on your system, but you may need to add the following lines to your _eclipse.ini_:

```
-vmargs
-Xms128m
-Xmx512m
-XX:PermSize=128M
-XX:MaxPermSize=384M
```

> _For more information, see other [End User documentation](http://code.google.com/p/iaml/w/list?q=label:EndUser)._