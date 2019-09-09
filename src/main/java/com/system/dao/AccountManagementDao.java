package com.system.dao;

import com.system.Message.Messages;
import com.system.Queries.Query;
import com.system.dao.Interfaces.IAccountManagement;
import com.system.models.Customers;
import com.system.models.Employee;
import com.system.services.SqlConnectionServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class AccountManagementDao implements IAccountManagement {

    //to close the open sql connections
    public void closeSqlConnection(Connection connection){
        if(connection != null){
            SqlConnectionServices.closeConnection(connection);
        }
    }


    @Override
    public ObservableList<String> getAllUserNames() {

        ObservableList<String> list = FXCollections.observableArrayList();
        Connection connection = SqlConnectionServices.getConnection();

        try{

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection, Query.employeeUserNames,null);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet != null){
                while (resultSet.next()){
                    list.add(resultSet.getString(1));
                }
            }

        }catch (SQLException exp){
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }

        return list;
    }

    @Override
    public void addNewEmployee(Employee employee) {


        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();

        params.put(1 , employee.getName());
        params.put(2 , employee.getPhNo());
        params.put(3 , employee.getAddress());
        params.put(4 , employee.getEmailAddress());
        params.put(5 , employee.getDesignation());

        try{

            int affectedRows = SqlConnectionServices.prepareAStatement(connection,Query.addEmployeeInfo,params).executeUpdate();

            if(affectedRows == 0){
                System.out.println("Data Not Inserted");
            }else {
                System.out.println("Data Inserted SuccessFully");
            }

        }catch (Exception exp){
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }
    }

    @Override
    public int getEmployeeId(Employee employee) {

        int employeeId = 0;

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();


        params.put(1, employee.getEmailAddress());

        try {

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection,Query.employeeId,params);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet != null){
                while (resultSet.next()){
                    employeeId = resultSet.getInt(1);
                }
            }

        }catch (Exception exp){
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }

        return employeeId;
    }

    @Override
    public void addNewSalary(Employee employee) {

        int employeeId = getEmployeeId(employee);

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();


        params.put(1 , employeeId);
        params.put(2 , employee.getBasicSalary() );
        params.put(3 , employee.getDate());

        try {

            int affectedRows = SqlConnectionServices.prepareAStatement(connection,Query.addBaseSalary,params).executeUpdate();

            if(affectedRows == 0){
                Messages.getWarning("Data Not Inserted");
            }else {
                Messages.getAlert("Data Inserted SuccessFully");
            }


        }catch (Exception exp){
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }
    }

    @Override
    public void addNewEmployeeUser(Employee employee) {

        String result = DigestUtils.md5Hex(employee.getPassword());
        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();


        params.put(1 , employee.getName());
        params.put(2 , employee.getEmailAddress() );
        params.put(3 , result);

        try {

            int affectedRows = SqlConnectionServices.prepareAStatement(connection,Query.empLogIn,params).executeUpdate();

            if(affectedRows == 0){
                Messages.getWarning("Data Not Inserted");
            }else {
                Messages.getAlert("Data Inserted SuccessFully");
            }


        }catch (Exception exp){
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }
    }

    @Override
    public ObservableList<String> getEmployeeName() {

        ObservableList<String> list = FXCollections.observableArrayList();
        Connection connection = SqlConnectionServices.getConnection();

        try{

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection, Query.empNames,null);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet != null){
                while (resultSet.next()){
                    list.add(resultSet.getString(1));
                }
            }

        }catch (SQLException exp){
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }

        return list;
    }

    @Override
    public Employee getEmployeeData(String name) {
        Employee employee = new Employee();

        Connection connection =SqlConnectionServices.getConnection();
        HashMap<Integer ,Object> params = new HashMap<>();


        params.put(1 , name);

        try {

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection,Query.emplyeeData,params);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet != null){
                while (resultSet.next()){
                    employee.setName(resultSet.getString(1));
                    employee.setPhNo(resultSet.getString(2));
                    employee.setAddress(resultSet.getString(3));
                    employee.setEmailAddress(resultSet.getString(4));
                    employee.setDesignation(resultSet.getString(5));
                    employee.setBasicSalary(resultSet.getFloat(6));

                }
            }

        }catch (Exception exp){
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }

        return employee;
    }

    @Override
    public void addnewBaseSalary(Employee employee) {

        int employeeId = getEmployeeId(employee);

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();


        params.put(1 , employeeId);
        params.put(2 , employee.getBasicSalary() );
        params.put(3 , employee.getDate());

        try {

            int affectedRows = SqlConnectionServices.prepareAStatement(connection,Query.newBasicSalary,params).executeUpdate();

            if(affectedRows == 0){
                Messages.getWarning("Data Not Inserted");
            }else {
                Messages.getAlert("Data Inserted SuccessFully");
            }


        }catch (Exception exp){
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }
    }

    @Override
    public Customers getCustomerInfo(String name, String phoneNo) {
        Customers customer = new Customers();
        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();


        params.put(1 , name);
        params.put(2,phoneNo);

        try{

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection,Query.customerInfo,params);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet != null){
                while (resultSet.next()){
                    customer.setId(resultSet.getInt(1));
                    customer.setName(resultSet.getString(2));
                    customer.setPhoneNumber(resultSet.getString(3));
                    customer.setShopName(resultSet.getString(4));
                    customer.setEmailAddress(resultSet.getString(5));
                }
            }

        }catch (Exception exp){
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }

        return customer;
    }


    @Override
    public void UpdatePassword(String userName, String password,String oldPassword) {

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();


        params.put(1,password);
        params.put(2,userName);
        params.put(3,oldPassword);

        try{
            int affectedRows = SqlConnectionServices.prepareAStatement(connection,Query.updateAdminPassword,params).executeUpdate();

            if(affectedRows == 0){
                Messages.getWarning("User Name is Wrong");
            }else{
                Messages.getAlert("Password Updated Successfully");
            }
        }catch (Exception exp){
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }
    }


    @Override
    public Boolean adminLogIn(String userName, String password) {

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();


        params.put(1,userName);
        params.put(2,password);

        try{

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection,Query.adminLogIn,params);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return true;
            }

        }catch (Exception exp){
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }
        return false;
    }

    @Override
    public Boolean employeeLogIn(String userName, String password) {
        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();


        params.put(1,userName);
        params.put(2,password);

        try{

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection,Query.employeeLogIn,params);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return true;
            }

        }catch (Exception exp){
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }
        return false;
    }
}
