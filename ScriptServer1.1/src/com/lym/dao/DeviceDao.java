package com.lym.dao;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.lym.model.Device;
import com.lym.util.FileUtil;

public class DeviceDao {
	private static ArrayList<Device> devices;
	private static final String DEVICE_FILE = "devices.txt";
	// 每次保存数据的间隔
	private static final long SAVE_TIME = 5 * 1000 * 60;
	private static long nextSaveTime = 0;
	static {
		try {
			read();
		} catch (Exception e) {
		}
	}

	public static void read() {
		devices = new ArrayList<Device>();
		String allData[] = FileUtil.readAllLines(DEVICE_FILE);
		Gson gson = new Gson();
		for (String data : allData) {
			try {
				devices.add(gson.fromJson(data, Device.class));
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
		// 保存
		Gson gson = new Gson();
		StringBuilder sb = new StringBuilder();
		for (Device d : devices) {
			sb.append(gson.toJson(d));
			sb.append("\n");
		}
		FileUtil.write(DEVICE_FILE, sb.toString());
	}

	public List<Device> list() {
		return devices;
	}

	public void delete(Device device) {
		devices.remove(device);
		save();
	}

	public void add(Device device) {
		for (Device d : devices) {
			if (d.getDevieId().equals(device.getDevieId())) {
				delete(d);
				break;
			}
		}
		devices.add(device);
		save();
	}

	public Device getDevice(String id) {
		for (Device device : devices) {
			if (device.getDevieId().equals(id)) {
				return device;
			}
		}
		return null;
	}
}
