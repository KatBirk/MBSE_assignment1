package main.metamodel;

import java.util.ArrayList;
import java.util.List;

public class State {

	private String name;
	private List<Transition> transitions;

	public State(String name) {
		this.name = name;
		transitions = new ArrayList<>();
	}

	public State(String name, List<Transition> transitions) {
		this.name = name;
		this.transitions = transitions;
	}

	public String getName() {
		return name;
	}

	public List<Transition> getTransitions() {
		return transitions;
	}

	public Transition getTransitionByEvent(String string) {
		return transitions.stream()
				.filter(transition -> transition.getEvent().equals(string))
				.findFirst()
				.orElse(null);
	}
}
