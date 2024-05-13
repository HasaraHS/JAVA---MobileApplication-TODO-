package com.example.todo_java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Date;

public class AddActivity extends AppCompatActivity {

    EditText title_input, priority_input, deadline_input, description_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title_input = findViewById(R.id.title_input);
        priority_input = findViewById(R.id.priority_input);
        deadline_input = findViewById(R.id.deadline_input);
        description_input = findViewById(R.id.description_input);
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = title_input.getText().toString().trim();
                String priority = priority_input.getText().toString().trim();
                String deadline = deadline_input.getText().toString().trim();
                String description = description_input.getText().toString().trim();

                if (title.isEmpty() || priority.isEmpty() || deadline.isEmpty() || description.isEmpty()) {
                    Toast.makeText(AddActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                    myDB.addTask(title, priority, deadline, description);
                    setResult(RESULT_OK);
                    recreate();
                    finish();
                }
            }
        });
    }
}
