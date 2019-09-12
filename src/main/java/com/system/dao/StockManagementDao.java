package com.system.dao;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.system.Logs.Log;
import com.system.Message.Messages;
import com.system.Queries.Query;
import com.system.config.Config;
import com.system.dao.Interfaces.IStockManagement;
import com.system.models.Products;
import com.system.models.StockDetails;
import com.system.services.SqlConnectionServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class StockManagementDao implements IStockManagement {

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

    ProductManagementDao productManagementDao = new ProductManagementDao();
    //to close the open sql connections
    public void closeSqlConnection(Connection connection){
        if(connection != null){
            SqlConnectionServices.closeConnection(connection);
        }
    }

    @Override
    public void addnewStock(Products product) {

        int productId = productManagementDao.getProductId(product.getProductName());
        int suppliearId = productManagementDao.getSupplierId(product.getSuppliearName());

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer, Object> params = new HashMap<>();


        params.put(1, productId);
        params.put(2, suppliearId);
        params.put(3, product.getBoughtPrice());
        params.put(4, product.getQuantity());
        params.put(5, product.getDate());

        try {

            int affectedRows = SqlConnectionServices.prepareAStatement(connection, Query.addNewStock, params).executeUpdate();

            if (affectedRows == 0) {
                Messages.getWarning("Insertation failed");
                getLogInfo("insertation failed in add new Stock");
            } else {
                Messages.getAlert("Successfully Inserted");
                getLogInfo("insertation successfull in add new Stock");
            }

        } catch (Exception exp) {
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        } finally {
            this.closeSqlConnection(connection);
        }
    }

    @Override
    public float getAvailableQuantity(Products product) {

        int productId = productManagementDao.getProductId(product.getProductName());
        float totalQuantity = 0;

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer, Object> params = new HashMap<>();

        params.put(1, productId);

        try {

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection, Query.availableStock, params);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    totalQuantity = resultSet.getFloat(1);
                }
            }

        } catch (SQLException exp) {
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        } finally {
            this.closeSqlConnection(connection);
        }

        return totalQuantity;
    }

    @Override
    public void removeQuantity(Products product) {

        Products data = new Products();
        String description = "Stock removed";

        data.setProductName(product.getProductName());
        int productId = productManagementDao.getProductId(product.getProductName());
        float previousQuantity = getAvailableQuantity(data);

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer, Object> params = new HashMap<>();


        params.put(1, productId);
        params.put(2, (previousQuantity - product.getQuantity()));
        params.put(3, description);
        params.put(4, product.getDate());

        try {

            int affectedRows = SqlConnectionServices.prepareAStatement(connection, Query.removeStockQuantity, params).executeUpdate();

            if (affectedRows == 0) {
                getLogInfo("Insertation not sucessfully in remove quantity");
                Messages.getWarning("Insertation not sucessfully");
            } else {
                Messages.getAlert("Successfully Removed");
                getLogInfo("Insert successfull in remove quantity");
            }

        } catch (SQLException exp) {
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        } finally {
            this.closeSqlConnection(connection);
        }
    }


    @Override
    public ObservableList<StockDetails> getStockDetailsData() {

        ObservableList<StockDetails> list = FXCollections.observableArrayList();

        Connection connection = SqlConnectionServices.getConnection();


        try {

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection, Query.stockDetail, null);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    list.add(new StockDetails(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)
                            , resultSet.getFloat(5), resultSet.getFloat(7), resultSet.getString(4),
                            resultSet.getFloat(6)));
                }
            }

        } catch (SQLException exp) {
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        } finally {
            this.closeSqlConnection(connection);
        }
        return list;
    }

    @Override
    public void printStockDetails() {
        try{
        Document doc = new Document(PageSize.A4.rotate());
        Date date = new Date();
        if(!Files.exists(Config.stockDetailPath)){
            Files.createDirectories(Config.stockDetailPath);
        }

            String file = Paths.get(Config.stockDetailPath.toAbsolutePath().toString(),
                    String.format("StockDetailsReport-%tF-%tI-%tM-%tS.pdf", date, date, date, date)).toString();
            System.out.println(Config.stockDetailPath.toAbsolutePath().toString());

            FileOutputStream fileStream = new FileOutputStream(file);
            PdfWriter writer = PdfWriter.getInstance(doc, fileStream);
            doc.open();
            doc.add(new Paragraph(Config.address, Config.font));
            doc.add(new Paragraph(Config.contactNumber, Config.font));

            Paragraph paraDate = new Paragraph(new Date().toString());
            paraDate.setAlignment(Config.alignRight);
            doc.add(paraDate);

            Paragraph shopTitle = new Paragraph(Config.shopName, FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD
                    , BaseColor.DARK_GRAY));
            shopTitle.setAlignment(Config.alignCenter);
            doc.add(shopTitle);

            doc.add(new Paragraph(Config.rotateLines));
            doc.add(new Paragraph("The Stock Details are given"));
            doc.add(new Paragraph("\n"));

            Connection connection = SqlConnectionServices.getConnection();



            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection, Query.stockDetail, null);
            ResultSet resultSet = preparedStatement.executeQuery();

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            PdfPTable table = new PdfPTable(resultSetMetaData.getColumnCount());
            table.setTotalWidth(90);

            String header[] = {"Id", "Product Name", "Suppliear Name", "Suppliear Phone no", "Quantity Available", "Bought Price", "Selling Price"};

            for (int i = 0; i < header.length; i++) {
                PdfPCell pdfPCell = new PdfPCell(new Paragraph(header[i], Config.font));
                table.addCell(pdfPCell);
            }

            while (resultSet.next()) {
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    PdfPCell cell = new PdfPCell(new Paragraph(resultSet.getString(i)));
                    table.addCell(cell);
                }
            }

            doc.add(table);
            doc.addCreationDate();
            doc.close();
            writer.close();
            Desktop d = Desktop.getDesktop();
            d.open(new File(file));
            Messages.getAlert("Exported to Pdf please Wait while file is opening");
        }catch (Exception exp){
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        }

    }
}
