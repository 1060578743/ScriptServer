package com.lym.dao;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.lym.model.ScriptOption;
import com.lym.util.FileUtil;

public class ScriptOptionDao {
	private static final String OPTION_FILE = "options.txt";
	// ÿ�α������ݵļ��
	private static final long SAVE_TIME = 5 * 1000 * 60;
	private static List<ScriptOption> options;
	private static long nextSaveTime = 0;
	static {
		try {
			read();
		} catch (Exception e) {
		}
	}

	public static void read() {
		options = new ArrayList<ScriptOption>();
		String allData[] = FileUtil.readAllLines(OPTION_FILE);
		Gson gson = new Gson();
		for (String data : allData) {
			try {
				options.add(gson.fromJson(data, ScriptOption.class));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized static void save() {
		if (System.currentTimeMillis() < nextSaveTime) {
			return;
		}
		nextSaveTime = SAVE_TIME + System.currentTimeMillis();
		saveNow();
	}

	public static void saveNow() {
		// ����
		Gson gson = new Gson();
		StringBuilder sb = new StringBuilder();
		for (ScriptOption option : options) {
			sb.append(gson.toJson(option));
			sb.append("\n");
		}
		FileUtil.write(OPTION_FILE, sb.toString());
	}

	// ���»�����ӽű�����
	public void addOrUpdate(ScriptOption option) {
		for (ScriptOption o : options) {
			if (o.getScriptId() != option.getScriptId()) {
				continue;
			}
			if (o.getDeviceId() != option.getDeviceId()) {
				continue;
			}
			options.remove(o);
			break;
		}
		options.add(option);
		save();
	}

	// �����豸id�Լ��ű�id��������
	public ScriptOption getOption(int deviceId, int scriptId) {
		for (ScriptOption option : options) {
			if (option.getScriptId() != scriptId) {
				continue;
			}
			if (option.getDeviceId() != deviceId) {
				continue;
			}
			return option;
		}
		return null;
	}
}
