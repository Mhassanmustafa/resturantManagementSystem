package com.system.Queries;

import java.awt.*;

public class Query {

    public static final String categoryNames = "SELECT NAME \n" +
            "FROM   category ";

    public static final String supplierNames = "SELECT NAME \n" +
            "FROM   supplier ";

    public static final String supplierId = "SELECT id \n" +
            "FROM   supplier \n" +
            "WHERE  NAME = ? ";

    public static final String categoryId = "SELECT id \n" +
            "FROM   category \n" +
            "WHERE  NAME = ? ";

    public static final String addNewProductQuery = "INSERT INTO product \n" +
            "            (categoryid, \n" +
            "             supplierid, \n" +
            "             NAME) \n" +
            "VALUES      (?, \n" +
            "             ?, \n" +
            "             ?) ";

    public static final String  allProductsNames = "SELECT NAME \n" +
            "FROM   product ";

    public static final String productId = "SELECT id \n" +
            "FROM   product \n" +
            "WHERE  NAME = ? ";

    public static final String addSellHistory = "INSERT INTO sellhistory \n" +
            "            (productid, \n" +
            "             sellprice, \n" +
            "             date) \n" +
            "VALUES      (?, \n" +
            "             ?, \n" +
            "             ?) ";


    public static final String currentSellingPrice = "SELECT TOP 1 sellprice \n" +
            "FROM   sellhistory \n" +
            "WHERE  productid = ? \n" +
            "ORDER  BY date DESC ";

    public static final String updateSellingPrice = "INSERT INTO sellhistory \n" +
            "            (productid, \n" +
            "             sellprice, \n" +
            "             date) \n" +
            "VALUES      (?, \n" +
            "             ?, \n" +
            "             ?) ";


    public static final String newCategoryName = "INSERT INTO category \n" +
            "            (NAME) \n" +
            "VALUES      (?) ";


    public static final String newSupplier = "INSERT INTO supplier \n" +
            "            (NAME, \n" +
            "             phoneno, \n" +
            "             address, \n" +
            "             email) \n" +
            "VALUES      (?, \n" +
            "             ?, \n" +
            "             ?, \n" +
            "             ?) ";

    public static final String supplierPhNo = "SELECT phoneno \n" +
            "FROM   supplier ";

    public static final String supplierInfo = "SELECT * \n" +
            "FROM   supplier \n" +
            "WHERE  NAME = ? ";

    public static final String recpieCategoryNames = "select categoryName from recipieCategory ";

    public static final String newRecipieCategory = "INSERT INTO recipiecategory \n" +
            "            (categoryname) \n" +
            "VALUES      (?) ";

    public static final String recipieCatId = "SELECT id \n" +
            "FROM   recipiecategory \n" +
            "WHERE  categoryname = ? ";

    public static final String recipieNames = "SELECT NAME \n" +
            "FROM   recipieproduct ";

    public static final String addRecipeName = "INSERT INTO recipieproduct \n" +
            "            (categoryid, \n" +
            "             NAME) \n" +
            "VALUES      (?, \n" +
            "             ?) ";

    public static final String recipieProductId = "SELECT id \n" +
            "FROM   recipieproduct \n" +
            "WHERE  NAME = ? ";

    public static final String recipeSellPrice = "INSERT INTO recipiesellprice \n" +
            "            (productid, \n" +
            "             sellprice, \n" +
            "             date) \n" +
            "VALUES      (?, \n" +
            "             ?, \n" +
            "             ?) ";

    public static final String addRecipieIngredents = "INSERT INTO recipieingreidents \n" +
            "            (recipeproductid, \n" +
            "             productid, \n" +
            "             quantity, \n" +
            "             date) \n" +
            "VALUES      (?, \n" +
            "             ?, \n" +
            "             ?, \n" +
            "             ?)";

    public static final String recipeNameId = "SELECT recipeproductid \n" +
            "FROM   recipieingreidents ";

    public static final String recipieCurrentPrice = "SELECT TOP 1 sellprice \n" +
            "FROM   recipiesellprice \n" +
            "WHERE  productid = ? \n" +
            "ORDER  BY date DESC ";

    public static final String newRecipieSellPrice = "INSERT INTO recipiesellprice \n" +
            "            (productid, \n" +
            "             sellprice, \n" +
            "             date) \n" +
            "VALUES      (?, \n" +
            "             ?, \n" +
            "             ?) ";

