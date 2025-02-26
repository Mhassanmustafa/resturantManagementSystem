package com.system.dao.Interfaces;

import com.system.models.Customers;
import com.system.models.Employee;
import javafx.collections.ObservableList;

public interface IAccountManagement {
    public ObservableList<String> getAllUserNames();
    public void addNewEmployee(Employee employee);
    public void addNewSalary(Employee employee);
    public void addNewEmployeeUser(Employee employee);
    public int getEmployeeId(Employee employee);
    public ObservableList<String> getEmployeeName();
    public Employee getEmployeeData(String name);
    public void addnewBaseSalary(Employee employee);
    public Customers getCustomerInfo(String name,String phoneNo);
    public void UpdatePassword(String userName, String password,String oldPassword);
    public Boolean adminLogIn(String userName, String password);
    public Boolean employeeLogIn(String userName, String password);
    public ObservableList<Customers> getCustomersData(String custName,String phoneNo);

}
