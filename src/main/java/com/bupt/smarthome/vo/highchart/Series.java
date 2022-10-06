package com.bupt.smarthome.vo.highchart;

import java.util.ArrayList;

public class Series {
	private String name;
	private ArrayList<ArrayList<Double>> data;

	public Series(String name, ArrayList<ArrayList<Double>> data) {
		this.name = name;
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public ArrayList<ArrayList<Double>> getData() {
		return data;
	}
}
