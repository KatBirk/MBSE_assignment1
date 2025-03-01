package main;

import main.metamodel.Machine;
import main.metamodel.State;
import main.metamodel.Transition;

import java.util.*;

public class StateMachine {

	private final List<State> states = new ArrayList<>();
	private final Map<String, Integer> variables = new HashMap<>();
	private State initialState;
	private State currentState;
	private Transition currentTransition;

	public Machine build() {
		return new Machine(states,initialState,variables);
	}

	public StateMachine state(String string) {
        Optional<State> state = states.stream()
				.filter(s -> s.getName().equals(string))
				.findFirst();

		if (state.isPresent()){
			currentState = state.get();
		}
		else{
			currentState = new State(string);
			states.add(currentState);
		}
		return this;
	}

	public StateMachine initial() {
		if (currentState != null) {
			this.initialState = currentState;
		}
		return this;
	}

	public StateMachine when(String string) {
		currentTransition = new Transition(string);
		currentTransition.setEvent(string);
		currentState.getTransitions().add(currentTransition);
		return this;
	}

	public StateMachine to(String string) {
		State targetState = states.stream()
				.filter(state -> state.getName().equals(string))
				.findFirst()
				.orElseGet(() -> {
					State state = new State(string);
					states.add(state);
					return state;
				});
		currentTransition.setTarget(targetState);
		return this;
	}

	public StateMachine integer(String string) {
		variables.put(string,0);
		return this;
	}

	public StateMachine set(String string, int i) {
		currentTransition.setOperation("set",i,string);
		return this;
	}

	public StateMachine increment(String string) {
		int value = variables.get(string);
		currentTransition.setOperation("increment",value+1,string);
		return this;
	}

	public StateMachine decrement(String string) {
		int value = variables.get(string);
		currentTransition.setOperation("decrement",value-1,string);
		return this;
	}

	public StateMachine ifEquals(String string, int i) {
		currentTransition.setCondition(i,string,"equal");
		return this;
	}

	public StateMachine ifGreaterThan(String string, int i) {
		currentTransition.setCondition(i,string,"greater");
		return this;
	}

	public StateMachine ifLessThan(String string, int i) {
		currentTransition.setCondition(i,string,"less");
		return this;
	}

}
