package com.example.poly.countdown;

import android.os.AsyncTask;
import android.widget.TextView;

public class CountDownAsyncTask extends AsyncTask<Void, Integer, Void> {
    private TextView text;

    CountDownAsyncTask(TextView text) {
        this.text = text;
    }

    @Override
    protected Void doInBackground(Void... aVoid) {

        for (int i = 3; i >= 0; i--) {
            try {
                Thread.sleep(1000);
                publishProgress(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        text.setText(Integer.toString(values[0]));
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        text.setText("終了");
    }
}
