Using the apply/make-diff scripts.

These scripts are made to help merging the generated .gmfgens together. This
is because GMF overrides OpenDiagramPolicies of the various elements. We can
either delve into the .gmfgen and revert back the OpenDiagramPolicies manually,
or we can use this script!

Use:

$ apply root

Merges ../diff/root.gmfgen.diff into ../root.gmfgen.

$ make-diff-copies

Copies all ../*.gmfgen into ../copies/*.gmfgen.
You should do this when the .gmfgen DOES have the OpenDiagramPolicies in.
(I think)

$ make-diff root

Makes ../diff/root.gmfgen.diff.
you should do this when the .gmfgen DOES NOT have the OpenDiagramPolicies
in.
(I think)

-- TODO: Documentation to be expanded