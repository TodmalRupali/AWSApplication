package com.csi.service;

import com.csi.dao.EmployeeDaoImpl;
import com.csi.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

@Service
public class EmployeeService {
    @Autowired
    EmployeeDaoImpl employeeDao;

    public Employee signUp(Employee employee) {
        return employeeDao.signUp(employee);
    }

    public boolean signIn(String empEmailId, String empPassword) {
       return employeeDao.signIn(empEmailId,empPassword);
    }

    public Optional<Employee> getDataById(int empId) {
        return employeeDao.getDataById(empId);
    }

    public List<Employee> getDataByName(String empName) {
        return employeeDao.getDataByName(empName);

    }

    public Employee getDataByEmailId(String empEmailId) {
        return employeeDao.getDataByEmailId(empEmailId);
    }

    public Employee getDataByContact(long empContact) {
        return employeeDao.getDataByContact(empContact);
    }

    public List<Employee> getDataByAnyInput(String input) throws ParseException {
       return  employeeDao.getDataByAnyInput(input);
    }

    public List<Employee> getAllData() {
        return employeeDao.getAllData();
    }

    public List<Employee> sortByName() {
      return employeeDao.sortByName();
    }

    public List<Employee> sortById() {
           return employeeDao.sortById();
    }

    public List<Employee> sortBySalary() {
         return employeeDao.sortBySalary();
    }

    public List<Employee> sortByAge() {
       return employeeDao.sortByAge();
    }

    public List<Employee> sortByDOB() {
        return employeeDao.sortByDOB();
    }

    public List<Employee> filterBySalary(double empSalary) {
        return employeeDao.filterBySalary(empSalary);
    }

    public boolean loanEligibility(String input) throws ParseException {
        return employeeDao.loanEligibility(input);
    }
    public  Employee updateData(Employee employee ){

        return employeeDao.updateData(employee);
    }
    public void deleteData(int empId){
        employeeDao.deleteData(empId);
    }
    public Optional<Employee> getSecondLargestSalaryRecord(){
        return employeeDao.getSecondLargestSalaryRecord();
    }
    public void deleteAllData(){
        employeeDao.deleteAllData();
    }
}
