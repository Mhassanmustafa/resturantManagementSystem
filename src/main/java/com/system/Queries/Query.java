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
    public static final String previousBalance = "select top 1 balance from ledger where custId = ? order by date desc";
    public static final String getOrderId = "select id from customerOrder";

    public static final String customerOrderHistoryQuery = "SELECT customerorder.id, \n" +
            "       (SELECT NAME \n" +
            "        FROM   customer \n" +
            "        WHERE  customer.id = customerorder.customerid)             AS \n" +
            "       CustomerName, \n" +
            "       (SELECT NAME \n" +
            "        FROM   recipieproduct \n" +
            "        WHERE  recipieproduct.id = customerorderhistory.recipieid) AS \n" +
            "       productName, \n" +
            "       (SELECT TOP 1 recipiesellprice.sellprice \n" +
            "        FROM   recipiesellprice \n" +
            "        WHERE  recipiesellprice.productid = customerorderhistory.recipieid \n" +
            "        ORDER  BY date DESC)                                       AS \n" +
            "       productPrice, \n" +
            "       customerorderhistory.quantity, \n" +
            "       customerorderhistory.amount, \n" +
            "       customerorderhistory.discount, \n" +
            "       customerorderhistory.date \n" +
            "FROM   customerorder \n" +
            "       INNER JOIN customerorderhistory \n" +
            "               ON customerorder.id = customerorderhistory.customerorderid \n" +
            "       INNER JOIN recipieproduct \n" +
            "               ON recipieproduct.id = customerorderhistory.recipieid \n" +
            "WHERE  customerorder.id = ? ";

    public static final String getDailySales = "select sum(credit) from ledger where accountId = ? and day(date) = ? and MONTH(date) = ? and year(date) = ?";
    public static final String getDailyProfit = "select ((select isnull (sum(credit) ,0 )from ledger where accountId = ? and day(date) = ? and MONTH(date) = ? and year(date) = ?) -\n" +
            " (select isnull (sum(totalPurchase) , 0) from temp where day(date) = ? and Month(date) = ? and year( date) = ? ))";

    public static final String getMonthlySale = " select sum(credit) from ledger where accountId = ? and  MONTH(date) = ? and year(date) = ?";

    public static final String getMonthlyProfit = "SELECT( (SELECT isnull(Sum(credit),0) \n" +
            "         FROM   ledger \n" +
            "         WHERE  accountid = ? \n" +
            "                AND Month(date) = ? \n" +
            "                AND Year(date) = ?) - (SELECT isnull(Sum (totalPurchase),0) \n" +
            "                                          FROM   temp \n" +
            "                                          WHERE  Month(date) = ? \n" +
            "                                                 AND Year(date) = ?) - \n" +
            "                (SELECT isnull(Sum(credit) , 0 )\n" +
            "                 FROM   ledger \n" +
            "                 WHERE \n" +
            "                accountid = ? \n" +
            "                AND Month(date) = ? \n" +
            "                AND Year(date) = ?)) ";

    public static final String getYearlySales = "select sum(credit) from ledger where accountId = ? and  year(date) = ?";

    public static final String getYearlyProfit = "SELECT( (SELECT isnull(Sum(credit),0) \n" +
            "         FROM   ledger \n" +
            "         WHERE  accountid = ? \n" +
            "                 \n" +
            "                AND Year(date) = ?) - (SELECT isnull(Sum (totalPurchase ),0) \n" +
            "                                          FROM   temp \n" +
            "                                          WHERE   \n" +
            "                                                  Year(date) = ?) - \n" +
            "                (SELECT isnull(Sum(credit) , 0 )\n" +
            "                 FROM   ledger \n" +
            "                 WHERE \n" +
            "                accountid = ? \n" +
            "                 \n" +
            "                AND Year(date) = ?)) ";

    public static final String getTotalQuantityData =  "\n" +
            " SELECT NAME, \n" +
            "       (SELECT TOP 1 totalquantity \n" +
            "        FROM   stockhistory \n" +
            "        WHERE  stockhistory.productid = p.id \n" +
            "        ORDER  BY date DESC) AS quantity \n" +
            "FROM   product p ";

    public static final String updateAdminPassword = "UPDATE Admin SET password = ? WHERE userName =? and password = ?";
    public static final String adminLogIn = "select * from admin where userName = ? and password = ?";
    public static final String employeeLogIn = "select * from empLogIn where userName = ? and password = ?";
    public static final String getCustomerDetails = "SELECT TOP 1 customer.id, \n" +
            "             customer.NAME, \n" +
            "             customer.phoneno, \n" +
            "             (SELECT TOP 1 customerorder.id \n" +
            "              FROM   customerorder \n" +
            "              WHERE  customer.id = customerorder.customerid \n" +
            "              ORDER  BY date DESC)                AS LastOrderId, \n" +
            "             (SELECT TOP 1 ledger.credit \n" +
            "              FROM   ledger \n" +
            "              WHERE  customer.id = ledger.custid \n" +
            "              ORDER  BY date DESC)                AS credit, \n" +
            "             (SELECT TOP 1 ledger.debit \n" +
            "              FROM   ledger \n" +
            "              WHERE  customer.id = ledger.custid \n" +
            "              ORDER  BY date DESC)                AS debit, \n" +
            "             (SELECT TOP 1 ledger.balance \n" +
            "              FROM   ledger \n" +
            "              WHERE  customer.id = ledger.custid \n" +
            "              ORDER  BY date DESC)                AS balance, \n" +
            "             (SELECT Count(custid) \n" +
            "              FROM   ledger \n" +
            "              WHERE  customer.id = ledger.custid) AS visted \n" +
            "FROM   customer \n" +
            "       INNER JOIN customerorder \n" +
            "               ON customer.id = customerorder.customerid \n" +
            "       INNER JOIN ledger \n" +
            "               ON customer.id = ledger.custid \n" +
            "WHERE  customer.NAME = ? \n" +
            "       AND customer.phoneno = ? ";

    public static final String getCustomerDetailsByPh = "SELECT TOP 1 customer.id, \n" +
            "             customer.NAME, \n" +
            "             customer.phoneno, \n" +
            "             (SELECT TOP 1 customerorder.id \n" +
            "              FROM   customerorder \n" +
            "              WHERE  customer.id = customerorder.customerid \n" +
            "              ORDER  BY date DESC)                AS LastOrderId, \n" +
            "             (SELECT TOP 1 ledger.credit \n" +
            "              FROM   ledger \n" +
            "              WHERE  customer.id = ledger.custid \n" +
            "              ORDER  BY date DESC)                AS credit, \n" +
            "             (SELECT TOP 1 ledger.debit \n" +
            "              FROM   ledger \n" +
            "              WHERE  customer.id = ledger.custid \n" +
            "              ORDER  BY date DESC)                AS debit, \n" +
            "             (SELECT TOP 1 ledger.balance \n" +
            "              FROM   ledger \n" +
            "              WHERE  customer.id = ledger.custid \n" +
            "              ORDER  BY date DESC)                AS balance, \n" +
            "             (SELECT Count(custid) \n" +
            "              FROM   ledger \n" +
            "              WHERE  customer.id = ledger.custid) AS visted \n" +
            "FROM   customer \n" +
            "       INNER JOIN customerorder \n" +
            "               ON customer.id = customerorder.customerid \n" +
            "       INNER JOIN ledger \n" +
            "               ON customer.id = ledger.custid \n" +
            "WHERE  \n" +
            "        customer.phoneno = ? ";

    public static final String getCustomerDetailsByName = "SELECT TOP 1 customer.id, \n" +
            "             customer.NAME, \n" +
            "             customer.phoneno, \n" +
            "             (SELECT TOP 1 customerorder.id \n" +
            "              FROM   customerorder \n" +
            "              WHERE  customer.id = customerorder.customerid \n" +
            "              ORDER  BY date DESC)                AS LastOrderId, \n" +
            "             (SELECT TOP 1 ledger.credit \n" +
            "              FROM   ledger \n" +
            "              WHERE  customer.id = ledger.custid \n" +
            "              ORDER  BY date DESC)                AS credit, \n" +
            "             (SELECT TOP 1 ledger.debit \n" +
            "              FROM   ledger \n" +
            "              WHERE  customer.id = ledger.custid \n" +
            "              ORDER  BY date DESC)                AS debit, \n" +
            "             (SELECT TOP 1 ledger.balance \n" +
            "              FROM   ledger \n" +
            "              WHERE  customer.id = ledger.custid \n" +
            "              ORDER  BY date DESC)                AS balance, \n" +
            "             (SELECT Count(custid) \n" +
            "              FROM   ledger \n" +
            "              WHERE  customer.id = ledger.custid) AS visted \n" +
            "FROM   customer \n" +
            "       INNER JOIN customerorder \n" +
            "               ON customer.id = customerorder.customerid \n" +
            "       INNER JOIN ledger \n" +
            "               ON customer.id = ledger.custid \n" +
            "WHERE  \n" +
            "        customer.name = ? ";


    public  static final String getAllCustomersDetails = "SELECT cus.id, \n" +
            "       cus.NAME, \n" +
            "       (SELECT Count(custid) \n" +
            "        FROM   ledger \n" +
            "        WHERE  cus.id = ledger.custid) AS shopVisted, \n" +
            "       cus.phoneno, \n" +
            "       (SELECT TOP 1 led.credit \n" +
            "        FROM   ledger AS led \n" +
            "        WHERE  cus.id = led.custid \n" +
            "        ORDER  BY date DESC)           AS credit, \n" +
            "       (SELECT TOP 1 led.debit \n" +
            "        FROM   ledger AS led \n" +
            "        WHERE  cus.id = led.custid \n" +
            "        ORDER  BY date DESC)           AS debit, \n" +
            "       (SELECT TOP 1 led.balance \n" +
            "        FROM   ledger AS led \n" +
            "        WHERE  cus.id = led.custid \n" +
            "        ORDER  BY date DESC)           AS balance, \n" +
            "       (SELECT TOP 1 led.date \n" +
            "        FROM   ledger AS led \n" +
            "        WHERE  cus.id = led.custid \n" +
            "        ORDER  BY date DESC)           AS date \n" +
            "FROM   customer AS cus ";


    public static final String getSalaryDetails = "SELECT employee.id, \n" +
            "       employee.NAME, \n" +
            "       employee.phoneno, \n" +
            "       employee.designation, \n" +
            "       (SELECT TOP 1 salaryhistory.basicsalary \n" +
            "        FROM   salaryhistory \n" +
            "        WHERE  salaryhistory.employeeid = employee.id \n" +
            "        ORDER  BY salaryhistory.date DESC) AS basicSalary, \n" +
            "       (SELECT TOP 1 salaryhistory.date \n" +
            "        FROM   salaryhistory \n" +
            "        WHERE  salaryhistory.employeeid = employee.id \n" +
            "        ORDER  BY salaryhistory.date DESC) AS date \n" +
            "FROM   employee ";

    public static final String getLatestPurchasePrice = " select top 1 boughtPrice from purchaseHistory where productId = ? order by date desc";
    public static final String insertRecipiePurchase = " insert into recipiePurchase (productId , purchasePrice,date) values(?,?,?)";
    public static final String getRecipePurchasePrice = " select top 1 purchasePrice from recipiePurchase where productId = ? order by date desc";
    public static final String insertTemp = " insert into temp (orderid,totalPurchase ,date) values (?,?,?)";
    public static final String sqlBackupQuery = "BACKUP DATABASE [data_database] TO  DISK = N'C:\\Program Files\\Microsoft SQL Server\\MSSQL12.MSSQLSERVER\\MSSQL\\Backup\\data_database.bak'";
    public static final String deleteLedgerOrder = "delete from ledger where ledger.custOrderId = ?";
    public static final String deleteOrderhistory = "delete from customerOrderHistory where customerOrderId = ?";
    public static final String deleteCustomerOrder = "delete from customerOrder where id = ?";
    public static final String getRecipieData = "select recipieIngreidents.recipeProductId , (select product.name from product where product.id = recipieIngreidents.productId) as productName\n" +
            " , recipieIngreidents.quantity from recipieIngreidents where recipieIngreidents.recipeProductId = ?";
    public static final String deleteTemp = "delete from temp where temp.orderid = ?";

}
