/*
 * Represents a calendar year/month/day date.
 */
import java.util.*;

public class CalendarDate {
    // constants that may be helpful in avoiding "magic numbers"
    // and making code more readable and extendable
    private static final int JANUARY  =  1;
    private static final int FEBRUARY =  2;
    private static final int DECEMBER = 12;
    private static final int DAYS_PER_WEEK = 7;
    
    // arrays that might simplify your code
    private static final String[] DAY_NAMES = {
        //    0,        1,         2,           3,
        "Sunday", "Monday", "Tuesday", "Wednesday",
        //    4,        5,         6
        "Thursday", "Friday", "Saturday"};

    private static final int[] DAYS_PER_MONTH = { -1,
    //   1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12
        31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31,
    };

    // fields 
    private int year; // this year or later
    private int month;
    private int day;

    // constructor(s)
    
    /*
     * Constructs a new Date to represent the specified year/month/day.
     */
    public CalendarDate(int year, int month, int day) {
       this.year = year;
       this.month = month;
       this.day = day;
    }
    
    /*
     * Constructs a new CalendarDate to represent today.
     * Don't tinker with this code.
     */ 
    public CalendarDate() {
        this(1970, JANUARY, 1); // start date here
        // computes days since Jan 1, 1970
        int daysSinceEpoch = (int) ((System.currentTimeMillis() + 
            TimeZone.getDefault().getRawOffset()) / 1000 / 60 / 60 / 24);
        for (int i = 0; i < daysSinceEpoch; i++) 
            nextDay(); // Changes date to today
    }

    /*
     * Returns whether the given object is a CalendarDate that refers to the 
     * same year/month/day as this CalendarDate.
     */ 
    public boolean equals(Object o) {
        // a well-behaved equals method returns false for null and non-Dates
        if (o != null && o.getClass( ) == this.getClass( )) {
            CalendarDate other = (CalendarDate) o;
            return day == other.day && month == other.month && 
                   year == other.year;
        } else {
            return false;
        }
    }
    
    // Returns this Date's day.
    public int getDay() {
        return day;
    }
    
    // Returns this Date's month.
    public int getMonth() {
        return month;
    }
    
    // Returns this Date's year.
    public int getYear() {
        return year;
    }

    /*
     *  Returns the day of the week on which this Date occurred,
     *  such as "Sunday" or "Wednesday".
     *  Hint: 1753/1/1 was a Monday (1)
     */
    public String getDayOfWeek() {
        int dayIndex = 1;
        CalendarDate temp = new CalendarDate(1753, JANUARY, 1);
        while (!temp.equals(this)) {
            temp.nextDay();
            dayIndex = (dayIndex + 1) % DAYS_PER_WEEK;
        }      
        return DAY_NAMES[dayIndex];
    }
    
    /*
     * Returns whether this Date occurred during a leap year.
     * Leap years are every fourth year, except those that are
     * divisible by 100 and not by 400.
     */ 

    public boolean isLeapYear() {
        return (year % 400 == 0) || (year % 4 == 0 && 
                year % 100 != 0);
    }
    
    /*
     * Advances this Date to the next day.
     * This may cause a wrap-around into the next month or year.
     */ 
    public void nextDay() {
        day++;
        if (day > getDaysInMonth()) {
            // wrap to next month
            month++;
            day = 1;
            if (month > DECEMBER) {
                // wrap to next year
                year++;
                month = JANUARY;
            }
        }
    }
    
    /**
     * Returns a String representation of this Date, such as "2005/9/22".
     */ 
    public String toString() {
        return month + "/" + day + "/" + year;
    }

    /*
     * A helper method to return the number of days in this Date's month.
     */ 
    private int getDaysInMonth() {
        int result = DAYS_PER_MONTH[month];
        if (month == FEBRUARY && isLeapYear()) {
            result++;
        }
        return result;
    }
    
    /**
     * Returns true if the implicit parameter comes before
     * the explicit parameter.
     */
    public boolean isEarlier(CalendarDate d){
        
        return year < d.year ||
               year == d.year && month < d.month ||
               year == d.year && month == d.month && day < day;
    }
    
    /**
     *  Returns true for a valid date. For example,
     *  months are 1-12 and February never has a 30th
     *  and no month ever has a 35th, etc. And is today or later.
     */ 
    public boolean isAValidDate(){
       return monthIsValid() && 
              day >= 1 && day <= getDaysInMonth();
    }
    
    private boolean monthIsValid(){
       return month >=JANUARY && month <= DECEMBER;
    }
    
    public boolean isTodayOrLater(){
        
        
        return !isEarlier(new CalendarDate());
        
    }
}// Represents a calendar year/month/day date.