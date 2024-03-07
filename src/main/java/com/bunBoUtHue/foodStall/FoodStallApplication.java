package com.bunBoUtHue.foodStall;

import com.bunBoUtHue.foodStall.dto.SignUpRequest;
import com.bunBoUtHue.foodStall.service.imple.AuthServiceImpl;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FoodStallApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodStallApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AuthServiceImpl service
	) {
		return args -> {
			if (!service.exitsAdmin()) { // Kiểm tra xem admin đã tồn tại chưa
				SignUpRequest admin = new SignUpRequest();
				admin.setEmail("admin@gmail.com");
				admin.setName("Admin System");
				System.out.println("Tài khoản quản trị đã được tạo. Admin Token: " + service.createAmin(admin).getToken());
			} else {
				System.out.println("Tài khoản quản trị đã tồn tại.");
			}
		};
	}

}
