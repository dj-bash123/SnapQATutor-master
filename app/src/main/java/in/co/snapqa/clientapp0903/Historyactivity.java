package in.co.snapqa.clientapp0903;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import in.co.snapqa.clientapp0903.adapters.UserHistoryRecyclerViewAdapter;
import in.co.snapqa.clientapp0903.interfaces.API;
import in.co.snapqa.clientapp0903.models.UserHistoryRequest;
import in.co.snapqa.clientapp0903.models.UserHistoryResponses;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by dhananjay on 12-06-2017.
 */

public class Historyactivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    RecyclerView userHistoryRecyclerView;

    RecyclerView.LayoutManager layoutManager;
    UserHistoryResponses userHistoryResponses;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Key = "key";
    //private List<UserHistoryFragmentResponse> historyList = new ArrayList<>();
    UserHistoryRecyclerViewAdapter userHistoryRecyclerViewAdapter;
    ProgressDialog progressDialog;
    TextView tv;
    Date date2;



    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Historyactivity.this, TutorProfile.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_history);

        //userHistoryRecyclerView = (RecyclerView) findViewById(R.id.user_history_rv1);


        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/opensanslight.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        userHistoryRecyclerView = (RecyclerView) findViewById(R.id.user_history_rv1);


        tv = (TextView) findViewById(R.id.user_history_fragment_rv_tv);
        tv.setVisibility(View.GONE);

        progressDialog = ProgressDialog.show(this, "Just a sec!", "Loading History", true);

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.sssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-52-54-173-224.compute-1.amazonaws.com:7200/api/snapQA/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Log.d("Tag","sad");

        final API service = retrofit.create(API.class);

        Log.d("Tag","very sad");

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String date = preferences.getString("date", "");

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(Key, "notPresent");

        Log.d("date1",date);

        Log.d("Tag","very very sad");

        UserHistoryRequest userHistoryRequest = new UserHistoryRequest();
        userHistoryRequest.setToken(token);
        userHistoryRequest.setDate(date);
        Call<UserHistoryResponses> call = service.dealsHistory(userHistoryRequest);

        Log.d("Tag","very very very sad");

        call.enqueue(new Callback<UserHistoryResponses>() {
            @Override
            public void onResponse(Call<UserHistoryResponses> call, Response<UserHistoryResponses> response) {

                userHistoryResponses = response.body();

                if (!userHistoryResponses.getResponse().isEmpty()){
                        Log.d("TAG", "user response :" + userHistoryResponses.getResponse().get(0).get_id());
                        progressDialog.dismiss();
                        //Log.d("New deal resp:  ", "" + userHistoryResponses.getResponses().get(2).getDealsId());

                        userHistoryRecyclerViewAdapter = new UserHistoryRecyclerViewAdapter(userHistoryResponses);
                        layoutManager = new LinearLayoutManager(Historyactivity.this);
                        userHistoryRecyclerView.setLayoutManager(layoutManager);
                        userHistoryRecyclerView.setAdapter(userHistoryRecyclerViewAdapter);
                }
                else{
                    progressDialog.dismiss();
                    tv.setVisibility(View.VISIBLE);
                }
            }




            @Override
            public void onFailure(Call<UserHistoryResponses> call, Throwable t) {
                progressDialog.dismiss();
                Log.d("TAG", "response failure" + t.getMessage());
                tv.setVisibility(View.VISIBLE);
            }
        });


    }


}


