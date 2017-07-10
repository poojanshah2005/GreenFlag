package com.example.shahp.greenflag.Realm;

import com.example.shahp.greenflag.Model.Person;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Poojan on 08/07/2017.
 */

public class RealmHelper {
    Realm realm;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    public void saveData(final Person customerModel){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(customerModel);
            }
        });
    }

    public ArrayList<Person> getCustomers(){
        ArrayList<Person> people = new ArrayList<>();
        RealmResults<Person> c = realm.where(Person.class).findAll();
        for(Person customerModel: c){
            people.add(customerModel);
        }
        return people;
    }
}
