package in.co.snapqa.clientapp0903.adapters;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import in.co.snapqa.clientapp0903.AcceptedDeadlineSessionFragment;
import in.co.snapqa.clientapp0903.AcceptedLiveSessionFragment;
import in.co.snapqa.clientapp0903.Historyactivity;
import in.co.snapqa.clientapp0903.MainActivity;
import in.co.snapqa.clientapp0903.NewDealsFragment;
import in.co.snapqa.clientapp0903.NotificationMessage;
import in.co.snapqa.clientapp0903.R;
import in.co.snapqa.clientapp0903.TutorProfile;
import in.co.snapqa.clientapp0903.interfaces.API;
import in.co.snapqa.clientapp0903.models.AcceptDealRequest;
import in.co.snapqa.clientapp0903.models.AcceptedDealResponse;
import in.co.snapqa.clientapp0903.models.NewDealResponses;
import in.co.snapqa.clientapp0903.models.RejectDealRequest;
import in.co.snapqa.clientapp0903.models.RejectedDealResponse;
import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.ALARM_SERVICE;
import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by OLA on 28/03/17.
 */

public class NewDealsRecyclerViewAdapter extends RecyclerView.Adapter<NewDealsRecyclerViewAdapter.MyViewHolder> {


    NewDealResponses newDealResponsess;
    String ftime, gtime;
    Date date,date1;
    Calendar cal,calendar;
    AlertDialog.Builder alertDialog;
    String testDate,testdate1;

    MainActivity mainActivity = new MainActivity();

    Context context;

    SharedPreferences sharedpreferences;



    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Key = "key";

