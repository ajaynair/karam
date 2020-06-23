package com.karam.view.notification;

import android.content.Context;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.karam.view.activity.R;

public class AddWorkRequestNotification {
    private Context context;
    // TODO for future release: Support different channel for different notification
    private String channelID = "0";
    // TODO: Make sure the id is changed everytime
    private int notificationID = 1;

    public AddWorkRequestNotification(Context context) {
        this.context = context;
    }

    /**
     * Get the stored context
     */
    private Context getContext() {
        return context;
    }

    /**
     * Shows the notification
     */
    public void showPeriodNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), channelID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(context.getResources().getString(R.string.notif_work_req_create_job_req))
                .setContentText(context.getResources().getString(R.string.notif_work_req_create_job_req_text))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getContext());

        notificationManager.notify(notificationID, builder.build());
    }
}
