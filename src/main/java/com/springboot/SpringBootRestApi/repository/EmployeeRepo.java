package com.springboot.SpringBootRestApi.repository;

import com.springboot.SpringBootRestApi.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee , Long> {
}
