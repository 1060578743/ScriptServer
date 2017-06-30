package com.lym.model;

public class ScriptOption {
	// 脚本id：即ScriptInfo的id
	private int scriptId;
	// 设备id
	private int deviceId;

	private String option;


	public int getScriptId() {
		return scriptId;
	}

	public void setScriptId(int scriptId) {
		this.scriptId = scriptId;
	}

	public int getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}
}
