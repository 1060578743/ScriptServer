package com.lym.model;

public class Device {
	public static int TYPE_START;
	public static int TYPE_STOP;
	// �豸id
	private String devieId;
	// �豸���еĽű�
	private int scriptId;
	// ����״̬
	private int state;

	public String getDevieId() {
		return devieId;
	}

	public int getScriptId() {
		return scriptId;
	}

	public int getState() {
		return state;
	}

	public void setDevieId(String devieId) {
		this.devieId = devieId;
	}

	public void setScriptId(int scriptId) {
		this.scriptId = scriptId;
	}

	public void setState(int state) {
		this.state = state;
	}
}
