[< Development](Development.md)

# Introduction #

When adding a new edge type to the meta-model that will be created in a diagram editor, there are additional steps that must be taken, otherwise the created link may not persist across Eclipse restarts/initialize diagram.

  1. [Add the new edge type](http://code.google.com/p/iaml/source/diff?spec=svn2075&r=2075&path=/trunk/org.openiaml.model/src/org/openiaml/model/helpers/EdgeTypes.java&old_path=/trunk/org.openiaml.model/src/org/openiaml/model/helpers/EdgeTypes.java&old=1664) to `org.openiaml.model.helpers.EdgeTypes`
  1. [Modify GetShortcuts.java](http://code.google.com/p/iaml/source/diff?spec=svn2076&r=2076&path=/trunk/org.openiaml.model.diagram.helpers/src/org/openiaml/model/diagram/helpers/GetShortcuts.java&old_path=/trunk/org.openiaml.model.diagram.helpers/src/org/openiaml/model/diagram/helpers/GetShortcuts.java&old=1736):
    1. Add the edge type to `getAllImportantRelationships()`
    1. Create a new method `getAllShortcutsFromXXXEdges()`
    1. Add the edge type to `getSourceElement()`
    1. Add the edge type to `getTargetElement()`

These steps are on top of the normal requirements (i.e. adding it to the ecore meta-model, and updating the diagram editors as necessary).

Ideally these steps would be created automatically ([issue 183](https://code.google.com/p/iaml/issues/detail?id=183)).