# Introduction #

I have made a very simple and basic extension which resolves Eclipse bug **[283775: Generate Diagram Code for multiple .gmfgens](https://bugs.eclipse.org/bugs/show_bug.cgi?id=283775)**.

You can install it from the update site below.

```
Name: IAML GMF Tools Update Site
URL: http://iaml.googlecode.com/svn/trunk/org.openiaml.gmf.tools.update/
```

Once installed, you can select any number of .gmfgen files, and select "Generate multiple diagram code". It will cycle through each file and generate the code as normal.

![http://iaml.googlecode.com/svn/trunk/org.openiaml.gmf.tools.update/screenshot.png](http://iaml.googlecode.com/svn/trunk/org.openiaml.gmf.tools.update/screenshot.png)

It uses internal GMF code, so it will likely break in a later GMF version, but this will have to suffice until the bug is resolved :)