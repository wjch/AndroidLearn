package com.mywjch.androidlearn.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by mywjch on 2017/11/13.
 */

@Dao
public interface UserDao {

    @Query("select * from user")
    List<User> getAll();

    @Query("select * from user where name like :uname limit 1")
    User getUserByName(String uname);

    @Insert
    void insert(List<User> users);

    @Delete
    void delete(User user);
}
