package com.anjali.internship_challenge;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.anjali.internship_challenge.adapter.ToDosAdapter;
import com.anjali.internship_challenge.data.Post;
import com.anjali.internship_challenge.data.ToDos;

import java.util.ArrayList;
import java.util.Calendar;

public class ToDoActivity extends AppCompatActivity {
    private Dialog dialog;
    private ArrayList<ToDos> todos=new ArrayList<>();
    private AlertDialog dialogTodo;
    private ToDosAdapter tadpater;
    DbHelper myDB;
    ArrayList<String> task_id, task, time;
    private ToDos todo;
    private RecyclerView todo_recycler_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        ImageView add_tasks = (ImageView) findViewById(R.id.add_tasks);

        todo = (ToDos) getIntent().getSerializableExtra("todo");
        todo_recycler_view=findViewById(R.id.todo_recycler_view);

        add_tasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(view.getRootView().getContext());
                View dialogView= LayoutInflater.from(view.getRootView().getContext()).inflate(R.layout.todos_popup,null);
                final EditText toDoTask;
                final TextView date;
                Button select;
                toDoTask=dialogView.findViewById(R.id.todoTask);
                date=dialogView.findViewById(R.id.todo_date);
                select=dialogView.findViewById(R.id.selectDate);
                builder.setView(dialogView)
                        .setTitle("ToDo task")
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(toDoTask.getText().toString().isEmpty()){
                                    Toast.makeText(ToDoActivity.this, "Task cannot be empty", Toast.LENGTH_SHORT).show();
                                }else if(date.getText().toString().isEmpty()){
                                    Toast.makeText(ToDoActivity.this, "Date cannot be empty", Toast.LENGTH_SHORT).show();
                                }else{
                                    DbHelper myDB=new DbHelper(ToDoActivity.this);
                                    myDB.addBook(toDoTask.getText().toString().trim(),
                                            date.getText().toString().trim());
                                    displaydata();
                                    tadpater.notifyDataSetChanged();
                                }

                            }
                        });
                //builder.setCancelable(true);
                builder.show();

                final Calendar newCalender = Calendar.getInstance();
                select.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog dialog = new DatePickerDialog(ToDoActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, final int year, final int month, final int dayOfMonth) {

                                final Calendar newDate = Calendar.getInstance();
                                Calendar newTime = Calendar.getInstance();
                                TimePickerDialog time = new TimePickerDialog(ToDoActivity.this, new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                                        newDate.set(year,month,dayOfMonth,hourOfDay,minute,0);
                                        Calendar tem = Calendar.getInstance();
                                        Log.w("TIME",System.currentTimeMillis()+"");
                                        if(newDate.getTimeInMillis()-tem.getTimeInMillis()>0)
                                            date.setText(newDate.getTime().toString());
                                        else
                                            Toast.makeText(ToDoActivity.this,"Invalid time",Toast.LENGTH_SHORT).show();

                                    }
                                },newTime.get(Calendar.HOUR_OF_DAY),newTime.get(Calendar.MINUTE),true);
                                time.show();

                            }
                        },newCalender.get(Calendar.YEAR),newCalender.get(Calendar.MONTH),newCalender.get(Calendar.DAY_OF_MONTH));

                        dialog.getDatePicker().setMinDate(System.currentTimeMillis());
                        dialog.show();

                    }
                });

            }
        });

        myDB=new DbHelper(ToDoActivity.this);
        task_id=new ArrayList<>();
        task=new ArrayList<>();
        time=new ArrayList<>();

        displaydata();
        tadpater=new ToDosAdapter(this,task_id,task,time);
        todo_recycler_view.setAdapter(tadpater);
        todo_recycler_view.setLayoutManager(new LinearLayoutManager(this));



    }
    public void displaydata(){
        task_id.clear();
        task.clear();
        time.clear();
        Cursor cursor =myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(ToDoActivity.this, "No data", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                task_id.add(cursor.getString(0));
                task.add(cursor.getString(1));
                time.add(cursor.getString(2));
            }
        }
    }
}