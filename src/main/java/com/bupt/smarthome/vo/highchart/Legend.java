package com.bupt.smarthome.vo.highchart;

public class Legend {
	private String align;
	private String verticalAlign;
	private String layout;

	public Legend(String align, String verticalAlign, String layout) {
		this.align = align;
		this.verticalAlign = verticalAlign;
		this.layout = layout;
	}

	public String getAlign() {
		return align;
	}

	public String getVerticalAlign() {
		return verticalAlign;
	}

	public String getLayout() {
		return layout;
	}
}
