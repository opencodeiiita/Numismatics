package com.example.numismatics;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.internal.NavigationMenuItemView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;


public class MainActivity extends AppCompatActivity implements MyAdapter.onDeleteClickListener {
    public DrawerLayout drawerLayout;
    public NavigationMenuItemView analytics;
    private ViewModel viewModel;
    private RelativeLayout layout;
    RoomDB database;
    private LiveData<List<TransactionEntity>> dataList;
    private EditText amount, remark, date;
    private int ADD_TRANSACTION_REQUEST = 1;
    private Button sort_popup;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.add_amount);
        remark = findViewById(R.id.add_remark);
        date = findViewById(R.id.add_date);
        sort_popup = findViewById(R.id.sort);

        layout = findViewById(R.id.layout);
        configureNavigationDrawer();
        configureToolbar();

        database = RoomDB.getInstance(this);
        //dataList=database.transactionDAO().getTransactions();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        MyAdapter adapter = new MyAdapter(this);
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        viewModel.getAllTransactions().observe(this, new Observer<List<TransactionEntity>>() {
            @Override
            public void onChanged(List<TransactionEntity> transactionEntities) {

                adapter.setTransactionEntities(transactionEntities);
            }
        });

        FloatingActionButton fab = findViewById(R.id.regular_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddTransaction.class);
                startActivityForResult(intent, ADD_TRANSACTION_REQUEST);
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
                } else if (itemId == R.id.nav_share) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT, "Hey,checkout this app,it's great for managing all your expenses as well as earnings https://github.com/opencodeiiita/Numismatics.Happy accounting!");
                    Intent chooser = Intent.createChooser(intent,null);
                    try {
                        startActivity(chooser);
                    } catch (ActivityNotFoundException e) {
                    }
                }

                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_TRANSACTION_REQUEST && resultCode == RESULT_OK) {
            String amount = data.getStringExtra(AddTransaction.EXTRA_AMOUNT);
            String remark = data.getStringExtra(AddTransaction.EXTRA_REMARK);
            String date = data.getStringExtra(AddTransaction.EXTRA_DATE);
            Double amt = Double.parseDouble(amount);
            TransactionEntity transactionEntity = new TransactionEntity(amt, date, remark);
            viewModel.insert(transactionEntity);
            Toast.makeText(this, "Transaction saved !", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Transaction not saved !", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            // Android home
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            // manage other entries if you have it ...
            case R.id.sort:
                showPopup();
                break;
        }
        return true;
    }

    private void showPopup() {
       ExPopup exPopup= new ExPopup();
       exPopup.show(getSupportFragmentManager(),"Dialog");
    }

    @Override
    public void onDeleteClickListener(TransactionEntity transactionEntity) {
        viewModel.delete(transactionEntity);
    }
}
