package io.github.escposjava.print;

// by khichihaider
// 19/09/2019

import com.fazecast.jSerialComm.SerialPort;
import com.github.terrytsai.escpos.serial.SerialFactory;
import com.github.terrytsai.escpos.serial.config.SerialConfig;

import java.io.IOException;
import java.io.OutputStream;

public class SerialPrinter implements Printer {

   private SerialPort serialPort;

   public SerialPrinter(int portNo) {
      // baud rate is 9800
      this.serialPort = SerialFactory.com(portNo, SerialConfig.CONFIG_9600_8N1());
   }

   @Override
   public void open() {
      if (!this.serialPort.openPort()) {
         //
         // this is bad but im lazy
         System.out.println("[SerialPrinter]: Port not opened");
      }
   }

   @Override
   public void write(byte[] command) {
      OutputStream stream = this.serialPort.getOutputStream();
      try {
         stream.write(command);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   @Override
   public void close() {
      if (!this.serialPort.closePort()) {
         System.out.println("[SerialPrinter]: Port not closed");
      }
   }
}
