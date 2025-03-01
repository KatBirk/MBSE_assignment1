package main;

import main.metamodel.Machine;
import main.metamodel.State;
import main.metamodel.Transition;

public class MachineInterpreter {
    private Machine machine;
    private State currentState;

    public void run(Machine m) {
        this.machine = m;
        this.currentState = m.getInitialState();

    }

    public State getCurrentState() {
        return currentState;
    }

    public void processEvent(String string) {
        for (Transition transition : currentState.getTransitions()) {
            if (transition.getEvent().equals(string)) {
                if (transition.isConditional()) {
                    int currentValue = machine.getInteger(transition.getConditionVariableName().toString());
                    boolean conditionMet = false;
                    if (transition.isConditionEqual()) {
                        conditionMet = (currentValue == transition.getConditionComparedValue());
                    } else if (transition.isConditionGreaterThan()) {
                        conditionMet = currentValue > transition.getConditionComparedValue();
                    } else if (transition.isConditionLessThan()) {
                        conditionMet = currentValue < transition.getConditionComparedValue();
                    }
                    if (!conditionMet) continue;
                }
                //process var assignment
                String varName = transition.getOperationVariableName();
                if (machine.hasInteger(varName)) {
                    if (transition.hasIncrementOperation()) {
                        int newValue = machine.getInteger(varName) + 1;
                        machine.setInteger(varName, newValue);
                    } else if (transition.hasDecrementOperation()) {
                        int newValue = machine.getInteger(varName) - 1;
                        machine.setInteger(varName, newValue);
                    } else if (transition.hasSetOperation()){
                        int operationValue = transition.getOperationValue();
                        machine.setInteger(varName,operationValue);
                    }
                }
                currentState = transition.getTarget();
                return;
            }
        }
    }

    public int getInteger(String string) {
        return machine.hasInteger(string) ? machine.getInteger(string) : 0;
    }

}
