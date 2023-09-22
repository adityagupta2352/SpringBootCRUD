package com.springboot.SpringBootRestApi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long empId;
    @ManyToOne
    @JsonIgnore
    private Company company;
    private String empName;
    private LocalDate empDateofJoin;
    private long salary;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public long getEmpId() {
        return empId;
    }

    public void setEmpId(long empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public LocalDate getEmpDateofJoin() {
        return empDateofJoin;
    }

    public void setEmpDateofJoin(LocalDate empDateofJoin) {
        this.empDateofJoin = empDateofJoin;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }
}
