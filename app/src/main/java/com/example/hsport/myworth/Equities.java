package com.example.hsport.myworth;

import android.app.DatePickerDialog;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Equities extends AppCompatActivity {

    MyDBHandler dbHandler;
    ListView EquitiesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equities);
        EquitiesList = (ListView) findViewById(R.id.EquitiesList);
        //Set the dbHandler object. {context = this, pass in no data for name of db,
        //pass in no data for factory, 1 for version}
        dbHandler = new MyDBHandler(this, null, null, 1);

        printEquitiesDB();

        /*******Handle Accounts List******/
        //////////////////////ADD CODE HERE/////////////////////
    }

    public void showEquitiesDialog(View view) {
        /***************************************************/
        /***************Initialise dialog*******************/
        /***************************************************/

        AlertDialog.Builder EBuilder = new AlertDialog.Builder(Equities.this);
        final View EView = getLayoutInflater().inflate(R.layout.dialog_equities, null);
        final LinearLayout Layout = (LinearLayout) EView.findViewById(R.id.EquitiesDialogLayout);

        //Handling all fields in the dialog
        Button EAddPurchase = (Button) EView.findViewById(R.id.AddAPurchase);
        Button BTNAddEquity = (Button) EView.findViewById(R.id.BTNAddEquity);
        final GridLayout Purchases = (GridLayout) EView.findViewById(R.id.Purchases);
        final Spinner ECurrency=(Spinner) EView.findViewById(R.id.EquityCurrency);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.currency_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ECurrency.setAdapter(adapter);

        //Showing the dialog
        EBuilder.setView(EView);
        final AlertDialog dialog = EBuilder.create();
        dialog.show();

        //Button to generate more fields to add additional purchases
        EAddPurchase.setOnClickListener(new View.OnClickListener() {
            int id = 0;
            int PurchaseFields = 0;
            @Override
            public void onClick(View view) {
                id++;
                if (PurchaseFields == 3){
                    Toast.makeText(Equities.this, "Max of 3 purchases. Please input another entry for more purchases", Toast.LENGTH_LONG).show();
                }
                else {
                    Purchases.addView(Create_TextView_Number_of_Shares());
                    Purchases.addView(Create_TextView_Colon());
                    Purchases.addView(Create_EditText_Number_of_Shares(id));
                    Purchases.addView(Create_TextView_Price());
                    Purchases.addView(Create_TextView_Colon());
                    Purchases.addView(Create_EditText_Price(id));
                    Purchases.addView(Create_TextView_Bought());
                    Purchases.addView(Create_TextView_Colon());
                    Purchases.addView(Create_EditText_Bought(id));
                    PurchaseFields++;
                }
            }
        });

        /***************************************************/
        /***************Handle dialog Submit button*********/
        /***************************************************/
        BTNAddEquity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Getting IDs of EditText boxes in Linear Layout
                LinearLayout DialogEquitiesLayout = (LinearLayout) EView.findViewById(R.id.EquitiesDialogLayout);
                GridLayout Purchases = (GridLayout) EView.findViewById(R.id.Purchases);
                String data = "";
                int Views = 0; //How many EditText Views are there in the dialog
                for( int i = 0; i < Purchases.getChildCount(); i++ ) {
                    //Checks how many EditText instances exist in the ViewGroup
                    if (Purchases.getChildAt(i) instanceof  EditText ||Purchases.getChildAt(i) instanceof Spinner) {
                        if (Purchases.getChildAt(i) instanceof EditText)
                            data += ((EditText) Purchases.getChildAt(i)).getText();
                        if (Purchases.getChildAt(i) instanceof Spinner)
                            data += ((Spinner) Purchases.getChildAt(i)).getSelectedItem().toString();
                        data += ";";
                        Views ++;
                    }
                }

                //HANDLER FOR IF FIELDS ARE POPULATED
                if (Views == 3 ){
                    Toast.makeText(Equities.this,
                            "Please add at least 1 stock purchase",
                            Toast.LENGTH_SHORT).show();
                }
                else if (data.contains(";;")){
                    Toast.makeText(Equities.this,
                            "Please fill in all the fields",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    BTNSubmitEquity(data);
                    dialog.dismiss();
                }
            }
        });
    }

    public void BTNSubmitEquity(String data) {
        String[] rawdata = data.split(";");

        //Setting Equities name
        String EquityName = rawdata [0];
        String EquityCurrency = rawdata [1];
        String EquityCurrentPrice = rawdata [2];

        int dataamount = rawdata.length;
        int Entries = (dataamount - 3)/3;

        Toast.makeText(Equities.this, "data: " + data, Toast.LENGTH_LONG).show();
        Toast.makeText(Equities.this, "There is this many entries " + Entries, Toast.LENGTH_LONG).show();

        for (int i = 1; i<=Entries; i++){
            //Make correct reference to array of data passed
            int REFNumberofShares = 3*i;
            int REFPriceofShares = 3*i +1;
            int REFBoughtDateofShares = 3*i +2;

            Object_Equities Equity = new Object_Equities();
            Equity.set_EquityName(EquityName);
            Equity.set_EquityCurrency(EquityCurrency);
            Equity.set_EquityCurrentPrice(Double.parseDouble(EquityCurrentPrice));
            Equity.set_EquityNumberofShares(Double.parseDouble(rawdata[REFNumberofShares]));//Error is occuring here.
            Equity.set_EquityBoughtPrice(Double.parseDouble(rawdata[REFPriceofShares]));
            Equity.set_EquityBoughtDate(rawdata[REFBoughtDateofShares]);

            dbHandler.DBaddEquity(Equity);
        }
        printEquitiesDB();
    }

    public void printEquitiesDB() {
        //Initialise string of data
        String data = "";
        data += dbHandler.EquitiesdatabaseToString();
        if (data == ""){
            data = "Please add a Equity, , , , , ";
        }

        String[] datatoAdapter = null;
        datatoAdapter = data.split(";");

        ListAdapter adapter = new EquitiesAdapter(this, datatoAdapter);
        EquitiesList.setAdapter(adapter);
    }

    /*********************************ADD MORE ROWS INTO THE DIALOG FOR MORE SHARE PURCHASES ***********************************/

    private TextView Create_TextView_Number_of_Shares() {
        TextView TShares = new TextView(this);
        TShares.setText("Shares:");
        return TShares;
    }

    private TextView Create_TextView_Price() {
        TextView TPrice = new TextView(this);
        TPrice.setText("Price:");
        return TPrice;
    }

    private TextView Create_TextView_Bought() {
        TextView TBought = new TextView(this);
        TBought.setText("Bought on:");
        return TBought;
    }

    private TextView Create_TextView_Colon(){
        TextView Colon = new TextView(this);
        Colon.setText(":");
        return Colon;
    }

    private EditText Create_EditText_Number_of_Shares(int i) {
        //Set id as 1i where 1 denotes it is number of shares
        int id = i + 10;
        EditText ENumber = new EditText(this);
        ENumber.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
        ENumber.setId(i);
        return ENumber;
    }

    private EditText Create_EditText_Price(int i) {
        //Set id as 2i where 2 denotes it is a Price
        int id = i + 20;
        EditText EPrice = new EditText(this);
        EPrice.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
        EPrice.setId(id);
        return EPrice;
    }

    private EditText Create_EditText_Bought(int i) {
        //Set id as 3i where 3 denotes it is a Bought date
        final EditText EBought = new EditText(this);

        int id = i + 30;//Set ID of the EditText field
        EBought.setId(id);

        GridLayout.LayoutParams params = new GridLayout.LayoutParams();//Set Layout Params
        params.setMargins(0,0,0,5);
        EBought.setLayoutParams(params);

        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "dd/MM/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
                EBought.setText(sdf.format(myCalendar.getTime()));
            }

        };//Declare dialog and actions
                                                                                                      // when dialog is clicked on

        EBought.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(Equities.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        return EBought;
    }
}

