package com.example.controller;

import com.example.dao.AmapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Description: get和post请求测试controller
 * 
 * @author JourWon
 * @date Created on 2018年4月19日
 */
@RestController
public class HelloWorldController {

	@Autowired
	private AmapClient amapClient;

	@GetMapping("/get")
	public Map get() throws InterruptedException {
		Map location = amapClient.getLocation(1);
		return location;
	}

}