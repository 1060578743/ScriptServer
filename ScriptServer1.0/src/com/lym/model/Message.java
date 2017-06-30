package com.lym.model;

/**
 * ��Ϣ��
 * 
 * @author Administrator
 * 
 */
public class Message {
	// ����Ա��½
	public static final int TYPE_LOGIN = 1;
	// �豸�б�(�豸�յ�����Ϣ��ʾ�յ��豸�б���Ϣ;���������յ������ʾ�յ��豸������)
	public static final int TYPE_DEVICE_LIST = 2;
	// �����ֻ�
	public static final int TYPE_REBOOT = 3;
	// �����ű�
	public static final int TYPE_START_SCRIPT = 4;
	// ֹͣ�ű�
	public static final int TYPE_STOP_SCRIPT = 5;
	// ���ýű�
	public static final int TYPE_SAVE_OPTION = 6;
	// �ű��б�
	public static final int TYPE_SCRIPT_LIST = 7;
	// �豸����
	public static final int TYPE_DEVICE_CONNECT = 8;
	// ��ȡ����
	public static final int TYPE_READ_OPTION = 9;
	// ɾ���ű�
	public static final int TYPE_DELETE_SCRIPT = 10;
	// �ϴ��ű�
	public static final int TYPE_UPLOAD_SCRIPT = 11;
	// ��Ϣ����
	private int type;

	// ��Ϣ���͵�user
	private String to;

	// ����
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
