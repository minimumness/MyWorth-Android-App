package com.example.hsport.myworth;

import android.widget.ArrayAdapter;
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
 * Created by Huan Min Gan on 14/9/2017.
 */

class EquitiesAdapter extends ArrayAdapter<String> {

    private String Name;
    private String Currency;
    private String CurrentPrice;
    private String NumberofShares;
    private String BoughtPrice;
    private String BoughtDate;

    public EquitiesAdapter(Equities Equities, String[] datatoAdapter) {
        super(Equities, R.layout.custom_row_equities, datatoAdapter);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater Inflater = LayoutInflater.from(getContext());
        View customView = Inflater.inflate(R.layout.custom_row_equities, parent, false);

        //Get references and split array strings into
        //Name, Currency
        String[] Item = null;
        Item = getItem(position).split(",");
        Name = Item[0];
        Currency = Item[1];
        CurrentPrice = Item[2];
        NumberofShares = Item[3];
        BoughtPrice = Item[4];
        BoughtDate = Item[5];

        TextView EquityName = (TextView) customView.findViewById(R.id.EquityName);
        TextView EquityCurrency = (TextView) customView.findViewById(R.id.EquityCurrency);
        TextView EquityCurrentPrice = (TextView) customView.findViewById(R.id.EquityCurrentPrice);
        TextView EquityNumberofShares = (TextView) customView.findViewById(R.id.EquityNumberofShares);
        TextView EquityBoughtPrice = (TextView) customView.findViewById(R.id.EquityBoughtPrice);
        TextView EquityBoughtDate = (TextView) customView.findViewById(R.id.EquityBoughtDate);

        //Update the textViews from the array
        EquityName.setText(Name);
        EquityCurrency.setText(Currency);
        EquityCurrentPrice.setText(CurrentPrice);
        EquityNumberofShares.setText(NumberofShares);
        EquityBoughtPrice.setText(BoughtPrice);
        EquityBoughtDate.setText(BoughtDate);

        return customView;
    }
}