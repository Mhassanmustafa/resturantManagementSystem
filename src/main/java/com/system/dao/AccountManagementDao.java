package com.system.dao;

import com.system.Logs.Log;
import com.system.Message.Messages;
import com.system.Queries.Query;
import com.system.config.Config;
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

public class AccountManagementDao  implements IAccountManagement  {

    public static void getLogInfo (String message){
        try {
            Log log = Log.getInstance();
            log.logger.info(message);
        }catch (Exception exp){
            exp.printStackTrace();
        }

    }

    public static void getLogWarning (String message){
        try {
            Log log = Log.getInstance();
            log.logger.warning(message);
        }catch (Exception exp){
            exp.printStackTrace();
        }

    }

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
                getLogInfo("user names added  in observable list ");
            }

        }catch (SQLException exp){
            exp.printStackTrace();
            getLogWarning(exp.getMessage());
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
                getLogInfo("Data is not inserted in Employee table");
            }else {
                System.out.println("Data Inserted SuccessFully");
                getLogInfo("Data is Sucessfully inserted in Employee table");
            }

        }catch (Exception exp){
            exp.printStackTrace();
            getLogWarning(exp.getMessage());
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
            getLogWarning(exp.getMessage());
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
                getLogInfo("Data is not inserted in Employee Salary table");
            }else {
                Messages.getAlert("Data Inserted SuccessFully");
                getLogInfo("Data is  inserted in Employee Salary table");
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
                getLogInfo("Employee log in data not inserted ");
            }else {
                Messages.getAlert("Data Inserted SuccessFully");
                getLogInfo("Employee log in data inserted ");
            }


        }catch (Exception exp){
            exp.printStackTrace();
            getLogWarning(exp.getMessage());
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
            getLogWarning(exp.getMessage());
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
                getLogInfo("Exployee data added in list");
            }

        }catch (Exception exp){
            getLogWarning(exp.getMessage());
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
                getLogInfo("Employee base Salary not inserted");
            }else {
                Messages.getAlert("Data Inserted SuccessFully");
                getLogInfo("Employee base Salary  inserted successfully");
            }


        }catch (Exception exp){
            getLogWarning(exp.getMessage());
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
            getLogWarning(exp.getMessage());
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
                getLogInfo("password is not updated successfully");
            }else{
                Messages.getAlert("Password Updated Successfully");
                getLogInfo("password updated successfull");
            }
        }catch (Exception exp){
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }
    }


    @Override
    public Boolean adminLogIn(String userName, String password)  {

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();


        params.put(1,userName);
        params.put(2,password);

        try{

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection,Query.adminLogIn,params);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
               getLogInfo("Admin log in successfull");
                return true;
            }

        }catch (Exception exp){
            exp.printStackTrace();
            getLogWarning(exp.getMessage());
        }finally {
            this.closeSqlConnection(connection);
        }

        getLogInfo("Admin login Unsuccessfull");
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
                getLogInfo("Employee log in success full");
                return true;
            }

        }catch (Exception exp){
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }
        getLogInfo("employee log in not successfull");
        return false;
    }

    @Override
    public ObservableList<Customers> getCustomersData(String custName, String phoneNo) {
        ObservableList<Customers> list = FXCollections.observableArrayList();

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();

        params.put(1,custName);
        params.put(2,phoneNo);


        try{

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection, Query.getCustomerDetails,params);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet != null){
                while (resultSet.next()){
                    list.add(new Customers(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3)
                    ,resultSet.getInt(4),resultSet.getFloat(5),resultSet.getFloat(6),resultSet.getFloat(7),
                            resultSet.getInt(8),resultSet.getString(9)));
                }
                getLogInfo("customer data successfully added");
            }

        }catch (SQLException exp){
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }

        return list;
    }

    public ObservableList<Customers> getCustomersDataByPh( String phoneNo) {
        ObservableList<Customers> list = FXCollections.observableArrayList();

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();


        params.put(1,phoneNo);


        try{

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection, Query.getCustomerDetailsByPh,params);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet != null){
                while (resultSet.next()){
                    list.add(new Customers(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3)
                            ,resultSet.getInt(4),resultSet.getFloat(5),resultSet.getFloat(6),resultSet.getFloat(7),
                            resultSet.getInt(8),resultSet.getString(9)));
                }
            }

        }catch (SQLException exp){
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }

        return list;
    }
    public ObservableList<Customers> getCustomersDataByName( String name) {

        ObservableList<Customers> list = FXCollections.observableArrayList();

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();


        params.put(1,name);


        try{

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection, Query.getCustomerDetailsByName,params);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet != null){
                while (resultSet.next()){
                    list.add(new Customers(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3)
                            ,resultSet.getInt(4),resultSet.getFloat(5),resultSet.getFloat(6),resultSet.getFloat(7),
                            resultSet.getInt(8),resultSet.getString(9)));
                }
            }

        }catch (SQLException exp){
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }

        return list;
    }

    public ObservableList<Customers> getAllCustomersData() {

        ObservableList<Customers> list = FXCollections.observableArrayList();

        Connection connection = SqlConnectionServices.getConnection();



        try{

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection,Query.getAllCustomersDetails,null);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet != null){
                while (resultSet.next()){
                    list.add(new Customers(resultSet.getInt(1),resultSet.getString(2),resultSet.getInt(3),
                            resultSet.getString(4),resultSet.getFloat(5),resultSet.getFloat(6),resultSet.getFloat(7),
                            resultSet.getString(8)));
                }
                getLogInfo("Data added in list");
            }

        }catch (Exception exp){
            getLogWarning(exp.getMessage());
            exp.printStackTrace();

        }finally {

            this.closeSqlConnection(connection);

        }

        return list;
    }

    public ObservableList<Employee> getAllSalaryHistory(){
        ObservableList<Employee> list = FXCollections.observableArrayList();

        Connection connection = SqlConnectionServices.getConnection();



        try {

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection,Query.getSalaryDetails,null);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet != null) {
                while (resultSet.next()) {
                    list.add(new Employee(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),
                            resultSet.getString(4),resultSet.getFloat(5),resultSet.getString(6)));

                }
            }else{
              getLogInfo("Data is not add in list salary history data");
            }
        }catch (Exception exp){
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }
        return list;

    }
}
