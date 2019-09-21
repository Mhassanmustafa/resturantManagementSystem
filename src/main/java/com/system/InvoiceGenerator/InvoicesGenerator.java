package com.system.InvoiceGenerator;


import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.scenario.effect.ImageData;

import com.system.config.Config;
import com.system.Message.Messages;
import com.system.dao.InvoicesDao;
import com.system.models.Customers;
import com.system.models.Invoices;
import io.github.escposjava.PrinterService;
import io.github.escposjava.print.Printer;
import io.github.escposjava.print.SerialPrinter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalTime;

public class InvoicesGenerator {

    public static String truncate(String value, int length) {
        if (value != null && value.length() > length)
            value = value.substring(0, length);
        return value;
    }


    private void printItemLine(PrinterService service, String qty, String name, String price) {
        // qty gets total of 9 spaces
        if (qty == null || name == null || price == null) {
            return;
        }

        qty = qty.trim();
        service.setTextAlignLeft();
        service.print(qty);
        service.insertSpaces(9 - qty.length()); // hardcoded , written by Muhamaad Hassan Musatafa
        name = name.trim();
        name = truncate(name, 31);
        service.print(name);
        service.insertSpaces(31 - name.length());
        price = price.trim();
        service.printLn(price);
    }

    private void makeLine(PrinterService service, boolean bold) {
        String line = "";
        for (int i = 0; i < 48; i++) {
            line += '_';
        }
        if (bold) {
            service.setTextTypeBold();
        }
        service.printLn(line);

        service.setTextNormal();
    }


    private void printBold(PrinterService service, String text, boolean newLine) {
        service.setTextTypeBold();
        if (newLine) {
            service.printLn(text);
        } else {
            service.print(text);
        }
        service.setTextNormal();
    }