    public NewDealsRecyclerViewAdapter(NewDealResponses newDealResponses){
        newDealResponsess = newDealResponses;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_deal_rv, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.subject.setText(newDealResponsess.getResponses().get(position).getSubjectName());

        final String ty = newDealResponsess.getResponses().get(position).getDealType();
        Log.d("type ki value", "  " + ty);

        Log.d("jkhdjksahda  ", "" + newDealResponsess.getResponses().get(position).getTimeTo());

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        try {
            date = formatter.parse(String.valueOf(newDealResponsess.getResponses().get(position).getTimeTo()));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH) + 1;
        cal.add(Calendar.MINUTE, 30);
        cal.add(Calendar.HOUR_OF_DAY, 5);


        Log.d("hdfbsdhbfs :", "" + cal.get(Calendar.HOUR));

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa");
        String time1 = sdf.format(cal.getTime());

        /*if (Calendar.HOUR_OF_DAY < 10) {

            if (Calendar.MINUTE < 10) {
                ftime = "0" + cal.get(Calendar.HOUR_OF_DAY) + ":" + "0" + cal.get(Calendar.MINUTE);
            } else {
                ftime = "0" + cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE);
            }
        } else {
            if (Calendar.MINUTE < 10) {
                ftime = cal.get(Calendar.HOUR_OF_DAY) + ":" + "0" + cal.get(Calendar.MINUTE);
            } else {
                ftime = cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE);
            }
        }*/

        /*if (Calendar.DATE < 10) {
            if (month < 10) {
                testdate1 = cal.get(Calendar.DATE) + "-" + "0" + month + "-" + cal.get(Calendar.YEAR);
            } else {
                testdate1= cal.get(Calendar.DATE) + "-" + month + "-" + cal.get(Calendar.YEAR);
            }
        } else {
            if (month < 10) {
                testdate1 = cal.get(Calendar.DATE) + "-" + "0" + month + "-" + cal.get(Calendar.YEAR);
            } else {
                testdate1 = cal.get(Calendar.DATE) + "-" + month + "-" + cal.get(Calendar.YEAR);
            }
        }*/

        SimpleDateFormat month_date = new SimpleDateFormat("MMM");
        String month_name = month_date.format(cal.getTime());

        testdate1 = cal.get(Calendar.DATE) + "-" + month_name + "-" + cal.get(Calendar.YEAR);


            DateFormat formatte = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            try {
                date = formatte.parse(String.valueOf(newDealResponsess.getResponses().get(position).getTimeFrom()));

            } catch (ParseException e) {
                e.printStackTrace();
            }

            cal = Calendar.getInstance();
            cal.setTime(date);
            int months = cal.get(Calendar.MONTH) + 1;
            cal.add(Calendar.MINUTE, 30);
            cal.add(Calendar.HOUR_OF_DAY, 5);

            String time2 = sdf.format(cal.getTime());

            /*if (Calendar.HOUR_OF_DAY < 10) {

                if (Calendar.MINUTE < 10) {
                    gtime = "0" + cal.get(Calendar.HOUR_OF_DAY) + ":" + "0" + cal.get(Calendar.MINUTE);
                } else {
                    gtime = "0" + cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE);
                }
            } else {
                if (Calendar.MINUTE < 10) {
                    gtime = cal.get(Calendar.HOUR_OF_DAY) + ":" + "0" + cal.get(Calendar.MINUTE);
                } else {
                    gtime = cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE);
                }
            }*/

           /* if (Calendar.DATE < 10) {
                if (months < 10) {
                    testDate = cal.get(Calendar.DATE) + "-" + "0" + months + "-" + cal.get(Calendar.YEAR);
                } else {
                    testDate = cal.get(Calendar.DATE) + "-" + months + "-" + cal.get(Calendar.YEAR);
                }
            } else {
                if (months < 10) {
                    testDate = cal.get(Calendar.DATE) + "-" + "0" + months + "-" + cal.get(Calendar.YEAR);
                } else {
                    testDate = cal.get(Calendar.DATE) + "-" + months + "-" + cal.get(Calendar.YEAR);
                }
            }*/


        String month_name1 = month_date.format(cal.getTime());

        testDate = cal.get(Calendar.DATE) + "-" + month_name1 + "-" + cal.get(Calendar.YEAR);

        if (ty.equals("Deadline Session") || ty.equals("HomeWork")) {
            holder.date.setText(testdate1);
            holder.topIV.setBackgroundResource(R.drawable.dat);
            holder.leftIV.setBackgroundResource(R.drawable.hourglass_r);
            holder.type.setText("Deadline Session");
            holder.leftTV.setText(testdate1 + ", " + time1);
            holder.rightIV.setBackgroundResource(R.drawable.rupee);
            holder.rightTV.setText(Integer.toString(newDealResponsess.getResponses().get(position).getPriceTold()));
            holder.type.setBackgroundResource(R.color.colorTurquoise);
        } else {
            holder.date.setText(testDate);
            holder.topIV.setBackgroundResource(R.drawable.dat);
            holder.leftIV.setBackgroundResource(R.drawable.hourglass_g);
            holder.type.setText("Live Session");
            holder.leftTV.setText(time2);
            holder.rightIV.setBackgroundResource(R.drawable.hourglass_r);
            holder.rightTV.setText(time1);
        }


        holder.linearLayout.setVisibility(LinearLayout.GONE);

        final LinearLayout layout = holder.linearLayout;

        if (newDealResponsess.getResponses().get(position).getMaterialComment() != null) {
            holder.material.setText(newDealResponsess.getResponses().get(position).getMaterialComment());
        }
        if (newDealResponsess.getResponses().get(position).getBookName() != null) {
            holder.bookName.setText(newDealResponsess.getResponses().get(position).getBookName());
        }


        holder.itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layout.getVisibility() == LinearLayout.VISIBLE) {
                    layout.setVisibility(LinearLayout.GONE);
                } else {
                    layout.setVisibility(LinearLayout.VISIBLE);
                }

            }
        });

        sharedpreferences = holder.itemView.getContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        final String token = sharedpreferences.getString(Key, "notPresent");

        final AcceptDealRequest acceptDealRequest = new AcceptDealRequest();
        acceptDealRequest.set_id(newDealResponsess.getResponses().get(position).get_id());
        acceptDealRequest.setToken(token);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(holder.itemView.getContext().getString(R.string.api_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final API service = retrofit.create(API.class);

        holder.accept.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                Call<AcceptedDealResponse> call = service.acceptedDeal(acceptDealRequest);
                call.enqueue(new Callback<AcceptedDealResponse>() {
                    @Override
                    public void onResponse(Call<AcceptedDealResponse> call, Response<AcceptedDealResponse> response) {
                        AcceptedDealResponse response1 = response.body();
                        if (response.body().getMessage().equals("Successful !! Deal Booked !!")) {
                            Log.d(",,", "" + " deal boooked");


                            Log.d("jkhdjksahda  ", "" + newDealResponsess.getResponses().get(position).getTimeFrom());

                            if (ty.equals("Deadline Session") || ty.equals("HomeWork")) {

                                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                                try {
                                    date1 = formatter.parse(String.valueOf(newDealResponsess.getResponses().get(position).getTimeTo()));

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                cal = Calendar.getInstance();
                                cal.setTime(date1);
                                cal.add(Calendar.MINUTE, 30);
                                cal.add(Calendar.HOUR, 5);

                                Log.d("Tag", String.valueOf(cal.get(Calendar.DATE)));


                                calendar = Calendar.getInstance();

                                calendar.set(Calendar.YEAR, cal.get(Calendar.YEAR));
                                calendar.set(Calendar.MONTH, cal.get(Calendar.MONTH));
                                calendar.set(Calendar.DATE, cal.get(Calendar.DATE));
                                calendar.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY));
                                calendar.set(Calendar.MINUTE, cal.get(Calendar.MINUTE));
                                calendar.set(Calendar.SECOND, cal.get(Calendar.SECOND));

                                Log.d("hdfbsdhbfs :", "" + calendar.get(Calendar.HOUR_OF_DAY));
                                Log.d("hdfbsdhbfs :", "" + calendar.get(Calendar.MINUTE));
                                Log.d("hdfbsdhbfs :", "" + calendar.get(Calendar.SECOND));
                                Log.d("hdfbsdhbfs :", "" + calendar.get(Calendar.DATE));
                                Log.d("hdfbsdhbfs :", "" + calendar.get(Calendar.MONTH));
                                Log.d("hdfbsdhbfs :", "" + calendar.get(Calendar.YEAR));

                                Intent notificationmassage = new Intent(v.getContext(), NotificationMessage.class);

                                Log.d("Tag", "cool");

                                int id = (int) calendar.getTimeInMillis();

                                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(v.getContext());
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putInt("Id", id);
                                editor.putString("type", ty);
                                Log.d("ID", String.valueOf(id));
                                editor.apply();

                                PendingIntent pi = PendingIntent.getBroadcast(v.getContext(), id, notificationmassage, PendingIntent.FLAG_UPDATE_CURRENT);


                                Log.d("Tag", "cooled");
                                AlarmManager am = (AlarmManager) v.getContext().getSystemService(ALARM_SERVICE);
                                Log.d("Tag", "cooooled");


                                am.set(AlarmManager.RTC_WAKEUP, (calendar.getTimeInMillis() - 7200000), pi);


                                Log.d("Tag", String.valueOf(calendar.getTimeInMillis()));


                            } else {

                                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                                try {
                                    date1 = formatter.parse(String.valueOf(newDealResponsess.getResponses().get(position).getTimeFrom()));

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                cal = Calendar.getInstance();
                                cal.setTime(date1);
                                cal.add(Calendar.MINUTE, 30);
                                cal.add(Calendar.HOUR, 5);

                                Log.d("Tag", String.valueOf(cal.get(Calendar.DATE)));


                                calendar = Calendar.getInstance();

                                calendar.set(Calendar.YEAR, cal.get(Calendar.YEAR));
                                calendar.set(Calendar.MONTH, cal.get(Calendar.MONTH));
                                calendar.set(Calendar.DATE, cal.get(Calendar.DATE));
                                calendar.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY));
                                calendar.set(Calendar.MINUTE, cal.get(Calendar.MINUTE));
                                calendar.set(Calendar.SECOND, cal.get(Calendar.SECOND));

                                Log.d("hdfbsdhbfs :", "" + calendar.get(Calendar.HOUR_OF_DAY));
                                Log.d("hdfbsdhbfs :", "" + calendar.get(Calendar.MINUTE));
                                Log.d("hdfbsdhbfs :", "" + calendar.get(Calendar.SECOND));
                                Log.d("hdfbsdhbfs :", "" + calendar.get(Calendar.DATE));
                                Log.d("hdfbsdhbfs :", "" + calendar.get(Calendar.MONTH));
                                Log.d("hdfbsdhbfs :", "" + calendar.get(Calendar.YEAR));

                                Intent notificationmassage = new Intent(v.getContext(), NotificationMessage.class);

                                Log.d("Tag", "cool");

                                int id = (int) calendar.getTimeInMillis();

                                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(v.getContext());
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putInt("Id", id);
                                editor.putString("type", ty);
                                Log.d("ID", String.valueOf(id));
                                editor.apply();

                                PendingIntent pi = PendingIntent.getBroadcast(v.getContext(), id, notificationmassage, PendingIntent.FLAG_UPDATE_CURRENT);


                                Log.d("Tag", "cooled");
                                AlarmManager am = (AlarmManager) v.getContext().getSystemService(ALARM_SERVICE);
                                Log.d("Tag", "cooooled");


                                am.set(AlarmManager.RTC_WAKEUP, (calendar.getTimeInMillis() - 900000), pi);


                                Log.d("Tag", String.valueOf(calendar.getTimeInMillis()));
                            }

                            AppCompatActivity activity = (AppCompatActivity) v.getContext();

                            if (ty.equals("HomeWork") || ty.equals("Deadline Session")) {

                                AcceptedDeadlineSessionFragment newDealFragment = new AcceptedDeadlineSessionFragment();
                                activity.getSupportFragmentManager().beginTransaction().replace(R.id.content_frame_main_activity, newDealFragment).addToBackStack(null).commit();
                                activity.getSupportActionBar().setTitle("Your Deadline Sessions");

                            } else {

                                AcceptedLiveSessionFragment newDealFragment = new AcceptedLiveSessionFragment();
                                activity.getSupportFragmentManager().beginTransaction().replace(R.id.content_frame_main_activity, newDealFragment).addToBackStack(null).commit();
                                activity.getSupportActionBar().setTitle("Your Live Sessions");
                            }
                        } else {
                            Toast.makeText(holder.itemView.getContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                            Log.d("error in recycle", "  ");
                        }
                    }

                    @Override
                    public void onFailure(Call<AcceptedDealResponse> call, Throwable t) {
                        Log.d(" error recycer no res", "   " + t.getMessage());
                    }
                });
            }
        });

        final RejectDealRequest rejectDealRequest = new RejectDealRequest();
        rejectDealRequest.setDealId(newDealResponsess.getResponses().get(position).get_id());
        rejectDealRequest.setToken(token);

        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl(holder.itemView.getContext().getString(R.string.api_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final API service1 = retrofit1.create(API.class);

        holder.reject.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(final View v) {
                Call<RejectedDealResponse> call = service1.rejectedDeal(rejectDealRequest);
                call.enqueue(new Callback<RejectedDealResponse>() {
                    @Override
                    public void onResponse(Call<RejectedDealResponse> call, Response<RejectedDealResponse> response) {

                        RejectedDealResponse response2 = response.body();
                        if (response.body().getMessage().equals("Unsuccessful")){

                            Toast.makeText(holder.itemView.getContext(), "Unsuccessful", Toast.LENGTH_LONG).show();
                            Log.d("error in recycle", "  ");

                        }
                        else{

                            AppCompatActivity activity = (AppCompatActivity) v.getContext();

                            NewDealsFragment newDealFragment = new NewDealsFragment();
                            activity.getSupportFragmentManager().beginTransaction().replace(R.id.content_frame_main_activity, newDealFragment).addToBackStack(null).commit();
                        }
                    }

                    @Override
                    public void onFailure(Call<RejectedDealResponse> call, Throwable t) {
                        Log.d(" error recycer no res", "   " + t.getMessage());
                    }
                });


            }
        });


    }

    @Override
    public int getItemCount() {
        if(newDealResponsess != null) {
            return newDealResponsess.getResponses().size();
        }else {
            return 0;
        }
    }



    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView subject, type, date, leftTV, rightTV, bookName, material, comments;
        LinearLayout linearLayout;
        ImageView leftIV, rightIV,topIV;
        Button accept, reject;

        Typeface font1 = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/opensanslight.ttf");
        Typeface font2 = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/opensansregular.ttf");
        Typeface font3 = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/opensanssemibold.ttf");


        public MyViewHolder(final View itemView) {
            super(itemView);

            subject = (TextView) itemView.findViewById(R.id.new_deal_fragment_rv_subject);
            type = (TextView) itemView.findViewById(R.id.new_deal_fragment_rv_type);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.new_deal_fragment_rv_extended_layout);
            date = (TextView) itemView.findViewById(R.id.new_deal_fragment_rv_date);
            leftTV = (TextView) itemView.findViewById(R.id.new_deal_fragment_rv_left_tv);
            rightTV=(TextView) itemView.findViewById(R.id.new_deal_fragment_rv_right_tv);
            bookName = (TextView) itemView.findViewById(R.id.new_deal_fragment_rv_book_name);
            material = (TextView) itemView.findViewById(R.id.new_deal_fragment_rv_material);

            leftIV = (ImageView) itemView.findViewById(R.id.new_deal_fragment_rv_left_iv);
            rightIV = (ImageView) itemView.findViewById(R.id.new_deal_fragment_rv_right_iv);
            topIV = (ImageView) itemView.findViewById(R.id.tv);


            accept = (Button) itemView.findViewById(R.id.new_deal_fragment_rv_accept);
            reject = (Button) itemView.findViewById(R.id.new_deal_fragment_rv_reject);

            accept.setTypeface(font2);
            reject.setTypeface(font2, Typeface.BOLD);
            subject.setTypeface(font3);
            type.setTypeface(font1);
            date.setTypeface(font1);
            leftTV.setTypeface(font1, Typeface.BOLD);
            rightTV.setTypeface(font1, Typeface.BOLD);
            bookName.setTypeface(font1);
            material.setTypeface(font1);





        }
    }
}