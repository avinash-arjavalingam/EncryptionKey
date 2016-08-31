package com.example.adi20f.encryptionkey;


import android.content.ClipData;


import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.content.ClipboardManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.view.View.OnClickListener;


/**
 * Created by adi20f on 5/3/2015.
 */
public class Decrypt extends ActionBarActivity implements OnClickListener {
    Button mButton;
    EditText keyText;
    EditText encryptedMessage;
    TextView message;
    String text;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.decrypt);
        keyText = (EditText) findViewById(R.id.inputKey);
        encryptedMessage = (EditText) findViewById(R.id.inputMessage);
        message = (TextView) findViewById(R.id.outputMessage);
        mButton = (Button) findViewById(R.id.decrypt);
        mButton.setOnClickListener(this);
        text = encryptedMessage.getText().toString();
    }

    @Override
    public void onClick(View v) {
        String decoded = decrypt(text.substring(0, text.length() - 1));
        message.setText(decoded);
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("message", decoded);
        clipboard.setPrimaryClip(clip);
    }

    public String decrypt(String mess) {
        String message = mess.substring(0, mess.length() - 13);
        int randOne = Integer.parseInt(mess.substring(mess.length() - 13, mess.length() - 12));
        int randTwo = Integer.parseInt(mess.substring(mess.length() - 12, mess.length() - 11));
        int randAscii = Integer.parseInt(mess.substring(mess.length() - 11, mess.length() - 10));
        int randNum = randOne * randTwo;
        String stop = mess.substring(mess.length() - 10, mess.length());
        int lenStop = stop.length();
        int lenBig = message.length();

        int extra = lenBig / randNum;
        int half = 1;
        if (extra > 1) {
            half = (extra / 2);
            //int width = half-1;

            while ((!(extra % half == 0)) && half > 1) {
                half -= 1;
            }
        }

        int width = half * randTwo;
        int length = lenBig / width;

        String newStop = "";
        for (int i = 0; i < lenStop; i++) {
            int displace = (int) (stop.charAt(i)) + randAscii;
            char yoloswag = (char) (displace);
            newStop = newStop + yoloswag;
        }

        String plusExtra = "";
        int counter = 0;
        char[][] characters = new char[width][length];
        for (int col = 0; col < length; col++) {
            for (int row = 0; row < width; row++) {
                char c = message.charAt(counter);
                characters[row][col] = c;
                counter++;
            }
        }

        for (int row = 0; row < width; row++) {
            for (int col = 0; col < length; col++) {
                char c = characters[row][col];
                plusExtra = plusExtra + c;
            }
        }
        String actual = plusExtra;


        String actualFinal = "";
        for (int ya = ((length * width) - lenStop - 1); ya >= 0; ya--) {
            if ((actual.substring(ya, ya + lenStop)).equals(newStop)) {
                actualFinal = actual.substring(0, ya);
                break;
            }
        }

        //String actualFinal = actual;
        int len = actualFinal.length();

        String actualFinalReally = "";
        for (int yo = 0; yo < len; yo++) {
            if ((actualFinal.substring(yo, yo + 1)).equals("~")) {
                actualFinalReally = actualFinalReally + " ";
            } else {
                int displace = (int) (actual.charAt(yo)) - randAscii;
                char yoloswag = (char) (displace);
                actualFinalReally = actualFinalReally + yoloswag;
            }
        }
        return actualFinalReally;

    }
}
