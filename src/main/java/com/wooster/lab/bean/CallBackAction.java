package com.wooster.lab.bean;

/**
 * Call back request object
 * @author pta200
 *
 */
public class CallBackAction {
	
	private String toNumber;
	private String fromNumber;
	public String getToNumber() {
		return toNumber;
	}
	public void setToNumber(String toNumber) {
		this.toNumber = toNumber;
	}
	public String getFromNumber() {
		return fromNumber;
	}
	public void setFromNumber(String fromNumber) {
		this.fromNumber = fromNumber;
	}
	@Override
	public String toString() {
		return "CallBackAction [getFromNumber()=" + getFromNumber() + ", getToNumber()=" + getToNumber()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ ", fromNumber=" + fromNumber + ", toNumber=" + toNumber + "]";
	}
	
	
	

}
