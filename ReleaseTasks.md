[DevelopmentTasks](DevelopmentTasks.md)

# Releasing a new plugin version #

This page briefly describes the steps involved with releasing a new plugin version. Ideally in the future this will be part of an [automated release script](http://code.google.com/p/iaml/issues/detail?id=37).

Given that we want to upgrade the plugin to a new version number:

  1. For each .gmfgen,
    1. Update _Editor Generator/Plugin/Version_ to the new version
    1. Regenerate diagram code
  1. Diff all _plugin/META-INF/MANIFEST.MF_ and replace _Bundle-Vendor: %providerName_ with _Bundle-Vendor: www.openiaml.org_
  1. Update the _MANIFEST.MF_ in all other plugins
  1. Run all unit tests, especially those in _ReleaseTestsSuite_ and _ModelTestsSuite_
  1. Commit changes to SVN
  1. Update the feature.xml and site.xml (instructions to be continued)