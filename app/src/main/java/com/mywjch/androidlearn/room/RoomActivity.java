package com.mywjch.androidlearn.room;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.mywjch.androidlearn.R;

import java.util.ArrayList;
import java.util.List;

public class RoomActivity extends AppCompatActivity {

    List<User> users = new ArrayList<>();
    TextView nametv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        nametv = findViewById(R.id.tv_name);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "dbuser").allowMainThreadQueries().build();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


        for (int i = 0; i < 20; i++) {
            User user = new User();
            user.setId(i);
            user.setEmail(i + "my" + "@gmail.com");
            user.setName(i + "as");
            user.setPassword(i * 20 + "pass");
            users.add(user);
        }

        db.userDao().insert(users);

        fab.setOnClickListener(view -> {
            List<User> dbusers = db.userDao().getAll();
            int size = dbusers.size();

            Snackbar.make(view, "数据库有" + size + "条数据", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        });

    }

}
