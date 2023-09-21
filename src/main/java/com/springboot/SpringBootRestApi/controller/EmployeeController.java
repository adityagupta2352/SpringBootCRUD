package com.springboot.SpringBootRestApi.controller;

import com.springboot.SpringBootRestApi.entity.Company;
import com.springboot.SpringBootRestApi.entity.Employee;
import com.springboot.SpringBootRestApi.repository.CompanyRepo;
import com.springboot.SpringBootRestApi.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/company") // Change the mapping to "/employees"
public class EmployeeController {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private CompanyRepo companyRepo;

    @GetMapping("/getEmp")
    public List<Employee> getEmployees() { // Rename the method to reflect the purpose
        // System.out.println("Jai***********");
        return employeeRepo.findAll();
    }

    @GetMapping("/getCom")
    public List<Company> getCompany() { // Rename the method to reflect the purpose
        // System.out.println("Jai***********");
        return companyRepo.findAll();
    }

    @PostMapping("/addCom")
    public Company addCompany(@RequestBody Company company)
    {
        return companyRepo.save(company);
    }

    @PostMapping("/addEmp")
    public Employee addEmployee(@RequestBody Employee employee)
    {
        return employeeRepo.save(employee);
    }

    @DeleteMapping("/delEmp/{id}")
    public String deleteEmployee(@PathVariable long id) {
        Optional<Employee> check = employeeRepo.findById(id);

        if(check.isPresent()){
            employeeRepo.deleteById(id);
            return "Deleted";
        }
        else{
            return "Not Found";
        }
    }

    @DeleteMapping("/delCom/{id}")
    public String deleteCompany(@PathVariable long id) {
        Optional<Company> check = companyRepo.findById(id);

        if(check.isPresent()){
            companyRepo.deleteById(id);
            return "Deleted";
        }
        else{
            return "Not Found";
        }
    }

    @PutMapping("/updateEmp/{id}")
    public Employee updateEmployee(@PathVariable long id , @RequestBody Employee updateEmployee){

        Optional<Employee> optionalEmployee = employeeRepo.findById(id);

        if(optionalEmployee.isPresent()){

            Employee employeeExist = optionalEmployee.get();

            employeeExist.setEmpName(updateEmployee.getEmpName());
            employeeExist.setSalary(updateEmployee.getSalary());
            employeeExist.setEmpDateofJoin(updateEmployee.getEmpDateofJoin());
            employeeExist.setCompany(updateEmployee.getCompany());

            return employeeRepo.save(employeeExist);
        }
        else{
            return null;
        }
    }

    @PutMapping("/updateCom/{id}")
    public Company updateCompany(@PathVariable long id , @RequestBody Company updateCompany){

        Optional<Company> optionalCompany = companyRepo.findById(id);

        if(optionalCompany.isPresent()){

            Company companyExist = optionalCompany.get();

            companyExist.setComName(updateCompany.getComName());
            companyExist.setComNumOfEmp(updateCompany.getComNumOfEmp());
            companyExist.setEmployeeList(updateCompany.getEmployeeList());

            return companyRepo.save(companyExist);
        }
        else{
            return null;
        }
    }
}
