/*
package com.springboot.reactive.resource;

import com.springboot.reactive.model.Employee;
import com.springboot.reactive.model.EmployeeEvent;
import com.springboot.reactive.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import javax.validation.Valid;
import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

@RestController
@RequestMapping("/rest/employee")
public class EmployeeResource {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/all")
    public Flux<Employee> getAll(){
     return   employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Employee> getId(@PathVariable("id") final String empid){
        return employeeRepository.findById(empid);
    }

    @GetMapping(value = "/{id}/events",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<EmployeeEvent> getEvents(@PathVariable("id") final String empid){
     return  employeeRepository.findById(empid).flatMapMany(employee -> {
           Flux<Long> interval=Flux.interval(Duration.ofSeconds(2));
           Flux<EmployeeEvent> employeeEventFlux=Flux.fromStream(
                   Stream.generate(()->new EmployeeEvent(employee,new Date()))
           );
           return Flux.zip(interval, employeeEventFlux)
                   .map(Tuple2::getT2);
       });

    }

  @PostMapping("/save")
    public Mono<Employee> createEmployees(@Valid @RequestBody Employee employee){
       return employeeRepository.save(employee);
  }

  @PutMapping("/update/{id}")
    public Mono<ResponseEntity<Employee>>  updateEmployeeById(@PathVariable(value="id") String empId,@Valid @RequestBody Employee employee){
        return employeeRepository.findById(empId)
                .flatMap(existingEmployee->{
            existingEmployee.setName(employee.getName());
                    existingEmployee.setSalary(employee.getSalary());
                    return employeeRepository.save(existingEmployee);
        })
                .map(updatedEmployee-> ResponseEntity.ok(updatedEmployee))
                .defaultIfEmpty(ResponseEntity.badRequest().build());

    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Void>> deleteEmployeeById(@PathVariable(value="id") String empId) {
        return employeeRepository.findById(empId)
                .flatMap(existingEmployee->
                        employeeRepository.delete(existingEmployee)
                .then(Mono.just(ResponseEntity.ok().<Void>build())))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
*/
