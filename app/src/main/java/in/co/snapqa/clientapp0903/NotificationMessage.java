package in.co.snapqa.clientapp0903;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.preference.*;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;




public class NotificationMessage extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent arg1) {
        Log.d("Tag","dd");
        showNotification(context);
    }

    private void showNotification(Context context) {
        Log.d("Tag", "visible");

        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, MainActivity.class), 0);


        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
                                R.mipmap.logo))
                        .setSmallIcon(R.drawable.logo)
                        .setContentTitle("HURRY UP!!")
                        .setContentText("You have a session in 15 minutes.");
        mBuilder.setContentIntent(contentIntent);
        mBuilder.setDefaults(Notification.DEFAULT_SOUND);
        mBuilder.setAutoCancel(true);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int notiid = preferences.getInt("Id", -1 );

        int notification_id = notiid;
        Log.d("ID1", String.valueOf(notification_id));

        mNotificationManager.notify(notification_id, mBuilder.build());
        Log.d("Tag","fuck u");
    }
}