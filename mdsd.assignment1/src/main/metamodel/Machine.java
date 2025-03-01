package main.metamodel;

import java.util.List;
import java.util.Map;

public class Machine {

	private List<State> states;
	private State initialState;
	private Map<String, Integer> variables;

	public Machine(List<State> states, State initialState, Map<String, Integer> variables) {
		this.states = states;
		this.initialState = initialState;
		this.variables = variables;
	}

	public List<State> getStates() {
		return states;
	}

	public State getInitialState() {
		return initialState;
	}

	public State getState(String string) {
		return states.stream().filter(state -> state.getName().equals(string))
				.findFirst()
				.orElse(null);
	}

	public int numberOfIntegers() {
		return variables.size();
	}

	public int getInteger(String string){
		return variables.get(string);
	}

	public void setInteger(String string, int i){
		variables.put(string,i);
	}
	public boolean hasInteger(String string) {
		return variables.containsKey(string);
	}
}

