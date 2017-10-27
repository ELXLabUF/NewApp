package com.example.newapp;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.wearable.view.WatchViewStub;
import android.widget.TextView;

public class MessagePromptEndTwo extends FragmentActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_prompt_end_two);
        /*final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
            }
        });*/

        ViewPager viewpager = (ViewPager) findViewById(R.id.pager);
        SecondQuestionAdapter padapter = new SecondQuestionAdapter(getSupportFragmentManager());
        viewpager.setAdapter(padapter);
    }
}
