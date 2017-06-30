package com.lym.server;

import java.util.ArrayList;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.google.gson.Gson;
import com.lym.dao.DeviceDao;
import com.lym.dao.ScriptInfoDao;
import com.lym.dao.ScriptOptionDao;
import com.lym.dao.UserDao;
import com.lym.model.Device;
import com.lym.model.Message;
import com.lym.model.ScriptInfo;
import com.lym.model.ScriptOption;
import com.lym.model.User;

public class MinaHandler implements IoHandler {
	private ArrayList<IoSession> sessions = new ArrayList<IoSession>();
	DeviceDao mDeviceDao = new DeviceDao();
	ScriptInfoDao mScriptInfoDao = new ScriptInfoDao();
	ScriptOptionDao mScriptOptionDao = new ScriptOptionDao();
	UserDao mUserDao = new UserDao();
	Gson mGson = new Gson();

	public void exceptionCaught(IoSession session, Throwable arg1)
			throws Exception {
		// 异常
	}

	public void inputClosed(IoSession session) throws Exception {
		// 输入关闭
		session.closeNow();

	}

	public void messageReceived(IoSession session, Object msg) throws Exception {
		Message message = mGson.fromJson((String) msg, Message.class);

		switch (message.getType()) {
		case Message.TYPE_DEVICE_CONNECT: {
			// 设备连接
			Device device = mGson.fromJson(message.getContent(), Device.class);
			mDeviceDao.add(device);
			session.setAttribute("device", device.getDevieId());
			session.write(mGson.toJson(message));
			break;
		}
		case Message.TYPE_DEVICE_LIST: {
			// 设备请求列表
			message.setContent(mGson.toJson(mDeviceDao.list()));
			session.write(mGson.toJson(message));
			break;
		}
		case Message.TYPE_LOGIN: {
			// 用户登陆
			User user = mGson.fromJson(message.getContent(), User.class);
			message.setContent(String.valueOf(mUserDao.login(user)));
			session.write(mGson.toJson(message));
			break;
		}
		case Message.TYPE_SAVE_OPTION: {
			// 保存配置
			ScriptOption option = mGson.fromJson(message.getContent(),
					ScriptOption.class);
			mScriptOptionDao.addOrUpdate(option);
			message.setContent(String.valueOf(true));
			session.write(mGson.toJson(message));
			break;
		}

		case Message.TYPE_READ_OPTION: {
			// 读取配置
			ScriptOption option = mGson.fromJson(message.getContent(),
					ScriptOption.class);
			option = mScriptOptionDao.getOption(option.getDeviceId(),
					option.getScriptId());
			message.setContent(mGson.toJson(option));
			session.write(mGson.toJson(message));
			break;
		}
		case Message.TYPE_REBOOT: {
			// 重启手机
			IoSession s = getSession(message.getTo());
			if (s != null) {
				s.write(mGson.toJson(message));
			}
			break;
		}
		case Message.TYPE_SCRIPT_LIST: {
			// 脚本列表
			message.setContent(mGson.toJson(mScriptInfoDao.list()));
			session.write(mGson.toJson(message));
			break;
		}
		case Message.TYPE_START_SCRIPT: {
			// 启动脚本
			IoSession s = getSession(message.getTo());
			if (s != null) {
				s.write(mGson.toJson(message));
			}
			break;
		}
		case Message.TYPE_STOP_SCRIPT: {
			// 停止脚本
			IoSession s = getSession(message.getTo());
			if (s != null) {
				s.write(mGson.toJson(message));
			}
			break;
		}
		case Message.TYPE_UPLOAD_SCRIPT: {
			// 上传脚本
			ScriptInfo scriptInfo = mGson.fromJson(message.getContent(),
					ScriptInfo.class);
			mScriptInfoDao.addOrUpdate(scriptInfo);
			break;
		}
		case Message.TYPE_DELETE_SCRIPT: {
			// 删除脚本
			ScriptInfo scriptInfo = mGson.fromJson(message.getContent(),
					ScriptInfo.class);
			mScriptInfoDao.deleteScriptInfo(scriptInfo);
			break;
		}

		}

	}

	private IoSession getSession(String deviceId) {
		for (int i = 0; i < sessions.size(); i++) {
			IoSession s = sessions.get(i);
			String id = (String) s.getAttribute("device");
			if (deviceId.equals(id)) {
				return s;
			}
		}
		return null;
	}

	public void messageSent(IoSession session, Object msg) throws Exception {

	}

	public void sessionClosed(IoSession session) throws Exception {
		// 连接关闭
		sessions.remove(session);
	}

	public void sessionCreated(IoSession session) throws Exception {

	}

	public void sessionIdle(IoSession session, IdleStatus arg1)
			throws Exception {

	}

	public void sessionOpened(IoSession session) throws Exception {
		sessions.add(session);
	}

}
