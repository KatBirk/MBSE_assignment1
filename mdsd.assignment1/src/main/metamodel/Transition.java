package main.metamodel;

public class Transition{
	private String event;
	private State target;
	private String operationType;
	private String operationVariable;
	private Integer operationValue;
	private String conditionType;
	private String conditionVariable;
	private Integer conditionValue;

	public Transition(String event) {
		this.event = event;
	}

	public Object getEvent() {
		return event;
	}

	public void setEvent(String event){
		this.event = event;
	}

	public void setTarget(State targetState){
		target = targetState;
	}
	public State getTarget() {
		return target;
	}

	public boolean hasSetOperation() {
		return "set".equals(operationType);
	}

	public boolean hasIncrementOperation() {
		return "increment".equals(operationType);
	}

	public boolean hasDecrementOperation() {
		return "decrement".equals(operationType);
	}

	public void setOperation(String type, int value, String name){
		operationValue = value;
		operationType = type;
		operationVariable = name;
	}

	public int getOperationValue(){
		return operationValue;
	}
	public String getOperationVariableName(){
		return operationVariable;
	}
	public void setOperationVariableName(String name){
		operationVariable = name;
	}
	public boolean isConditional() {
		return conditionVariable != null;
	}

	public Object getConditionVariableName() {
		return conditionVariable;
	}

	public void setCondition(int value, String name, String type){
		conditionValue = value;
		conditionVariable = name;
		conditionType = type;
	}
	public Integer getConditionComparedValue() {
		return conditionValue;
	}

	public boolean isConditionEqual() {
		return "equal".equals(conditionType);
	}

	public boolean isConditionGreaterThan() {
		return "greater".equals(conditionType);
	}

	public boolean isConditionLessThan() {
		return "less".equals(conditionType);
	}

	public boolean hasOperation() {
		return operationVariable != null;
	}

}
