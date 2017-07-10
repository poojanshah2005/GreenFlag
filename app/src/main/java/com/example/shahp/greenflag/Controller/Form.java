package com.example.shahp.greenflag.Controller;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shahp.greenflag.Const;
import com.example.shahp.greenflag.R;
import com.example.shahp.greenflag.Service.EmailPassword;
import com.example.shahp.greenflag.Service.PasswordValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Form extends AppCompatActivity {
    TextView tvEmail;
    TextView tvPassword;
    TextView tvPasswordConfirm;
    TextView tvMessage;
    EditText txEmail;
    EditText txPassword;
    EditText  txPasswordConfirm;
    Button btnNext;
    private PasswordValidator passwordValidator ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        passwordValidator = new PasswordValidator();

        tvPassword = (TextView) findViewById(R.id.tvPassword);
        tvPasswordConfirm = (TextView) findViewById(R.id.tvPasswordConfirm);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvMessage = (TextView) findViewById(R.id.tvMessage);
        txEmail = (EditText) findViewById(R.id.txEmail);
        txPassword = (EditText) findViewById(R.id.txPassword);
        txPasswordConfirm = (EditText) findViewById(R.id.txPasswordConfirm);
        btnNext = (Button) findViewById(R.id.btnNext);

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/museosans500.ttf");
        tvPassword.setTypeface(custom_font);
        tvPasswordConfirm.setTypeface(custom_font);
//        txEmail.setText("shah@shah.com");
//        txPassword.setText("123qweasdCzxc");
//        txPasswordConfirm.setText("123qweasdCzxc");
        tvEmail.setTypeface(custom_font);
        tvMessage.setTypeface(custom_font);
        tvPassword.setTypeface(custom_font);

        btnNext.setBackgroundResource(R.drawable.gradient_button_background);

//        if( isValidEmail(tvPassword.getText().toString()))
//            tvPassword.setError( "Email is not valid" );
    }

    public void nextActivity(View view){
        Log.i("password",""+txPassword.getText().toString());
        Log.i("password",""+passwordValidator.validate(txPassword.getText().toString()));
//        Log.i("email",""+isValidEmail(txEmail.getText().toString()));

        if(isValidEmail(txEmail.getText().toString()) && txPassword.getText().toString().equals(txPasswordConfirm.getText().toString()) && txPassword.getText().toString().length()>8 && passwordValidator.validate(txPassword.getText().toString())){
            Intent intent = new Intent(Form.this,Registration.class);
            EmailPassword ep = new EmailPassword();
            ep.setEmail(txEmail.getText().toString());
            ep.setPassword(txPassword.getText().toString());
            intent.putExtra(Const.TAG_EMAIL_PASSWORD,ep);

//            intent.putExtra("email",txEmail.getText().toString());
//            intent.putExtra("password",txPassword.getText().toString());
            startActivity(intent);
        } else {
            if(!isValidEmail(txEmail.getText().toString())){
                Toast.makeText(this, "Please enter a email address", Toast.LENGTH_SHORT).show();
            }
            else if(txPassword.getText().toString().length()<8){
                Toast.makeText(this, "Passwords is too short", Toast.LENGTH_SHORT).show();
            }
            else if(!txPassword.getText().toString().equals(txPasswordConfirm.getText().toString())){
                Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show();
            }

        }

    }

    public final static boolean isValidEmail(CharSequence target) {

        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }


}
