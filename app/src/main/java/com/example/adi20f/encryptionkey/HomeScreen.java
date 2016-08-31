package com.example.adi20f.encryptionkey;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;


public class HomeScreen extends ActionBarActivity implements OnClickListener {
    private TextView mytext;
    Bundle view;

    public HomeScreen() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Button one = (Button) findViewById(R.id.encrypt);
        one.setOnClickListener(this);
        Button two = (Button) findViewById(R.id.decrypt);
        two.setOnClickListener(this);
        view = savedInstanceState;

    }


    protected void onCreateEncrpyt(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypt);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_screen, menu);
        return true;
    }

    public void buttonOnClick(View v) {
// do something when the button is clicked
        Button button = (Button) v;
        ((Button) v).setText("Clicked");
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.encrypt:
                Intent i = new Intent(
                        HomeScreen.this,
                        Encrypt.class);
                startActivity(i);

                break;
            case R.id.decrypt:
                Intent j = new Intent(
                        HomeScreen.this,
                        Decrypt.class);
                startActivity(j);
                break;
        }
    }

}
