package com.csi.dao;

import com.csi.model.Employee;
import com.csi.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class EmployeeDaoImpl {

    @Autowired
    EmployeeRepo employeeRepo;

    public Employee signUp(Employee employee) {
        return employeeRepo.save(employee);
    }

    public boolean signIn(String empEmailId, String empPassword) {
        boolean flag = false;
        for (Employee employee : employeeRepo.findAll()) {
            if (employee.getEmpEmailId().equals(empEmailId) && employee.getEmpPassword().equals(empPassword)) {
                flag = true;
            }
        }
        return flag;
    }

    public Optional<Employee> getDataById(int empId) {
        return employeeRepo.findById(empId);
    }

    public List<Employee> getDataByName(String empName) {
        return employeeRepo.findByEmpName(empName);

    }

    public Employee getDataByEmailId(String empEmailId) {
        return employeeRepo.findByEmpEmailId(empEmailId);
    }

    public Employee getDataByContact(long empContact) {
        return employeeRepo.findByEmpContact(empContact);
    }

    public List<Employee> getDataByAnyInput(String input) throws ParseException {
        List<Employee> employees = new ArrayList<>();
        for (Employee employee : employeeRepo.findAll()) {
            if (employee.getEmpName().equals(input) || String.valueOf(employee.getEmpId()).equals(input)  || String.valueOf(employee.getEmpSalary()).equals(input)|| String.valueOf(employee.getEmpContact()).equals(input) || employee.getEmpEmailId().equals(input)||String.valueOf(employee.getEmpDOB()).equals(input)|| String.valueOf(employee.getEmpAge()).equals(input)) {
                employees.add(employee);
            }

        }
        return employees;
    }

    public List<Employee> getAllData() {
        return employeeRepo.findAll();
    }

    public List<Employee> sortByName() {
        return employeeRepo.findAll().stream().sorted((e1, e2) -> e1.getEmpName().compareTo(e2.getEmpName())).collect(Collectors.toList());
    }

    public List<Employee> sortById() {
        return employeeRepo.findAll().stream().sorted(Comparator.comparingInt(Employee::getEmpId)).collect(Collectors.toList());
    }

    public List<Employee> sortBySalary() {
        return employeeRepo.findAll().stream().sorted(Comparator.comparingDouble(Employee::getEmpSalary)).collect(Collectors.toList());

    }

    public List<Employee> sortByAge() {
        return employeeRepo.findAll().stream().sorted(Comparator.comparingInt(Employee::getEmpAge)).collect(Collectors.toList());
    }

    public List<Employee> sortByDOB() {
        return employeeRepo.findAll().stream().sorted((e1, e2) -> e1.getEmpDOB().compareTo(e2.getEmpDOB())).collect(Collectors.toList());
    }

    public List<Employee> filterBySalary(double empSalary) {
        return employeeRepo.findAll().stream().filter(emp -> emp.getEmpSalary() >= empSalary).collect(Collectors.toList());
    }

    public boolean loanEligibility(String input) throws ParseException {
        boolean flag = false;
        for (Employee employee : employeeRepo.findAll()) {
            if (employee.getEmpName().equals(input) || String.valueOf(employee.getEmpId()).equals(input)|| String.valueOf(employee.getEmpSalary()).equals(input) || String.valueOf(employee.getEmpContact()).equals(input)|| employee.getEmpEmailId().equals(input)) {
                if (employee.getEmpSalary() >= 50000) {
                    flag = true;
                }
            }
        }
        return flag;
    }
    public  Employee updateData(Employee employee ){

        return employeeRepo.save(employee);
    }
    public void deleteData(int empId){
        employeeRepo.deleteById(empId);
    }
    public Optional<Employee> getSecondLargestSalaryRecord(){
        Optional<Employee> employee =  getAllData().stream().sorted(Comparator.comparingDouble(Employee::getEmpSalary).reversed()).skip(1).findFirst();
        return employee;
    }
    public void deleteAllData(){
        employeeRepo.deleteAll();
    }
}