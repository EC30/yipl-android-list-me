package com.anjali.internship_challenge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.anjali.internship_challenge.adapter.UserAdapter;
import com.anjali.internship_challenge.data.Address;
import com.anjali.internship_challenge.data.Company;
import com.anjali.internship_challenge.data.Geo;
import com.anjali.internship_challenge.data.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayList<User> users = new ArrayList<>();

        DownloadTask task = new DownloadTask();
        try {
            JSONArray usersArray = new JSONArray(task.execute(Api.usersAPI).get());
            for (int i = 0; i < usersArray.length(); i++) {
                JSONObject userObject = usersArray.getJSONObject(i);

                int id = userObject.getInt(Api.ID);
                String name = userObject.getString(Api.NAME);
                String username = userObject.getString(Api.USERNAME);
                String email = userObject.getString(Api.EMAIL);
                String phone = userObject.getString(Api.PHONE);
                String website = userObject.getString(Api.WEBSITE);

                JSONObject addressObject = userObject.getJSONObject(Api.ADDRESS);
                String street = addressObject.getString(Api.STREET);
                String suite = addressObject.getString(Api.SUITE);
                String city = addressObject.getString(Api.CITY);
                String zipcode = addressObject.getString(Api.ZIPCODE);

                JSONObject geoObject = addressObject.getJSONObject(Api.GEO);
                double lat = geoObject.getDouble(Api.LAT);
                double lng = geoObject.getDouble(Api.LNG);

                JSONObject companyObject = userObject.getJSONObject(Api.COMPANY);
                String companyName = companyObject.getString(Api.NAME);
                String catchphrase = companyObject.getString(Api.CATCHPHRASE);
                String bs = companyObject.getString(Api.BS);

                Geo geo = new Geo(lat, lng);
                Address address = new Address(street, suite, city, zipcode, geo);
                Company company = new Company(companyName, catchphrase, bs);
                User user = new User(id, name, username, email, address, phone, website, company);
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        UserAdapter adapter = new UserAdapter(this, users);
        RecyclerView userListRecyclerView = findViewById(R.id.userListRecyclerView);
        userListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        userListRecyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drop_down_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id == R.id.addTodo){
            Intent intent=new Intent(MainActivity.this,ToDoActivity.class);
            startActivity(intent);
        }

        return true;

    }
}