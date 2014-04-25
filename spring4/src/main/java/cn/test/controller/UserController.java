package cn.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.test.model.User;
/**
 * 用户控制器
 * 使用了RestController ：
 * 		1. 方法可以直接返回json，不用再设置 @ResponseBody ，更加方便
 * @author admin
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {
	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	public User getUser(){
		User u = new User();
		u.setHead("http://head.jpg");
		u.setName("superman");
		return u;
	}
}
