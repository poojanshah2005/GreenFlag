package com.example.shahp.greenflag.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.shahp.greenflag.Model.Person;
import com.example.shahp.greenflag.R;
import com.example.shahp.greenflag.Realm.RealmHelper;

import java.util.ArrayList;

import io.realm.Realm;

public class ViewAllPeople extends AppCompatActivity {
    Realm realm;
    RealmHelper realmHelper;
    ArrayList<Person> people;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_people);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        realm = Realm.getDefaultInstance();
        realmHelper = new RealmHelper(realm);
        initializeRecyclerView();

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ViewAllPeople.this,MainActivity.class);
        startActivity(intent);
    }

    public void initializeRecyclerView(){
        recyclerView = (RecyclerView) findViewById(R.id.peopleRecyclerView);
        /**
         * Two important things required: layout, adapter
         */
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new PersonAdapter(realmHelper.getCustomers()));
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

}
