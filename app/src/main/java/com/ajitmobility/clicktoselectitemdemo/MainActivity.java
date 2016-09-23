package com.ajitmobility.clicktoselectitemdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ClickToSelectEditText<CustomItem> myItems;
    List<CustomItem> itemList = new ArrayList<CustomItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myItems = (ClickToSelectEditText<CustomItem>) findViewById(R.id.til_selectable);


        for (RECURRING rec : RECURRING.getAll()) {
            itemList.add(new CustomItem(rec.getName(), rec.getCode()));
        }
        myItems.setItems(itemList);
        myItems.setSelectedPosition(0);
        myItems.setText(itemList.get(0).getLabel());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public enum RECURRING {
        NONE("None", 1),
        YEARLY("Yearly", 2),
        QUATERLY("Quaterly", 3),
        MONTHLY("Monthly", 4),
        WEEKLY("Weekly", 5);
        private String name;
        private int    code;

        RECURRING(String name, int code) {
            this.name = name;
            this.code = code;
        }

        public String getName() {
            return name;
        }

        private void setName(String name) {
            this.name = name;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public static List<RECURRING> getAll() {
            List<RECURRING> list = Arrays.asList(RECURRING.values());
            return list;
        }

    }


}
