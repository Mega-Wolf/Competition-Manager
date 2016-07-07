package gui.controller;

import network.Operand;
import network.Operation;

public class SendData {
	private final Operation operation;
	private final Operand operand;
	private final Object object;
	
	private Object returnObject;
	
	public SendData(Operation operation, Operand operand, Object object){
		this.operation = operation;
		this.operand = operand;
		this.object = object;
	}
	
	public Operation getOperation() {
		return operation;
	}
	
	public Operand getOperand() {
		return operand;
	}
	
	public Object getObject() {
		return object;
	}
	
	public void setReturnValue(Object returnObject) {
		this.returnObject = returnObject;
	}
	
	public Object getReturnValue() {
		return returnObject;
	}
	
}
