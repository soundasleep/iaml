[< Development](Development.md)

# Introduction #

This is a brief technical guideline on the process necessary to release a new version of the IAML plugin.

The release process has been automated as much as possible (for example, through [AllReleaseTests](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/release/AllReleaseTests.java?r=3133)), but there are certain steps that are still impossible to run programatically (Eclipse bug [315247](https://bugs.eclipse.org/bugs/show_bug.cgi?id=315247)).

See also [issue 37](https://code.google.com/p/iaml/issues/detail?id=37).

  1. Update the version of `org.openiaml.model` plugin to `0.6.0.qualifier`.
  1. Run [AllReleaseTests](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/release/AllReleaseTests.java?r=3133).
    1. This will automatically update the `.gmfgen`s with new versions, and update other version references throughout the plugin.
  1. Delete the generated model source code in `org.openiaml.model/src-generated`.
  1. Delete the generated edit source code in `org.openiaml.model.edit/src`
  1. Reload and regenerate the model and edit source code from `iaml.genmodel`.
  1. Delete all of the generated diagram editors (for example, with [cleanup\_diagram\_editors.bat](http://code.google.com/p/iaml/source/browse/trunk/cleanup_diagram_editors.bat?r=3133)).
  1. Regenerate all of the diagram editors from the source `.gmfgen`s.
  1. Run [AllReleaseTests](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.runtime/src/web/index.html?r=3133) again, to update the versions in generated diagram editor `MANIFEST.MF`s.
    1. Update the plugin version in [runtime/index.html](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.runtime/src/web/index.html?r=3133).
    1. Resolve any other failing test cases.
  1. Refresh all plugins, and force a clean rebuild of the entire workspace - mostly to identify any lingering errors from Drools/OAW templates.
  1. Commit all of these changes to SVN (for example, [r3126](https://code.google.com/p/iaml/source/detail?r=3126)).

The whole environment is now ready to be built and released.

  1. Update the version of each plugin feature, and _recompute the dependencies_ for each feature
    1. [org.openiaml.model.feature/feature.xml](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model.feature/feature.xml?r=3133) - the main IAML environment
    1. [org.openiaml.verification.crocopat.feature/feature.xml](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.verification.crocopat.feature/feature.xml?r=3133) - the CrocoPat verification plugin
    1. [org.openiaml.verification.nusmv.feature/feature.xml](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.verification.nusmv.feature/feature.xml?r=3133) - the CrocoPat verification plugin
  1. Update the [org.openiaml.update/site.xml](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.update/site.xml?r=3133) update site definitions
  1. _Build all_ the things!
  1. Do not commit the changes to SVN yet. With a local fresh copy of Eclipse, try to install the IAML modelling environment from your local repository, and ensure that everything is generally working
    1. A smoke test: make a simple IAML model that keeps two elements synchronised; generate the code for the model; and test the generated application.

The environment has now been successfully built, so now the release process can conclude.

  1. Commit the built plugins in [org.openiaml.update](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.update?r=3133) to SVN.
  1. [Tag](http://code.google.com/p/iaml/source/browse/trunk/tags?r=3133) the build on SVN.
  1. Update all [outstanding issues](http://code.google.com/p/iaml/issues/list) to use new versions, and tag fixed issues to the correct version.
  1. Write release documentation on the wiki, for example [Model0\_6](Model0_6.md).
  1. Write a new release entry on [the development blog](http://journals.jevon.org/users/jevon-phd/tag/iaml/).
  1. Tag the release on the [development calendar](https://www.google.com/calendar/embed?mode=AGENDA&src=bio39kbr7stcjntgk061ck3uts@group.calendar.google.com&ctz=Pacific/Auckland&wkst=1&gsessionid=LqGCYu0wL7Q1Gv0HBJC4Qg) (using the format `IAML Plugin v 0.x.y`).
  1. [Discuss release on Twitter](http://twitter.com/search/%23iaml).

The [API documentation](http://openiaml.org/model/) for the release may be regenerated through [ModelDoc](ModelDoc.md).