package com.example.webappmovie02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Movie> _al=null;
    ListView _lstMovie =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _al = new ArrayList<>();
        _lstMovie = findViewById(R.id.lstMovie);
        JsonMovieReader _jr = new JsonMovieReader();
        _jr.execute("");
    }
    class JsonMovieReader extends AsyncTask<String,String,String>{
        HttpURLConnection _conn=null;
        String _movieData="";

        @Override
        protected String doInBackground(String... strings) {
         try {
             URL _url = new URL("https://www.omdbapi.com/?s=harry&apikey=6ff571af");
             _conn= (HttpURLConnection) _url.openConnection();
             InputStream _inStream = _conn.getInputStream();
             InputStreamReader _sReader = new InputStreamReader(_inStream);
             BufferedReader _bReader = new BufferedReader(_sReader);
             _movieData = _bReader.readLine();


         }
            catch (Exception ex){

                Log.e( "doInBackground: ",ex.getMessage().toString() );
            }

            return _movieData;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject _jo = new JSONObject(s);
                JSONArray _jArray = _jo.getJSONArray("Search");
                for (int i=0;i<_jArray.length();i++){
                   JSONObject _jobj =  _jArray.getJSONObject(i);
                   String _Title = _jobj.getString("Title");
                    String _Year = _jobj.getString("Year");
                    String _Poster = _jobj.getString("Poster");
                    _al.add(new Movie(_Title,_Year,_Poster));
                    Log.e( "onPostExecute: ",_Title);
                }
                CusotmListAdapter _dAdpter = new CusotmListAdapter(getApplication(), R.layout.custom_movie_list,_al);
                _lstMovie.setAdapter(_dAdpter);
            }
            catch (Exception ex){}


        }
    }
}