This document will briefly summarise the steps required to create your own development environment from the source code, in order to understand and contribute to the project. If you only want to check out a copy of the plugin in order to create web application models, check out the [installation instructions](Installation.md) instead.



# Introduction #
IAML is currently developed in Java and [Eclipse 3.5 (Galileo)](http://www.eclipse.org/galileo/), and heavily utilizes [EMF](http://www.eclipse.org/emf/) (for the [meta-model definition](Model0_4.md)), [GMF](http://www.eclipse.org/modeling/gmf/) (for the graphical editor), [OpenArchitectureWare](http://www.eclipse.org/gmt/oaw/download/) (for code generation and constraint validation) and [JBoss Drools](http://www.jboss.org/drools/) (for model inference). Currently an in-depth knowledge of all of these related technologies is assumed for development.

In the future, once [issue 28](http://code.google.com/p/iaml/issues/detail?id=28) is resolved, a copy of the entire IAML development environment will be provided on the [update site](Installation.md) as an SDK feature.

# Requirements #

First you will need to download [Eclipse 3.5 (Galileo)](http://www.eclipse.org/galileo/) and extract it to your given working directory; and, of course, you will need Java 1.6+ installed.

I suggest adding the following parameters to your `eclipse.ini` to prevent _Out of PermGen Memory_ errors:

```
-vmargs
-Xms128m
-Xmx512m
-XX:PermSize=128M
-XX:MaxPermSize=384M
```

Then, through the _Install New Software..._ manager, install the following plugins and SDKs along with their pre-requisites:

## Galileo ##

```
Name: Galileo update site
URL: http://download.eclipse.org/releases/galileo
```

  * EMF - Eclipse Modeling Framework SDK (2.5.0)
  * UML2 Extender SDK (3.0.0)
  * UML2 Tools SDK (Incubation) (0.9.0)
  * XSD - XML Schema Definition SDK (2.5.0)

You may also install the _TPTP Testing and Profiling tools_ if you wish to profile the application, but this is not necessary.

## GMF ##

We update from the GMF update site itself to keep up-to-date with recent releases.

```
Name: GMF update site
URL: http://download.eclipse.org/modeling/gmf/updates/releases/
```

  * GMF SDK 2.2.0

## OpenArchitecureWare ##

When installing, uncheck "Group items by category". This needs to be installed after the UML2 tools above, or you may run into a prerequisite problem that Eclipse cannot solve itself. We will be using OAW until [issue 71](http://code.google.com/p/iaml/issues/detail?id=71) is resolved to migrate it to the new Xpand Eclipse project.

```
Name: OpenArchitectureWare update site 
URL: http://www.openarchitectureware.org/updatesite/milestone/
```

  * openArchitectureWare Classic feature (4.3.1)
  * openArchitectureWare Classic SDK feature (4.3.1)
  * openArchitectureWare SDK feature (4.3.1)

## JBoss Drools ##

While [JBoss Drools 5.0.1](http://downloads.jboss.com/drools/updatesite3.4/) has been released, there is [a major bug](https://jira.jboss.org/jira/browse/JBRULES-2218) that prevents us from using this implementation yet. So, you should instead install Drools 4.0.7:

```
Name: JBoss Drools update site 
URL: http://downloads.jboss.com/drools/updatesite3.3/
```

  * JBoss Drools Workbench (4.0.7)

## XPath support for EMF ##

This helps us in our test cases to select various parts of EMF models. ([more information](http://www.eclipticalsoftware.com/emf/xpath/))

```
Name: Ecliptical Software update site
URL: http://www.eclipticalsoftware.com/updates
```

  * XPath support for EMF (2.0.1)
  * XPath Support for EMF SDK (2.0.1)

## GMF Tools ##

This is not strictly necessary, but the [GmfTools](GmfTools.md) sub-project adds some extra functionality not yet provided by GMF; in particular, the ability to generate diagram code from multiple .gmfgens.

```
Name: IAML GMF Tools Update Site
URL: http://iaml.googlecode.com/svn/trunk/org.openiaml.gmf.tools.update/
```

  * GMF Tools Feature (0.0.1)

## iacleaner ##

[iacleaner](http://code.google.com/p/iacleaner/) is used to format the generated web application code. You will either need to check out the source code directly, or you may instead [install the plugin itself](http://code.google.com/p/iacleaner/wiki/Installation).

```
Name: iacleaner Update Site
URL: http://iacleaner.googlecode.com/svn/trunk/org.openiaml.iacleaner.update/
```

  * iacleaner Feature (0.3.0)

## Subclipse ##

[Subclipse](http://subclipse.tigris.org) permits version control with Subversion; another option is Subversive, but [I don't prefer it](http://journals.jevon.org/users/jevon-phd/entry/19739). You will need to [install SVN separately](http://subversion.tigris.org/servlets/ProjectDocumentList?folderID=8100) onto your system. [Make sure](http://journals.jevon.org/users/jevon-phd/entry/19513) that you select the correct Subclipse version for your current installation of SVN.

```
Name: Subclipse 1.6.x update site
URL: http://subclipse.tigris.org/update_1.6.x
```

  * Subclipse (Required) (1.6.5)
  * Subversion Client Adapter (Required) (1.6.4.1)
  * Subversion JavaHL Native Library Adapter (Required) (1.6.4.1)

# Checking out #

Once you have your Eclipse environment set up, you can [check out the latest trunk](http://code.google.com/p/iaml/source/checkout) from the Subversion repository. Simply check out all projects in **[trunk](http://iaml.googlecode.com/svn/trunk/)** to your local workspace.

```
SVN Trunk: http://iaml.googlecode.com/svn/trunk/
```

# Development Guides #

In the future this guide will also contain information on topics such as:

  1. Plugin Overview
  1. Building
    1. Reloading the meta-model
    1. [GeneratingDiagramEditors](GeneratingDiagramEditors.md)
    1. [SimpleGMF](SimpleGMF.md)
  1. Testing
  1. Deploying
    1. [ReleaseProcess](ReleaseProcess.md)
  1. Contributing
    1. [MetamodelRefactoring](MetamodelRefactoring.md)
  1. Extending
    1. [CreatingANewVisualEditor](CreatingANewVisualEditor.md)
    1. [AddingANewEdgeType](AddingANewEdgeType.md)
  1. Integrating
    1. [GeneratingModelDoc](GeneratingModelDoc.md)
  1. Profiling

For now you can see the [PluginIDs](PluginIDs.md) of each of the modelling plugins.