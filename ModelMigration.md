# Introduction #

Model migration has been available since [version 0.1](Model0_1.md), and there will always be a model migration path to newer model versions. To migrate an older model version, right click the model file and select _Migrate model to latest version_. You will have to select a new model file to place the migrated model.

Once the model has been migrated, you will likely have to recreate the .iaml\_diagram files for the new model version; right click the model file and select "Initialize iaml\_diagram diagram file".

Generally there will not be any errors or warnings while migrating models; however, if there are significant problems that the model developer should know about, warning messages will be placed in the Eclipse error log and can be viewable by opening the _Error Log_ tab.

## Test Cases ##

Technical details about the changes to each model version are stored as Javadoc inside each Migrator class. (TODO [Link up to generated Javadoc](http://code.google.com/p/iaml/issues/detail?id=22))

Technical details about the model migration:

| **Version** | **Migrator** | **Test case** | **Test model** |
|:------------|:-------------|:--------------|:---------------|
| Model 0.0 to 0.1 | [Migrate0To1.java](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.diagram.custom/src/org/openiaml/model/diagram/custom/migrate/Migrate0To1.java) | [Migrate0\_1SignupForm.java](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/eclipse/migration/Migrate0_1SignupForm.java) | [signup-form-0\_1.iaml](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/eclipse/migration/signup-form-0_1.iaml) |
| Model 0.1 to 0.2 | [Migrate1To2.java](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.diagram.custom/src/org/openiaml/model/diagram/custom/migrate/Migrate1To2.java) | [Migrate0\_2SyncPages.java](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/eclipse/migration/Migrate0_2SyncPages.java) | [codegen-sync-pages-0\_2.iaml](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/eclipse/migration/codegen-sync-pages-0_2.iaml) |
| Model 0.2 to 0.3 | [Migrate2To3.java](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.diagram.custom/src/org/openiaml/model/diagram/custom/migrate/Migrate2To3.java) | ? | ? |