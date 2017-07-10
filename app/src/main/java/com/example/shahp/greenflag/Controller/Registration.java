package com.example.shahp.greenflag.Controller;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.codetroopers.betterpickers.datepicker.DatePickerBuilder;
import com.codetroopers.betterpickers.datepicker.DatePickerDialogFragment;
import com.example.shahp.greenflag.Const;
import com.example.shahp.greenflag.Model.Person;
import com.example.shahp.greenflag.R;
import com.example.shahp.greenflag.Realm.RealmHelper;
import com.example.shahp.greenflag.Service.EmailPassword;
import com.example.shahp.greenflag.Service.GetCountryList;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class Registration extends AppCompatActivity implements DatePickerDialogFragment.DatePickerDialogHandler {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    String mGenderSelected;

    EditText etName;
    EditText etAge;
    Spinner spCountry;
    EditText etAddress;

    Button btnPhoto;
    Bitmap photo;
    byte[] photobyte;
    RadioButton rbMale;
    RadioButton rbFemale;
    RadioButton rbOther;
    EmailPassword ep;
    String email;
    String password;
    String coutry;
    Realm realm;
    RealmHelper realmHelper;
    private ImageView imageView;
    List<String> spinnerArray;

    Button btnDate;
    TextView tvDate;
    private int _year, _month, _day;
    static final int DIALOO_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        realm = Realm.getDefaultInstance();
        realmHelper = new RealmHelper(realm);

//        String email = getIntent().getStringExtra("email");
//        String password = getIntent().getStringExtra("password");
        EmailPassword ep = (EmailPassword)getIntent().getSerializableExtra(Const.TAG_EMAIL_PASSWORD);
        email = ep.getEmail();
        password = ep.getPassword();


        imageView = (ImageView)findViewById(R.id.ivPhoto);
        btnDate = (Button)findViewById(R.id.btnDate);
        etName = (EditText)findViewById(R.id.etName);
        etAge = (EditText)findViewById(R.id.etAge);
        etAddress = (EditText)findViewById(R.id.etAddress);
        tvDate = (TextView)findViewById(R.id.tvDate);
        spCountry = (Spinner)findViewById(R.id.spCountry);
        btnPhoto = (Button)findViewById(R.id.btnPhoto);
        btnDate.setBackgroundResource(R.drawable.gradient_button_background);
        btnPhoto.setBackgroundResource(R.drawable.gradient_button_background);
        rbMale = (RadioButton) findViewById(R.id.rbMale);
        rbFemale = (RadioButton) findViewById(R.id.rbFemale);
        rbOther = (RadioButton) findViewById(R.id.rbOther);

//        ArrayList<String> countries = GetCountryList.getList();
//        for(String s: countries){
//            System.out.println("countries: " + s);
//
//        }
        spinnerArray = new ArrayList<>();
        spinnerArray.add("Please Select your Country");
        spinnerArray.addAll(GetCountryList.getList());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int m = -1;
                int d = -1;
                int y = -1;
                DatePickerBuilder dpb = new DatePickerBuilder()
                        .setFragmentManager(getSupportFragmentManager())
                        .setStyleResId(R.style.BetterPickersDialogFragment)
                        .setYearOptional(true)
                        .setMonthOfYear(m)
                        .setDayOfMonth(d)
                        .setYear(y);

                dpb.show();
            }
        });

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCountry.setAdapter(adapter);
        spCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if(position > 0){
                    coutry = spinnerArray.get(position);
                }else{
                    // show toast select gender
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Person person = new Person();
                if(email != null)person.setEmail(email);
                if(mGenderSelected != null)person.setGender(mGenderSelected);
                if(etName.getText().toString() != null)person.setName(etName.getText().toString());
                if(etAge.getText().toString() != null)person.setAge(etAge.getText().toString());
                if(coutry != null)person.setCoutry(coutry);
                if(etAddress.getText() != null)person.setAddress(etAddress.getText().toString());
                if(tvDate.getText().toString() != null)person.setDate(tvDate.getText().toString());
                if(photo != null)person.setPhoto(photobyte);
                realmHelper.saveData(person);
                Intent intent = new Intent(Registration.this, ViewAllPeople.class);
                startActivity(intent);
            }
        });

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/museosans500.ttf");
        etName.setTypeface(custom_font);
        etAge.setTypeface(custom_font);
        etAddress.setTypeface(custom_font);
        tvDate.setTypeface(custom_font);

        rbMale.setTypeface(custom_font);
        rbFemale.setTypeface(custom_font);
        rbOther.setTypeface(custom_font);
    }

    public void showDialog(View view ){
        showDialog(DIALOO_ID);
    }

    private DatePickerDialog.OnDateSetListener dpickerListner = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            _year = year;
            _month = month;
            _day = day;
            StringBuilder sb = new StringBuilder();
            sb.append(_day);
            sb.append(" / ");
            sb.append(_month);
            sb.append(" / ");
            sb.append(_year);
            tvDate.setText(sb.toString());

        }
    };

    @Override
    protected Dialog onCreateDialog(int id){
        if(id == DIALOO_ID){
            return new DatePickerDialog(Registration.this,dpickerListner, _year,_month,_day);
        }
        return  null;
    }


    public void onGenderSelectionCLicked(View view){
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.rbMale:
                if (checked){
                    mGenderSelected = Const.TAG_MALE;
                }
                break;
            case R.id.rbFemale:
                if (checked){
                    mGenderSelected = Const.TAG_FEMALE;
                }
                break;
            case R.id.rbOther:
                if (checked){
                    mGenderSelected = Const.TAG_OTHER;
                }
                break;
        }
        Log.i("Gender",mGenderSelected);
    }

    public void getImage(View view){
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            photo = thumbnail;
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
            photobyte = stream.toByteArray();
            imageView.setImageBitmap(photo);
        }
    }

    public void saveInformation(View view){
        String mGenderSelected = this.mGenderSelected ;

        String etName = this.etName.getText().toString() ;
        String etAge = this.etAge.getText().toString();
        String spCountry = this.spCountry.getSelectedItem().toString() ;
        String etAddress = this.etAddress.getText().toString() ;
        Bitmap ivPhoto = this.photo;
        String etDate = this.tvDate.getText().toString();
    }

    @Override
    public void onDialogDateSet(int reference, int year, int month, int day) {
        _year = year;
        _month = month;
        _day = day;
        StringBuilder sb = new StringBuilder();
        sb.append(_day);
        sb.append(" / ");
        sb.append(_month);
        sb.append(" / ");
        sb.append(_year);
        tvDate.setText(sb.toString());
    }

}
