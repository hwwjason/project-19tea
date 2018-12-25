package Utils;

import org.junit.jupiter.api.Test;
import sun.applet.Main;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public  Date getFomateDate(Date date) throws  Exception{
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);
        System.out.println("str" + str);
        Date dateFormate = format.parse(str);
        System.out.println("dateFormate"+dateFormate);

        //String ------->Date对象
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(date);
        Timestamp timestamp = Timestamp.valueOf(time);
        System.out.println("timestamp"+timestamp);

        return dateFormate;
    }


    @Test
   public void test() throws  Exception{
        Date now = new Date();
        Date nowFormate = this.getFomateDate(now);
        System.out.println("result1" + now);
        System.out.println("result2" + nowFormate);
   }
}