    public void createThermalPrint(ObservableList<Invoices> tableData, Customers customer,
                                   String netPrice, String subTotal, String discount, String amountPaid, int orderID) {
        for (int j = 0; j <2 ; j++) {


            String dis = discount;
            if (discount.isEmpty()) {
                dis = "0.0";
            }
            float change = Float.parseFloat(amountPaid) - Float.parseFloat(netPrice);

            InvoicesDao invoicesDao = new InvoicesDao();
            ObservableList<String> list = FXCollections.observableArrayList();
            try {
                File file = new File(Config.filePath);
                if (!Files.exists(Config.logFile)) {
                    Files.createDirectories(Config.logFile);
                    if (!file.exists()) {
                        file.createNewFile();
                        Messages.getWarning("Please add server details first in config files and try again");
                    }
                } else {

                    FileReader fr = new FileReader(file);
                    BufferedReader br = new BufferedReader(fr);
                    String line;
                    while ((line = br.readLine()) != null) {

                        list.add(line);
                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
            }


            Printer printer = new SerialPrinter(Integer.parseInt(list.get(4)));
            PrinterService service = new PrinterService(printer);

            service.setTextAlignCenter();
            try {
//            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//            String path = classLoader.getResource("images/small270.png").getPath();

                service.printImage(Config.imagePath);
            } catch (IOException e) {
                e.printStackTrace();
            }

            service.init();
            service.setTextNormal();

            service.setTextAlignCenter();
            printBold(service, "Welcome to " + Config.shopName, true);
            service.setTextAlignCenter();
            printBold(service, "Address : " + Config.address, true);
            service.setTextAlignCenter();
            printBold(service, "Phone no : " + Config.contactNumber, true);
//        service.lineBreak();
            makeLine(service, false);

            // order numbner
            service.setTextAlignCenter();
            printBold(service, "Your Order No. Is", true);
            service.setTextBig();
            service.setTextAlignCenter();
            service.printLn(Integer.toString(invoicesDao.getCallOrder()));
            service.setTextNormal();
            makeLine(service, false);
            service.setTextNormal();

            String date = LocalDate.now().toString();
            System.out.println(date + " " + date.length());
            String time = LocalTime.now().toString();
            System.out.println(time + " " + time.length());

            service.setTextAlignLeft();
            service.print(date);
            service.insertSpaces(26);
            service.print(time);

            makeLine(service, true);
            service.lineBreak();
            service.setTextTypeBold();
            service.printLn("Customer Name :  " + customer.getName());
            service.setTextTypeBold();
            service.printLn("Trans ID : " + orderID);
            makeLine(service, true);


            service.setTextTypeBold();
            service.print("Qty");
            service.insertSpaces(6);
            service.print("Name");
            service.insertSpaces(27);
            service.print("Price");
            service.insertSpaces(3);
            service.lineBreak();
            makeLine(service, true);

            for (int i = 0; i < tableData.size(); i++) {
                Invoices invoices = tableData.get(i);
                printItemLine(service, Float.toString(invoices.getQuantity()), invoices.getProductName(), Float.toString(invoices.getPrice()));
            }
            makeLine(service, true);

            service.lineBreak();
            service.setTextAlignRight();
            service.setTextTypeBold();
            service.print("SubTotal =    " + subTotal);
            service.insertSpaces(5);
            service.lineBreak();
            service.setTextAlignRight();
            service.setTextTypeBold();
            service.print("Discount =    " + dis);
            service.insertSpaces(5);
            service.lineBreak();
            service.setTextAlignRight();
            service.setTextTypeBold();
            service.print("Net amount =    " + netPrice);
            service.insertSpaces(5);
            service.lineBreak();
            service.setTextAlignRight();
            service.setTextTypeBold();
            service.print("Cash Recieved =    " + amountPaid);
            service.insertSpaces(5);
            service.lineBreak();
            service.setTextAlignRight();
            service.setTextTypeBold();
            service.print("Change =    " + change);
            service.insertSpaces(5);
            service.lineBreak();
            makeLine(service, true);

            // normalize that baby
            service.lineBreak();
            service.setTextAlignCenter();
            service.setText4Square();
            service.setTextTypeBold();
            service.printLn("Thank You ");
            service.lineBreak(1);

            makeLine(service, false);
            service.lineBreak();
            service.setTextNormal();
            service.setTextAlignLeft();

            service.printLn("Powered By HR DEVELOPERS for BIG BANG BRGR");
            service.lineBreak();
            service.setTextTypeBold();
            service.printLn("Like and follow us on facebook and instagram");
            service.setTextTypeBold();
            service.printLn("www.facebook.com/bigbngbrgr");
            service.setTextTypeBold();
            service.printLn("www.instagram.com/bigbngbrgr");
            service.lineBreak(4);
            service.cutFull();
            service.close();
        }

    }

    public void createOrderBill(ObservableList<Invoices> tableData, Customers customer,
                                String netPrice, String subTotal, String discount, int orderID) {

        String dis = discount;
        if (discount.isEmpty()) {
            dis = "0.0";
        }


        InvoicesDao invoicesDao = new InvoicesDao();
        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            File file = new File(Config.filePath);
            if (!Files.exists(Config.logFile)) {
                Files.createDirectories(Config.logFile);
                if (!file.exists()) {
                    file.createNewFile();
                    Messages.getWarning("Please add server details first in config files and try again");
                }
            } else {

                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                String line;
                while ((line = br.readLine()) != null) {

                    list.add(line);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        Printer printer = new SerialPrinter(Integer.parseInt(list.get(4)));
        PrinterService service = new PrinterService(printer);

        service.setTextAlignCenter();
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//            String path = classLoader.getResource("images/small270.png").getPath();
//
//
//
//            File file = new File(path);
//            if(file.exists()){
//                System.out.println("exit");
//            }else {
//                System.out.println("not exit");
//            }
            service.printImage(Config.imagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        service.init();
        service.setTextNormal();

        service.setTextAlignCenter();
        printBold(service, "Welcome to " + Config.shopName, true);
        service.setTextAlignCenter();
        printBold(service, "Address : " + Config.address, true);
        service.setTextAlignCenter();
        printBold(service, "Phone no : " + Config.contactNumber, true);

        makeLine(service, false);
        service.setTextNormal();

        String date = LocalDate.now().toString();
        System.out.println(date + " " + date.length());
        String time = LocalTime.now().toString();
        System.out.println(time + " " + time.length());

        service.setTextAlignLeft();
        service.print(date);
        service.insertSpaces(26);
        service.print(time);

        makeLine(service, true);
        service.lineBreak();
        service.setTextTypeBold();
        service.printLn("Customer Name :  " + customer.getName());
        service.setTextTypeBold();
        service.printLn("Trans ID : " + orderID);
        makeLine(service, true);


        service.setTextTypeBold();
        service.print("Qty");
        service.insertSpaces(6);
        service.print("Name");
        service.insertSpaces(27);
        service.print("Price");
        service.insertSpaces(3);
        service.lineBreak();
        makeLine(service, true);

        for (int i = 0; i < tableData.size(); i++) {
            Invoices invoices = tableData.get(i);
            printItemLine(service, Float.toString(invoices.getQuantity()), invoices.getProductName(), Float.toString(invoices.getDiscount()));
        }
        makeLine(service, true);

        service.lineBreak();
        service.setTextAlignRight();
        service.setTextTypeBold();
        service.print("SubTotal =    " + subTotal);
        service.insertSpaces(5);
        service.lineBreak();
        service.setTextAlignRight();
        service.setTextTypeBold();
        service.print("Discount =    " + dis);
        service.insertSpaces(5);
        service.lineBreak();
        service.setTextAlignRight();
        service.setTextTypeBold();
        service.print("Net amount =    " + netPrice);
        service.insertSpaces(5);
        service.lineBreak();
        makeLine(service,true);

        // normalize that baby
        service.lineBreak();
        service.setTextAlignCenter();
        service.setText4Square();
        service.setTextTypeBold();
        service.printLn("Thank You ");
        service.lineBreak(1);

        makeLine(service, false);
        service.lineBreak();
        service.setTextNormal();
        service.setTextAlignLeft();

        service.printLn("Powered By HR DEVELOPERS for BIG BANG BRGR");
        service.lineBreak();
        service.setTextTypeBold();
        service.printLn("Like and follow us on facebook and instagram");
        service.setTextTypeBold();
        service.printLn("www.facebook.com/bigbngbrgr");
        service.setTextTypeBold();
        service.printLn("www.instagram.com/bigbngbrgr");
        service.lineBreak(4);
        service.cutFull();
        service.close();

    }
}
