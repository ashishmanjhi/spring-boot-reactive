package com.springboot.reactive.repository;


import com.springboot.reactive.model.Employee;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface EmployeeRepository extends ReactiveMongoRepository<Employee,String> {
    Mono<Object> findByName(String empName);
}
