package com.example.adi20f.encryptionkey;

/**
 * Created by adi20f on 5/3/2015.
 */

import java.util.*;

public class Key {
    Random random;
    private int length;
    private int width;
    private int asciiDisplace;
    private String stopper = "";

    public Key() {
        random = new Random();
        length = random.nextInt(8) + 2;
        width = random.nextInt(8) + 2;
        asciiDisplace = random.nextInt(3) + 1;
        for (int i = 0; i < 10; i++) {
            int ascii = random.nextInt(93 - asciiDisplace) + 33;
            char rand = (char) (ascii);
            stopper += rand;
        }
    }

    public String toString() {
        String str = "" + length + "" + width + "" + asciiDisplace + "" + stopper;
        return str;
    }

    public void setLength(int len) {
        this.length = len;
    }

    public int getLength() {
        return length;
    }

    public void setWidth(int wid) {
        this.width = wid;
    }

    public int getWidth() {
        return width;
    }

    public void setAscii(int ascii) {
        this.asciiDisplace = ascii;
    }

    public int getAscii() {
        return asciiDisplace;
    }

    public void setStopper(String str) {
        this.stopper = str;
    }

    public String getStopper() {
        return stopper;
    }
}
