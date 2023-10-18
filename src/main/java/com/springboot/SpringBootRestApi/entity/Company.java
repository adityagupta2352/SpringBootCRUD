package com.springboot.SpringBootRestApi.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    //@Column(nullable = false)
     private long comId;
     private String comName;

     // IN POSTMAN WE HAVE TO UPDATE
     @Transient
     private long comNumOfEmp;
     @OneToMany(mappedBy = "company" , cascade = CascadeType.ALL)
     private List<Employee> employeeList;

    public long getComId() {
        return comId;
    }

    public void setComId(long comId) {
        this.comId = comId;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public long getComNumOfEmp() {
        return employeeList != null ? employeeList.size() : 0;
    }

    public void setComNumOfEmp(long comNumOfEmp) {
        this.comNumOfEmp = comNumOfEmp;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}
