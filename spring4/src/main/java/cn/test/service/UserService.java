package cn.test.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {
	public UserService(){
		System.out.println("init UserService");
	}
	public void save() {
		System.out.println("save UserService");
	}
}
