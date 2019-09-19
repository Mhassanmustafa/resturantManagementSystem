package com.system.InvoiceGenerator;



import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.system.config.Config;
import com.system.Message.Messages;
import com.system.dao.InvoicesDao;
import com.system.models.Customers;
import com.system.models.Invoices;
import io.github.escposjava.PrinterService;
import io.github.escposjava.print.Printer;
import io.github.escposjava.print.SerialPrinter;
import javafx.collections.ObservableList;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class InvoicesGenerator {

    private String makeLine() {
        String line = "";
        for (int i = 0; i < 62; i++) {
            line += '_';
        }
        return line;
    }

    public void createThermalPrint(ObservableList<Invoices> tableData, String invoiceNumber, String date, Customers customer,
                                   String netPrice, String subTotal, String discount, String amountPaid, int orderID) {
        // okay first of all
        InvoicesDao invoicesDao = new InvoicesDao();
        Printer printer = new SerialPrinter(3);
        PrinterService service = new PrinterService(printer);
        service.init();
        try {
            service.setTextAlignCenter();
//            String path = this.getClass().getResource("images/small270.png").getPath();
            String path = "G:\\resturantManagementSystem\\src\\main\\resources\\images\\small270.png";
//            System.out.println(path);
//
//            File file = new File(path);
//            if (!file.exists()) {
//                System.out.println("Not found");
//                return;
//            }
//
//            System.out.println("found");



            service.printImage(path);
            service.lineBreak(5);

            service.setTextTypeBold(); // sets the font bold
            service.printLn("Welcome to " + Config.shopName);

            service.lineBreak(6);


            service.setTextTypeBold();
            service.printLn(makeLine());

            service.setTextTypeBold();
            service.setTextSize2H();
            service.setTextTypeBold();
            service.printLn("Your Order Number Is");
            service.setTextNormal();
            service.setTextAlignCenter();
            service.lineBreak(3);
            service.setTextSize2H();
            service.printLn(Integer.toString(invoicesDao.getCallOrder()));
            service.setTextNormal();
            service.lineBreak(3);
            service.setTextTypeBold();
            service.printLn(makeLine());
            service.setTextNormal();

            service.lineBreak(2);

            service.setTextAlignLeft();
            service.print(java.time.LocalDate.now().toString());
            service.setTextAlignRight();
            service.print(java.time.LocalTime.now().toString());
            service.setTextAlignCenter();
            service.print(Integer.toString(orderID));

            service.lineBreak(3);

            service.setTextTypeBold();
            service.printLn(makeLine());

            service.setTextTypeBold();
            service.printLn("Customer Name:" + customer.getName());

            service.setTextTypeBold();
            service.printLn(makeLine());

            service.setTextAlignLeft();
            service.setTextTypeBold();
            service.print("Quantity");
            service.setTextAlignRight();
            service.setTextTypeBold();
            service.print("Price");
            service.setTextAlignCenter();
            service.setTextTypeBold();
            service.print("Name");
            service.setTextNormal();
            service.lineBreak(2);

            for (int i = 0; i < tableData.size(); i++) {
                Invoices invoices = tableData.get(i);
                service.setTextAlignLeft();

                service.print(Float.toString(invoices.getQuantity()));
                service.setTextAlignRight();
                service.print("Rs. "+Float.toString(invoices.getPrice()));
                service.setTextAlignCenter();
                service.print(invoices.getProductName());
                service.lineBreak();
            }

            service.lineBreak(2);

            service.setTextTypeBold();
            service.printLn(makeLine());
            service.setTextNormal();

            service.setTextAlignRight();
            service.printLn("Rs ." + subTotal);

            service.setTextAlignLeft();
            service.print("Cash Received" );
            service.setTextAlignRight();
            service.printLn("Rs ." + amountPaid);

            service.setTextAlignLeft();
            service.print("Discount" );
            service.setTextAlignRight();
            service.printLn("Rs ." + discount);


            service.setTextAlignLeft();
            service.print("Change" );
            service.setTextAlignRight();
            service.printLn("Rs ." + (Float.parseFloat(amountPaid) - (Float.parseFloat(subTotal) - Float.parseFloat(discount))));

            service.lineBreak(3);

            service.setTextTypeBold();
            service.printLn(makeLine());
            service.setTextNormal();

            service.setTextSize2H();
            service.setTextAlignCenter();
            service.printLn("Thank you");
            service.setTextNormal();



            service.setTextTypeBold();
            service.printLn(makeLine());
            service.setTextNormal();


            service.lineBreak(30);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            service.cutFull();
            service.close();
        }

    }


    public void createPDF(String pdfFilename, ObservableList<Invoices> tableData, String invoiceNumber, String date, Customers customer, String netPrice, String subTotal, String discount, String amountPaid) {

        try {

            OutputStream file = new FileOutputStream(new File(pdfFilename));
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, file);

            PdfPTable heading = new PdfPTable(1);
            heading.addCell(getHeadingCell(Config.shopName));
            heading.addCell(getAddress(Config.address));
            heading.addCell(getAddress(Config.contactNumber));

            PdfPTable irdTable = new PdfPTable(2);
            irdTable.addCell(getIRDCell("Invoice No"));
            irdTable.addCell(getIRDCell("Invoice Date"));
            irdTable.addCell(getIRDCell(invoiceNumber)); // pass invoice number
            irdTable.addCell(getIRDCell(date)); // pass invoice date

            PdfPTable irhTable = new PdfPTable(3);
            irhTable.setWidthPercentage(100);

            irhTable.addCell(getIRHCell("", PdfPCell.ALIGN_RIGHT));
            irhTable.addCell(getIRHCell("", PdfPCell.ALIGN_RIGHT));
            irhTable.addCell(getIRHCell("Invoice", PdfPCell.ALIGN_RIGHT));
            irhTable.addCell(getIRHCell("", PdfPCell.ALIGN_RIGHT));
            irhTable.addCell(getIRHCell("", PdfPCell.ALIGN_RIGHT));
            PdfPCell invoiceTable = new PdfPCell(irdTable);
            invoiceTable.setBorder(0);
            irhTable.addCell(invoiceTable);

            FontSelector fs = new FontSelector();
            Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 13, Font.BOLD);
            fs.addFont(font);
            Phrase bill = fs.process("Bill To"); // customer information
            Paragraph name = new Paragraph("Name : " + customer.getName());
            name.setIndentationLeft(20);
            Paragraph contact = new Paragraph("PhoneNo : " + customer.getPhoneNumber());
            contact.setIndentationLeft(20);
            Paragraph address = new Paragraph("Shop Name : " + customer.getShopName());
            address.setIndentationLeft(20);

            PdfPTable billTable = new PdfPTable(6); //one page contains 15 records
            billTable.setWidthPercentage(100);
            billTable.setWidths(new float[]{1, 2, 5, 2, 1, 2});
            billTable.setSpacingBefore(30.0f);
            billTable.addCell(getBillHeaderCell("SrNo."));
            billTable.addCell(getBillHeaderCell("Unit"));
            billTable.addCell(getBillHeaderCell("Product Name"));
            billTable.addCell(getBillHeaderCell("Unit Price"));
            billTable.addCell(getBillHeaderCell("Qty"));
            billTable.addCell(getBillHeaderCell("Amount"));
            int index = 1;
            for (int i = 0; i < tableData.size(); i++) {

                Invoices invoices = tableData.get(i);

                billTable.addCell(getBillRowCell(Integer.toString(index)));
                billTable.addCell(getBillRowCell(invoices.getUnit()));
                billTable.addCell(getBillRowCell(invoices.getProductName()));
                billTable.addCell(getBillRowCell(Float.toString(invoices.getPrice())));
                billTable.addCell(getBillRowCell(Float.toString(invoices.getQuantity())));
                billTable.addCell(getBillRowCell(Float.toString(invoices.getTotalAmount())));
                index++;

            }
            billTable.addCell(getBillRowCell(" "));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));

            billTable.addCell(getBillRowCell(" "));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));


            billTable.addCell(getBillRowCell(" "));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));

            billTable.addCell(getBillRowCell(" "));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));

            billTable.addCell(getBillRowCell(" "));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));

            billTable.addCell(getBillRowCell(" "));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));

            billTable.addCell(getBillRowCell(" "));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));

            PdfPTable validity = new PdfPTable(1);
            validity.setWidthPercentage(100);
            validity.addCell(getValidityCell(" "));
            validity.addCell(getValidityCell(" "));
            validity.addCell(getValidityCell(" "));
            validity.addCell(getValidityCell(" "));
            PdfPCell summaryL = new PdfPCell(validity);
            summaryL.setColspan(3);
            summaryL.setPadding(1.0f);
            billTable.addCell(summaryL);

            PdfPTable accounts = new PdfPTable(2);
            accounts.setWidthPercentage(100);
            accounts.addCell(getAccountsCell("Subtotal"));
            accounts.addCell(getAccountsCellR(subTotal));
            accounts.addCell(getAccountsCell("Discount If applicable"));
            accounts.addCell(getAccountsCellR(discount));
            accounts.addCell(getAccountsCell("Net Total"));
            accounts.addCell(getAccountsCellR(netPrice));
            accounts.addCell(getAccountsCell("Total Amount Paid"));
            accounts.addCell(getAccountsCellR(amountPaid));
            PdfPCell summaryR = new PdfPCell(accounts);
            summaryR.setColspan(3);
            billTable.addCell(summaryR);

            PdfPTable describer = new PdfPTable(1);
            describer.setWidthPercentage(100);
            describer.addCell(getdescCell(" "));
            describer.addCell(getdescCell("Product sold will be taken back or exchanged || Subject to product justification || Product replace with in seven Days "));

            document.open();//PDF document opened........

            document.add(heading);
            document.add(irhTable);
            document.add(bill);
            document.add(name);
            document.add(contact);
            document.add(address);
            document.add(billTable);
            document.add(describer);

            document.close();

            file.close();
            Desktop d = Desktop.getDesktop();
            d.open(new File(pdfFilename));
            Messages.getAlert("Exported to Pdf please Wait while file is opening");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private PdfPCell getHeadingCell(String text) {
        PdfPCell cell = new PdfPCell(new Paragraph(text, Config.boldFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5.0f);
        cell.setBorderColor(BaseColor.LIGHT_GRAY);
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }

    private PdfPCell getAddress(String text) {
        PdfPCell cell = new PdfPCell(new Paragraph(text, Config.simpleFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5.0f);
        cell.setBorderColor(BaseColor.LIGHT_GRAY);
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }


    private PdfPCell getIRHCell(String text, int alignment) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 16);
        /*	font.setColor(BaseColor.GRAY);*/
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setPadding(5);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private PdfPCell getIRDCell(String text) {
        PdfPCell cell = new PdfPCell(new Paragraph(text));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5.0f);
        cell.setBorderColor(BaseColor.LIGHT_GRAY);
        return cell;
    }

    private PdfPCell getBillHeaderCell(String text) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 11);
        font.setColor(BaseColor.GRAY);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5.0f);
        return cell;
    }

    private PdfPCell getBillRowCell(String text) {
        PdfPCell cell = new PdfPCell(new Paragraph(text));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5.0f);
        cell.setBorderWidthBottom(0);
        cell.setBorderWidthTop(0);
        return cell;
    }

    private PdfPCell getBillFooterCell(String text) {
        PdfPCell cell = new PdfPCell(new Paragraph(text));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5.0f);
        cell.setBorderWidthBottom(0);
        cell.setBorderWidthTop(0);
        return cell;
    }

    private PdfPCell getValidityCell(String text) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
        font.setColor(BaseColor.GRAY);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setBorder(0);
        return cell;
    }

    private PdfPCell getAccountsCell(String text) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setBorderWidthRight(0);
        cell.setBorderWidthTop(0);
        cell.setPadding(5.0f);
        return cell;
    }

    private PdfPCell getAccountsCellR(String text) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setBorderWidthLeft(0);
        cell.setBorderWidthTop(0);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setPadding(5.0f);
        cell.setPaddingRight(20.0f);
        return cell;
    }

    private PdfPCell getdescCell(String text) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
        font.setColor(BaseColor.GRAY);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(0);
        return cell;
    }
}
