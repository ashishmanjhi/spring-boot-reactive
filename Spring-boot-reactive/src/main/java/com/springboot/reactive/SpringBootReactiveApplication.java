package com.springboot.reactive;

import com.springboot.reactive.model.Employee;
import com.springboot.reactive.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class SpringBootReactiveApplication {

	@Bean
	CommandLineRunner employees(EmployeeRepository employeeRepository){
		return args->{
			employeeRepository.deleteAll()
					.subscribe(null,null,()->{
				Stream.of(new Employee(UUID.randomUUID().toString(),"Ashish",27000L),
						new Employee(UUID.randomUUID().toString(),"Suyash",90000L),
						new Employee(UUID.randomUUID().toString(),"Pratyush",20000L),
						new Employee(UUID.randomUUID().toString(),"Chand",40000L)).forEach(employee -> {
							employeeRepository.save(employee)
									.subscribe(System.out::println);
				});
			});
		};
	}

	public static void main(String[] args) {

		SpringApplication.run(SpringBootReactiveApplication.class, args);
	}

}
