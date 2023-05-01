package com.example.slotgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Thread thrd;
    Handler hndler;

    int id = 1;
    private ImageView imgSlot;
    private Button btnStartStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.imgSlot = this.findViewById(R.id.imgSlot);
        this.btnStartStop = this.findViewById(R.id.btnStop);
        this.btnStartStop.setOnClickListener(this);
        this.hndler = new Handler(Looper.getMainLooper());

    }

    @Override
    public void onClick(View view) {

        if (this.thrd != null && this.thrd.isAlive()) {
            this.thrd.interrupt();
        }
        this.thrd = new Thread(new Runnable() {
            @Override
            public void run() {
                // kode disini akan dijalankan di thread terpisah
                while (true) {
                    if (id == 9)
                        id = 1;
                    else
                        id++;
                    try {
                        hndler.post(new Runnable() {
                            @Override
                            public void run() {
                                imgSlot.setImageResource(Helper.getIcon(id));
                            }
                        });
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        break;
//                        throw new RuntimeException(e);
                    }
                }


            }
        });
        this.thrd.start();
    }
}