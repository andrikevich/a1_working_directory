package by.a1.andrikevich.stu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
public class StuApplication {

	public static void main(String[] args) {

		SpringApplication.run(StuApplication.class, args);
	}

}
