package com.lym.model;

/**
 * 消息类
 * 
 * @author Administrator
 * 
 */
public class Message {
	// 管理员登陆
	public static final int TYPE_LOGIN = 1;
	// 设备列表(设备收到该消息表示收到设备列表信息;而服务器收到，则表示收到设备的请求)
	public static final int TYPE_DEVICE_LIST = 2;
	// 重启手机
	public static final int TYPE_REBOOT = 3;
	// 启动脚本
	public static final int TYPE_START_SCRIPT = 4;
	// 停止脚本
	public static final int TYPE_STOP_SCRIPT = 5;
	// 配置脚本
	public static final int TYPE_SAVE_OPTION = 6;
	// 脚本列表
	public static final int TYPE_SCRIPT_LIST = 7;
	// 设备连接
	public static final int TYPE_DEVICE_CONNECT = 8;
	// 读取配置
	public static final int TYPE_READ_OPTION = 9;
	// 删除脚本
	public static final int TYPE_DELETE_SCRIPT = 10;
	// 上传脚本
	public static final int TYPE_UPLOAD_SCRIPT = 11;
	// 消息类型
	private int type;

	// 消息发送到user
	private String to;

	// 内容
	private String content;

	public String getContent() {
		return content;
	}

	public String getTo() {
		return to;
	}

	public int getType() {
		return type;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public void setType(int type) {
		this.type = type;
	}

}
