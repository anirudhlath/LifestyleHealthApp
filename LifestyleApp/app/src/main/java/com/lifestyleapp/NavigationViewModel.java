package com.lifestyleapp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class NavigationViewModel extends AndroidViewModel {

    private LiveData<User> userMutableLiveData;
    private UserRoomDatabase profilePageDb;

    public NavigationViewModel(Application application) {

        super(application);
        profilePageDb = UserRoomDatabase.getInstance(application.getApplicationContext());
        profilePageDb.create(application.getApplicationContext());
        userMutableLiveData = profilePageDb.userDao().getUser();

    }

    // RETRIEVE DATA FROM THE REPOSITORY
    private LiveData<User> getProfileViewModelData() {
        return profilePageDb.userDao().getUser();
    }

    public String getCity() {

        String city = "Salt Lake City";  // default if there is no user

        if (getProfileViewModelData().getValue() != null) {

            return getProfileViewModelData().getValue().getCity();

        }

        return city;
    }

}
