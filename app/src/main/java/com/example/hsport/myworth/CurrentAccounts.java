package com.example.hsport.myworth;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CurrentAccounts extends AppCompatActivity {
    MyDBHandler dbHandler;
    ListView CurrentAccountsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_accounts);
        CurrentAccountsList = (ListView) findViewById(R.id.CurrentAccountsList);

        //Set the dbHandler object. {context = this, pass in no data for name of db,
        //pass in no data for factory, 1 for version}
        dbHandler = new MyDBHandler(this, null, null, 1);

        //Show Accounts list
        printAccountsDB();

        /*******Handle Accounts List***/
        CurrentAccountsList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    //Gets the value of the item where there was a click and convert it into a String called food
                    final String Item = String.valueOf(parent.getItemAtPosition(position));
                    AlertDialog alertDialog = new AlertDialog.Builder(CurrentAccounts.this).create();
                    alertDialog.setTitle("Delete this Account?");
                    alertDialog.setMessage("Are you sure you want to remove this Account?");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "YES",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //Display the Toast Message
                                    Toast.makeText(CurrentAccounts.this, Item, Toast.LENGTH_LONG).show();
                                    String Delete[] = Item.split(",");
                                    //Initialise object
                                    Object_Account Bank = new Object_Account();
                                    //Set Object values
                                    Bank.set_BankName(Delete[0]);
                                    Bank.set_BankCurrency(Delete[1]);
                                    Bank.set_BankAmount(Double.parseDouble(Delete[2]));
                                    dbHandler.DBdeleteCurrentAccount(Bank);
                                    printAccountsDB();
                                    dialog.dismiss();
                                }
                            });

                    alertDialog.show();
                    return false;
                }
            }
        );
    }

    //Dialog method
    public void showCurrentAccountDialog(View view) {

        /***************************************************/
        /***************Initialise dialog*******************/
        /***************************************************/
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(CurrentAccounts.this);
        View mView =  getLayoutInflater().inflate(R.layout.dialog_current_account, null);

        //Handling all fields in the dialog
        final EditText mName = (EditText) mView.findViewById((R.id.CurrentAccountName));
        final EditText mAmount = (EditText) mView.findViewById((R.id.CurrentAccountAmount));
        final Spinner mCurrency=(Spinner) mView.findViewById(R.id.CurrentAccountCurrency);
        Button mSubmit = (Button) mView.findViewById(R.id.UpdateCurrentAccounts);
        //Populating the spinner:
        //Note that mView.findViewById is needed to specify to look for an Id in the inflated object
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.currency_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCurrency.setAdapter(adapter);

        //Showing the dialog
        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();

        /***************************************************/
        /***************Handle dialog Submit button*********/
        /***************************************************/
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mName.getText().toString().isEmpty() && !mAmount.getText().toString().isEmpty()) {
                    Toast.makeText(CurrentAccounts.this,
                            R.string.success_submit_CA,
                            Toast.LENGTH_SHORT).show();
                    //Execute data submission
                    BTNSubmit(mName, mAmount, mCurrency);
                    dialog.dismiss();
                } else {
                    Toast.makeText(CurrentAccounts.this,
                            R.string.error_submit,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void BTNSubmit(EditText mName, EditText mAmount, Spinner mCurrency) {
        //Initialise object
        Object_Account Bank = new Object_Account();
        //Set Object values
        Bank.set_BankName(mName.getText().toString());
        Bank.set_BankCurrency(mCurrency.getSelectedItem().toString());
        Bank.set_BankAmount(Double.parseDouble(mAmount.getText().toString()));
        Date currentDate = Calendar.getInstance().getTime(); //Get current Date from Calendar
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy"); //Specify the dateformat
        Bank.set_BankDate(sdf.format(currentDate).toString());

        //Add object to DB
        dbHandler.DBdeleteCurrentAccount(Bank);
        dbHandler.DBaddCurrentAccount(Bank);
        printAccountsDB();
    }

    public void printAccountsDB() {
        //Initialise string of data
        String data = "";
        data += dbHandler.CAdatabaseToString();
        if (data == ""){
            data = "Please add a bank, , , ";
        }

        String[] datatoAdapter = null;
        datatoAdapter = data.split(";");

        ListAdapter adapter = new CurrentAccountsAdapter(this, datatoAdapter);
        CurrentAccountsList.setAdapter(adapter);

        String total = "";
        total += dbHandler.CAtotal();

        TextView CAtotal = (TextView) findViewById(R.id.CA_Totals);
        CAtotal.setText(total);
    }
}

