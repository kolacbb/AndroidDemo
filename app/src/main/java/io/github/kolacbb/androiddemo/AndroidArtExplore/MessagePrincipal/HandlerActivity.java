package io.github.kolacbb.androiddemo.AndroidArtExplore.MessagePrincipal;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import io.github.kolacbb.androiddemo.R;

public class HandlerActivity extends AppCompatActivity {

    TextView tvThread;
    TextView tvHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        initView();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try { Thread.sleep(500);} catch (InterruptedException e) { e.printStackTrace(); }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvThread.setText("thread update it");
                    }
                });
            }
        }).start();

        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        tvHandler.setText("Handler update it");
                        break;
                }
                super.handleMessage(msg);
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                try { Thread.sleep(500);} catch (InterruptedException e) { e.printStackTrace(); }
                Message msg = new Message();
                msg.what = 0;
                handler.sendMessage(msg);
            }
        }).start();


    }

    public void initView() {
        tvThread = (TextView) findViewById(R.id.tv_thread);
        tvHandler = (TextView) findViewById(R.id.tv_handler);
    }
}

