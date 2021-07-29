package com.example.cryptocurrencyinvesting;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

public class MyCustomAdapter extends ArrayAdapter<CurrencyModel> {
    private Context context;
    private List<CurrencyModel> currencyModelsList;
    private List<CurrencyModel> currencyModelsListFiltered;


    public MyCustomAdapter( Context context, List<CurrencyModel> currencyModelsList) {
        super(context, R.layout.list_custom_item,currencyModelsList);

        this.context = context;
        this.currencyModelsList = currencyModelsList;
        this.currencyModelsListFiltered = currencyModelsList;

    }

}
