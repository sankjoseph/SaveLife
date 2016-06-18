package lemonlabs.in.savelife;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Santhosh.Joseph on 17-06-2016.
 */
public class Utils {
//http://www.lemonlabs.in/accidentapp/service/register.php?name=santhosh&mobile=9446431692&email=sankjoseph@gmail.com&device_id=xeq212121212121
    public static final String BASE_URL= "http://www.lemonlabs.in/accidentapp/service";
    public static final String LOGIN_URL = "/login/";
    public static final String REGISTER_URL = "/register.php";


    public static final String REGISTER_SUCCESS = "Login Success";

    public static final String MSG_TITLE = "SaveLife";
    public static final String MSG_NO_INTERNET = "You are not connected to Internet. Please try later.";


    public static void showInfoDialog(Context c, String title, String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(c)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .show();
    }

    public static String getFormatedTime(String dateString, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date convertedDate = null;
        try {
            convertedDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat newFormat = new SimpleDateFormat(format);
        return newFormat.format(convertedDate);
    }
}
