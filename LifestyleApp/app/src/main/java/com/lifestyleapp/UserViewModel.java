package com.lifestyleapp;

import android.app.Application;
import android.content.Context;

import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class UserViewModel extends AndroidViewModel {

    private LiveData<User> userLiveData;
//    private UserRepository profilePageRepository;
    private UserRoomDatabase profilePageDb;

    public UserViewModel(Application application) {

        super(application);

//        profilePageRepository = UserRepository.getInstance();

//        userLiveData = profilePageRepository.getUserData();

    }

//    // FORWARD ALL OF THE DATA TO THE REPOSITORY
//    public void setProfileViewModelData(String fullName, int age, String city, String country, double height, double weight, int gender, @Nullable String profilePhotoFileName, @Nullable int profilePhotoSize, @Nullable Integer steps, double bmi, double bmr, boolean sedentary){
//        UserRepository.getInstance().setUserData(fullName, age, city, country, height, weight, gender, profilePhotoFileName, profilePhotoSize, steps, bmi, bmr, sedentary);
//    }

    // FORWARD ALL OF THE DATA TO THE REPOSITORY
    public void setProfileViewModelData(Context context, String fullName, int age, String city, String country, double height, double weight, int gender, @Nullable String profilePhotoFileName, @Nullable int profilePhotoSize, @Nullable Integer steps, double bmi, double bmr, boolean sedentary){
        User userData = new User(fullName, age, city, country, height, weight, gender, profilePhotoFileName, profilePhotoSize, steps);
        UserRoomDatabase.getInstance(context).userDao().insert(userData);
    }

    // RETRIEVE DATA FROM THE REPOSITORY
//    public LiveData<User> getProfileViewModelData() {
//        return UserRepository.getInstance().getUserData();
//    }

    public static LiveData<User> getProfileViewModelData(Context context) {
        return UserRoomDatabase.getInstance(context).userDao().getUser();
    }

}
