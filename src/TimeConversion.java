/**
 * Created by Edwards on 23/03/2016.
 */
public class TimeConversion {


    public static void main(String[] args) {


        String time = "07:05:45PM";

        String nOrD = time.substring(8, 10);

        String hourString = time.substring(0,2);

        int hour = Integer.parseInt(hourString);

        if (nOrD.equals("PM") && hour!=12) {
            hour=hour+12;
        } else if (nOrD.equals("AM") && hour == 12) {
            hour = 0;
        }

        if (hour <10 ) {
            hourString = "0" + Integer.toString(hour);
        } else {
            hourString = Integer.toString(hour);
        }

        time = hourString + time.substring(2,8);

        System.out.println(time);
    }


}
