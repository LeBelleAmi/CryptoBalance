package com.lebelle.cryptobalance.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Omawumi Eyekpimi on 28-Oct-17.
 */

public class BtcSharedPreference {
    //shared preferences for Main Activity
    public static final String PREFS_NAME = "prefs";
    public static final String CARDS = "user_card";

    public BtcSharedPreference (){
        super();
    }

    public void saveCard(Context context, List<Currency> card){
        SharedPreferences save;
        SharedPreferences.Editor editSave;
        save = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editSave = save.edit();
        Gson gson = new Gson();
        String jsonItem = gson.toJson(card);
        editSave.putString(CARDS, jsonItem);
        editSave.commit();
    }

    public void addCard(Context context, Currency currency){
        List<Currency> currencies = getCurrencyList(context);
        if (currencies == null){
            currencies = new ArrayList<Currency>();
            currencies.add(currency);
            saveCard(context, currencies);
        }
    }

    public void deleteCard (Context context, Currency currency){
        ArrayList<Currency> currencies = getCurrencyList(context);
        if (currencies != null){
            currencies.remove(currency);
            saveCard(context, currencies);
        }
    }

    public ArrayList<Currency> getCurrencyList(Context context) {
        SharedPreferences preferences;
        List<Currency> currencies;
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        if (preferences.contains(CARDS)) {
            String response = preferences.getString(CARDS, null);
            Gson gson = new Gson();
            Currency [] cardItems = gson.fromJson(response, Currency[].class);
            currencies = Arrays.asList(cardItems);
            currencies = new ArrayList<Currency>(currencies);
        }else
            return null;
        return (ArrayList<Currency>) currencies;
    }
}
