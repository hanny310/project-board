package com.fast.projectboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan//사용자가 만든 config를 인식하기 위해 필요(thymeleaf config 파일 내 thymeleaf3)
@SpringBootApplication
public class ProjectBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectBoardApplication.class, args);
	}

}



