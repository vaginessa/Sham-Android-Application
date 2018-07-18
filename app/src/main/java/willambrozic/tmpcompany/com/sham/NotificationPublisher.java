package willambrozic.tmpcompany.com.sham;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class NotificationPublisher extends BroadcastReceiver {

    public static String NOTIFICATION_ID = "notification_id";
    public static String NOTIFICATION = "notification";
    public static AtomicInteger rndID = new AtomicInteger(0);
    private static int i = 10;
    public static boolean stopSpam = true;

    public void onReceive(final Context context, Intent intent) {

        final NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        final Notification notification = intent.getParcelableExtra(NOTIFICATION);
        int id = intent.getIntExtra(NOTIFICATION_ID, rndID.getAndAdd(1));
        final Vibrator vib = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (!stopSpam) {
                    createNotification(notificationManager, notification);
                    handler.postDelayed(this, 1000);
                    vib.vibrate(10);
                }
            }
        });
        createNotification(notificationManager, notification);

    }
    private void createNotification (NotificationManager notificationManager, Notification notification) {
        notificationManager.notify((int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE), notification);
    }
}
