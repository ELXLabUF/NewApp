package com.example.newapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.TextView;

public class MessagePromptEnd extends FragmentActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_prompt_end);
        /*final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
            }
        });*/

        ViewPager viewpager = (ViewPager) findViewById(R.id.pager);
        FirstQuestionAdapter padapter = new FirstQuestionAdapter(getSupportFragmentManager());
        viewpager.setAdapter(padapter);
    }

    public void secondQuestion(View view) {
        Intent intent;
        intent = new Intent(this, MessagePromptEndTwo.class);
        startActivity(intent);
        this.finish();
    }
}
