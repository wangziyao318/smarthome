package com.bupt.smarthome.vo.highchart;

import java.util.ArrayList;

public class YAxis {
	private Title title;
	private double min;
	private double max;
	private ArrayList<PlotLines> plotLines;

	public YAxis(Title title, double min, double max) {
		this.title = title;
		this.min = min;
		this.max = max;
		plotLines = new ArrayList<>();
		plotLines.add(new PlotLines(min, 1, "#808080"));
		plotLines.add(new PlotLines(max, 1, "#808080"));
	}

	public Title getTitle() {
		return title;
	}

	public double getMin() {
		return min;
	}

	public double getMax() {
		return max;
	}

	public ArrayList<PlotLines> getPlotLines() {
		return plotLines;
	}
}