    public static final String addNewStock = "INSERT INTO purchasehistory \n" +
            "            (productid, \n" +
            "             supplierid, \n" +
            "             boughtprice, \n" +
            "             quantity, \n" +
            "             date) \n" +
            "VALUES      (?, \n" +
            "             ?, \n" +
            "             ?, \n" +
            "             ?, \n" +
            "             ?) ";
    public static final String availableStock = "SELECT TOP(1) totalQuantity \n" +
            "FROM   stockhistory \n" +
            "WHERE  productId = ? \n" +
            "ORDER  BY date DESC ";

    public static final String removeStockQuantity = "INSERT INTO stockhistory \n" +
            "            (productid, \n" +
            "             totalquantity, \n" +
            "             description, \n" +
            "             date) \n" +
            "VALUES      (?, \n" +
            "             ?, \n" +
            "             ?, \n" +
            "             ?) ";

    public static final String stockDetail = "select p.id ,p.name,(\n" +
            "select s.name from supplier s where s.id = p.supplierId\n" +
            ")as supplierName , (\n" +
            "select s.phoneNo from supplier s where s.id = p.supplierId\n" +
            ")as supplierPhone, (\n" +
            "select top 1 st.totalQuantity from stockHistory st where st.productId =p.id order by st.date desc) as quantity,(\n" +
            "select top 1 ph.boughtPrice from purchaseHistory ph where ph.productId =p.id order by ph.date desc\n" +
            ")as boughtPrice , (\n" +
            "select top 1 sl.sellPrice from sellHistory sl where sl.productId =p.id order by sl.date desc) as sellPrice\n" +
            "from\n" +
            "product p";

    public static final String employeeUserNames = "SELECT username \n" +
            "FROM   emplogin ";

    public static final String addEmployeeInfo = "INSERT INTO employee \n" +
            "            (NAME, \n" +
            "             phoneno, \n" +
            "             address, \n" +
            "             username, \n" +
            "             designation) \n" +
            "VALUES      (?, \n" +
            "             ?, \n" +
            "             ?, \n" +
            "             ?, \n" +
            "             ?) ";

    public static final String employeeId = "SELECT id \n" +
            "FROM   employee \n" +
            "WHERE  username = ? ";

    public static final String addBaseSalary = "insert into salaryHistory (employeeId , basicSalary ,date) values (?,?,?)";
    public static final String empLogIn = " insert into empLogIn(name,userName,password) values (?,?,?)";
    public static final String empNames = "select name from Employee";
    public static final String emplyeeData = "select e.userName ,e.phoneNo,e.address,e.name,e.designation,(select top 1 se.basicSalary from salaryHistory as se where se.employeeId = e.id  order by date desc) as basePrice \n" +
            "\n" +
            " from Employee as e where e.userName = ?";
    public static final String newBasicSalary = "insert into salaryHistory (employeeId , basicSalary ,date) values (?,?,?)";

    public static final String customerNames = "select name from customer";
    public static final String customerPhonos = "select phoneNo from customer";
    public static final String addNewCustomer = "insert into Customer (name , phoneNo) values(?,?)";
    public static final String customerInfo = "select id ,name , phoneNo , address , email from Customer where name = ? and phoneNo = ?";
    public static final String customerOrder = "insert into customerOrder (customerId,date) values (?,?)";
    public static final String customerOrderId = "select top 1 id from customerOrder where customerId = ? order by date desc";
    public static final String customerOrderHistoy  = "insert into customerOrderHistory (customerOrderId,RecipieId,quantity,amount,discount,date) values (?,?,?,?,?,?)";


    public static final String custLedgerData = "insert into ledger (accountId,custID,custOrderId,credit,debit,balance,desciption,date) values (?,?,?,?,?,?,?,?)";
    public static final String getIngredentsId = "select productId  from recipieIngreidents where recipeProductId = ?";
    public static final String getProductQuantity = "select quantity  from recipieIngreidents where recipeProductId = ?";
    public static final String getStockQuantityAvailable = "select top 1 totalQuantity from stockHistory where productId = ? order by date desc";
    public static final String newStockHistory = "insert into stockHistory (productId,totalQuantity,description,date) values (?,?,?,?)";

}
