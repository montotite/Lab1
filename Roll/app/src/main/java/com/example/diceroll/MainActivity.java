package com.example.diceroll;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button btn;
    ImageView img ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        clicktoPlay();
    }

    private void AnhXa() {
        btn = findViewById(R.id.cbx1);
        img = findViewById(R.id.imageView);
    }

    private void clicktoPlay(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random ram = new Random();
                int number = ram.nextInt(6);
                if(number == 1){
                    img.setImageResource(R.drawable.dice_1);
                }
                if(number == 2){
                    img.setImageResource(R.drawable.dice_2);
                }
                if(number == 3){
                    img.setImageResource(R.drawable.dice_3);
                }
                if(number == 4){
                    img.setImageResource(R.drawable.dice_4);
                }
                if(number == 5){
                    img.setImageResource(R.drawable.dice_5);
                }
                if(number == 6){
                    img.setImageResource(R.drawable.dice_6);
                }
            }
        });
   }


}