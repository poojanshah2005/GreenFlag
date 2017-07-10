package com.example.shahp.greenflag.Controller;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shahp.greenflag.R;

public class MainActivity extends AppCompatActivity {
    ImageView logo;
    ImageView tick1;
    ImageView tick2;
    ImageView tick3;
    Button createButton;
    TextView msg1;
    TextView msg2;
    TextView msg3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //set content view AFTER ABOVE sequence (to avoid crash)
        this.setContentView(R.layout.activity_main);

        logo = (ImageView) findViewById(R.id.image);
        logo.setImageResource(R.drawable.logo_green_flag);

        tick1 = (ImageView) findViewById(R.id.tick1);
        tick1.setImageResource(R.drawable.tick);

        tick2 = (ImageView) findViewById(R.id.tick2);
        tick2.setImageResource(R.drawable.tick);

        tick3 = (ImageView) findViewById(R.id.tick3);
        tick3.setImageResource(R.drawable.tick);

        TextView message = (TextView) findViewById(R.id.txMessage);
        msg1= (TextView) findViewById(R.id.msg1);
        msg2= (TextView) findViewById(R.id.msg2);
        msg3= (TextView) findViewById(R.id.msg3);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/museosans500.ttf");
        message.setTypeface(custom_font);
        msg1.setTypeface(custom_font);
        msg2.setTypeface(custom_font);
        msg3.setTypeface(custom_font);

        createButton = (Button) findViewById(R.id.btn_create);
        createButton.setBackgroundResource(R.drawable.gradient_button_background);
        createButton.setText("Create an account");
    }

    public void nextActivity(View view){
        Intent intent = new Intent(MainActivity.this,Form.class);
        startActivity(intent);
    }

}
