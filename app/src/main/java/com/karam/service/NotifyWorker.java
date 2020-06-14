package com.karam.service;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.karam.view.notification.AddWorkRequestNotification;

public class NotifyWorker extends Worker {
    AddWorkRequestNotification notification;

    public NotifyWorker(Context context, WorkerParameters params) {
        super(context, params);
        notification = new AddWorkRequestNotification(context);
    }

    @Override
    public Result doWork() {
        try {
            // Method to trigger a notification
            notification.showPeriodNotification();
            return Result.success();
        } catch (final Exception e) {
            final String m = e.getMessage();
            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), m, Toast.LENGTH_LONG).show();
                }
            }, 1000);

            // (Returning RETRY tells WorkManager to try this task again
            // later; FAILURE says not to try again.)
            return Result.failure();
        }
    }
}