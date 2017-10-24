package com.example.hsport.myworth;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
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

public class Bonds extends AppCompatActivity {

    MyDBHandler dbHandler;
    ListView BondsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonds);
        BondsList = (ListView) findViewById(R.id.BondsList);
        //Set the dbHandler object. {context = this, pass in no data for name of db,
        //pass in no data for factory, 1 for version}
        dbHandler = new MyDBHandler(this, null, null, 1);

        printBondsDB();

        /*******Handle Accounts List******/
        BondsList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

           @Override
           public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
               //Gets the value of the item where there was a click and convert it into a String called food
               final String Item = String.valueOf(parent.getItemAtPosition(position));
               AlertDialog alertDialog = new AlertDialog.Builder(Bonds.this).create();
               alertDialog.setTitle("Delete this Bond?");
               alertDialog.setMessage("Are you sure you want to remove this Bond?");
               alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "YES",
                   new DialogInterface.OnClickListener() {
                       public void onClick(DialogInterface dialog, int which) {
                           //Display the Toast Message
                           //Toast.makeText(Bonds.this, Item, Toast.LENGTH_LONG).show();
                           String Delete[] = Item.split(",");
                           //Initialise object
                           Object_Bond Bond = new Object_Bond();
                           //Set Object values
                           Bond.set_BondName(Delete[0]);
                           //Bond.set_BankCurrency(Delete[1]);
                           //Bond.set_BankAmount(Double.parseDouble(Delete[2]));
                           dbHandler.DBdeleteBond(Bond);
                           printBondsDB();
                           dialog.dismiss();
                       }
                   });
               alertDialog.show();
               return false;
           }
       });
    }

    public void showBondsDialog(View view){
        /***************************************************/
        /***************Initialise dialog*******************/
        /***************************************************/
        AlertDialog.Builder BBuilder = new AlertDialog.Builder(Bonds.this);
        View BView =  getLayoutInflater().inflate(R.layout.dialog_bonds, null);

        //Handling all fields in the dialog
        final EditText BName = (EditText) BView.findViewById((R.id.BondsName));
        final Spinner BType = (Spinner) BView.findViewById((R.id.BondsType));
            //Populating the spinner:
            ArrayAdapter<CharSequence> Bondadapter = ArrayAdapter.createFromResource(this,
                    R.array.bond_type_array, android.R.layout.simple_spinner_item);
            Bondadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            BType.setAdapter(Bondadapter);
        final Spinner BCurrency=(Spinner) BView.findViewById(R.id.BondsCurrency);
            //Populating the spinner:
            ArrayAdapter<CharSequence> Currencyadapter = ArrayAdapter.createFromResource(this,
                    R.array.currency_array, android.R.layout.simple_spinner_item);
            Currencyadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            BCurrency.setAdapter(Currencyadapter);
        final EditText BNominalValue =(EditText) BView.findViewById(R.id.BondsNominalValue) ;
        final EditText BMarketValue =(EditText) BView.findViewById(R.id.BondsMarketValue);
        final EditText BCouponRate =(EditText) BView.findViewById(R.id.BondsCouponRate) ;
        final EditText BExpiryDate =(EditText) BView.findViewById(R.id.BondsExpiryDate) ;
        Button BSubmit = (Button) BView.findViewById(R.id.UpdateBonds);

        //Showing the dialog
        BBuilder.setView(BView);
        final AlertDialog dialog = BBuilder.create();
        dialog.show();

        /***************************************************/
        /***************Handle dialog Date Picker***********/
        /***************************************************/
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
                BExpiryDate.setText(sdf.format(myCalendar.getTime()));
            }

        };

        BExpiryDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(Bonds.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        /***************************************************/
        /***************Handle dialog Submit button*********/
        /***************************************************/
        BSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!BName.getText().toString().isEmpty() && !BNominalValue.getText().toString().isEmpty()
                        && !BMarketValue.getText().toString().isEmpty() && !BCouponRate.getText().toString().isEmpty() && !BExpiryDate.getText().toString().isEmpty()) {
                    Toast.makeText(Bonds.this,
                            R.string.success_submit_Bonds,
                            Toast.LENGTH_SHORT).show();
                    //Execute data submission
                    BTNSubmit(BName, BType, BCurrency, BNominalValue, BMarketValue, BCouponRate, BExpiryDate);
                    dialog.dismiss();
                } else {
                    Toast.makeText(Bonds.this,
                            R.string.error_submit,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void BTNSubmit(EditText BName, Spinner BType, Spinner BCurrency, EditText BNominalValue, EditText BMarketValue, EditText BCouponRate, EditText BExpiryDate){
        //Initialise object
        Object_Bond Bond = new Object_Bond();
        //Set Object values
        Bond.set_BondName(BName.getText().toString());
        Bond.set_BondType(BType.getSelectedItem().toString());
        Bond.set_BondCurrency(BCurrency.getSelectedItem().toString());
        Bond.set_BondNominalValue(Double.parseDouble(BNominalValue.getText().toString()));
        Bond.set_BondMarketValue(Double.parseDouble(BMarketValue.getText().toString()));
        Bond.set_BondCouponRate(Double.parseDouble(BCouponRate.getText().toString()));
        Bond.set_BondExpiryDate(BExpiryDate.getText().toString());

        Date currentDate = Calendar.getInstance().getTime(); //Get current Date from Calendar
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy"); //Specify the dateformat
        Bond.set_BondUpdatedDate(sdf.format(currentDate).toString());

        //Add object to DB
        dbHandler.DBdeleteBond(Bond);
        dbHandler.DBaddBond(Bond);
        printBondsDB();
    }

    public void printBondsDB() {
        //Print database of Bonds to the List
        String data = ""; //Initialise string of data
        data += dbHandler.BondsdatabaseToString();

        if (data == ""){
            data = "Please add a bond, , , , , , ";
        }
        String[] datatoAdapter = null;
        datatoAdapter = data.split(";");

        ListAdapter adapter  = new BondsAdapter(this, datatoAdapter);
        BondsList.setAdapter(adapter);

        //Print Market Value of Bonds below the ListView
        String total = "";
        total += dbHandler.Bondstotal();

        TextView Bondstotal = (TextView) findViewById(R.id.Bonds_Totals);
        Bondstotal.setText(total);
    }
}
