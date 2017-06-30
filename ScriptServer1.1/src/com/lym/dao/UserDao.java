package com.lym.dao;

import com.lym.model.User;

public class UserDao {
	private static User admin;
	static {
		admin = new User();
		admin.setName("admin");
		admin.setPassword("jydh3353255");
	}

	public boolean login(User user) {
		if (!admin.getName().equals(user.getName())) {
			return false;
		}
		if (!admin.getPassword().equals(user.getPassword())) {
			return false;
		}
		return true;
	}
}
