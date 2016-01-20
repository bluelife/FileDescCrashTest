package com.bluelife.test.filedesccrashtest;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.system.ErrnoException;
import android.system.Os;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.io.FileDescriptor;

public class MainActivity extends AppCompatActivity {

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        crashTest();
    }

    private void crashTest(){
        int mCount = 0;


        while( true )
        {
            try {
                Os.dup( FileDescriptor.out );
                mCount++;
                if( mCount >= 940 )
                {
                    break;
                }
            } catch (ErrnoException e) {
                throw new RuntimeException( "Exception [" + e.getMessage() + "]" );
            }
        }

        handler = new Handler();

        final Runnable r = new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder( MainActivity.this );
                builder.setMessage("Dialog, hoping to be created");
                AlertDialog mDialog = builder.create();
                mDialog.show();
                handler.postDelayed(this, 1000);
            }
        };
        handler.postDelayed(r, 1000);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
