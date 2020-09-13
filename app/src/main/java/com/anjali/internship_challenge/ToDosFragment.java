package com.anjali.internship_challenge;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.anjali.internship_challenge.adapter.ToDosAdapter;
import com.anjali.internship_challenge.data.ToDos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ToDosFragment extends Fragment {
    private Dialog dialog;
    private ArrayList<ToDos> todos=new ArrayList<>();
    private AlertDialog dialogTodo;
    private ToDosAdapter tadpater;
    DbHelper myDB;
    ArrayList<String> task_id, task, time;
    private ToDos todo;
    private RecyclerView todo_recycler_view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_to_dos, container, false);
        ImageView add_tasks = (ImageView) view.findViewById(R.id.add_tasks);


        todo = (ToDos) getArguments().getSerializable("todo");
        todo_recycler_view=view.findViewById(R.id.todo_recycler_view);

        add_tasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(view.getRootView().getContext());
                View dialogView=LayoutInflater.from(view.getRootView().getContext()).inflate(R.layout.todos_popup,null);
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
                                DbHelper myDB=new DbHelper(getContext());
                                myDB.addBook(toDoTask.getText().toString().trim(),
                                        date.getText().toString().trim());
                            }
                        });
                //builder.setCancelable(true);
                builder.show();

                final Calendar newCalender = Calendar.getInstance();
                select.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, final int year, final int month, final int dayOfMonth) {

                                final Calendar newDate = Calendar.getInstance();
                                Calendar newTime = Calendar.getInstance();
                                TimePickerDialog time = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                                        newDate.set(year,month,dayOfMonth,hourOfDay,minute,0);
                                        Calendar tem = Calendar.getInstance();
                                        Log.w("TIME",System.currentTimeMillis()+"");
                                        if(newDate.getTimeInMillis()-tem.getTimeInMillis()>0)
                                            date.setText(newDate.getTime().toString());
                                        else
                                            Toast.makeText(getContext(),"Invalid time",Toast.LENGTH_SHORT).show();

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

        myDB=new DbHelper(getContext());
        task_id=new ArrayList<>();
        task=new ArrayList<>();
        time=new ArrayList<>();

        displaydata();
        tadpater=new ToDosAdapter(getContext(),task_id,task,time);
        todo_recycler_view.setAdapter(tadpater);
        todo_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        tadpater.notifyDataSetChanged();


        return view;
    }


    void displaydata(){
        Cursor cursor =myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(getContext(), "No data", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                task_id.add(cursor.getString(0));
                task.add(cursor.getString(1));
                time.add(cursor.getString(2));
            }
        }
    }
}