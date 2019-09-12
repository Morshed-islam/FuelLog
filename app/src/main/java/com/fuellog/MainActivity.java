package com.fuellog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.fuellog.fragment.FuelsFragment;
import com.fuellog.fragment.StatisticsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    static final String INTENT_FROM_LOGIN = "fromLogin";
    private static final int RC_SIGN_IN = 123;
    private static final String TAG = "NewFuelLog";
    BottomNavigationView bottomNavigationView;
    int currentFragment;
    FuelsFragment fuelsFragment;
    FrameLayout mViewPager;
    LinearLayout mainContent;
    private Menu optionsMenu;
    StatisticsFragment statisticsFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        mainContent = findViewById(R.id.main_content);

        loadFragment(new StatisticsFragment());

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                Fragment fragment;

                switch (menuItem.getItemId()) {
                    case R.id.stats:
                        fragment = new StatisticsFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.logs:
                        fragment = new FuelsFragment();
                        loadFragment(fragment);
                        return true;

                }

                return false;
            }
        });

    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_content, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
