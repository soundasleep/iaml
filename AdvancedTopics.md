# Introduction #

This page covers details of advanced topics when working with the IAML platform.



## Runtime Library ##

Since [r888](https://code.google.com/p/iaml/source/detail?r=888), IAML provides a separate runtime library plugin which can be aliased to a virtual folder to improve performance. To enable this, you will need to set up an alias on your web server to the IAML client-side runtime libraries (similar to Symfony's /sf virtual folder):

```
# iaml library include (client-side)
Alias "/iaml-runtime" "C:/.../org.openiaml.model.runtime/src/web"
<Directory "C:/.../org.openiaml.model.runtime/src/web">
   Options Indexes FollowSymLinks
   DirectoryIndex index.html index.php default.html default.php
   AllowOverride All
   Allow from All   
</Directory>
```

To make your project use these libraries, set "include\_runtime" to "false" in your project properties, and set "config\_runtime" and "config\_web" appropriately. See: [IamlProjectProperties](IamlProjectProperties.md)