package com.example.cource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	@RequestMapping("/hello")
String hello(Map<String, Object> model) {
    RelativisticModel.select();
    String energy = System.getenv().get("ENERGY");
    if (energy == null) {
       energy = "12 GeV";
    }
    Amount<Mass> m = Amount.valueOf(energy).to(KILOGRAM);
    model.put("science", "E=mc^2: " + energy + " = "  + m.toString());
    return "hello";
}
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
