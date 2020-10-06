package com.jocodes.microservices.limitsservice.bean;

public class LimitConfiguration {

	private int maximum;
	private int minimum;

	protected LimitConfiguration() {
		super();

	}

	public LimitConfiguration(int maximum, int minimum) {
		super();
		this.maximum = maximum;
		this.minimum = minimum;
	}

	public int getMinimum() {
		return minimum;
	}

	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}

	public int getMaximum() {
		return maximum;
	}

	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}

}
