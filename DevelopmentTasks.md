# Common Tasks #

This page will ideally have information on how to achieve various common development tasks.

## Adding a new model element ##

  1. Define what the model element will help achieve
  1. Add the model element to the .ecore
    1. Regenerate model, edit code
    1. Run test cases to ensure model structure is still valid
  1. Add **runtime** test cases to identify what the new element will do
    1. First, add runtime test cases (_.tests.codegen_)
    1. Add test cases to CodegenTestsSuite
  1. Add the model element to graphical editors
    1. Edit .gmfgraph, .gmfmap, .gmfgen (where necessary)
    1. Regenerate editors and re-run test cases
  1. Using these new editors, create the sample models for the **runtime** test cases above
  1. Add **inference** test cases (_.tests.inference_)
  1. Add inference rules (_.drools_) to satisfy the **inference** test cases
  1. Modify OAW code generation rules to satisfy the **runtime** test cases

If the development of the **runtime** code generation rules is getting to complex, break the problem into smaller models. For each of these models, make an inference test case; once this test case passes, make a runtime test case.

From [readme.txt r464](http://code.google.com/p/iaml/source/browse/trunk/org.openiaml.model/model/scripts/readme.txt?spec=svn538&r=464):

  1. edit .ecore
  1. reload .genmodel
  1. generate Model code
  1. generate Edit code
  1. organise Model imports
  1. open .gmfmap
  1. [gmfmap](edit.md)
  1. generate .gmfgen
  1. $ apply root
  1. refresh root.gmfgen
  1. generate diagram code

Other development tasks still to document:

  1. Creating a new graphical editor
  1. Model migration: creating new model versions
  1. [Running test cases](DevelopmentTests.md)
  1. [Releasing and publishing a new version](ReleaseTasks.md)