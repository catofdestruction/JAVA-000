package org.xy.mysqldemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.xy.mysqldemo.persistence.PersistenceConfiguration;

@SpringBootApplication
@Import({PersistenceConfiguration.class})
public class MysqlDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MysqlDemoApplication.class, args);
	}

}
