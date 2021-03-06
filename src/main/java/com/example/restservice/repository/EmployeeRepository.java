package com.example.restservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restservice.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}