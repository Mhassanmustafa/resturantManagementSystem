package com.system.Tasks;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.util.IOUtil;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.system.Message.Messages;
import com.system.Queries.Query;
import com.system.SqlBackup.SqlBackUp;
import com.system.services.SqlConnectionServices;
import com.victorlaerte.asynctask.AsyncTask;
import javafx.scene.control.ProgressBar;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Statement;

public class SqlBackupTask extends AsyncTask<Object, Double, Boolean> {

    private static final String DEVELOPER_TOKEN = "k5OI9gvwWsAAAAAAAAAADC-VCglptlR3nhA1Bhs-EYUjVv8k-e5mFbD5qtqiTiRb";
    private void printProgress(long uploaded, long size) {
        publishProgress((100 * (uploaded / (double) size))/100);

    }

    private ProgressBar progressBar;
    public SqlBackupTask(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    @Override
    public void onPreExecute() {
        Messages.getAlert("Backup is going to start please wait for finished message");
    }

    public void makeBackupOnDisk(){
        try {
            Connection connection = SqlConnectionServices.getConnection();
            Statement stmt = connection.createStatement();
            stmt.execute(Query.sqlBackupQuery);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public Boolean doInBackground(Object[] objects) {

        SqlBackUp sqlBackUp = new SqlBackUp();

            if(sqlBackUp.checkFileisPresent()){
                if(sqlBackUp.delfile()){
                    makeBackupOnDisk();
                    try {


                        if (sqlBackUp.checkFilePresent()) {
                            Thread.sleep(1000);
                            DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/hassan").build();
                            DbxClientV2 client = new DbxClientV2(config, DEVELOPER_TOKEN);
                            File file = new File("C:\\Program Files\\Microsoft SQL Server\\MSSQL12.MSSQLSERVER\\MSSQL\\Backup\\data_database.bak");
                            // Upload "test.txt" to Dropbox
                            try (InputStream in = new FileInputStream("C:\\Program Files\\Microsoft SQL Server\\MSSQL12.MSSQLSERVER\\MSSQL\\Backup\\data_database.bak")) {
                                IOUtil.ProgressListener progressListener = l -> printProgress(l, file.length());
                                FileMetadata metadata = client.files().uploadBuilder("/data_database.bak")
                                        .uploadAndFinish(in,progressListener);

                            }
                        } else {
                            Messages.getWarning("operation unsucess");
                            System.out.println("operation unsuccess");
                        }
                    }catch (Exception exp){
                        Messages.getWarning("Operation unsuccessfull");
                        exp.printStackTrace();
                        return false;
                    }
                }
            }else {
                makeBackupOnDisk();
                try {
                    if (sqlBackUp.checkFilePresent()) {
                        Thread.sleep(1000);
                        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/hassan").build();
                        DbxClientV2 client = new DbxClientV2(config, DEVELOPER_TOKEN);
                        File file = new File("C:\\Program Files\\Microsoft SQL Server\\MSSQL12.MSSQLSERVER\\MSSQL\\Backup\\data_database.bak");
                        // Upload "test.txt" to Dropbox
                        try (InputStream in = new FileInputStream("C:\\Program Files\\Microsoft SQL Server\\MSSQL12.MSSQLSERVER\\MSSQL\\Backup\\data_database.bak")) {
                            IOUtil.ProgressListener progressListener = l -> printProgress(l, file.length());
                            FileMetadata metadata = client.files().uploadBuilder("/data_database.bak")
                                    .uploadAndFinish(in,progressListener);
                            System.out.println(metadata.toStringMultiline());
                        }
                    } else {
                        Messages.getWarning("operation unsuccess");
                        System.out.println("operation unsuccess");
                    }
                }catch (Exception e){
                    Messages.getWarning("Operation unsuccessfull");
                    e.printStackTrace();
                    return false;
                }
            }



        return true;
    }

    @Override
    public void onPostExecute(Boolean o) {
        Messages.getAlert("Backup to cloud is finished");
        Messages.getLogInfo("Back up is maked");
    }

    @Override
    public void progressCallback(Double[] objects) {
        this.progressBar.setProgress((double) objects[0]);

    }
}
