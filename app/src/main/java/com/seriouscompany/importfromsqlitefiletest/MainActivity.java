package com.seriouscompany.importfromsqlitefiletest;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    public void readDataFromDB(View view) {
//    DatabaseManager manager = new DatabaseManager(this, "comboBox.db", null, 1);
//        String name = manager.getLgortList();
//        Toast.makeText(this, "이름: " + name , Toast.LENGTH_SHORT).show();

        DatabaseManager manager = new DatabaseManager(this, "test.db", null, 1);
        String name = manager.getName();


        Toast.makeText(this, "이름: " + name , Toast.LENGTH_SHORT).show();


//        DatabaseManager manager = new DatabaseManager(this, "test.db", null, 1);
//        String age = manager.getAge();
//
//        Toast.makeText(this, "나이: " + age , Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        copyDatabase("test.sqlite", "/data/data/com.seriouscompany.importfromsqlitefiletest/databases/test.db");
        //copyDatabase("comboBox.sqlite", "/data/data/com.seriouscompany.importfromsqlitefiletest/comboBox.db");
    }


    private void copyDatabase(String db, String dbPath) {

        if (true) {
            AssetManager am = null;
            InputStream is = null;
            BufferedInputStream bis = null;

            FileOutputStream fos = null;
            BufferedOutputStream bos = null;

            try {

                File dir = new File("/data/data/com.seriouscompany.importfromsqlitefiletest/databases/");
                if (!dir.exists()) {
                    dir.mkdir();
                }

                File f = new File(dbPath);

                if (f.exists()) {
                    f.delete();
                    f.createNewFile();
                }

                am = this.getResources().getAssets();

                is = am.open(db);
                bis = new BufferedInputStream(is);

                fos = new FileOutputStream(f);
                bos = new BufferedOutputStream(fos);

                int read = -1;
                byte[] buffer = new byte[1024];

                while ((read = bis.read(buffer, 0, 1024)) != -1) {
                    bos.write(buffer, 0, read);
                }

                bos.flush();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "디비 복사 오류: " + e.getMessage() , Toast.LENGTH_SHORT).show();
            } finally {

                try {
                    is.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                try {
                    bis.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                try {
                    if (fos != null)
                        fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    if (bos != null)
                        bos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                is = null;
                bis = null;
                Toast.makeText(MainActivity.this, "디비 복사 종료" , Toast.LENGTH_SHORT).show();
            }
        }
    }

}
