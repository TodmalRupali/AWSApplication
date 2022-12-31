package com.csi.controller;

import com.csi.exception.RecordNotFoundException;
import com.csi.model.Employee;
import com.csi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/")
    public String sayHello(){
        return "Hello ....";
    }
    @PostMapping("/signup")
    public String signUp(@RequestBody Employee employee){
        String str = "Sign Up Done Successfully";
        for(Employee employee1 : employeeService.getAllData()){
            if(employee1.getEmpEmailId().equals(employee.getEmpEmailId())){
                return "User Already exit !! Please try with Another one....";
            }
        }
        employeeService.signUp(employee);
        return str;
    }
    @GetMapping("/signin/{empEmailId}/{empPassword}")
    public String signIn(@PathVariable String empEmailId , @PathVariable String empPassword){
        String str = "";
        if(employeeService.signIn(empEmailId,empPassword)){
            return "SignIn Done Successfully";
        }else {
            return "Invalid credintial...Try Again.!!!";
        }
    }
    @GetMapping("/getbyid/{empId}")
    public Optional<Employee> getDataById(@PathVariable int empId){
        return employeeService.getDataById(empId);
    }
    @GetMapping("/getbyname/{empName}")
    public List<Employee> getDataByName(@PathVariable String empName){
        return employeeService.getDataByName(empName);
    }

    @GetMapping("/getbyemail/{empEmailId}")
    public  Employee gatDataByEmail(@PathVariable String empEmailId){
        return employeeService.getDataByEmailId(empEmailId);
    }
    @GetMapping("/getbycontact/{empContact}")
    public Employee getDataByContact(@PathVariable long empContact){
        return employeeService.getDataByContact(empContact);
    }
    @GetMapping("/getbyany/{input}")
    public List<Employee> getDataByAnyInput(@PathVariable String input) throws ParseException {
        return employeeService.getDataByAnyInput(input);
    }
    @GetMapping("/getall")
    public List<Employee> getAllData(){
        return  employeeService.getAllData();
    }
    @GetMapping("/sortbyname")
    public List<Employee> sortByName(){
       return   employeeService.sortByName();
    }
    @GetMapping("/sortbyid")
    public List<Employee> sortById(){
        return   employeeService.sortById();
    }
    @GetMapping("/sortbysalary")
    public List<Employee> sortBySalary(){
        return   employeeService.sortBySalary();
    }
    @GetMapping("/sortbyage")
    public List<Employee> sortByAge(){
        return   employeeService.sortByAge();
    }
    @GetMapping("/sortbydob")
    public List<Employee> sortByDOB(){
        return   employeeService.sortByDOB();
    }
    @GetMapping("/filterbysalary/{empSalary}")
    public List<Employee> filterBySalary(@PathVariable double empSalary){
        return   employeeService.filterBySalary(empSalary);
    }
    @GetMapping("/loaneligibility/{input}")
    public String loanEligibility(@PathVariable String input) throws ParseException {
        if(employeeService.loanEligibility(input)){
            return "Eligible for Loan";
        }else {
            return "Not Eligible for Loan";
        }
    }
    @PutMapping("/update/{empId}")
    public  Employee updateData(@RequestBody Employee employee , @PathVariable int empId){
        Employee employee1 = getDataById(empId).orElseThrow(()->new RecordNotFoundException("Employee Id Not Exist"));
        employee1.setEmpName(employee.getEmpName());
        employee1.setEmpContact(employee.getEmpContact());
        employee1.setEmpSalary(employee.getEmpSalary());
        employee1.setEmpDOB(employee.getEmpDOB());
        employee1.setEmpAge(employee.getEmpAge());
        employee1.setEmpEmailId(employee.getEmpEmailId());
        employee1.setEmpPassword(employee.getEmpPassword());

        return employee1;

    }
    @DeleteMapping("/deletebyid/{empId}")
    public String deleteById(@PathVariable int empId){
        employeeService.deleteData(empId);
        return "Data Deleted Successfully";
    }
    @GetMapping("/fetchsecondlargestsalary")
    public Optional<Employee> getSecondLargestSalaryRecord(){
        return employeeService.getSecondLargestSalaryRecord();
    }
    @DeleteMapping("/deleteall")
    public String deleteAll(){
        employeeService.deleteAllData();
        return "All Data Deleted successfully";
    }

}
