package com.anjali.internship_challenge.adapter;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.anjali.internship_challenge.DbHelper;
import com.anjali.internship_challenge.R;
import com.anjali.internship_challenge.data.ToDos;

import java.util.ArrayList;
import java.util.Calendar;

public class ToDosAdapter extends RecyclerView.Adapter<ToDosAdapter.ViewHolder> {
     Context context;
     ArrayList task_id, task, time;
    int position;
    String id, tasks,times;

    public ToDosAdapter( Context context, ArrayList task_id, ArrayList task, ArrayList time) {
        this.context = context;
        this.task_id = task_id;
        this.task = task;
        this.time = time;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflator=LayoutInflater.from(context);
        View view = inflator.inflate(R.layout.layout_todos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        this.position=position;
        holder.taskIdTextView.setText(String.valueOf(task_id.get(position)));
        holder.taskTextView.setText(String.valueOf(task.get(position)));
        holder.dateTextView.setText(String.valueOf(time.get(position)));
        holder.myLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(view.getRootView().getContext());
                View dialogView=LayoutInflater.from(view.getRootView().getContext()).inflate(R.layout.todos_popup,null);
                final EditText toDoTask;
                final TextView date;
                Button select;
                toDoTask=dialogView.findViewById(R.id.todoTask);
                date=dialogView.findViewById(R.id.todo_date);
                select=dialogView.findViewById(R.id.selectDate);
                id=String.valueOf(task_id.get(position));
                tasks=String.valueOf(task.get(position));
                times=String.valueOf(time.get(position));
                toDoTask.setText(tasks);
                date.setText(times);
                builder.setView(dialogView)
                        .setTitle("ToDo task")
                        .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                AlertDialog.Builder builder1=new AlertDialog.Builder(context);
                                builder1.setTitle("Confirmation !!");
                                builder1.setMessage("Do you want to delete ?");
                                builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        DbHelper dbHelper = new DbHelper(view.getContext());
                                       int results=dbHelper.removePlace(String.valueOf(task_id.get(position)));
                                       
                                       if(results>0){
                                           Toast.makeText(view.getContext(), "Deleted succesfully", Toast.LENGTH_SHORT).show();
                                           dbHelper.removePlace(String.valueOf(results));
                                           notifyDataSetChanged();
                                       }
                                       else{
                                           Toast.makeText(view.getContext(), "failed", Toast.LENGTH_SHORT).show();
                                       }

                                    }
                                });
                                builder1.setNegativeButton("No", null);
                                builder1.setCancelable(false);
                                builder1.show();


                            }
                        })
                        .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                DbHelper myDB=new DbHelper(view.getContext());
                                tasks=toDoTask.getText().toString().trim();
                                times=date.getText().toString().trim();

                                myDB.updateData(id,tasks,times);
                                notifyDataSetChanged();
                            }
                        });

                builder.show();
                final Calendar newCalender = Calendar.getInstance();
                select.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog dialog = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, final int year, final int month, final int dayOfMonth) {

                                final Calendar newDate = Calendar.getInstance();
                                Calendar newTime = Calendar.getInstance();
                                TimePickerDialog time = new TimePickerDialog(view.getContext(), new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                                        newDate.set(year,month,dayOfMonth,hourOfDay,minute,0);
                                        Calendar tem = Calendar.getInstance();
                                        Log.w("TIME",System.currentTimeMillis()+"");
                                        if(newDate.getTimeInMillis()-tem.getTimeInMillis()>0)
                                            date.setText(newDate.getTime().toString());
                                        else
                                            Toast.makeText(view.getContext(), "Invalid time", Toast.LENGTH_SHORT).show();

                                    }
                                },newTime.get(Calendar.HOUR_OF_DAY),newTime.get(Calendar.MINUTE),true);
                                time.show();

                            }
                        },newCalender.get(Calendar.YEAR),newCalender.get(Calendar.MONTH),newCalender.get(Calendar.DAY_OF_MONTH));

                        dialog.getDatePicker().setMinDate(System.currentTimeMillis());
                        dialog.show();

                    }
                });

//                intent.putExtra("id",String.valueOf(task_id.get(position)));
//                intent.putExtra("task",String.valueOf(task.get(position)));
//                intent.putExtra("time",String.valueOf(time.get(position)));
//                activity.startActivityForResult(intent,1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return task_id.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView userCardView;
        TextView taskTextView, dateTextView, taskIdTextView;
        LinearLayout myLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userCardView = itemView.findViewById(R.id.userCardView);
            taskTextView = itemView.findViewById(R.id.taskTextView);
            dateTextView=itemView.findViewById(R.id.dateTextView);
            taskIdTextView=itemView.findViewById(R.id.taskidTextView);
            myLayout=itemView.findViewById(R.id.myLayout);
        }
    }


}
