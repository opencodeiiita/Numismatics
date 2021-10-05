package com.example.numismatics;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.google.android.material.internal.NavigationMenuItemView;

public class SignupActivity extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    public NavigationMenuItemView analytics;

    private RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        layout = (RelativeLayout) findViewById(R.id.layout);
        configureToolbar();
    }
    private void configureToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("SignUp");
        actionbar.setDisplayHomeAsUpEnabled(true);
    }
}