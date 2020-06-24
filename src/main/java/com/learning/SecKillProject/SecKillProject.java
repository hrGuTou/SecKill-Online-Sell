package com.learning.SecKillProject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


/*
* 	Restful API
*
* 	For user account:
* 		login: POST method to /api/authentication/login
* 				@param: input: JSON {"userId":"{id}"
* 									"password":"{password}"}
* 						return JSON {"userNickname":"{userNickname}"
* 									"token":"{token}"}
*	 									if error occurs:
* 										{"error":"{error message}"}
*
* 		register: POST method to /api/user/register
* 				@param input: JSON {"userId":"{id}",
* 									"nickname":"{nickname}",
* 									"password":"{password}"}
* 						return: "OK"
* 									if error occurs:
* 									{"error":"error message"}
*
*	For all merchandises
* 		GET method to /api/merchandise/all
* 			return JSON {"Merchandise":[array of merchandises]}
*
*	Before Seckill event, load merchandises into redis
* 		GET method to /api/merchandise/loadRedis
* 			return "ok"
*
* 	For ordering
* 		POST method to api/order
* 			add token to header {"token","{token}"}
* 				if error occurs:
* 				{"error":"error message"}
*			@param input: JSON {"merchandise_id":"{id}"}
*
* 					return: if in stock: {"success":"Order initialized..."}
* 							else {"error":"Out of stock"}
*
* */

@SpringBootApplication
@EnableAsync
@MapperScan("com.learning.SecKillProject.mapper")
public class SecKillProject {

	public static void main(String[] args) {
		SpringApplication.run(SecKillProject.class, args);
	}

}
