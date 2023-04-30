package com.rshtech.convizy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.rshtech.convizy.advertisement.ServicesImpl;
import com.rshtech.convizy.weather.Weather;
/*import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;*/

import org.json.JSONException;
import org.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class Temperature extends AppCompatActivity {
    Spinner sp;
    Spinner spin_temp;
    Spinner spin_dist;
    Spinner spin_height;
    Spinner spin_weight;
    Spinner spin_currency;
    Spinner spin_fch;
    Spinner spin_speed;
    Spinner toSpin_temp_cel;
    Spinner toSpin_temp_fahr;
    Spinner toSpin_temp_kel;
    Spinner toSpin_dist_met;
    Spinner toSpin_dist_cm;
    Spinner toSpin_dist_km;
    Spinner toSpin_dist_mil;
    Spinner toSpin_Hf;
    Spinner toSpin_Hi;
    Spinner toSpin_Hy;
    Spinner toSpin_Hme;
    Spinner toSpin_Hmi;
    Spinner toSpin_Wg;
    Spinner toSpin_Wk;
    Spinner toSpin_Wp;
    Spinner toSpin_Wo;
    Spinner toSpin_Wt;
    Spinner toSpin_Wq;
    Spinner toSpin_currency;
    Spinner toSpin_speedK;
    Spinner toSpin_speedM;
    Spinner toSpin_speedMi;
    Spinner spin_tch;
    EditText etInput;
    TextView etOutput;
    TextView etRare;
    Button btnConv;
    AlertDialog alertDialog;
    ImageView imDuk;
    TextView etDuk;
    TextView etCurrentTemperature;

    Button btnSwap;

    public int to;
    public int from;
    //public Handler handler;

    Boolean isInternetPresent = false;
    ConnectionDetector cd;                      // Connection detector class
    ConvertMethods convertMethods = new ConvertMethods();
    ServicesImpl servicesImpl = new ServicesImpl();

    //Middle spinner - for temperature quantity
    public void fromTemp() {
        spin_temp = (Spinner) findViewById(R.id.myspin);
        ArrayAdapter<String> adapter1;
        final String temp[] = {"Celsius", "Fahrenheit", "Kelvin"};
        adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, temp);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_temp.setAdapter(adapter1);
        spin_temp.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    toTempCel();

                } else if (position == 1) {
                    toTempFahr();

                } else {
                    toTempKel();

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void fromChoose() {
        spin_fch = (Spinner) findViewById(R.id.myspin);
        ArrayAdapter<String> adapterfch;
        final String fch[] = {"From"};
        adapterfch = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, fch);
        adapterfch.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_fch.setAdapter(adapterfch);
        spin_fch.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    toChoose();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void toChoose() {
        spin_tch = (Spinner) findViewById(R.id.myspin1);
        ArrayAdapter<String> adaptertch;
        final String tch[] = {"To"};
        adaptertch = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tch);
        adaptertch.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_tch.setAdapter(adaptertch);
        spin_tch.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            invalidConversionToast();
                        }
                    });
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    // Middle spinner - for distance quantities
    public void fromDist() {
        spin_dist = (Spinner) findViewById(R.id.myspin);
        ArrayAdapter<String> adapter2;
        final String distance[] = {"metre", "centimetre", "kilometre", "miles"};
        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, distance);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_dist.setAdapter(adapter2);
        spin_dist.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    toDistMet();

                } else if (position == 1) {
                    toDistCm();

                } else if (position == 2) {
                    toDistKm();

                } else {
                    toDistMil();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    // Middle Spinner - from one American measuring unit to another(Except miles)
    public void fromHeight() {
        spin_height = (Spinner) findViewById(R.id.myspin);
        ArrayAdapter<String> adapterFh;
        final String height[] = {"foot", "inch", "yards", "metre", "miles"};
        adapterFh = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, height);
        adapterFh.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_height.setAdapter(adapterFh);
        spin_height.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    toHeightF();

                } else if (position == 1) {
                    toHeightI();

                } else if (position == 2) {
                    toHeightY();

                } else if (position == 3) {
                    toHeightMet();
                } else {
                    toHeightMil();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void fromWeight() {
        spin_weight = (Spinner) findViewById(R.id.myspin);
        ArrayAdapter<String> adapterFw;
        final String weight[] = {"gram", "Kilogram", "Pounds", "Ounces", "Tonne", "Quintal"};
        adapterFw = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, weight);
        adapterFw.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_weight.setAdapter(adapterFw);
        spin_weight.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    toWeightG();

                } else if (position == 1) {
                    toWeightK();

                } else if (position == 2) {
                    toWeightP();

                } else if (position == 3) {
                    toWeightO();
                } else if (position == 4) {
                    toWeightT();
                } else {
                    toWeightQ();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void Currency() {
        spin_currency = (Spinner) findViewById(R.id.myspin);
        toSpin_currency = (Spinner) findViewById(R.id.myspin1);

        ArrayAdapter<CharSequence> adapterC = ArrayAdapter.createFromResource(this, R.array.name,
                android.R.layout.simple_spinner_item);
        adapterC.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spin_currency.setAdapter(adapterC);
        spin_currency.setSelection(142);
        toSpin_currency.setAdapter(adapterC);
        toSpin_currency.setSelection(59);
        spin_currency.setOnItemSelectedListener(new spinOne(1));
        toSpin_currency.setOnItemSelectedListener(new spinOne(2));
        cd = new ConnectionDetector(getApplicationContext());
        btnConv.setOnClickListener(v -> {
            isInternetPresent = cd.isConnectingToInternet();
            if (from == to) {
                invalidConversionToast();
                etOutput.setText(null);
            } else {
                if (isInternetPresent) {

                    new DownloadData().execute();
                } else {

                    // Print message for No Internet Connection !
                    Toast.makeText(getApplicationContext(), "Please check your internet connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public String getJson(String url) throws ClientProtocolException, IOException {

        StringBuilder build = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();
        InputStream content = entity.getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(content));
        String con;
        while ((con = reader.readLine()) != null) {
            build.append(con);
        }
        return build.toString();
    }

    class DownloadData extends AsyncTask<Void, Integer, String> {
        ProgressDialog pd = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(Temperature.this);
            pd.setTitle("Converting...");
            pd.setMessage("Please wait...");
            pd.setCancelable(false);
            pd.show();

            long delayInMillis = 10000;
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    pd.dismiss();
                }
            }, delayInMillis);
        }

        @Override
        protected String doInBackground(Void... params) {
            String s;
            String exResult = new String();
            final String val[];
            val = getResources().getStringArray(R.array.value);
            try {
                s = getJson("https://api.freecurrencyapi.com/v1/latest?apikey=" +
                        getResources().getString(R.string.currency_api_key_01) +
                        "&base_currency=" + val[from] + "&currencies=" + val[to]);
                JSONObject jObj;
                jObj = new JSONObject(s);
                if(!Objects.isNull(jObj.getJSONObject("data"))) {
                    exResult = jObj.getJSONObject("data").getString(val[to]);
                    System.out.println(exResult);
                } else {
                    Toast.makeText(getApplicationContext(), "Currency exchange is unavailable", Toast.LENGTH_LONG);
                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return exResult;


        }

        @Override
        protected void onPostExecute(String exResult) {
            super.onPostExecute(exResult);
            pd.dismiss();

            System.out.println("theResult:" + exResult);
            if (etInput.getText().toString().trim().length() > 0) {
                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                    invalidConversionToast();
                } else {
                    try {
                        Double cur = Double.parseDouble(etInput.getText().toString());
                        etOutput.setText(String.valueOf(Double.parseDouble(exResult) * cur));
                        //servicesImpl.showInterstitial();
                    } catch (NumberFormatException n) {
                        n.printStackTrace();
                    }
                }
            } else {
                Toast.makeText(getApplicationContext(), "Please enter a valid value", Toast.LENGTH_SHORT).show();
                etOutput.setText(null);
            }
        }

        @Override
        protected void onCancelled() {
            pd.dismiss();
            super.onCancelled();
        }
    }

    private class spinOne implements OnItemSelectedListener {
        int ide;

        spinOne(int i) {
            ide = i;
        }

        public void onItemSelected(AdapterView<?> parent, View view,
                                   int index, long id) {
            if (ide == 1)
                from = index;
            else if (ide == 2)
                to = index;

        }

        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }
    }

    public void fromSpeed() {
        spin_speed = (Spinner) findViewById(R.id.myspin);
        ArrayAdapter<String> adapterFs;
        final String speed[] = {"Km/hr (Kmph)", "m/sec", "miles/hr (Mph)"};
        adapterFs = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, speed);
        adapterFs.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_speed.setAdapter(adapterFs);
        spin_speed.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    toSpeedK();

                } else if (position == 1) {
                    toSpeedM();

                } else {
                    toSpeedMi();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    // 3rd spinner - to convert celsius into other quantities (*F and Kelvin)
    public void toTempCel() {
        toSpin_temp_cel = (Spinner) findViewById(R.id.myspin1);
        ArrayAdapter<String> adapter3;
        final String celTempTo[] = {"Fahrenheit", "Kelvin"};
        adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, celTempTo);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toSpin_temp_cel.setAdapter(adapter3);
        toSpin_temp_cel.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                    showduk();
                                } else {
                                    float fahr = Float.parseFloat(etInput.getText().toString()); // fetch editText value
                                    etOutput.setText(String.valueOf(convertMethods.convertctof(fahr)) + " Fahrenheit"); // convert *c to *F
                                    etRare.setText("In 1742, Swedish astronomer Anders Celsius discovered celsius. This is why this scale of temperature is named so.");
                                    showduk();
                                    //servicesImpl.showInterstitial();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the temperature", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                                showduk();
                                etRare.setText("In 1742, Swedish astronomer Anders Celsius discovered celsius. This is why this scale of temperature is named so.");
                            }
                        }
                    });
                } else {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.")
                                        || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                    etRare.setText("The hottest man-made temperature ever recorded is 7.2 trillion" +
                                            " degrees fahrenheit, or about four billion degrees celsius.");
                                    showduk();
                                } else {
                                    float kel = Float.parseFloat(etInput.getText().toString()); // fetch editText value
                                    etOutput.setText(String.valueOf(convertMethods.convertctok(kel) + " Kelvin")); //convert *c to kelvin
                                    etRare.setText("The hottest man-made temperature ever recorded is 7.2 trillion" +
                                            " degrees fahrenheit, or about four billion degrees celsius.");
                                    showduk();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the temperature", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                                etRare.setText("The hottest man-made temperature ever recorded is 7.2 trillion degrees" +
                                        " fahrenheit, or about four billion degrees celsius.");
                                showduk();
                            }
                        }
                    });
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Temperature.this, "Please select something to convert", Toast.LENGTH_SHORT).show();

            }
        });

    }

    // 3rd Spinner - to convert *F into *C and Kelvin
    public void toTempFahr() {
        toSpin_temp_fahr = (Spinner) findViewById(R.id.myspin1);
        ArrayAdapter<String> adapterFahr;
        final String FahrTempTo[] = {"Celsius", "Kelvin"};
        adapterFahr = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, FahrTempTo);
        adapterFahr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toSpin_temp_fahr.setAdapter(adapterFahr);
        toSpin_temp_fahr.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                    etRare.setText("Celsius and Fahrenheit scales are same on -40 degree. Try this out.");
                                    showduk();
                                } else {
                                    float cel = Float.parseFloat(etInput.getText().toString()); // fetch editText value
                                    etOutput.setText(String.valueOf(convertMethods.convertftoc(cel)) + " Celsius"); // convert *F to *C
                                    etRare.setText("Celsius and Fahrenheit scales are same on -40 degree. Try this out.");
                                    showduk();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the temperature", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                                etRare.setText("Celsius and Fahrenheit scales are same on -40 degree. Try this out.");
                                showduk();
                            }
                        }
                    });
                } else {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") ||
                                        etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                    etRare.setText("The hottest part of our solar system is the core of Jupiter, which, remarkably," +
                                            " is five times hotter than the Sun’s surface.");
                                    showduk();
                                } else {
                                    float kel1 = Float.parseFloat(etInput.getText().toString()); //fetch editText value
                                    etOutput.setText(String.valueOf(convertMethods.convertftok(kel1)) + " Kelvin"); //convert *F to Kelvin
                                    //servicesImpl.showInterstitial();
                                    etRare.setText("The hottest part of our solar system is the core of Jupiter, which, remarkably," +
                                            " is five times hotter than the Sun’s surface.");
                                    showduk();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                                etRare.setText("The hottest part of our solar system is the core of Jupiter," +
                                        " which, remarkably, is five times hotter than the Sun’s surface.");
                                showduk();
                            }
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Temperature.this, "Please select anything", Toast.LENGTH_LONG).show();

            }
        });
    }

    //3rd spinner - to convert Kelvin to *F and *C
    public void toTempKel() {
        toSpin_temp_kel = (Spinner) findViewById(R.id.myspin1);
        ArrayAdapter<String> adapterKel;
        final String KelTempTo[] = {"Celsius", "Fahrenheit"};
        adapterKel = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, KelTempTo);
        adapterKel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toSpin_temp_kel.setAdapter(adapterKel);
        toSpin_temp_kel.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") ||
                                        etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                    etRare.setText("Kelvin scale is used to define absolute zero—the bottom limit" +
                                            " of temperature— known as the triple point of water.");
                                    showduk();
                                } else {
                                    float cel1 = Float.parseFloat(etInput.getText().toString());
                                    etOutput.setText(String.valueOf(convertMethods.convertktoc(cel1)) + " Celsius");
                                    etRare.setText("Kelvin scale is used to define absolute zero—the bottom limit" +
                                            " of temperature— known as the triple point of water.");
                                    showduk();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                                etRare.setText("Kelvin scale is used to define absolute zero—the bottom limit" +
                                        " of temperature— known as the triple point of water.");
                                showduk();
                            }
                        }
                    });
                } else {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                    etRare.setText("The coldest city in the world is Yakutsk in Siberia city of Russia.");
                                    showduk();
                                } else {
                                    float fahr1 = Float.parseFloat(etInput.getText().toString());
                                    etOutput.setText(String.valueOf(convertMethods.convertktof(fahr1)) + " Fahrenheit");
                                    etRare.setText("The coldest city in the world is Yakutsk in Siberia city of Russia.");
                                    showduk();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                                etRare.setText("The coldest city in the world is Yakutsk in Siberia city of Russia.");
                                showduk();
                            }
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Temperature.this, "Please select anything", Toast.LENGTH_LONG).show();

            }
        });
    }

    //3rd spinner to convert metre into cm. ,km. ,mi.
    public void toDistMet() {
        toSpin_dist_met = (Spinner) findViewById(R.id.myspin1);
        ArrayAdapter<String> adapter4;
        final String metDistTo[] = {"centimetre", "Kilometre", "miles"};
        adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, metDistTo);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toSpin_dist_met.setAdapter(adapter4);
        toSpin_dist_met.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                    etRare.setText("The longest street in the world is Yonge street in Toronto Canada" +
                                            " measuring 1,896 km (1,178 miles)");
                                    showduk();
                                } else {
                                    double cm = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.2f", convertMethods.convertmtocm(cm)) + " cm.");
                                    etRare.setText("The longest street in the world is Yonge street in Toronto Canada" +
                                            " measuring 1,896 km (1,178 miles)");
                                    showduk();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                                etRare.setText("The longest street in the world is Yonge street in Toronto Canada" +
                                        " measuring 1,896 km (1,178 miles)");
                                showduk();
                            }

                        }
                    });
                } else if (position == 1) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                    etRare.setText("The Great Wall of China is approximately 6,430 Km long");
                                    showduk();
                                } else {
                                    double km = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.6f", convertMethods.convertmtokm(km)) + " km.");
                                    etRare.setText("The Great Wall of China is approximately 6,430 Km long");
                                    showduk();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                                etRare.setText("The Great Wall of China is approximately 6,430 Km long");
                                showduk();
                            }

                        }
                    });
                } else {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                    etRare.setText("Pluto is smaller than the USA.");
                                    showduk();
                                } else {
                                    double mil = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.6f", convertMethods.convertmtomil(mil)) + " mi.");
                                    etRare.setText("Pluto is smaller than the USA.");
                                    showduk();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                                etRare.setText("Pluto is smaller than the USA.");
                                showduk();
                            }

                        }
                    });
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Temperature.this, "Please select anything", Toast.LENGTH_LONG).show();

            }
        });
    }

    //3rd spinner to convert cm. into m. ,km. ,mi.
    public void toDistCm() {
        toSpin_dist_cm = (Spinner) findViewById(R.id.myspin1);
        ArrayAdapter<String> adapterCm;
        final String cmDistTo[] = {"metre", "Kilometre", "miles"};
        adapterCm = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cmDistTo);
        adapterCm.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toSpin_dist_cm.setAdapter(adapterCm);
        toSpin_dist_cm.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                    etRare.setText("The Challenger Deep in the Mariana Trench is the deepest known" +
                                            " point in Earth's oceans(10,994 meters below sea level).");
                                    showduk();
                                } else {
                                    double m = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.6f", convertMethods.convertcmtom(m)) + " met.");
                                    etRare.setText("The Challenger Deep in the Mariana Trench is the deepest known" +
                                            " point in Earth's oceans(10,994 meters below sea level).");
                                    showduk();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                                etRare.setText("The Challenger Deep in the Mariana Trench is the deepest known" +
                                        " point in Earth's oceans(10,994 meters below sea level).");
                                showduk();
                            }

                        }
                    });
                } else if (position == 1) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                    etRare.setText("The world's longest bridge is the Danyang–Kunshan Grand Bridge in China");
                                    showduk();
                                } else {
                                    double km1 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.6f", convertMethods.convertcmtokm(km1)) + " km.");
                                    etRare.setText("The world's longest bridge is the Danyang–Kunshan Grand Bridge in China");
                                    showduk();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                                etRare.setText("The world's longest bridge is the Danyang–Kunshan Grand Bridge in China");
                                showduk();
                            }

                        }
                    });
                } else {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                    etRare.setText("The official length of Great Wall of China is 21,196.18 km ");
                                    showduk();
                                } else {
                                    double mil1 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.6f", convertMethods.convertcmtomil(mil1)) + " mi.");
                                    etRare.setText("The official length of Great Wall of China is 21,196.18 km ");
                                    showduk();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                                etRare.setText("The official length of Great Wall of China is 21,196.18 km ");
                                showduk();
                            }

                        }
                    });
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Temperature.this, "Please select anything", Toast.LENGTH_LONG).show();

            }
        });
    }

    //3rd spinner to convert Km. into m. ,cm. ,km. ,mi.
    public void toDistKm() {
        toSpin_dist_km = (Spinner) findViewById(R.id.myspin1);
        ArrayAdapter<String> adapterKm;
        final String kmDistTo[] = {"metre", "centimetre", "miles"};
        adapterKm = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, kmDistTo);
        adapterKm.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toSpin_dist_km.setAdapter(adapterKm);
        toSpin_dist_km.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double m1 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.2f", convertMethods.convertkmtom(m1)) + " met.");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }

                        }
                    });
                } else if (position == 1) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double cm1 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.2f", convertMethods.convertkmtocm(cm1)) + " cm.");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }

                        }
                    });
                } else {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double mil2 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.6f", convertMethods.convertkmtomil(mil2)) + "mi.");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }

                        }
                    });
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Temperature.this, "Please select anything", Toast.LENGTH_LONG).show();

            }
        });
    }

    //3rd spinner to convert mi. into m., cm. ,km.
    public void toDistMil() {
        toSpin_dist_mil = (Spinner) findViewById(R.id.myspin1);
        ArrayAdapter<String> adapterMil;
        final String milDistTo[] = {"metre", "centimetre", "kilometre"};
        adapterMil = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, milDistTo);
        adapterMil.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toSpin_dist_mil.setAdapter(adapterMil);
        toSpin_dist_mil.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double m2 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.2f", convertMethods.convertmiltomet(m2)) + " met.");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }

                        }
                    });
                } else if (position == 1) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double cm2 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.2f", convertMethods.convertmiltocm(cm2)) + " cm.");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }

                        }
                    });
                } else {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double km2 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.2f", convertMethods.convertmiltokm(km2)) + " km.");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }

                        }
                    });
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Temperature.this, "Please select anything", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void toHeightF() {
        toSpin_Hf = (Spinner) findViewById(R.id.myspin1);
        ArrayAdapter<String> adapterHf;
        final String fHTo[] = {"inch", "yards", "metre", "miles"};
        adapterHf = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, fHTo);
        adapterHf.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toSpin_Hf.setAdapter(adapterHf);
        toSpin_Hf.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                    etRare.setText("Babies grow about 10 inches in height from birth to age one");
                                    showduk();
                                } else {
                                    double in1 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.2f", convertMethods.convertftoin(in1)) + " in.");
                                    etRare.setText("Babies grow about 10 inches in height from birth to age one");
                                    showduk();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the height", Toast.LENGTH_SHORT).show();
                                etRare.setText("Babies grow about 10 inches in height from birth to age one");
                                showduk();
                            }

                        }
                    });
                } else if (position == 1) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                    etRare.setText("You are tallest in the morning when you wake up and by the end of the day, you become one centimeter shorter.");
                                    showduk();
                                } else {
                                    double y1 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.2f", convertMethods.convertftoy(y1)) + " yd.");
                                    etRare.setText("You are tallest in the morning when you wake up and by the end of the day, you become one centimeter shorter.");
                                    showduk();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                                etRare.setText("You are tallest in the morning when you wake up and by the end of the day, you become one centimeter shorter.");
                                showduk();
                            }

                        }
                    });
                } else if (position == 2) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                    etRare.setText("Sultan Kosen(8 ft 1 in.) is the world's tallest human in the world");
                                    showduk();
                                } else {
                                    double meh1 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.2f", convertMethods.convertftome(meh1)) + " m.");
                                    etRare.setText("Sultan Kosen(8 ft 1 in.) is the world's tallest human in the world");
                                    showduk();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                                etRare.setText("Sultan Kosen(8 ft 1 in.) is the world's tallest human in the world");
                                showduk();
                            }

                        }
                    });
                } else {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double mih1 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.5f", convertMethods.convertftomi(mih1)) + " mi.");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }
                        }
                    });
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Temperature.this, "Please select anything", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void toHeightI() {
        toSpin_Hi = (Spinner) findViewById(R.id.myspin1);
        ArrayAdapter<String> adapterHi;
        final String iHTo[] = {"foot", "yards", "metre", "miles"};
        adapterHi = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, iHTo);
        adapterHi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toSpin_Hi.setAdapter(adapterHi);
        toSpin_Hi.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double ft1 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.3f", convertMethods.convertitoft(ft1)) + " ft.");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }

                        }
                    });
                } else if (position == 1) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double y2 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.3f", convertMethods.convertitoy(y2)) + " yd.");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }

                        }
                    });
                } else if (position == 2) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double meh2 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.4f", convertMethods.convertitome(meh2)) + " m.");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }

                        }
                    });
                } else {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double mih2 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.6f", convertMethods.convertitomi(mih2)) + " mi.");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }
                        }
                    });
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Temperature.this, "Please select anything", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void toHeightY() {
        toSpin_Hy = (Spinner) findViewById(R.id.myspin1);
        ArrayAdapter<String> adapterHy;
        final String yHTo[] = {"foot", "inch", "metre", "miles"};
        adapterHy = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, yHTo);
        adapterHy.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toSpin_Hy.setAdapter(adapterHy);
        toSpin_Hy.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double ft2 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.2f", convertMethods.convertytoft(ft2)) + " ft.");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }

                        }
                    });
                } else if (position == 1) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double in2 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.2f", convertMethods.convertytoin(in2)) + " in.");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }

                        }
                    });
                } else if (position == 2) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double meh3 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.3f", convertMethods.convertytome(meh3)) + " m.");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }

                        }
                    });
                } else {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double mih3 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.5f", convertMethods.convertytomi(mih3)) + " mi.");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }
                        }
                    });
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Temperature.this, "Please select anything", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void toHeightMet() {
        toSpin_Hme = (Spinner) findViewById(R.id.myspin1);
        ArrayAdapter<String> adapterHme;
        final String meHTo[] = {"foot", "inch", "yard", "miles"};
        adapterHme = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, meHTo);
        adapterHme.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toSpin_Hme.setAdapter(adapterHme);
        toSpin_Hme.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double ft3 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.2f", convertMethods.convertmetoft(ft3)) + " ft.");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }

                        }
                    });
                } else if (position == 1) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double in3 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.2f", convertMethods.convertmetoin(in3)) + " in.");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }

                        }
                    });
                } else if (position == 2) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double y3 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.2f", convertMethods.convertmetoy(y3)) + " m.");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }

                        }
                    });
                } else {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double mih4 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.6f", convertMethods.convertmtomil(mih4)) + " mi.");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }
                        }
                    });
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Temperature.this, "Please select anything", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void toHeightMil() {
        toSpin_Hmi = (Spinner) findViewById(R.id.myspin1);
        ArrayAdapter<String> adapterHmi;
        final String miHTo[] = {"foot", "inch", "yard", "metre"};
        adapterHmi = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, miHTo);
        adapterHmi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toSpin_Hmi.setAdapter(adapterHmi);
        toSpin_Hmi.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double ft4 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.2f", convertMethods.convertmitoft(ft4)) + " ft.");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the height", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }

                        }
                    });
                } else if (position == 1) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double in4 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.2f", convertMethods.convertmitoin(in4)) + " in.");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }

                        }
                    });
                } else if (position == 2) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double y4 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.2f", convertMethods.convertmitoy(y4)) + " yd.");
                                }
                            } else {
                                Toast.makeText(Temperature.this, "Please enter the measurement", Toast.LENGTH_LONG).show();
                                etOutput.setText(null);
                            }

                        }
                    });
                } else {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double meh4 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format(".2f", convertMethods.convertmiltomet(meh4)) + " m.");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }
                        }
                    });
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Temperature.this, "Please select anything", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void toWeightG() {
        toSpin_Wg = (Spinner) findViewById(R.id.myspin1);
        ArrayAdapter<String> adapterWg;
        final String gWTo[] = {"Kilogram", "Pounds", "Ounces", "Tonne", "Quintal"};
        adapterWg = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gWTo);
        adapterWg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toSpin_Wg.setAdapter(adapterWg);
        toSpin_Wg.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double kg1 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.3f", convertMethods.convertgtokg(kg1)) + " kg.");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }

                        }
                    });
                } else if (position == 1) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double p1 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.3f", convertMethods.convertgtop(p1)) + " lb.");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }

                        }
                    });
                } else if (position == 2) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double o1 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.4f", convertMethods.convertgtoo(o1)) + " oz.");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }

                        }
                    });
                } else if (position == 3) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double t1 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.6f", convertMethods.convertgtot(t1)) + " ton.");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }
                        }
                    });
                } else {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double q1 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.6f", convertMethods.convertgtoq(q1)) + " quintal");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }
                        }
                    });
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Temperature.this, "Please select anything", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void toWeightK() {
        toSpin_Wk = (Spinner) findViewById(R.id.myspin1);
        ArrayAdapter<String> adapterWk;
        final String kWTo[] = {"gram", "Pounds", "Ounces", "Tonne", "Quintal"};
        adapterWk = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, kWTo);
        adapterWk.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toSpin_Wk.setAdapter(adapterWk);
        toSpin_Wk.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double g1 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.2f", convertMethods.convertktog(g1)) + " gm.");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }

                        }
                    });
                } else if (position == 1) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double p2 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.3f", convertMethods.convertktop(p2)) + " lb.");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }

                        }
                    });
                } else if (position == 2) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double o2 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.3f", convertMethods.convertktoo(o2)) + " oz.");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }

                        }
                    });
                } else if (position == 3) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double t2 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.4f", convertMethods.convertktot(t2)) + " ton.");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }
                        }
                    });
                } else {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double q2 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.3f", convertMethods.convertktoq(q2)) + " quintal");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }
                        }
                    });
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Temperature.this, "Please select anything", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void toWeightP() {
        toSpin_Wp = (Spinner) findViewById(R.id.myspin1);
        ArrayAdapter<String> adapterWp;
        final String pWTo[] = {"gram", "Kilogram", "Ounces", "Tonne", "Quintal"};
        adapterWp = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pWTo);
        adapterWp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toSpin_Wp.setAdapter(adapterWp);
        toSpin_Wp.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double g2 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.3f", convertMethods.convertptog(g2)) + " gm.");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }

                        }
                    });
                } else if (position == 1) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double k2 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.3f", convertMethods.convertptok(k2)) + " kg.");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }

                        }
                    });
                } else if (position == 2) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double o3 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.2f", convertMethods.convertptoo(o3)) + " oz.");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }

                        }
                    });
                } else if (position == 3) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double t3 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.6f", convertMethods.convertptot(t3)) + " ton.");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }
                        }
                    });
                } else {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double q3 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.6f", convertMethods.convertptoq(q3)) + " quintal");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }
                        }
                    });
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Temperature.this, "Please select anything", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void toWeightO() {
        toSpin_Wo = (Spinner) findViewById(R.id.myspin1);
        ArrayAdapter<String> adapterWo;
        final String oWTo[] = {"gram", "Kilogram", "Pounds", "Tonne", "Quintal"};
        adapterWo = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, oWTo);
        adapterWo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toSpin_Wo.setAdapter(adapterWo);
        toSpin_Wo.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double g3 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.3f", convertMethods.convertotog(g3)) + " gm.");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }

                        }
                    });
                } else if (position == 1) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double k3 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.3f", convertMethods.convertotok(k3)) + " kg.");
                                    //servicesImpl.showInterstitial();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }

                        }
                    });
                } else if (position == 2) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double p3 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.4f", convertMethods.convertotop(p3)) + " lb.");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }

                        }
                    });
                } else if (position == 3) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double t4 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.6f", convertMethods.convertotot(t4)) + " ton.");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }
                        }
                    });
                } else {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double q4 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.6f", convertMethods.convertotoq(q4)) + " quintal");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }
                        }
                    });
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Temperature.this, "Please select anything", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void toWeightT() {
        toSpin_Wt = (Spinner) findViewById(R.id.myspin1);
        ArrayAdapter<String> adapterWt;
        final String tWTo[] = {"gram", "Kilogram", "Pounds", "Ounces", "Quintal"};
        adapterWt = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tWTo);
        adapterWt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toSpin_Wt.setAdapter(adapterWt);
        toSpin_Wt.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double g4 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.2f", convertMethods.convertttog(g4)) + " gm.");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }

                        }
                    });
                } else if (position == 1) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double kg4 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.2f", convertMethods.convertttok(kg4)) + " kg.");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }

                        }
                    });
                } else if (position == 2) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double o4 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.2f", convertMethods.convertttoo(o4)) + " oz.");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }

                        }
                    });
                } else if (position == 3) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double p4 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.2f", convertMethods.convertttop(p4)) + " lb.");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }
                        }
                    });
                } else {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double q5 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.2f", convertMethods.convertttoq(q5)) + " quintal");
                                    //servicesImpl.showInterstitial();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }
                        }
                    });
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Temperature.this, "Please select anything", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void toWeightQ() {
        toSpin_Wq = (Spinner) findViewById(R.id.myspin1);
        ArrayAdapter<String> adapterWq;
        final String qWTo[] = {"gram", "Kilogram", "Pounds", "Ounces", "Tonne"};
        adapterWq = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, qWTo);
        adapterWq.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toSpin_Wq.setAdapter(adapterWq);
        toSpin_Wq.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double g5 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.2f", convertMethods.convertqtog(g5)) + " gm.");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }

                        }
                    });
                } else if (position == 1) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double k5 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.2f", convertMethods.convertqtok(k5)) + " kg.");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }

                        }
                    });
                } else if (position == 2) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double p5 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.3f", convertMethods.convertqtop(p5)) + " lb.");
                                    //servicesImpl.showInterstitial();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }

                        }
                    });
                } else if (position == 3) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double o5 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.2f", convertMethods.convertqtoo(o5)) + " oz.");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }
                        }
                    });
                } else {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double t5 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.format("%.3f", convertMethods.convertqtot(t5)) + " ton.");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the measurement", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }
                        }
                    });
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Temperature.this, "Please select anything", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void toSpeedK() {
        toSpin_speedK = (Spinner) findViewById(R.id.myspin1);
        ArrayAdapter<String> adaptersK;
        final String Tosk[] = {"m/s", "Mi/hr (Mph)"};
        adaptersK = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Tosk);
        adaptersK.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toSpin_speedK.setAdapter(adaptersK);
        toSpin_speedK.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") ||
                                        etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                    etRare.setText("Usain Bolt,the fastest man on Earth,ran the 100 meters in 9.69 seconds at the Beijing Olympic Games");
                                    showduk();
                                } else {
                                    double ms1 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.valueOf(convertMethods.convertkphtoms(ms1)) + " m/s");
                                    etRare.setText("Usain Bolt,the fastest man on Earth,ran the 100 meters in 9.69 seconds at the Beijing Olympic Games");
                                    showduk();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the value", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                                etRare.setText("Usain Bolt,the fastest man on Earth,ran the 100 meters in 9.69 seconds at the Beijing Olympic Games");
                                showduk();
                            }
                        }
                    });
                } else {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double mih1 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.valueOf(convertMethods.convertkphtomih(mih1)) + " mi/hr");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the value", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Temperature.this, "Please select anything", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void toSpeedM() {
        toSpin_speedM = (Spinner) findViewById(R.id.myspin1);
        ArrayAdapter<String> adaptersM;
        final String Tosm[] = {"km/hr (Kmph)", "mi/hr (Mph)"};
        adaptersM = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Tosm);
        adaptersM.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toSpin_speedM.setAdapter(adaptersM);
        toSpin_speedM.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") ||
                                        etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double kph1 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.valueOf(convertMethods.convertmstokh(kph1)) + " km/hr");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the value", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }
                        }
                    });
                } else {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double mih2 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.valueOf(convertMethods.convertmstomih(mih2)) + " mi/hr");
                                    //servicesImpl.showInterstitial();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the value", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Temperature.this, "Please select anything", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void toSpeedMi() {
        toSpin_speedMi = (Spinner) findViewById(R.id.myspin1);
        ArrayAdapter<String> adaptersMi;
        final String Tosmi[] = {"km/hr (Kmph)", "m/sec"};
        adaptersMi = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Tosmi);
        adaptersMi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toSpin_speedMi.setAdapter(adaptersMi);
        toSpin_speedMi.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") ||
                                        etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double kph2 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.valueOf(convertMethods.convertmihtokh(kph2)) + " km/hr");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the value", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }
                        }
                    });
                } else {
                    btnConv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etInput.getText().toString().trim().length() > 0) {
                                if (etInput.getText().toString().equals(".") || etInput.getText().toString().equals("-.") || etInput.getText().toString().equals("-")) {
                                    invalidConversionToast();
                                } else {
                                    double ms2 = Double.parseDouble(etInput.getText().toString());
                                    etOutput.setText(String.valueOf(convertMethods.convertmihtoms(ms2)) + " m/sec");
                                    //servicesImpl.showInterstitial();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter the value", Toast.LENGTH_SHORT).show();
                                etOutput.setText(null);
                            }
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Temperature.this, "Please select anything", Toast.LENGTH_LONG).show();

            }
        });
    }

    ArrayAdapter<String> adapter;
    String quantity[] = {"Choose a quantity to convert", "Temperature", "Distance", "Height", "Mass", "Currency", "Speed","Time"}; // Quantities at spinner one

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);

        sp = (Spinner) findViewById(R.id.spin_quant);
        etInput = (EditText) findViewById(R.id.put_temp);
        etOutput = (TextView) findViewById(R.id.get_temp);
        etRare = (TextView) findViewById(R.id.rare_fact);
        btnConv = (Button) findViewById(R.id.btnConv);
        btnSwap = (Button) findViewById(R.id.btnSwap);
        etCurrentTemperature = (TextView) findViewById(R.id.current_temp);
        /*AdView mAdViewB = (AdView) findViewById(R.id.adView);
        AdRequest adRequestB = new AdRequest.Builder().build();
        mAdViewB.loadAd(adRequestB);

        servicesImpl.mInterstitialAd = new InterstitialAd(this);
        servicesImpl.mInterstitialAd.setAdUnitId("ca-app-pub-5754554166455195/9119252268");*/

        setOnLoadSpinners();

        btnSwap.setOnClickListener(v -> {
            int mainSpPosition = sp.getSelectedItemPosition();
            if(mainSpPosition == 0) {
                //do nothing
            } else if(mainSpPosition == 1) {
                int tempPosition = spin_temp.getSelectedItemPosition();
                if(tempPosition == 0) {
                    int toTempPosition = toSpin_temp_cel.getSelectedItemPosition();
                    toSpin_temp_cel.setSelection(tempPosition);
                    spin_temp.setSelection(toTempPosition);
                } else if(tempPosition == 1) {
                    int toTempPosition = toSpin_temp_fahr.getSelectedItemPosition();
                    toSpin_temp_fahr.setSelection(tempPosition);
                    spin_temp.setSelection(toTempPosition);
                } else if(tempPosition == 2) {
                    int toTempPosition = toSpin_temp_kel.getSelectedItemPosition();
                    toSpin_temp_kel.setSelection(tempPosition);
                    spin_temp.setSelection(toTempPosition);
                }
            } else if(mainSpPosition == 2) {
                int distPosition = spin_dist.getSelectedItemPosition();
                if(distPosition == 0) {
                    int toDistPosition = toSpin_dist_met.getSelectedItemPosition();
                    toSpin_dist_met.setSelection(distPosition);
                    spin_dist.setSelection(toDistPosition);
                } else if(distPosition == 1) {
                    int toDistPosition = toSpin_dist_cm.getSelectedItemPosition();
                    toSpin_dist_cm.setSelection(distPosition);
                    spin_dist.setSelection(toDistPosition);
                } else if(distPosition == 2) {
                    int toDistPosition = toSpin_dist_km.getSelectedItemPosition();
                    toSpin_dist_km.setSelection(distPosition);
                    spin_dist.setSelection(toDistPosition);
                } else if(distPosition == 3) {
                    int toDistPosition = toSpin_dist_mil.getSelectedItemPosition();
                    toSpin_dist_mil.setSelection(distPosition);
                    spin_dist.setSelection(toDistPosition);
                }
            } else if(mainSpPosition == 3) {
                int heightPosition = spin_height.getSelectedItemPosition();
                if(heightPosition == 0) {
                    int toHeightPosition = toSpin_Hf.getSelectedItemPosition();
                    toSpin_Hf.setSelection(heightPosition);
                    spin_height.setSelection(toHeightPosition);
                } else if(heightPosition == 1) {
                    int toHeightPosition = toSpin_Hi.getSelectedItemPosition();
                    toSpin_Hi.setSelection(heightPosition);
                    spin_height.setSelection(toHeightPosition);
                } else if(heightPosition == 2) {
                    int toHeightPosition = toSpin_Hy.getSelectedItemPosition();
                    toSpin_Hy.setSelection(heightPosition);
                    spin_height.setSelection(toHeightPosition);
                } else if(heightPosition == 3) {
                    int toHeightPosition = toSpin_Hme.getSelectedItemPosition();
                    toSpin_Hme.setSelection(heightPosition);
                    spin_height.setSelection(toHeightPosition);
                } else if(heightPosition == 4) {
                    int toHeightPosition = toSpin_Hmi.getSelectedItemPosition();
                    toSpin_Hmi.setSelection(heightPosition);
                    spin_height.setSelection(toHeightPosition);
                }
            } else if(mainSpPosition == 4) {
                //{"gram", "Kilogram", "Pounds", "Ounces", "Tonne", "Quintal"}
                int weightPosition = spin_weight.getSelectedItemPosition();
                if(weightPosition == 0) {
                    int toWeightPosition = toSpin_Wg.getSelectedItemPosition();
                    toSpin_Wg.setSelection(weightPosition);
                    spin_weight.setSelection(toWeightPosition);
                } else if(weightPosition == 1) {
                    int toWeightPosition = toSpin_Wk.getSelectedItemPosition();
                    toSpin_Wk.setSelection(weightPosition);
                    spin_weight.setSelection(toWeightPosition);
                } else if(weightPosition == 2) {
                    int toWeightPosition = toSpin_Wp.getSelectedItemPosition();
                    toSpin_Wp.setSelection(weightPosition);
                    spin_weight.setSelection(toWeightPosition);
                } else if(weightPosition == 3) {
                    int toWeightPosition = toSpin_Wo.getSelectedItemPosition();
                    toSpin_Wo.setSelection(weightPosition);
                    spin_weight.setSelection(toWeightPosition);
                } else if(weightPosition == 4) {
                    int toWeightPosition = toSpin_Wt.getSelectedItemPosition();
                    toSpin_Wt.setSelection(weightPosition);
                    spin_weight.setSelection(toWeightPosition);
                } else if(weightPosition == 5) {
                    int toWeightPosition = toSpin_Wq.getSelectedItemPosition();
                    toSpin_Wq.setSelection(weightPosition);
                    spin_weight.setSelection(toWeightPosition);
                }
            } else if(mainSpPosition == 5) {
                int currencyPosition = spin_currency.getSelectedItemPosition();
                int toCurrencyPosition = toSpin_currency.getSelectedItemPosition();
                spin_currency.setSelection(toCurrencyPosition);
                toSpin_currency.setSelection(currencyPosition);
            } else if(mainSpPosition == 6) {
                //"Km/hr (Kmph)", "m/sec", "miles/hr (Mph)"
                int speedPosition = spin_speed.getSelectedItemPosition();
                if(speedPosition == 0) {
                    int toSpeedPosition = toSpin_speedK.getSelectedItemPosition();
                    toSpin_speedK.setSelection(speedPosition);
                    spin_speed.setSelection(toSpeedPosition);
                } else if(speedPosition == 1) {
                    int toSpeedPosition = toSpin_speedM.getSelectedItemPosition();
                    toSpin_speedM.setSelection(speedPosition);
                    spin_speed.setSelection(toSpeedPosition);
                } else if(speedPosition == 2) {
                    int toSpeedPosition = toSpin_speedMi.getSelectedItemPosition();
                    toSpin_speedMi.setSelection(speedPosition);
                    spin_speed.setSelection(toSpeedPosition);
                }
            }
        });

        //AlertDialog
        alertDialog = new AlertDialog.Builder(
                Temperature.this).create();
        alertDialog.setTitle("About Us"); // Setting Dialog Title
        alertDialog.setIcon(R.drawable.measure);
        alertDialog.setMessage(Constants.aboutUs);          // Setting Dialog Message
        alertDialog.setCancelable(true);
    }

    private void setOnLoadSpinners() {
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, quantity);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setSelection(0);
        sp.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    fromChoose();
                    etCurrentTemperature.setText("");
                } else if (position == 1) {
                    fromTemp(); // this is redirected to action of middle spinner for temperature
                    etCurrentTemperature.setText("Click here to know current temperature");
                    etCurrentTemperature.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getApplicationContext(), "Loading..." , Toast.LENGTH_SHORT);
                            Intent weatherIntent = new Intent(Temperature.this, Weather.class);
                            startActivity(weatherIntent);
                        }
                    });
                } else if (position == 2) {
                    fromDist(); // this is redirected to action of middle spinner for distance
                    etCurrentTemperature.setText("");
                } else if (position == 3) {
                    fromHeight();
                    etCurrentTemperature.setText("");
                } else if (position == 4) {
                    fromWeight();
                    etCurrentTemperature.setText("");
                } else if (position == 5) {
                    etInput.setText(String.valueOf(1));
                    etCurrentTemperature.setText("");
                    Currency();
                } else {
                    fromSpeed();
                    etCurrentTemperature.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "Please select a unit", Toast.LENGTH_LONG).show();

            }
        });
    }

    //validate
    private void invalidConversionToast(){
        Toast.makeText(getApplicationContext(), Constants.invalidConversion, Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {


            // Showing Alert Message
            alertDialog.show();

        }
        return super.onOptionsItemSelected(item);
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Snackbar.make(findViewById(android.R.id.content), Constants.exit, Snackbar.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    public void showduk() {
        imDuk = (ImageView) findViewById(R.id.duk);
        imDuk.setImageResource(R.drawable.lamp);
        etDuk = (TextView) findViewById(R.id.duk1);
        etDuk.setText("Did You Know?");

    }
}