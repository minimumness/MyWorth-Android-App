package com.example.hsport.myworth;

/**
 * Created by Huan Min Gan on 2/9/2017.
 */

public class Object_Bond {

    //Define properties of my object
    private int _id;
    private String _BondName;
    private String _BondType;
    private String _BondCurrency;
    private double _BondNominalValue;
    private double _BondMarketValue;
    private double _BondCouponRate;
    private String _BondExpiryDate;
    private String _BondUpdatedDate;

    public Object_Bond() {
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_BondName() {
        return _BondName;
    }

    public void set_BondName(String _BondName) {
        this._BondName = _BondName;
    }

    public String get_BondType() {
        return _BondType;
    }

    public void set_BondType(String _BondType) {
        this._BondType = _BondType;
    }

    public String get_BondCurrency() {
        return _BondCurrency;
    }

    public void set_BondCurrency(String _BondCurrency) {
        this._BondCurrency = _BondCurrency;
    }

    public double get_BondNominalValue() {
        return _BondNominalValue;
    }

    public void set_BondNominalValue(double _BondNominalValue) {
        this._BondNominalValue = _BondNominalValue;
    }

    public double get_BondMarketValue() {
        return _BondMarketValue;
    }

    public void set_BondMarketValue(double _BondMarketValue) {
        this._BondMarketValue = _BondMarketValue;
    }

    public double get_BondCouponRate() {
        return _BondCouponRate;
    }

    public void set_BondCouponRate(double _BondCouponRate) {
        this._BondCouponRate = _BondCouponRate;
    }

    public String get_BondExpiryDate() {
        return _BondExpiryDate;
    }

    public void set_BondExpiryDate(String _BondExpiryDate) {
        this._BondExpiryDate = _BondExpiryDate;
    }

    public String get_BondUpdatedDate() {
        return _BondUpdatedDate;
    }

    public void set_BondUpdatedDate(String _BondUpdatedDate) {
        this._BondUpdatedDate = _BondUpdatedDate;
    }
}
