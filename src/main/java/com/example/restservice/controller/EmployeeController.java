package com.example.restservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservice.models.Employee;
import com.example.restservice.services.QuoteService;
import com.example.restservice.repository.EmployeeRepository;
import com.example.restservice.utilities.EmployeeNotFoundException;

@RestController
public class EmployeeController {

	 private final EmployeeRepository repository;
	 
	 @Autowired
	 private final QuoteService quoteService;

	 EmployeeController(EmployeeRepository repository) {
		this.quoteService = new QuoteService();
		this.repository = repository;
	  }

	 @GetMapping("/health")
	 public String health() {
			return "STATUS: OK";
		}

	 @GetMapping("/get_quote")
	 public String get_quote() {
		 return (quoteService.getQuote());
		}

	  // Aggregate root
	  // tag::get-aggregate-root[]
	  @GetMapping("/employees")
	  
	  List<Employee> all() {
	    return repository.findAll();
	  }
	  // end::get-aggregate-root[]

	  @PostMapping("/employees")
	  Employee newEmployee(@RequestBody Employee newEmployee) {
	    return repository.save(newEmployee);
	  }

	  // Single item
	  
	  @GetMapping("/employees/{id}")
	  Employee one(@PathVariable Long id) {
	    
	    return repository.findById(id)
	      .orElseThrow(() -> new EmployeeNotFoundException(id));
	  }

	  @PutMapping("/employees/{id}")
	  Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
	    
	    return repository.findById(id)
	      .map(employee -> {
	        employee.setName(newEmployee.getName());
	        employee.setRole(newEmployee.getRole());
	        return repository.save(employee);
	      })
	      .orElseGet(() -> {
	        newEmployee.setId(id);
	        return repository.save(newEmployee);
	      });
	  }

	  @DeleteMapping("/employees/{id}")
	  void deleteEmployee(@PathVariable Long id) {
	    repository.deleteById(id);
	  }
}
