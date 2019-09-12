package com.fuellog.config;

import android.content.Context;
import android.preference.PreferenceManager;

import com.fuellog.R;

import java.text.DecimalFormat;

public class Config {
    public static final String consumption_mpv = "mpv";
    public static final String consumption_vp100m = "vp100m";
    public static final String consumption_vpm = "vpm";
    private static final Context context = MyApp.getAppContext();
    public static final String distanceCost_cp100d = "cp100d";
    public static final String pref_account_key = "account";
    public static final String pref_capacity_key = "capacity";
    public static final String pref_consumption_key = "consumption";
    public static final String pref_currency_key = "currency";
    public static final String pref_dateformat_key = "date_format";
    public static final String pref_default_vehicle = "default_vehicle";
    public static final String pref_distanceCost_key = "distance_cost";
    public static final String pref_distance_key = "distance";
    public static final String pref_last_ad_click_date = "last_ad_click_date";
    public static final String pref_login_on_startup = "login_on_startup";
    public static final String remote_config_ad_hide_days = "ad_hide_days";

    public static String getConsumptionType() {
        return PreferenceManager.getDefaultSharedPreferences(MyApp.getAppContext()).getString(pref_consumption_key, context.getString(R.string.defaultConsumption));
    }

    public static String getConsumptionLabel() {
        String consumptionType = getConsumptionType();
        StringBuilder stringBuilder;
        if (consumptionType.equals(consumption_vp100m)) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(getVolumeLabel());
            stringBuilder.append("/100");
            stringBuilder.append(getDistanceLabel());
            return stringBuilder.toString();
        } else if (consumptionType.equals(consumption_vpm)) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(getVolumeLabel());
            stringBuilder.append("/");
            stringBuilder.append(getDistanceLabel());
            return stringBuilder.toString();
        } else if (!consumptionType.equals(consumption_mpv)) {
            return "---";
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append(getDistanceLabel());
            stringBuilder.append("/");
            stringBuilder.append(getVolumeLabel());
            return stringBuilder.toString();
        }
    }

    public static String getDistanceCostType() {
        return PreferenceManager.getDefaultSharedPreferences(MyApp.getAppContext()).getString(pref_distanceCost_key, context.getString(R.string.defaultDistanceCost));
    }

    public static String getDistanceCostLabel() {
        if (!getDistanceCostType().equals(distanceCost_cp100d)) {
            return "---";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getCurrencyLabel());
        stringBuilder.append("/100");
        stringBuilder.append(getDistanceLabel());
        return stringBuilder.toString();
    }

    public static String format1(double d) {
        if (d == 0.0d) {
            return "---,--";
        }
        return new DecimalFormat("#0.00").format(d);
    }

    public static String format2(double d) {
        return new DecimalFormat("###,###").format(d);
    }

    public static String getCurrencyLabel() {
        return PreferenceManager.getDefaultSharedPreferences(MyApp.getAppContext()).getString(pref_currency_key, context.getString(R.string.defaultCurrency));
    }

    public static String getVolumeLabel() {
        return PreferenceManager.getDefaultSharedPreferences(MyApp.getAppContext()).getString(pref_capacity_key, context.getString(R.string.defaultCapacity));
    }

    public static String getDistanceLabel() {
        return PreferenceManager.getDefaultSharedPreferences(MyApp.getAppContext()).getString(pref_distance_key, context.getString(R.string.defaultDistance));
    }

    public static String getDateFormat() {
        return PreferenceManager.getDefaultSharedPreferences(MyApp.getAppContext()).getString(pref_dateformat_key, context.getString(R.string.defaultDateFormat));
    }

    public static boolean getLoginOnStartup() {
        return PreferenceManager.getDefaultSharedPreferences(MyApp.getAppContext()).getBoolean(pref_login_on_startup, true);
    }

    public static void setLoginOnStartup(boolean z) {
        PreferenceManager.getDefaultSharedPreferences(MyApp.getAppContext()).edit().putBoolean(pref_login_on_startup, z).apply();
    }

    public static String getDefaultVehicle() {
        return PreferenceManager.getDefaultSharedPreferences(MyApp.getAppContext()).getString(pref_default_vehicle, null);
    }

    public static void setDefaultVehicle(String str) {
        PreferenceManager.getDefaultSharedPreferences(MyApp.getAppContext()).edit().putString(pref_default_vehicle, str).apply();
    }

    public static void setLastAdClickDate(Date date) {
        PreferenceManager.getDefaultSharedPreferences(MyApp.getAppContext()).edit().putLong(pref_last_ad_click_date, date.getTime()).apply();
    }

    public static Date getLastAdClickDate() {
        return new Date(Long.valueOf(PreferenceManager.getDefaultSharedPreferences(MyApp.getAppContext()).getLong(pref_last_ad_click_date, 0)).longValue());
    }

    public static int getAdHideDays() {
        return (int) FirebaseRemoteConfig.getInstance().getLong(remote_config_ad_hide_days);
    }
}
