package com.example.models;

import android.text.TextUtils;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {

    // return false if empty
    public static boolean checkInput(EditText editText, String msg){
        if (!TextUtils.isEmpty(editText.getText().toString())){
            return  true;
        }
        editText.setError(msg);
        return false;
    }

    public static boolean checkValidEmail(EditText editText, String msg){
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(editText.getText().toString());
        if(matcher.find() && editText.getText().toString() != ""){
            return true;
        }
        editText.setError(msg);
        return  false;
    }

    public static boolean checkImageURL(EditText editText, String msg){
        String regex = "^https://";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(editText.getText().toString());
        if (matcher.find()) {
            return true;
        }
        editText.setError(msg);
        return  false;
    }

    public static boolean checkIfNumber(EditText editText, String msg){
        String regex = "^[1-9]\\d*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(editText.getText().toString());
        if(matcher.find()){
            return true;
        }
        editText.setError(msg);
        return  false;
    }

    public static String getTimeDiff(String datePosted){
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        try {
            Date datepost = dateFormat.parse(datePosted);
            Date currentDate = new Date();

            long difference_In_Time = datepost.getTime() - currentDate.getTime();

            long difference_In_Minutes = TimeUnit
                    .MILLISECONDS
                    .toMinutes(difference_In_Time)
                    % 60;

            long difference_In_Hours = TimeUnit
                    .MILLISECONDS
                    .toHours(difference_In_Time)
                    % 24;
            long h= Math.abs(difference_In_Hours);
            String hr = h == 0 ? "" : String.valueOf(h) + "h" ;
            String min = String.valueOf(Math.abs(difference_In_Minutes));
            return  hr + " " + min + "mins ago";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }
}
