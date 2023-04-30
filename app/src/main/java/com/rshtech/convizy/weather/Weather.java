package com.rshtech.convizy.weather;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.rshtech.convizy.ConnectionDetector;
import com.rshtech.convizy.Constants;
import com.rshtech.convizy.R;


public class Weather extends AppCompatActivity {

    ConnectionDetector cd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.weather, new WeatherFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.weather, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.change_city){
            showInputDialog();
        }
        return false;
    }

    private void showInputDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Change city");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        builder.setView(input);
        builder.setPositiveButton("Go", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(!(input.getText().toString().equals(""))) {
                    changeCity(input.getText().toString());
                } else{
                    Snackbar snackbar = Snackbar
                            .make(findViewById(android.R.id.content), Constants.emptyDialog, Snackbar.LENGTH_LONG)
                            .setAction("UNDO", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    cd = new ConnectionDetector(getApplicationContext());
                                    if(!cd.isConnectingToInternet()){
                                        Snackbar.make(findViewById(android.R.id.content), R.string.place_not_found, Snackbar.LENGTH_SHORT)
                                                .show();
                                    }else{
                                        showInputDialog();
                                    }
                                }
                            });

                    snackbar.show();
                }
            }
        });
        builder.show();
    }

    public void changeCity(String city){
        WeatherFragment wf = (WeatherFragment)getSupportFragmentManager()
                .findFragmentById(R.id.weather);
        wf.changeCity(city);
        new CityPreference(this).setCity(city);
    }
}