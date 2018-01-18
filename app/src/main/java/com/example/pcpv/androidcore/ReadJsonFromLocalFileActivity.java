package com.example.pcpv.androidcore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class ReadJsonFromLocalFileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_json_from_local_file);

        try {
            JSONObject jsonObject = new JSONObject(loadJSONFromAsset());
            JSONArray jsonArray = jsonObject.getJSONArray("listFAQ");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectInside = jsonArray.getJSONObject(i);
                Log.d("Details-->", jsonObjectInside.getString("id"));
                Log.d("Details-->", jsonObjectInside.getString("group"));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("jsonFile.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
