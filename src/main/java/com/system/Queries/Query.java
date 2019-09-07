package com.system.Queries;

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
}
