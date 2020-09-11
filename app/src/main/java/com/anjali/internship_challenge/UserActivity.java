package com.anjali.internship_challenge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.anjali.internship_challenge.data.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserActivity extends AppCompatActivity {
    private TextView nameTextView, usernameTextView, emailTextView, addressTextView, phoneTextView, websiteTextView, companyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        final User user = (User) getIntent().getSerializableExtra("user");

        TextView nameTextView = findViewById(R.id.nameTextView);
        nameTextView.setText(user.getName());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.profileFragment:
                        selectedFragment = new ProfileFragment();
                        break;
                    case R.id.postsFragment:
                        selectedFragment = new PostsFragment();
                        break;
                    case R.id.albumsFragment:
                        selectedFragment = new AlbumsFragment();
                        break;
                    case R.id.toDosFragment:
                        selectedFragment = new ToDosFragment();
                        break;
                }

                assert selectedFragment != null;
                Bundle bundle = new Bundle();
                bundle.putSerializable("user", user);
                selectedFragment.setArguments(bundle);

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, selectedFragment).commit();
                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.profileFragment);
    }
}