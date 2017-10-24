package com.example.hsport.myworth;

/**
 * Created by Huan Min Gan on 31/8/2017.
 */

public class Object_Account {

    //Define properties of my object
    private int _id;
    private String _BankName;
    private String _BankCurrency;
    private double _BankAmount;
    private String _BankDate;

    public Object_Account() {

    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_BankName() {
        return _BankName;
    }

    public void set_BankName(String _BankName) {
        this._BankName = _BankName;
    }

    public String get_BankCurrency() {
        return _BankCurrency;
    }

    public void set_BankCurrency(String _BankCurrency) {
        this._BankCurrency = _BankCurrency;
    }

    public double get_BankAmount() {
        return _BankAmount;
    }

    public void set_BankAmount(double _BankAmount) {
        this._BankAmount = _BankAmount;
    }

    public String get_BankDate() {
        return _BankDate;
    }

    public void set_BankDate(String _BankDate) {
        this._BankDate = _BankDate;
    }
}
