package com.fuellog.model;

@IgnoreExtraProperties
public class FuelEntry {
    private String date;
    private boolean full;
    @Exclude
    private long id = -1;
    private double liters;
    private String note;
    private int odometer;
    @Exclude
    private FuelEntry prevFuelEntry;
    private double price;

    public FuelEntry(String str, int i, double d, double d2, String str2, boolean z) {
        setFormatedDate(str);
        this.odometer = i;
        this.liters = d;
        this.price = d2;
        this.note = str2;
        this.full = z;
        this.prevFuelEntry = null;
    }

    public FuelEntry(long j, String str, int i, double d, double d2, String str2, boolean z) {
        this.id = j;
        setFormatedDate(str);
        this.odometer = i;
        this.liters = d;
        this.price = d2;
        this.note = str2;
        this.full = z;
        this.prevFuelEntry = null;
    }

    @Exclude
    public void update(FuelEntry fuelEntry) {
        this.id = fuelEntry.id;
        this.date = fuelEntry.date;
        this.liters = fuelEntry.liters;
        this.price = fuelEntry.price;
        this.note = fuelEntry.note;
        this.full = fuelEntry.full;
    }

    @Exclude
    public int getDistance() {
        return this.prevFuelEntry != null ? this.odometer - this.prevFuelEntry.getOdometer() : 0;
    }

    @Exclude
    public int getConsumptionDistance(FuelEntry fuelEntry) {
        FuelEntry prevFuelEntry = fuelEntry.getPrevFuelEntry();
        if (prevFuelEntry == null) {
            return 0;
        }
        if (prevFuelEntry.isFull()) {
            return fuelEntry.getOdometer() - prevFuelEntry.getOdometer();
        }
        int consumptionDistance = getConsumptionDistance(prevFuelEntry);
        if (consumptionDistance == 0) {
            return 0;
        }
        return consumptionDistance + (fuelEntry.getOdometer() - prevFuelEntry.getOdometer());
    }

    @Exclude
    public double getConsumptionLiters(FuelEntry fuelEntry) {
        if (fuelEntry.getPrevFuelEntry() == null) {
            return 0.0d;
        }
        if (fuelEntry.getPrevFuelEntry().isFull()) {
            return fuelEntry.getLiters();
        }
        double consumptionLiters = getConsumptionLiters(fuelEntry.getPrevFuelEntry());
        if (consumptionLiters == 0.0d) {
            return 0.0d;
        }
        return consumptionLiters + fuelEntry.getLiters();
    }

    @Exclude
    public double getCost() {
        return this.liters * this.price;
    }

    @Exclude
    public double getConsumption() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Liters:");
        stringBuilder.append(getConsumptionLiters(this));
        stringBuilder.append(" Distance:");
        stringBuilder.append(getConsumptionDistance(this));
        Log.d("Consumption", stringBuilder.toString());
        return isFull() ? FuelUtils.calculateConsumption(getConsumptionLiters(this), (double) getConsumptionDistance(this)) : 0.0d;
    }

    @Exclude
    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    @Exclude
    public String getFormatedDate() {
        return DateUtils.dbDateToFormatted(this.date);
    }

    public void setFormatedDate(String str) {
        this.date = DateUtils.formattedToDbDate(str);
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public int getOdometer() {
        return this.odometer;
    }

    public void setOdometer(int i) {
        this.odometer = i;
    }

    public double getLiters() {
        return this.liters;
    }

    public void setLiters(double d) {
        this.liters = d;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double d) {
        this.price = d;
    }

    @Exclude
    public FuelEntry getPrevFuelEntry() {
        return this.prevFuelEntry;
    }

    public void setPrevFuelEntry(FuelEntry fuelEntry) {
        this.prevFuelEntry = fuelEntry;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String str) {
        this.note = str;
    }

    public boolean isFull() {
        return this.full;
    }

    public void setFull(boolean z) {
        this.full = z;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("FuelEntry [id=");
        stringBuilder.append(this.id);
        stringBuilder.append(", date=");
        stringBuilder.append(this.date.toString());
        stringBuilder.append(", odometer=");
        stringBuilder.append(this.odometer);
        stringBuilder.append(", liters=");
        stringBuilder.append(this.liters);
        stringBuilder.append(", price=");
        stringBuilder.append(this.price);
        stringBuilder.append(", full=");
        stringBuilder.append(this.full);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (this.odometer != ((FuelEntry) obj).odometer) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return this.odometer;
    }
}

