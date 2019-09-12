package com.fuellog.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.fuellog.R;
import com.fuellog.fragment.AddFuelFragment;

public class AddFuelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_add_fuel);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (bundle == null) {
            AddFuelFragment newInstance = AddFuelFragment.newInstance(getString(R.string.title_activity_add_fuel), getIntent().getLongExtra("id", -1));
            getSupportFragmentManager().beginTransaction().add(R.id.container, newInstance, newInstance.getClass().getSimpleName()).commit();
        }

    }
}
