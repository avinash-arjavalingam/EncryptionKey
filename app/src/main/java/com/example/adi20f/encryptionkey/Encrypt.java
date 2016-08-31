package com.example.adi20f.encryptionkey;


import android.content.ClipData;
import android.content.Context;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.content.ClipboardManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.RadioGroup;
import java.util.*;


/**
 * Created by adi20f on 4/16/2015.
 */
public class Encrypt extends ActionBarActivity implements OnClickListener {
    Button mButton;
    RadioButton game1, game2;
    EditText mEdit;
    TextView response;
    TextView keyText;
    String gameKey;
    RadioGroup radioGroup;
    String jumbled;
    Key key;
    String lastLetter;
    int lengthTemp = 0;
    int widthTemp = 0;
    int asciiDisplaceTemp = 0;
    String stopperTemp = "";
   public Encrypt () {
   }
    @Override
    public void onCreate(Bundle savedInstanceState){
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_encrypt);

       mButton = (Button) findViewById(R.id.buttonEncypt);
       mButton.setOnClickListener(this);
        game1 = (RadioButton) findViewById(R.id.fighting);
        game2 = (RadioButton) findViewById(R.id.concentration);
        mEdit = (EditText) findViewById(R.id.message);
       response = (TextView) findViewById(R.id.response);
       keyText = (TextView) findViewById(R.id.key);
        radioGroup = (RadioGroup) findViewById(R.id.myRadioGroup);
       gameKey = "";
       createKey();
        jumbled = "";

    }
    @Override
   public void onClick(View v) {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        String message = mEdit.getText().toString();
        if(selectedId == game1.getId()) {
            gameKey= "a";
        }
        else{
            gameKey= "c";
        }
      encryption(message);
      response.setText(jumbled+gameKey);
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("key", jumbled+gameKey);
        clipboard.setPrimaryClip(clip);
   }

    public void createKey(){
        key = new Key();
        lengthTemp = key.getLength();
        widthTemp = key.getWidth();
        asciiDisplaceTemp = key.getAscii();
        stopperTemp = key.getStopper();
        keyText.setText(key.toString());
    }
   public void encryption(String s){

       String message1 = s;
       String coded = message1;
       int len = message1.length();
       Random rand = new Random();
       //Key k = this.getKey(keyName);
       int randOne =  lengthTemp;
       int randTwo = widthTemp;
       int randAscii = asciiDisplaceTemp;
       int randNum = randOne*randTwo;
       String stop = stopperTemp;
       coded = coded + " " + stop;

       while(!(len%randNum==0))
       {
           int randChar = rand.nextInt(93-randAscii)+33;
           char a = (char)(randChar);
           coded = coded + a;
           len = coded.length();
       }

       int extra = len/randNum;
       int half = 1;
       if(extra >1){
           half =  (extra/2);
           //int width = half-1;

           while((!(extra%half==0)) && half >1)
           {
               half -= 1;
           }
       }

       int width = half * randTwo;
       int length = len/width;


       char[][] characters = new char[width][length];
       int counter = 0;
       for(int row = 0; row< width; row++){
           for(int col = 0; col< length; col++){
               char c = coded.charAt(counter);
               characters[row][col] = c;
               counter++;
           }
       }

       String crypt = "";
       for(int col = 0; col< length; col++){
           for(int row = 0; row< width; row++){
               char c = characters[row][col];
               crypt = crypt + c;
           }
       }

       String cryptFinal = "";
       for(int yo = 0; yo<len; yo++){
           if((crypt.substring(yo, yo+1)).equals(" ")){
               cryptFinal = cryptFinal + "~";
           }else{
               int displace = (int)(crypt.charAt(yo))+ randAscii;
               if(displace <= 255){
                   char incrementedChar = (char)(displace);
                   cryptFinal = cryptFinal + incrementedChar;
               }else{
                   cryptFinal = cryptFinal + crypt.substring(yo, yo+1);
               }
           }
       }

       jumbled = cryptFinal + key.toString();
       lengthTemp = 0;
       widthTemp = 0;
       asciiDisplaceTemp = 0;
       stopperTemp = "";
   }

}
