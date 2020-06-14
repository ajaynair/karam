package com.karam.view.notification;

import android.content.Context;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.karam.view.activity.R;

public class AddWorkRequestNotification {
    // variable to hold context
    private Context context;

    public AddWorkRequestNotification(Context context) {
        this.context = context;
    }

    private Context getContext() {
        return context;
    }

    public void showPeriodNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), "0")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Create a job request")
                .setContentText("Your status is set to closed! YOU may not be able to find a job.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getContext());

// notificationId is a unique int for each notification that you must define
        // TODO: Make sure the id is changed everytime
        notificationManager.notify(1, builder.build());
    }
}
