package com.springboot.SpringBootRestApi.controller;

import com.springboot.SpringBootRestApi.entity.Company;
import com.springboot.SpringBootRestApi.entity.Employee;
import com.springboot.SpringBootRestApi.repository.CompanyRepo;
import com.springboot.SpringBootRestApi.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/company")
public class EmployeeController {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private CompanyRepo companyRepo;

    // GET ALL EMPLOYEE BY COMPANY ID
    @GetMapping("/{id}/employees")
    public List<Employee> getEmployees(@PathVariable long id) {
        return companyRepo.findById(id).get().getEmployeeList();
    }

    // FOR INTEGRATION
    @GetMapping("/employees")
    public  List<Employee> getAllEmployees(){
        return employeeRepo.findAll();
    }

    // FOR INTEGRATION
    @DeleteMapping("/employees/{id}")
    public void deleteEmployees(@PathVariable long id)
    {
        employeeRepo.deleteById(id);
    }
    
    // FOR INTEGRATION
    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable("id") long id , @RequestBody Employee updateEmployee)
    {
        Optional<Employee> optionalEmployee = employeeRepo.findById(id);

        if(optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setEmpName(updateEmployee.getEmpName());
            employee.setSalary(updateEmployee.getSalary());
            employee.setEmpDateofJoin(updateEmployee.getEmpDateofJoin());
            return employeeRepo.save(employee);
        }
        else{
            return null;
        }
    }

    // GET EMPLOYEE BY ID IN A COMPANY
    @GetMapping("/{cId}/employees/{eId}")
    public Employee getEmployeesById(@PathVariable("cId") long cId , @PathVariable("eId") long eId) { // Rename the method to reflect the purpose

        Optional<Company> optionalCompany = companyRepo.findById(cId);
        Company company = optionalCompany.get();

        Optional<Employee> employeeOptional = company.getEmployeeList().stream().filter(emp -> emp.getEmpId() == eId).findFirst();
        return employeeOptional.get();
    }

    @GetMapping("/")
    public List<Company> getCompany() {
        return companyRepo.findAll();
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable long id) {
        Optional<Company> optionalCompany = companyRepo.findById(id);
        return optionalCompany.get();
    }

    @PostMapping("/")
    public Company addCompany(@RequestBody Company company)
    {
        return companyRepo.save(company);
    }

    @PostMapping("/{cId}/employees")
    public Employee addEmployee(@PathVariable long cId ,  @RequestBody Employee employee)
    {
        Company company = companyRepo.findById(cId).get();
        employee.setCompany(company);
        company.getEmployeeList().add(employee);
        employeeRepo.save(employee);
        return employee;
    }

    @DeleteMapping("/{comId}/employees/{empId}")
    public String deleteEmployee(@PathVariable("comId") long comId, @PathVariable("empId") long empId) {

        Optional<Company> optionalCompany = companyRepo.findById(comId);

        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            List<Employee> employees = company.getEmployeeList();

            // Find the employee to delete by empId
            Optional<Employee> employeeToDelete = employees.stream()
                    .filter(employee -> employee.getEmpId() == empId)
                    .findFirst();

            if (employeeToDelete.isPresent()) {

                // REMOVE FROM EMPLOYEELIST
                employees.remove(employeeToDelete.get());

                // REMOVE FROM EMPLOYEE DATABASE
                employeeRepo.delete(employeeToDelete.get());

                // UPDATE INTO COMPANY
                companyRepo.save(company);
                return "Employee with ID " + empId + " deleted successfully";
            }
            else{
                return "Employee with ID " + empId + " not found in company " + comId;
            }
        } else {
            return "No such company with ID " + comId + " is present";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteCompany(@PathVariable long id) {
        Optional<Company> companyExist = companyRepo.findById(id);

        if(companyExist.isPresent()){
            companyRepo.deleteById(id);
            return "Deleted";
        }
        else{
            return "Not Found";
        }
    }

    @PutMapping("/{cId}/employees/{eId}")
    public Employee updateEmployee(@PathVariable("cId") long cId , @PathVariable("eId") long eId , @RequestBody Employee updateEmployee){

        Optional<Company> optionalCompany = companyRepo.findById(cId);

        if(optionalCompany.isPresent()){
            Company company = optionalCompany.get();

            Optional<Employee> optionalEmployee = company.getEmployeeList().stream().filter(emp -> emp.getEmpId() == eId).findFirst();

            if(optionalEmployee.isPresent()){
                Employee employeeExist = optionalEmployee.get();

                employeeExist.setEmpName(updateEmployee.getEmpName());
                employeeExist.setSalary(updateEmployee.getSalary());
                employeeExist.setEmpDateofJoin(updateEmployee.getEmpDateofJoin());
                employeeExist.setCompany(company);

                return employeeRepo.save(employeeExist);
            }
            else{
                return null;
            }
        }
        else{
            return null;
        }
    }

    @PutMapping("/{id}")
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
