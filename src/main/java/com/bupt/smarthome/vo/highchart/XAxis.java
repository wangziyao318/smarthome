package com.bupt.smarthome.vo.highchart;

public class XAxis {
	private Title title;
	private String type = "datetime";
	private DateTimeLabelFormats dateTimeLabelFormats = new DateTimeLabelFormats("%e. %b");

	public XAxis(Title title) {
		this.title = title;
	}

	public Title getTitle() {
		return title;
	}

	public String getType() {
		return type;
	}

	public DateTimeLabelFormats getDateTimeLabelFormats() {
		return dateTimeLabelFormats;
	}
}
