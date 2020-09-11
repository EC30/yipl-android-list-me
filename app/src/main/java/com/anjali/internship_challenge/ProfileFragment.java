package com.anjali.internship_challenge;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anjali.internship_challenge.data.Address;
import com.anjali.internship_challenge.data.Company;
import com.anjali.internship_challenge.data.Geo;
import com.anjali.internship_challenge.data.User;


public class ProfileFragment extends Fragment {
    private View view;
    private TextView usernameTextView, emailTextView, addressTextView,
            phoneTextView, websiteTextView, companyTextView;
    private User user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        user = (User) getArguments().getSerializable("user");

        initComponents();
        setTextValues();

        return view;
    }

    private void initComponents() {
        usernameTextView = view.findViewById(R.id.usernameTextView);
        emailTextView = view.findViewById(R.id.emailTextView);
        addressTextView = view.findViewById(R.id.addressTextView);
        phoneTextView = view.findViewById(R.id.phoneTextView);
        websiteTextView = view.findViewById(R.id.websiteTextView);
        companyTextView = view.findViewById(R.id.companyTextView);
    }

    private void setTextValues() {
        usernameTextView.setText(user.getUsername());
        emailTextView.setText(user.getEmail());
        phoneTextView.setText(user.getPhone());
        websiteTextView.setText(user.getWebsite());

        Address address = user.getAddress();
        Geo geo = address.getGeo();
        String addressText = String.format("%s, %s\n%s, %s\n%s, %s", address.getStreet(), address.getSuite(), address.getCity(), address.getZipCode(), geo.getLat(), geo.getLng());
        addressTextView.setText(addressText);

        Company company = user.getCompany();
        String companyText = String.format("%s\n%s\n%s", company.getName(), company.getCatchphrase(), company.getBs());
        companyTextView.setText(companyText);
    }
}