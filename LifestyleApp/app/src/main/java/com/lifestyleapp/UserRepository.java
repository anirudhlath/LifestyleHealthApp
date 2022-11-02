package com.lifestyleapp;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

public class UserRepository {

    // Singleton pattern setup
    // create an object of the class
    private static UserRepository instance = new UserRepository();
    // make constructor private so the class can't be instantiated
    private UserRepository() {};
    // get the only repository in existence
    public static UserRepository getInstance() { return instance; }

    UserRoomDatabase db;

    public void createDb(Context context) {
        this.db = Room
                .databaseBuilder(
                        context,
                        UserRoomDatabase.class,
                        "user_database"
                )
                .allowMainThreadQueries()
                .build();
    }

//    public LiveData<User> getUserData(String fullName) { return db.userDao().getUser(fullName); }
    public LiveData<User> getUserData() {
        return db.userDao().getUser();
    }

    public void setUserData (String fullName, int age, String city, String country, double height, double weight, int gender, @Nullable String profilePhotoFileName, @Nullable int profilePhotoSize, @Nullable Integer steps, double bmi, double bmr, boolean sedentary) {
        User userData = new User(fullName, age, city, country, height, weight, gender, profilePhotoFileName, profilePhotoSize, steps);
        db.userDao().insert(userData);
    }

    public void update(int steps, String fullName) {
        db.userDao().update(steps, fullName);
    }
}
