package com.cook.simplerealmandroid.realm.repository.impl;

import com.cook.simplerealmandroid.app.SimpleRealmApp;
import com.cook.simplerealmandroid.model.University;
import com.cook.simplerealmandroid.realm.repository.IUniversityRepository;
import com.cook.simplerealmandroid.realm.table.RealmTable;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by roma on 16.10.15.
 */
public class UniversityRepository implements IUniversityRepository {


    @Override
    public void saveUniversity(University university, OnSaveUniversityCallback callback) {
        Realm realm = Realm.getInstance(SimpleRealmApp.getInstance());
        realm.beginTransaction();
        University u = realm.createObject(University.class);
        u.setId(UUID.randomUUID().toString());
        u.setName(university.getName());
        realm.commitTransaction();

        if (callback != null)
            callback.onSuccess();
    }

    @Override
    public void getAllUniversities(OnGetAllUniversityCallback callback) {
        Realm realm = Realm.getInstance(SimpleRealmApp.getInstance());
        realm.beginTransaction();
        RealmQuery<University> query = realm.where(University.class);
        RealmResults<University> results = query.findAll();
        realm.commitTransaction();

        if (callback != null)
            callback.onSuccess(results);
    }

    @Override
    public void getUniversityById(String id, OnGetSpecialUniversityCallback callback) {
        Realm realm = Realm.getInstance(SimpleRealmApp.getInstance());
        realm.beginTransaction();
        University result = realm.where(University.class).equalTo(RealmTable.ID, id).findFirst();
        realm.commitTransaction();

        if (callback != null)
            callback.onSuccess(result);
    }

    @Override
    public void deleteUniversityByPosition(int position, OnDeleteUniversityCallback callback) {
        Realm realm = Realm.getInstance(SimpleRealmApp.getInstance());
        realm.beginTransaction();
        RealmQuery<University> query = realm.where(University.class);
        RealmResults<University> results = query.findAll();
        results.remove(position);
        realm.commitTransaction();

        if (callback != null)
            callback.onSuccess();
    }
}