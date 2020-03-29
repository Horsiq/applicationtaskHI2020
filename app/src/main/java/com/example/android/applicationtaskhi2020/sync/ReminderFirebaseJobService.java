package com.example.android.applicationtaskhi2020.sync;

import android.app.job.JobParameters;
import android.content.Context;
import android.os.AsyncTask;

import com.firebase.jobdispatcher.JobService;

public class ReminderFirebaseJobService extends JobService {

    private AsyncTask mBackgroundTask;

    @Override
    public boolean onStartJob(final com.firebase.jobdispatcher.JobParameters job) {
        mBackgroundTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                Context context = ReminderFirebaseJobService.this;
                ReminderTask.executeTask(context, ReminderTask.ACTION_REMINDER);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                jobFinished(job, false);
            }
        };

        mBackgroundTask.execute();

        return true;
    }

    @Override
    public boolean onStopJob(com.firebase.jobdispatcher.JobParameters job) {
        if (mBackgroundTask != null) mBackgroundTask.cancel(true);
        return true;
    }

}
