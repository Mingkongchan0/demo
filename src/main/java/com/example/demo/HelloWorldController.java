package com.example.demo;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class HelloWorldController 
{
@RequestMapping("/hello")
@ResponseBody
	//Get
	public String sayHello()
	{
		return "Hello World!!!!";
	}

}
