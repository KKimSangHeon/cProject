package kkimsangheon.virtualtradecoin;

/**
 * Created by SangHeon on 2018-01-21.
 */

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.net.URL;
import java.util.Date;

public class FragmentTab extends Fragment {
    TextView textView;
    BackgroundTask task;
    int value;
    String temp=null;
    FragmentTab fragmentTab = this;
    View view = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_layout, container, false);
        view = v;

        temp = fragmentTab.getTag();

        if(task != null){
            task.cancel(true);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        task = new BackgroundTask();
        task.execute(100);


        return v;
    }

    class BackgroundTask extends AsyncTask<Integer, Integer, Integer> {
        protected void onPreExecute() {
            value = 0;
        }

        protected Integer doInBackground(Integer... values) {
            while (isCancelled() == false) {
                publishProgress(value);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                }
            }
            return value;
        }

        protected void onProgressUpdate(Integer... values) {
            if (isCancelled() == false) {
                textView = (TextView) view.findViewById(R.id.text);

                textView.setText(temp + "Current Value : " + values[0].toString() + new Date());
            }
        }

        protected void onPostExecute(Integer result) {
            textView.setText("Finished.");
        }

        protected void onCancelled() {
            textView.setText("Cancelled.");
        }
    }

}