# Introduction #

This is intended to be a quick guide to getting your first IAML-based application running.



# Prerequisites #

First, [install the editor](Installation.md) into your copy of Eclipse.

You will also have to [install a platform](InstallationPlatform.md) (such as PHP) in order to browse the generated application. Make sure that accessing a PHP script works.

# Create a New Project #
We will first create a new project in which to work in. This isn't strictly necessary but it will make your application easier to work with.

Right click the _Project Explorer_ view and select "New > Project".

![http://iaml.googlecode.com/svn/wiki/tutorial/menu-new-project.png](http://iaml.googlecode.com/svn/wiki/tutorial/menu-new-project.png)

Select a [new General Project](http://iaml.googlecode.com/svn/wiki/tutorial/project-new.png), and enter in a name of "hello-world". Press _Finish_ to create your new project.

![http://iaml.googlecode.com/svn/wiki/tutorial/project-name.png](http://iaml.googlecode.com/svn/wiki/tutorial/project-name.png)

# Create a New Application #

We will now create a new IAML-based web application. Right click your project and select "New > Other".

![http://iaml.googlecode.com/svn/wiki/tutorial/menu-new-iaml.png](http://iaml.googlecode.com/svn/wiki/tutorial/menu-new-iaml.png)

Browse down the list of available new projects, until you find "Example > Iaml Diagram".

![http://iaml.googlecode.com/svn/wiki/tutorial/wizard-new.png](http://iaml.googlecode.com/svn/wiki/tutorial/wizard-new.png)

Click _Next_. Enter in an [IAML diagram filename](http://iaml.googlecode.com/svn/wiki/tutorial/wizard-diagram.png) of "hello-world.iaml\_diagram", and then an [IAML domain filename of "hello-world.iaml"](http://iaml.googlecode.com/svn/wiki/tutorial/wizard-domain.png).

When you click Finish, a new model will be created, and the [new graphical editor](http://iaml.googlecode.com/svn/wiki/tutorial/editor-initial.png) will load.

![http://iaml.googlecode.com/svn/wiki/tutorial/editor-1.png](http://iaml.googlecode.com/svn/wiki/tutorial/editor-1.png)

# Editing the Application #

As you can see in this initial application, we are provided with a single initial page (termed a [Frame](http://openiaml.org/model/Frame)) called "Home". This is, by default, the starting point of your new web application.

In this _Hello World_ application, we will create a web site which shows two text fields that are synchronous with each other. This is a fairly basic use case of Rich Internet Applications.

Double click the "Home" page. This will open up a new editor. To reduce clutter, the editor provides a hierarchical view over the Internet Application. In this case, we are going to edit the contents of the "Home" page. By default, it is empty.

![http://iaml.googlecode.com/svn/wiki/tutorial/editor-2.png](http://iaml.googlecode.com/svn/wiki/tutorial/editor-2.png)

Create two new text fields in this Page, naming them "Hello", and "World". The available new elements are rendered in a Palette to the side of the editor; text fields are called [InputTextFields](http://openiaml.org/model/InputTextField), and are under the _Visual Elements_ section of the editor.

![http://iaml.googlecode.com/svn/wiki/tutorial/palette-small-select.png](http://iaml.googlecode.com/svn/wiki/tutorial/palette-small-select.png)

![http://iaml.googlecode.com/svn/wiki/tutorial/editor-3.png](http://iaml.googlecode.com/svn/wiki/tutorial/editor-3.png)

By default, these text fields would do nothing. So, we will connect these two fields up with a _Synchronisation Wire_, called a [SyncWire](http://openiaml.org/model/SyncWire). When a [SyncWire](http://openiaml.org/model/SyncWire) is connected between two elements, it will keep the values of these elements equal when they change.

![http://iaml.googlecode.com/svn/wiki/tutorial/editor-4.png](http://iaml.googlecode.com/svn/wiki/tutorial/editor-4.png)

The new wire does not need to have a name, but to keep the editor clean, we can name the new wire "sync". Press Ctrl-S to save your model.

# Generate Code #

Now that we have created a model of an Internet Application, we can automatically generate the server-side and client-side code necessary for it to function. This is the main benefit of using a platform-independent model for designing a web application; you do not need to worry about the implementation details of the application, but rather the work required in implementing your requirements.

![http://iaml.googlecode.com/svn/wiki/tutorial/menu-generate.png](http://iaml.googlecode.com/svn/wiki/tutorial/menu-generate.png)

Locate the "hello-world.iaml" file in your _Project Explorer_. Right click this file, and select "IAML Tools > Generate Code".

![http://iaml.googlecode.com/svn/wiki/tutorial/generation.png](http://iaml.googlecode.com/svn/wiki/tutorial/generation.png)

This step generates the PHP, Javascript, CSS, HTML and everything else necessary for your application. By default the generated code is placed into a new folder called "output".

![http://iaml.googlecode.com/svn/wiki/tutorial/project-generated.png](http://iaml.googlecode.com/svn/wiki/tutorial/project-generated.png)

# Set up PHP #

Now that we have generated the application, we can browse it just like any other web application written in PHP. The technical details of this step depend on the server you have installed.

If you are using Apache to serve PHP, and your current Eclipse workspace is located at "C:\Workspace", you can set up an Alias to this generated code by adding the following to your _httpd.conf_ configuration file:

```
Alias "/hello-world" "C:/Workspace/hello-world/output"
<Directory "C:/Workspace/hello-world/output">
   Options Indexes FollowSymLinks
   DirectoryIndex index.html index.php default.html default.php
   AllowOverride All
   Allow from All   
</Directory>
```

Save _httpd.conf_, and restart Apache.

![http://iaml.googlecode.com/svn/wiki/tutorial/restart-apache.png](http://iaml.googlecode.com/svn/wiki/tutorial/restart-apache.png)

This step only needs to be executed once per application. If you have multiple projects in a single workspace, it is possible to configure Apache (or any other server) to render each workspace project in a separate folder.

# Load in Browser #

We can now access the generated application from any web browser. If http://localhost:8080/hello-world/ points to the "output" folder above, we can visit this address.

![http://iaml.googlecode.com/svn/wiki/tutorial/generated-initial.png](http://iaml.googlecode.com/svn/wiki/tutorial/generated-initial.png)

Now, for the magic test: if you change the field of one value, it should automatically update the second value.

![http://iaml.googlecode.com/svn/wiki/tutorial/generated-changed.png](http://iaml.googlecode.com/svn/wiki/tutorial/generated-changed.png)

# What Next? #

Now that we have a working application, you can edit the model in the diagram editor until it represents your application.

In the future, there will be more information and user documentation on what can be achieved with the IAML model and its design tools.

> _For more information, see other [End User documentation](http://code.google.com/p/iaml/w/list?q=label:EndUser)._