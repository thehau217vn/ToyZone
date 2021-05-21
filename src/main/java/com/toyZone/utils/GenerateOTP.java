package com.toyZone.utils;

import java.util.Random;

/**
 * @Author : Hau Nguyen
 * @Created : 5/20/21, Thursday
 **/

public class GenerateOTP {
    public static String generateOTP() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }
}
