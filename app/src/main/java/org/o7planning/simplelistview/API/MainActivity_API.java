package org.o7planning.simplelistview.API;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import org.o7planning.simplelistview.API.Todo.API_Todos;
import org.o7planning.simplelistview.API.Heros.API_Heros;
import org.o7planning.simplelistview.R;

public class MainActivity_API extends AppCompatActivity {

    Button mHeroApiButton;
    Button mTodoApiButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);

        mHeroApiButton = findViewById(R.id.hero_api_button);
        mTodoApiButton = findViewById(R.id.todo_api_button);

        mHeroApiButton.setOnClickListener(v -> {
            setHeroActivity();
        });
        mTodoApiButton.setOnClickListener(v -> {
            setTodoActivity();
        });

    }

    private void setTodoActivity() {
        Intent intent = new Intent(this, API_Todos.class);
        startActivity(intent);
    }

    private void setHeroActivity() {
        Intent intent = new Intent(this, API_Heros.class);
        startActivity(intent);
    }

}
