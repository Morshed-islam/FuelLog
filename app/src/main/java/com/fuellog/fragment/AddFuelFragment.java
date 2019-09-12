package com.fuellog.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.fuellog.Constants;
import com.fuellog.R;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFuelFragment extends Fragment {

    private static final String DATE_KEY = "date_key";
    @BindView(R.id.textDate)
    EditText dateTv;
    private FuelEntry fuelEntry;
    private FuelEntryBO fuelEntryBO;
    @BindView(R.id.full_fueling)
    CheckBox fullFueling;
    @BindView(R.id.liters)
    EditText litersEt;
    @BindView(R.id.note)
    EditText noteEt;
    @BindView(R.id.odometer)
    EditText odometerEt;
    private FuelEntry originalFE;
    @BindView(R.id.price)
    EditText priceEt;
    private Unbinder unbinder;

    public AddFuelFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_fuel, container, false);
    }

    public static AddFuelFragment newInstance(String str, long j) {
        AddFuelFragment addFuelFragment = new AddFuelFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.RESPONSE_TITLE, str);
        bundle.putLong("id", j);
        addFuelFragment.setArguments(bundle);
        return addFuelFragment;
    }


    @OnClick({R.id.saveButton})
    public void actionSubmit() {
        String obj = this.dateTv.getText().toString();
        String obj2 = this.odometerEt.getText().toString();
        String obj3 = this.litersEt.getText().toString();
        String obj4 = this.priceEt.getText().toString();
        String obj5 = this.noteEt.getText().toString();
        Long valueOf = Long.valueOf(getArguments().getLong("id"));
        if (obj.equals("") || obj2.equals("") || obj3.equals("") || obj4.equals("")) {
            Toast.makeText(getContext(), getString(R.string.fill_all_fields), 1).show();
            return;
        }
        FuelEntry fuelEntry = new FuelEntry(obj, Integer.parseInt(obj2), Double.parseDouble(obj3), Double.parseDouble(obj4), obj5, this.fullFueling.isChecked());
        if (valueOf.longValue() != -1) {
            fuelEntry.setId(valueOf.longValue());
        }
        if (!(valueOf.longValue() == -1 && this.fuelEntryBO.getList().contains(fuelEntry)) && (valueOf.longValue() == -1 || !this.fuelEntryBO.getList().contains(fuelEntry) || fuelEntry.getOdometer() == this.originalFE.getOdometer())) {
            this.fuelEntryBO.update(valueOf.longValue(), fuelEntry);
            if (valueOf.longValue() == -1) {
                FirebaseAnalyticsUtils.logFueling(Double.parseDouble(obj3));
            } else {
                FirebaseAnalyticsUtils.logEditing();
            }
            getActivity().finish();
            return;
        }
        Toast.makeText(getContext(), getString(R.string.odo_already_used), 1).show();
    }
}
