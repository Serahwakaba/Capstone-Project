package com.example.cryptocurrencyinvesting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText edtSearch;
    ListView listView;

    public static List<CurrencyModel> currencyModelsList = new ArrayList<>();
    CurrencyModel currencyModel;
    MyCustomAdapter myCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtSearch = findViewById(R.id.edtSearch);
        listView = findViewById(R.id.listView);


        edtSearch.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String list = listView.getTextFilter().toString();
//                root.setValue(list);
            }
        });
        getSupportActionBar().setTitle("Check Bitcoin Prices");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fetchData();

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                myCustomAdapter.getFilter().filter(s);
                myCustomAdapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    private void fetchData() {

        String url  = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false";

//        simpleArcLoader.start();

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for(int i=0;i<jsonArray.length();i++){

                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                String bitcoin = jsonObject.getString("name");
                                String btc = jsonObject.getString("name");
                                String price = jsonObject.getString("current_price");
                                String percentage = jsonObject.getString("todayCases");
                                String volume = jsonObject.getString("total_volume");
                                String mkt  = jsonObject.getString("market_cap");


                                JSONObject object = jsonObject.getJSONObject("countryInfo");
                                String flagUrl = object.getString("image");

                                currencyModel = new CurrencyModel(flagUrl,btc,bitcoin,price,percentage,volume,mkt);
                                currencyModelsList.add(currencyModel);


                            }

                            myCustomAdapter = new MyCustomAdapter(MainActivity.this,currencyModelsList);
                            listView.setAdapter(myCustomAdapter);



                        } catch (JSONException e) {
                            e.printStackTrace();
//                            simpleArcLoader.stop();
//                            simpleArcLoader.setVisibility(View.GONE);
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                simpleArcLoader.stop();
//                simpleArcLoader.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


}

}