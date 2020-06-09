package com.learning.SecKillProject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


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
*	Main Page:
* 		Get method to /api/site/main
* 			add token to header {"token","{token}"}
* 				if error occurs:
* 				{"error":"error message"}
*
* */

@SpringBootApplication
@MapperScan("com.learning.SecKillProject.mapper")
public class SecKillProject {

	public static void main(String[] args) {
		SpringApplication.run(SecKillProject.class, args);
	}

}
