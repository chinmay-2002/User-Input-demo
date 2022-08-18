package com.example.useriput2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int quantity=1;
    String summery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        quantity = quantity + 1;
        display(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        quantity = quantity - 1;
        display(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
//        int quantity = 5;
//        display(quantity);
        EditText chocolate = (EditText) findViewById(R.id.name);
        String name = chocolate.getText().toString();
        if(name==""){
            Context context = getApplicationContext();
            Toast.makeText(context, "Enter Your Name First", Toast.LENGTH_LONG).show();
            return;
        }
        CheckBox chocolatee = (CheckBox) findViewById(R.id.chocolates);
        CheckBox topping = (CheckBox) findViewById(R.id.toppings);
        Boolean c1 = chocolatee.isChecked();
        Boolean t1 = topping.isChecked();
        orderSymery(quantity ,name,c1,t1);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.text2);

        if(number<1){
            number = 1;
            quantity=1;
            Toast.makeText(getApplicationContext(),"Quantity cannot be less than 1", Toast.LENGTH_SHORT ).show();
        }

        quantityTextView.setText("" + number);
    }
    private void orderSymery(int number, String name, Boolean choco, Boolean toppo) {
        summery = "Name :" +name;
        int price=number*7;
        if(choco){
            summery+= "\nChocolates Added";
            price+=10;
        }
        if(toppo){
            summery+= "\nToppings Added";
            price+=10;
        }
        summery+="\nQuantity :" +number;
        String pricee = "\nTotal Price : $"+ price;
        summery+=pricee;
        summery+="\nThank You From Chinmay";
        displayPrice(summery);

    }
    /**
     * This method displays the given price value on the screen.
     */

    private void displayPrice(String name) {
        TextView priceTextView = (TextView) findViewById(R.id.answer);
        priceTextView.setText(name);
    }

    public void sendMail(View view) {
        EditText chocolate = (EditText) findViewById(R.id.name);
        String name = chocolate.getText().toString();
        if(name==""){
            Context context = getApplicationContext();
            Toast.makeText(context, "Enter Your Name First", Toast.LENGTH_LONG).show();
            return;
        }
        actualMail(summery);
    }

    public  void actualMail( String summery){

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, "chinmayp100@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Order Details");
        intent.putExtra(Intent.EXTRA_TEXT, summery);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    public void sendMsg(View view){
        EditText chocolate = (EditText) findViewById(R.id.name);
        String name = chocolate.getText().toString();
        if(name==""){
            Context context = getApplicationContext();
            Toast.makeText(context, "Enter Your Name First", Toast.LENGTH_LONG).show();
            return;
        }
        actualSms(summery);
    }
    public void actualSms(String msg){
        Intent smsIntent = new Intent(android.content.Intent.ACTION_VIEW);
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address","9922175875");
        smsIntent.putExtra("sms_body",msg);
        smsIntent.setFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(smsIntent);
    }



}