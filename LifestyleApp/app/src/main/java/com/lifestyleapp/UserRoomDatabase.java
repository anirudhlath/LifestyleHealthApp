package com.lifestyleapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserRoomDatabase extends RoomDatabase {

    private static volatile UserRoomDatabase instance;

    public static synchronized UserRoomDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    public UserRoomDatabase() {};

    protected static UserRoomDatabase create(final Context context) {
        return Room.databaseBuilder(
                context,
                UserRoomDatabase.class,
                "user_database").allowMainThreadQueries().build();
    }

    public abstract UserDao userDao();
}


