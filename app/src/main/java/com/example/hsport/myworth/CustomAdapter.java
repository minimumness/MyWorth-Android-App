package com.example.hsport.myworth;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

/**
 * Created by Huan Min Gan on 31/8/2017.
 */

class CurrentAccountsAdapter extends ArrayAdapter<String> {
    private String AccountName;
    private String AccountCurrency;
    private String AccountAmount;
    private String AccountDate;

    public CurrentAccountsAdapter(CurrentAccounts currentAccounts, String[] datatoAdapter) {
        super(currentAccounts, R.layout.custom_row_current_accounts, datatoAdapter);
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater Inflater = LayoutInflater.from(getContext());
        View customView = Inflater.inflate(R.layout.custom_row_current_accounts, parent, false);

        //Get references and split array strings into
        //Name, Currency, Amount
        String[] Item = null;
        Item = getItem(position).split(",");
        AccountName = Item[0];
        AccountCurrency = Item[1];
        AccountAmount = Item[2];
        AccountDate = Item[3];

        TextView Name = (TextView) customView.findViewById(R.id.BankName);
        TextView Currency = (TextView) customView.findViewById(R.id.BankCurrency);
        TextView Amount = (TextView) customView.findViewById(R.id.BankAmount);
        TextView Date = (TextView) customView.findViewById(R.id.UpdatedDate);

        //Update the textViews from the array
        Name.setText(AccountName);
        Currency.setText(AccountCurrency);
        Amount.setText(AccountAmount);
        Date.setText(AccountDate);

        return customView;
    }
}
