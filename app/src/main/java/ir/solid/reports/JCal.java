package ir.solid.reports;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class JCal {

    /* 1 June 2023  ≡  11 Khoordad 1402
       this class convert find JCal date using gregory date
       first it determine current gregory calendar date and
       find difference between base date (1 June 2023) and current date then,
       it will count same day difference between JCal base Date (11 Khordad 1402)
       and return JCal current date
     */

    private Date cal = Calendar.getInstance().getTime();

    private final String baseMonth = "Jun";
    private final int baseDay = 1;

    static String currentMonth = "";
    static int currentDay = 0;

    static int dayDiff;

    public JCal() {
        // Determine Current Date
        setCurrentMonth();
        setCurrentDay();

        dayDiff = calcDayDiff(currentMonth, currentDay);
    }

    // [DONE]
    public String getJDate() {
        return JDay(dayDiff) + " " + JMonthName(JMonth(dayDiff));
    }

    public String get_DB_StyleJDate() {
        int m = JMonth(dayDiff);
        int d = JDay(dayDiff);
        return String.format("%d-%d", m, d);
    }

    public String convert_DB_ToJDate(String date) {
        int end = date.indexOf("-");
        int month = Integer.parseInt(date.substring(0, end));
        int day = Integer.parseInt(date.substring(end+1));
        return day + " " + JMonthName(month);
    }

    private void setCurrentMonth() {
        SimpleDateFormat mf = new SimpleDateFormat("MMM", Locale.getDefault());
        currentMonth = mf.format(cal);
    }
    private void setCurrentDay() {
        SimpleDateFormat df = new SimpleDateFormat("dd", Locale.getDefault());
        currentDay = Integer.parseInt(df.format(cal));
    }

    // [DONE]
    private static int getMonthDays(String month) {
        switch (month) {
            case "Apr":
            case "Jun":
            case "Sep":
            case "Nov":
                return 30;

            case "Feb":
                return 28;

            default:
                return 31;
        }
    }

    private int calcDayDiff(String month, int day) {
        int ans = 0;
        String[] monthNames = {
                "Jan", "Feb", "Mar", "Apr", "May", "Jun",
                "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
        };

        int index = 0;
        for (int i=0; i < 12; i++)
            if (month.equals(monthNames[i]))
                index = i;

        // index of month btw 12 available month
        int baseIndex = 5;
        if (index == baseIndex) {
            ans = day-baseDay;
        } else if (index > baseIndex) {
            // add Full months days
            for (int i = baseIndex; i < index; i++)
                ans += getMonthDays(monthNames[i]);
            // passed days of given month
            ans += day;
            // ans -1 --> for calculate days btw base and given date
            ans -= 1;
        } else {
            // add Full months days
            for (int i = baseIndex -1; i > index; i--)
                ans += getMonthDays(monthNames[i]);
            // Remained days of given month
            int monthDays = getMonthDays(month);
            int remainedDaysFromMonth = monthDays-day;
            ans += remainedDaysFromMonth;
        }
        return ans;
    }

    private int JMonth(int dayDiff) {
        int code = 0;
        int q = dayDiff / 114;
        if (q == 0) {
            int monthDiff = (dayDiff+11) / 31;
            code = monthDiff +3;
        } else {
            dayDiff -= 113;
            int monthDiff = (dayDiff+11) / 30;
            code = monthDiff +3 +3;
        }
        return code;
    }

    private int JDay (int dayDiff) {
        int d = 0;
        int q = dayDiff / 114;
        if (q == 0) {
            d = (dayDiff+11) % 31;
        } else {
            dayDiff -= 113;
            d = dayDiff % 30;
        }
        return d;
    }

    // [DONE]
    private String JMonthName (int month) {
        switch (month) {
            case 1:
                return "فروردین";
            case 2:
                return "اردیبهشت";
            case 3:
                return "خرداد";
            case 4:
                return "تیر";
            case 5:
                return "مرداد";
            case 6:
                return "شهریور";
            case 7:
                return "مهر";
            case 8:
                return "آبان";
            case 9:
                return "آذر";
            case 10:
                return "دی";
            case 11:
                return "بهمن";
            default:
                return "اسفند";
        }
    }

    private String backwardDate(int n) {
        int month = JMonth(dayDiff);
        int day = JDay(dayDiff)-n;
        while (day < 1) {
            month -= 1;
            day += 30;
            if (month < 7)
                day += 1;
        }
        return String.format("%d-%d", month, day);
    }

    public String _1day_ago() {
        return backwardDate(1);
    }

    public String _1week_ago() {
        return backwardDate(7);
    }

    public String _2week_ago() {
        return backwardDate(2*7);
    }

    public String _4week_ago() {
        return backwardDate(4*7);
    }

    public String _12week_ago() {
        return backwardDate(12*7);
    }
}