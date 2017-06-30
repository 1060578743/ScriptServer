package com.lym.dao;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.lym.model.ScriptInfo;
import com.lym.util.FileUtil;

public class ScriptInfoDao {
	private static ArrayList<ScriptInfo> scripts;
	private static final String SCRIPT_FILE = "scripts.txt";
	// ÿ�α������ݵļ��
	private static final long SAVE_TIME = 5 * 1000 * 60;
	private static long nextSaveTime = 0;
	static {
		read();
	}

	public static void read() {
		scripts = new ArrayList<ScriptInfo>();
		String allData[] = FileUtil.readAllLines(SCRIPT_FILE);
		Gson gson = new Gson();
		for (String data : allData) {
			try {
				scripts.add(gson.fromJson(data, ScriptInfo.class));
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
		for (ScriptInfo s : scripts) {
			sb.append(gson.toJson(s));
			sb.append("\n");
		}
		FileUtil.write(SCRIPT_FILE, sb.toString());
	}

	// �г����нű���Ϣ
	public List<ScriptInfo> list() {
		return scripts;
	}

	// ��ӽű���Ϣ
	public void addOrUpdate(ScriptInfo scriptInfo) {
		scripts.add(scriptInfo);
		saveNow();
	}

	// ɾ���ű���Ϣ
	public void deleteScriptInfo(ScriptInfo scriptInfo) {
		scripts.remove(scriptInfo);
		saveNow();
	}
}
