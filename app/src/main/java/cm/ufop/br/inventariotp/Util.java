package cm.ufop.br.inventariotp;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by davidson on 10/06/17.
 */

public class Util {

    //ref: https://stackoverflow.com/questions/8573250/android-how-can-i-convert-string-to-date
    public static Date stringToDate(String aDate, String aFormat) {

        if(aDate==null) return null;
        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat simpledateformat = new SimpleDateFormat(aFormat);
        Date stringDate = simpledateformat.parse(aDate, pos);
        return stringDate;

    }

    public static String DateToString(Date d) {
        SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = new Date();
            String datetime = dateformat.format(d);
            return datetime;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }
        return "";
    }
}
