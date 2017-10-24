package com.example.hsport.myworth;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Huan Min Gan on 1/9/2017.
 */

class BondsAdapter extends ArrayAdapter<String> {

    private String Name;
    private String Type;
    private String Currency;
    private String NominalValue;
    private String MarketValue;
    private String CouponRate;
    private String ExpiryDate;

    public BondsAdapter(Bonds Bonds, String[] datatoAdapter) {
        super(Bonds, R.layout.custom_row_bonds, datatoAdapter);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater Inflater = LayoutInflater.from(getContext());
        View customView = Inflater.inflate(R.layout.custom_row_bonds, parent, false);

        //Get references and split array strings into
        //Name, Currency, Amount
        /*String[] Item = null;
        Item = getItem(position).split(",");
        Name = Item[0];
        Type = Item[1];
        Currency = Item[2];
        NominalValue = Item[3];
        MarketValue = Item[4];
        CouponRate = Item[5];
        ExpiryDate = Item[6];

        TextView BondName = (TextView) customView.findViewById(R.id.BondName);
        TextView BondType = (TextView) customView.findViewById(R.id.BondType);
        TextView BondCurrency = (TextView) customView.findViewById(R.id.BondCurrency);
        TextView BondNominalValue = (TextView) customView.findViewById(R.id.BondNominalValue);
        TextView BondMarketValue = (TextView) customView.findViewById(R.id.BondMarketValue);
        TextView BondCouponRate = (TextView) customView.findViewById(R.id.BondCouponRate);
        TextView BondExpiryDate = (TextView) customView.findViewById(R.id.BondExpiryDate);

        //Update the textViews from the array
        BondName.setText(Name);
        BondType.setText(Type);
        BondCurrency.setText(Currency);
        BondNominalValue.setText(NominalValue);
        BondMarketValue.setText(MarketValue);
        BondCouponRate.setText(CouponRate);
        BondExpiryDate.setText(ExpiryDate);*/

        return customView;
    }
}
