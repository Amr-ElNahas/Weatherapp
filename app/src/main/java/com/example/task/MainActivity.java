package com.example.task;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedInputStream;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
public class MainActivity extends AppCompatActivity {
    RequestQueue queue;

    private JsonObjectRequest searchNameStringRequest(String nameSearch)  {
        final String city = "current?city=";
        final String key = "&Key=64cd8d245fd34c2692a271c4762c1ad4";
//        final String URL_PREFIX = "https://api.nal.usda.gov/ndb/search/?format=json";
        final String URL_PREFIX = "http://api.weatherbit.io/v2.0/";

        String url = URL_PREFIX + city + nameSearch + key ;

        // 1st param => type of method (GET/PUT/POST/PATCH/etc)
        // 2nd param => complete url of the API
        // 3rd param => Response.Listener -> Success procedure
        // 4th param => Response.ErrorListener -> Error procedure

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            System.out.println("worksnow");
                            TextView text= findViewById(R.id.textView);
                            text.setText("degree: "+ response.getJSONArray("data").getJSONObject(0).get("temp").toString()+"humidity: "+ response.getJSONArray("data").getJSONObject(0).get("rh").toString());

                        }
                        catch(Exception e){

                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);

                    }
                });

        return jsonObjectRequest;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ((ImageView) findViewById(R.id.imageView)).setImageResource(R.drawable.weathera);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void bigbutton(View v){
//        v.setVisibility(View.INVISIBLE);

        EditText city = findViewById(R.id.editText);
        boolean error = false;

        if(city.getText().length()==0){
            city.setError("Please enter a username");
            error = true;
        }

        if(!error)
        {
            //TextView text1= findViewById(R.id.textView);
            //text1.setText("please help");

            System.out.println("works");
            String actualCity=city.getText().toString();
            String URL="https://api.weatherbit.io/v2.0/current?city="+actualCity+"&key=64cd8d245fd34c2692a271c4762c1ad4";

            //final String url = "http://httpbin.org/get?param1=hello";
            RequestQueue queue = Volley.newRequestQueue(this);
            queue.start();
            JsonObjectRequest stringRequest = searchNameStringRequest(actualCity);
            queue.add(stringRequest);
// prepare the Request
            /*JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>()
                    {
                        @Override
                        public void onResponse(JSONObject response) {
                            System.out.println(response.toString());
                            TextView text= findViewById(R.id.textView);
                            text.setText(response.toString());
                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            System.out.println("error here");
                        }
                    }
            );

// add it to the RequestQueue
            queue.add(getRequest);
*/

          /* try{
            URL url = new URL("https://www.android.com");
            HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
            urlc.setConnectTimeout(5000);
            System.out.println("heres okay");
            urlc.connect();
            System.out.println("heres okay3");
            if (urlc.getResponseCode() == 200) {
                urlc.disconnect();
                System.out.println("work");
            }}
            catch(Exception e){
                System.out.println(e.getClass());
            }*/
            /*try{
                sendGET();
            }catch(Exception e){
                System.out.println(e.getCause());
            }*/
    }
    /*private String readStream(InputStream is) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while(i != -1) {
                bo.write(i);
                i = is.read();
            }
            return bo.toString();
        } catch (IOException e) {
            return "";
        }*/
    }

    private static final String USER_AGENT = "Mozilla/5.0";

//    private static final String GET_URL = "https://api.weatherbit.io/v2.0/current?city="+"Cairo"+"&key=64cd8d245fd34c2692a271c4762c1ad4";
    private static final String GET_URL = "https://www.android.com";


    private static void sendGET() throws IOException {
        URL obj = new URL(GET_URL);
        System.out.print("here1");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        System.out.print("here2");
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        System.out.print("here3");
        int responseCode = con.getResponseCode();
        System.out.print("here4");

        System.out.println("GET Response Code :: " + responseCode);
        System.out.print("here5");

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("GET request not worked");
        }

    }
}
