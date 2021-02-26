package org.o7planning.simplelistview.DB;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.o7planning.simplelistview.DB.RoomDB.RoomDB.ActivityRoomDB;
import org.o7planning.simplelistview.DB.SQLite.ActivitySqLite;
import org.o7planning.simplelistview.R;
import org.o7planning.simplelistview.utils.NetworkUtil;
import org.o7planning.simplelistview.utils.ToastUtil;

public class MainActivity_DB extends AppCompatActivity {

    Button mSqliteDbButton;
    Button mWordRoomButton;
    Button mUserRoomButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_db_main);

       mSqliteDbButton=findViewById(R.id.sql_db_button);
       mUserRoomButton=findViewById(R.id.user_room_button);
       mWordRoomButton =findViewById(R.id.word_room_button);

       mWordRoomButton.setOnClickListener(v->{
           Intent intent=new Intent(this, ActivityRoomDB.class);
           startActivity(intent);
       });

        mSqliteDbButton.setOnClickListener(v->{
            Intent intent=new Intent(this, ActivitySqLite.class);
            startActivity(intent);
        });

        NetworkUtil.getMutableLiveDataNetworkStatus().observe(this,(internet)->{
            if(internet){
                ToastUtil.makeShortToast(this,"From LiveData Internet Avilable");
            }
            else{
                ToastUtil.makeShortToast(this,"From LiveData Internet Not Avilable");
            }
        });

    }
}
