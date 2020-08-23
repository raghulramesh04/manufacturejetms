package ManufactureJet.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class jetapplication {
	public static void main(String[] args) {
		System.out.println("1");
		SpringApplication.run(jetapplication.class, args);
		System.out.println("2");
	}
	

}
