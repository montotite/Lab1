package com.example.workingwitsqlite;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageButton addPhone ; ListView lv ;
    MyDatabase  database;
    ArrayList<PhoneBook> arr ;
    CustomAdapter customAdapter;
    int position_item = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = new MyDatabase(this,"MyPhoneBook.sqlite",null,1);
        lv=findViewById(R.id.lv_phone);
        registerForContextMenu(lv);
        addPhone = findViewById(R.id.addPhone);
        addPhone();
        displayData();
        getPotion();
    }
    public void addPhone(){
        addPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddorEdit.class);
                startActivityForResult(intent,100);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

            if(requestCode == 100 && resultCode == 200){
                Bundle bundle = data.getExtras();
                if (bundle != null){
                    String name =  bundle.getString("sendName");
                    String number = bundle.getString("sendNumber");
                    insert_into_database(name,number);
                    displayData();
                }else{
                    Toast.makeText(this,"Không có dữ liệu",Toast.LENGTH_SHORT).show();
                }
            }
            if(requestCode == 101 && resultCode == 201){
                Bundle bundle2 = new Bundle();
                bundle2 = data.getExtras();
                String get_phone =  bundle2.getString("giveback_phone");
                String get_name = bundle2.getString("giveback_name");
                if(get_phone != null && get_name != null){
                    PhoneBook phones = arr.get(position_item);
                    int id = phones.getID();
                    update_database(get_name,get_phone,id);
                    displayData();
                }
            }
    }
    public void insert_into_database(String dataFirst, String dataSecond){
        //database = new MyDatabase(this,"MyPhoneBook.sqlite",null,1);
        String query_cratetable = "CREATE TABLE IF NOT EXISTS PhoneBook(ID INTEGER PRIMARY KEY AUTOINCREMENT,UserNamePhone VARCHAR(50), UserPhoneNumber VARCHAR(12))";
        String query_insertdata = "INSERT INTO PhoneBook VALUES(null,'"+dataFirst+"','"+dataSecond+"')";
        database.myQuery(query_cratetable);
        database.myQuery(query_insertdata);
    }
    public void deletedata_into_databse(int id){
       String query_delete = "Delete from PhoneBook where ID = "+id+"";
       database.myQuery(query_delete);
    }
    public void update_database(String name, String phone, int id){
        String query_update = "UPDATE PhoneBook SET UserNamePhone = '"+name+"' , UserPhoneNumber = '"+phone+"' WHERE ID = "+id+"  ";
        database.myQuery(query_update);
    }
    public void displayData(){
        String query_getListPhoneNumber = "SELECT * FROM PhoneBook";
        MyDatabase  database = new MyDatabase(this,"MyPhoneBook.sqlite",null,1);
        Cursor cursor = database.getData(query_getListPhoneNumber);
        arr = new ArrayList<>();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String phone = cursor.getString(2);
            arr.add(new PhoneBook(id,phone,name));
        }
        customAdapter = new CustomAdapter(MainActivity.this,R.layout.show_phone,arr);
        lv.setAdapter(customAdapter);
        customAdapter.notifyDataSetChanged();
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_edit_delete,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_edit:
                Intent intent = new Intent(MainActivity.this,AddorEdit.class);
                PhoneBook phone2 = arr.get(position_item);
                Bundle bundle = new Bundle();
                bundle.putString("send_edit_name",phone2.getUserNamephone());
                bundle.putString("send_edit_phone",phone2.getPhoneNumber());
                intent.putExtras(bundle);
                startActivityForResult(intent,101);
                break;
            case R.id.item_delete:
                PhoneBook phone1 = arr.get(position_item);
                int id = phone1.getID();
                deletedata_into_databse(id);
                displayData();
                Toast.makeText(MainActivity.this, "Delete Thành Công",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }
    public void getPotion(){
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                position_item = i;
                Toast.makeText(MainActivity.this, position_item +"",Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}