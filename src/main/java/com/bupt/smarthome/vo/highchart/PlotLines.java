package com.bupt.smarthome.vo.highchart;

public class PlotLines {
	private double value;
	private double width;
	private String color;

	public PlotLines(double value, double width, String color) {
		this.value = value;
		this.width = width;
		this.color = color;
	}

	public double getValue() {
		return value;
	}

	public double getWidth() {
		return width;
	}

	public String getColor() {
		return color;
	}
}
