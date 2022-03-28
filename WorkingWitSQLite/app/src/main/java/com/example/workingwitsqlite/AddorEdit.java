package com.example.workingwitsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddorEdit extends AppCompatActivity {
    EditText edit_phoneName,edit_userPhoneName;
    Button btn_Add,btn_Back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_phone);
        edit_phoneName = findViewById(R.id.edit_Phonename);
        edit_userPhoneName = findViewById(R.id.edit_userPhonename);
        btn_Add = findViewById(R.id.btnAdd);
        btn_Back = findViewById(R.id.btnBack);
        sendData();
        backHome();
        getData_editInfor();
    }
    public void sendData(){
        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("sendNumber",edit_phoneName.getText().toString());
                bundle.putString("sendName",edit_userPhoneName.getText().toString());
                intent.putExtras(bundle);
                setResult(200,intent);
                finish();
            }
        });
    }
    public void backHome(){
        btn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void getData_editInfor(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            String name = bundle.getString("send_edit_name");
            String phone = bundle.getString("send_edit_phone");
            edit_phoneName.setText(name);
            edit_userPhoneName.setText(phone);
            btn_Add.setText("Edit");
            btn_Add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(name.equals(edit_phoneName.getText().toString()) && phone.equals(edit_userPhoneName.getText().toString())){
                         finish();
                    }else{
                        Intent intent2 = new Intent();
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("giveback_phone",edit_phoneName.getText().toString());
                        bundle1.putString("giveback_name",edit_userPhoneName.getText().toString());
                        intent2.putExtras(bundle1);
                        setResult(201,intent2);
                        finish();
                    }
                }
            });
        }
    }
}