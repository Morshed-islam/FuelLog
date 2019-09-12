package com.fuellog.fragment;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;

import com.fuellog.R;

import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class AddFuelFragment_ViewBinding implements Unbinder {

    private AddFuelFragment target;
    private View view;


    @UiThread
    public AddFuelFragment_ViewBinding(final AddFuelFragment addFuelFragment, View view) {
        this.target = addFuelFragment;
        addFuelFragment.dateTv = (EditText) Utils.findRequiredViewAsType(view, R.id.textDate, "field 'dateTv'", EditText.class);
        addFuelFragment.odometerEt = (EditText) Utils.findRequiredViewAsType(view, R.id.odometer, "field 'odometerEt'", EditText.class);
        addFuelFragment.litersEt = (EditText) Utils.findRequiredViewAsType(view, R.id.liters, "field 'litersEt'", EditText.class);
        addFuelFragment.priceEt = (EditText) Utils.findRequiredViewAsType(view, R.id.price, "field 'priceEt'", EditText.class);
        addFuelFragment.noteEt = (EditText) Utils.findRequiredViewAsType(view, R.id.note, "field 'noteEt'", EditText.class);
        addFuelFragment.fullFueling = (CheckBox) Utils.findRequiredViewAsType(view, R.id.full_fueling, "field 'fullFueling'", CheckBox.class);
        view = Utils.findRequiredView(view, R.id.saveButton, "method 'actionSubmit'");
        this.view = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                addFuelFragment.actionSubmit();
            }
        });
    }


    @CallSuper
    public void unbind() {
        AddFuelFragment addFuelFragment = this.target;
        if (addFuelFragment != null) {
            this.target = null;
            addFuelFragment.dateTv = null;
            addFuelFragment.odometerEt = null;
            addFuelFragment.litersEt = null;
            addFuelFragment.priceEt = null;
            addFuelFragment.noteEt = null;
            addFuelFragment.fullFueling = null;
            this.view.setOnClickListener(null);
            this.view = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
