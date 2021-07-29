package com.example.cryptocurrencyinvesting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
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

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_custom_item,null,true);
        ImageView imageView = view.findViewById(R.id.image);
        TextView bitcoin = view.findViewById(R.id.bit);
        TextView btc = view.findViewById(R.id.btc);
        TextView price = view.findViewById(R.id.price);
        TextView volume = view.findViewById(R.id.total_volume);
        TextView percentage = view.findViewById(R.id.percent);
        TextView mkt = view.findViewById(R.id.mkt);





        bitcoin.setText(currencyModelsListFiltered.get(position).getBitcoin());
        btc.setText(currencyModelsListFiltered.get(position).getBtc());
        price.setText(currencyModelsListFiltered.get(position).getPrice());
        volume.setText(currencyModelsListFiltered.get(position).getVolume());
        percentage.setText(currencyModelsListFiltered.get(position).getPercentage());
        mkt.setText(currencyModelsListFiltered.get(position).getMarket());

        Glide.with(context).load(currencyModelsListFiltered.get(position).getBit()).into(imageView);

        return view;
    }
    @Override
    public int getCount() {
        return currencyModelsListFiltered.size();
    }

    @Nullable
    @Override
    public CurrencyModel getItem(int position) {
        return currencyModelsListFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults = new FilterResults();
                if(constraint == null || constraint.length() == 0){
                    filterResults.count = currencyModelsList.size();
                    filterResults.values = currencyModelsList;

                }else{
                    List<CurrencyModel> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for(CurrencyModel itemsModel:currencyModelsList){
                        if(itemsModel.getBitcoin().toLowerCase().contains(searchStr)){
                            resultsModel.add(itemsModel);

                        }
                        filterResults.count = resultsModel.size();
                        filterResults.values = resultsModel;
                    }


                }

                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                currencyModelsListFiltered = (List<CurrencyModel>) results.values;
                MainActivity.currencyModelsList = (List<CurrencyModel>) results.values;
                notifyDataSetChanged();

            }
        };

        return filter;
    }
}