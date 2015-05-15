# Introduction #

The IAML language supports an expressive language for describing the semantics of operations and conditions, called **Operational Modelling**. The syntax has been inspired by [UML 2.0 Activity Diagrams](http://www.uml.org/), however (unlike UML) this approach can be generated to code in PHP and Javascript.



# Overview #

This diagram illustrates all of the operational modelling primitives available in IAML ([source model](http://iaml.googlecode.com/svn/trunk/org.openiaml.model.tests/src/org/openiaml/model/tests/codegen/model0_5_1/OperationalExample.iaml)).

![http://iaml.googlecode.com/svn/wiki/opmodel/OperationalExample.png](http://iaml.googlecode.com/svn/wiki/opmodel/OperationalExample.png)

## Elements ##

  1. [StartNode](http://openiaml.org/model/StartNode)
  1. [CancelNode](http://openiaml.org/model/CancelNode)
  1. [FinishNode](http://openiaml.org/model/FinishNode)
  1. [DecisionNode](http://openiaml.org/model/DecisionNode)
  1. [SplitNode](http://openiaml.org/model/SplitNode)
  1. [JoinNode](http://openiaml.org/model/JoinNode)
  1. [OperationCallNode](http://openiaml.org/model/OperationCallNode)
  1. [Arithmetic](http://openiaml.org/model/Arithmetic)
  1. [CastNode](http://openiaml.org/model/CastNode)
  1. [TemporaryVariable](http://openiaml.org/model/TemporaryVariable)
  1. [Parameter](http://openiaml.org/model/Parameter)
  1. [StaticValue](http://openiaml.org/model/StaticValue)
  1. [DataFlowEdge](http://openiaml.org/model/DataFlowEdge)
  1. [ExecutionEdge](http://openiaml.org/model/ExecutionEdge)
  1. [ParameterEdge](http://openiaml.org/model/ParameterEdge)
  1. [RunAction](http://openiaml.org/model/RunAction)
  1. [ConditionEdge](http://openiaml.org/model/ConditionEdge)

# Comparison: UML #

These modelling primitives can be compared to a UML Activity Diagram equivalent:

![http://iaml.googlecode.com/svn/wiki/opmodel/UML.png](http://iaml.googlecode.com/svn/wiki/opmodel/UML.png)

# Comparison: Java #

We can also consider this operation as the following Java code:

```
public void compositeOperation(String input) {

  try {
    Integer.valueOf(input);
  } catch (NumberFormatException e) {
    throw new RuntimeException("Input is not an integer.");
  }

  final IntegerWrapper temp = new IntegerWrapper(Integer.valueOf(input));

  BlockingRunnable t1 = new BlockingRunnable() {
    @Override
    public void runActual() {
      // empty
    }     
  };
  BlockingRunnable t2 = new BlockingRunnable() {
    @Override
    public void runActual() {
      temp.setValue( temp.getValue() + 1 );
    }
  };

  new Thread(t1).start();
  new Thread(t2).start();
  while (!(t1.isFinished() && t2.isFinished())) {
    Thread.yield();
  }

  if (shouldUpdateResult()) {
    getLabel("Target").update(temp.getValue());
    return;
  } else {
    return;
  }

}
```