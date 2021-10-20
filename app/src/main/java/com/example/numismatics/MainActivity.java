package com.example.numismatics;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.google.android.material.internal.NavigationMenuItemView;
import com.google.android.material.navigation.NavigationView;

import java.util.List;


public class MainActivity extends AppCompatActivity{
    public DrawerLayout drawerLayout;
    public NavigationMenuItemView analytics;
    private ViewModel viewModel;
    private RelativeLayout layout;
    RoomDB database;
    private LiveData<List<TransactionEntity>> dataList;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.layout);
        configureNavigationDrawer();
        configureToolbar();

        database=RoomDB.getInstance(this);
        dataList=database.transactionDAO().getTransactions();

        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        MyAdapter adapter=new MyAdapter();
        recyclerView.setAdapter(adapter);

        viewModel= ViewModelProviders.of(this).get(ViewModel.class);
        viewModel.getAllTransactions().observe(this, new Observer<List<TransactionEntity>>() {
            @Override
            public void onChanged(List<TransactionEntity> transactionEntities) {
                // update recycler view
//                transactionEntities.add(new TransactionEntity(120.0,"19/10/21","Food"));
//                transactionEntities.add(new TransactionEntity(550.0,"19/10/21","Recharge"));
//                transactionEntities.add(new TransactionEntity(100.0,"19/10/21","Not found!"));
//                transactionEntities.add(new TransactionEntity(130.0,"19/10/21","Tailor"));
//                transactionEntities.add(new TransactionEntity(140.0,"19/10/21","Accessories"));

                adapter.setTransactionEntities(transactionEntities);
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menulist, menu);
        return true;
    }
    private void configureToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setHomeAsUpIndicator(R.drawable.ic_nav_menu);
        actionbar.setDisplayHomeAsUpEnabled(true);
    }
    private void configureNavigationDrawer() {
        drawerLayout = findViewById(R.id.my_drawer_layout);
        NavigationView navView = findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.nav_transaction) {
                    drawerLayout.closeDrawers();
                    return true;
                } else if (itemId == R.id.nav_analytics) {
                    Intent intent = new Intent(MainActivity.this, AnalyticsActivity.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();
        switch(itemId) {
            // Android home
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            // manage other entries if you have it ...
        }
        return true;
    }
}