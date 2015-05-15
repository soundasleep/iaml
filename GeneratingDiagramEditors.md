[< Development](Development.md)

# Tips #

Here is a basic script to delete the generated code from all generated editors ([current source](http://code.google.com/p/iaml/source/browse/trunk/cleanup_diagram_editors.bat)).

```
rem we should be in workspace\
rm -rf "org.openiaml.model.diagram\src\org"
rm -rf "org.openiaml.model.diagram.condition\src\org"
rm -rf "org.openiaml.model.diagram.element\src\org"
rm -rf "org.openiaml.model.diagram.email\src\org"
rm -rf "org.openiaml.model.diagram.frame\src\org"
rm -rf "org.openiaml.model.diagram.instance\src\org"
rm -rf "org.openiaml.model.diagram.iterator\src\org"
rm -rf "org.openiaml.model.diagram.operation\src\org"
rm -rf "org.openiaml.model.diagram.root\src\org"
rm -rf "org.openiaml.model.diagram.schema\src\org"
rm -rf "org.openiaml.model.diagram.session\src\org"
rm -rf "org.openiaml.model.diagram.visual\src\org"
```