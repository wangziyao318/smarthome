package com.bupt.smarthome.vo.highchart;

public class Tooltip {
	private String headerFormat;
	private String pointFormat;

	public Tooltip(String headerFormat, String pointFormat) {
		this.headerFormat = headerFormat;
		this.pointFormat = pointFormat;
	}

	public String getHeaderFormat() {
		return headerFormat;
	}

	public String getPointFormat() {
		return pointFormat;
	}
}
