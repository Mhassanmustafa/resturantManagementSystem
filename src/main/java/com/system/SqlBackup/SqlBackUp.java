package com.system.SqlBackup;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.GetMetadataErrorException;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;
import com.system.config.Config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class SqlBackUp  {
    private static final String DEVELOPER_TOKEN = "k5OI9gvwWsAAAAAAAAAADC-VCglptlR3nhA1Bhs-EYUjVv8k-e5mFbD5qtqiTiRb";
  public void getSqlDataBackup() throws Exception{

      DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/hassan").build();
      DbxClientV2 client = new DbxClientV2(config, DEVELOPER_TOKEN);

      // Upload "test.txt" to Dropbox
      try (InputStream in = new FileInputStream("C:\\Program Files\\Microsoft SQL Server\\MSSQL12.MSSQLSERVER\\MSSQL\\Backup\\data_database.bak")) {
          FileMetadata metadata = client.files().uploadBuilder("/data_database.bak")
                  .uploadAndFinish(in);
          System.out.println( metadata);
      }
  }

  public boolean checkInternetIsConnected(){
      try {
          final URL url = new URL("http://www.google.com");
          final URLConnection conn = url.openConnection();
          conn.connect();
          conn.getInputStream().close();
          return true;
      } catch (MalformedURLException e) {
          throw new RuntimeException(e);
      } catch (IOException e) {
          return false;
      }
  }

  public boolean checkFilePresent() throws Exception{
      DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/hassan").build();
      DbxClientV2 client = new DbxClientV2(config, DEVELOPER_TOKEN);

      try {
          client.files().getMetadata("/data_database.bak");
          client.files().deleteV2("/data_database.bak");
          return true;
      } catch (GetMetadataErrorException e){
          if (e.errorValue.isPath() && e.errorValue.getPathValue().isNotFound()) {
            return true;
          } else {
//            client.files().deleteV2("/data_database.bak");
//            return true;
          }
      }
      return false;
  }

  public  boolean checkFileisPresent(){
        File f = new File("C:\\Program Files\\Microsoft SQL Server\\MSSQL12.MSSQLSERVER\\MSSQL\\Backup\\data_database.bak");
        if(f.exists()){
            return true;
        }else {
            return false;
        }
    }

    public boolean delfile(){
        File f = new File("C:\\Program Files\\Microsoft SQL Server\\MSSQL12.MSSQLSERVER\\MSSQL\\Backup\\data_database.bak");
        if(f.delete()){
            return true;
        }else {
            return false;
        }
    }
}
