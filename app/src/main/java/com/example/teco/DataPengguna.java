package com.example.teco;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Adapter.AdapterPenggunaTaco;
import Model.ModelTaco;
import Pengguna.Menu_Pengguna;

public class DataPengguna extends AppCompatActivity {

    ConnectionCallbacks,
    OnConnectionFailedListener,

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_data_pengguna);

            getSupportActionBar().setTitle("Data Taco");
            mRequestQueue = Volley.newRequestQueue(this);
            pDialog = new ProgressDialog(this);
            pDialog.setCancelable(false);

            list = (ListView) findViewById(R.id.array_list);
            edtSearch = (EditText) findViewById(R.id.edtSearch);
            newsList.clear();
            adapter = new AdapterPenggunaTaco(DataPengguna.this, newsList);
            list.setAdapter(adapter);
        }

        private void getAllPet(JSONObject jsonObject) {
            // Pass second argument as "null" for GET requests
            pDialog.setMessage("Loading");
            showDialog();
            JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, BaseURL.gethistory + "0", jsonObject,
          mmmmmmm                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            hideDialog();
                            try {
                                boolean status = response.getBoolean("error");
                                if (status == false) {
                                    String data = response.getString("data");
                                    JSONArray jsonArray = new JSONArray(data);
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        final ModelTaco taco = new ModelTaco();
                                        final String _id = jsonObject.getString("_id");
                                        final String namaTaco = jsonObject.getString("namaTaco");
                                        final String arrTanggal = jsonObject.getString("tanggal");

                                        taco.setNamaTaco(namaTaco);
                                        taco.setTanggal(arrTanggal);
                                        taco.set_id(_id);

                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.e("Error: ", error.getMessage());
                    hideDialog();
                }
            });
        }

        @Override
        public void onBackPressed() {
            Intent i = new Intent(DataPengguna.this, Menu_Pengguna.class);
            startActivity(i);
            finish();
        }

        private void showDialog() {
            if (!pDialog.isShowing())
                pDialog.show();
        }

        private void hideDialog() {
            if (pDialog.isShowing())
                pDialog.dismiss();
        }


        @Override
        public void onConnectionSuspended(int i) {}

        @Override
        public void onConnectionFailed(ConnectionResult connectionResult) {
            /*
             * Google Play services can resolve some errors it detects.
             * If the error has a resolution, try sending an Intent to
             * start a Google Play services activity that can resolve
             * error.
             */
            if (connectionResult.hasResolution()) {
                try {
                    // Start an Activity that tries to resolve the error
                    connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
                    /*
                     * Thrown if Google Play services canceled the original
                     * PendingIntent
                     */
                } catch (IntentSender.SendIntentException e) {
                    // Log the error
                    e.printStackTrace();
                }
            } else {
                /*
                 * If no resolution is available, display a dialog to the
                 * user with the error.
                 */
                Log.e("Error", "Location services connection failed with code " + connectionResult.getErrorCode());
            }
        }

        /**
         * If locationChanges change lat and long
         *
         *
         * @param location
         */
        @Override
        public void onLocationChanged(Location location) {
            currentLatitude = location.getLatitude();
            currentLongitude = location.getLongitude();

            Toast.makeText(this, currentLatitude + " WORKS " + currentLongitude + "", Toast.LENGTH_LONG).show();
        }

        public void cari(){
            edtSearch.addTextChangedListener(new TextWatcher() {

                public void afterTextChanged(Editable s) {}

                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                public void onTextChanged(CharSequence query, int start, int before, int count) {

                    query = query.toString().toLowerCase();

                    final List<ModelTaco> filteredList = new ArrayList<ModelTaco>();

                    for (int i = 0; i < newsList.size(); i++) {

                        final String text = newsList.get(i).getNamaTaco().toLowerCase();
                        if (text.contains(query)) {
                            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            });
                            filteredList.add(newsList.get(i));
                        }
                    }
                    adapter = new AdapterPenggunaTaco(DataPengguna.this, filteredList);
                    list.setAdapter(adapter);
                }
            });
        }
        @Override
        public void onErrorResponse(VolleyError error){
            VolleyLog.e("Error: ", error.getMessage());
        }
    });
    mRequestQueue.add(req);
}

