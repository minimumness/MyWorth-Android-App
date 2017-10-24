package com.example.hsport.myworth;

/**
 * Created by Huan Min Gan on 8/9/2017.
 */

public class Object_Equities {
    private int _id;
    private String _EquityName;
    private String _EquityCurrency;
    private double _EquityCurrentPrice;
    private double _EquityNumberofShares;
    private double _EquityBoughtPrice;
    private String _EquityBoughtDate;

    public Object_Equities() {

    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_EquityName() {
        return _EquityName;
    }

    public void set_EquityName(String _EquityName) {
        this._EquityName = _EquityName;
    }

    public String get_EquityCurrency() {
        return _EquityCurrency;
    }

    public void set_EquityCurrency(String _EquityCurrency) {
        this._EquityCurrency = _EquityCurrency;
    }

    public double get_EquityCurrentPrice() {
        return _EquityCurrentPrice;
    }

    public void set_EquityCurrentPrice(double _EquityCurrentPrice) {
        this._EquityCurrentPrice = _EquityCurrentPrice;
    }

    public double get_EquityNumberofShares() {
        return _EquityNumberofShares;
    }

    public void set_EquityNumberofShares(double _EquityNumberofShares) {
        this._EquityNumberofShares = _EquityNumberofShares;
    }

    public double get_EquityBoughtPrice() {
        return _EquityBoughtPrice;
    }

    public void set_EquityBoughtPrice(double _EquityBoughtPrice) {
        this._EquityBoughtPrice = _EquityBoughtPrice;
    }

    public String get_EquityBoughtDate() {
        return _EquityBoughtDate;
    }

    public void set_EquityBoughtDate(String _EquityBoughtDate) {
        this._EquityBoughtDate = _EquityBoughtDate;
    }
}
