package com.example.view.ListView;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.view.BaseActivity;
import com.example.view.R;
import com.example.view.customizeTextView.CircleText;

import java.util.Timer;
import java.util.TimerTask;

public class QQStepActivirt extends BaseActivity {

    private CircleText text;
    private int count = 0;
    private Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        text = (CircleText) findViewById(R.id.text);
        timer = new Timer();

        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                count++;
                //Log.e(">>>>>>>>>", "count   " + count);
                if(count >= 100) {
                    timer.cancel();
                }
                mHandler.sendEmptyMessage(0);
            }
        }, 0, 1000);

    }
    @Override
    public int getContentViewLayoutID() {
        return R.layout.activity_qqstep_activirt;
    }

    private Handler mHandler= new Handler(){
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            text.setCurrentStep(count);
        }
    };


}
