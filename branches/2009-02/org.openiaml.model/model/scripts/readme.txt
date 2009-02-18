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

$ make-diff root

Makes ../diff/root.gmfgen.diff.
you should do this when the .gmfgen DOES NOT have the OpenDiagramPolicies
in.

---
Process to make diffs:

1. Complete all .gmfgen's and commit them to SVN. (This is what you want
   .gmfgen's to be.)

2. $ make-diff-copies
   This copies all the originals to /copies/.

3. In GMF, regenerate the .gmfgen's from .gmfmap's.
   These new .gmfgen's will NOT have OpenDiagramPolicies in them.

4. $ make-diff [name]
   For each .gmfgen, this will create the diff in /diff/.

5. $ apply [name]
   As a test, this will apply the diff to the current file without Policies,
   and makes sure they are in.

   If everything has been done right, you will only have to commit the diffs,
   and not the original .gmfgens (which have been reverted).

---
Development process of modifying the ECore model:

- edit .ecore
- reload .genmodel
- generate Model code
- generate Edit code
- organise Model imports
- open .gmfmap
- [edit gmfmap]
- generate .gmfgen
- $ apply root
- refresh root.gmfgen
- generate diagram code

(TODO put this into the Wiki.)
