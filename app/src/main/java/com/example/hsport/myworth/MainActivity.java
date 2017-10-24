package com.example.hsport.myworth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHandler = new MyDBHandler(this, null, null, 1);

        //Fill Main Activity total Current Accounts
        String CATotal = "";
        CATotal += dbHandler.CAtotal();
        TextView MainCATotal = (TextView) findViewById(R.id.MainCATotal);
        MainCATotal.setText(CATotal);

        //Fill Main Activity total Bonds
        String BondsTotal = "";
        BondsTotal += dbHandler.Bondstotal();
        TextView MainBondsTotal = (TextView) findViewById(R.id.MainBondsTotal);
        MainBondsTotal.setText(BondsTotal);
    }
//Display Equities page
    public void DisplayEquities(View view) {
        //Building the Intent
        Intent intent = new Intent(this, Equities.class);
        //Start activity of intent = Equities.class
        startActivity(intent);
    }
// Display Current Accounts page
    public void DisplayCurrentAccounts(View view) {
        //Building the Intent
        Intent intent = new Intent(this, CurrentAccounts.class);
        //Start activity of intent = DisplayMessageActivity.class
        startActivity(intent);
    }
// Display Fixed Deposits page
    public void DisplayFixedDeposits(View view) {
        //Building the Intent
        Intent intent = new Intent(this, FixedDeposits.class);
        //Start activity of intent = FixedDeposits.class
        startActivity(intent);
    }
// Display Bonds page
    public void DisplayBonds(View view) {
        //Building the Intent
        Intent intent = new Intent(this, Bonds.class);
        //Start activity of intent = Bonds.class
        startActivity(intent);
    }
}
