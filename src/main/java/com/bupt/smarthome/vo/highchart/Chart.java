package com.bupt.smarthome.vo.highchart;

public class Chart {
	private String type;
	private String zoomType = "x";

	public Chart(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public String getZoomType() {
		return zoomType;
	}
}
