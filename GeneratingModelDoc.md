# Introduction #

[ModelDoc](ModelDoc.md) is the under-development documentation solution for the IAML language. You can browse the most recently generated documentation at http://openiaml.org/model/.

This page will briefly discuss solutions to common problems in the future.

## testAllModelLinksAreValid ##

Error messages from this failing ModelDoc test case:

```
Found some @model elements that could not be resolved: [Editable, Form, NavigateWire, DataFlowEdgeSource, OperationCall, RunInstanceWire] expected:<0> but was:<6>
```

In order to find these, you should search the workspace (files `*.java *.drl *.xpt *.js *.html *.php *.ext`) for the `@model XXX` pattern. If they occur within generated model code, you should modify the Ecore directly.