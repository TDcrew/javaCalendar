/**
 * A class for keeping the time of day
 * All times stored are normalized. That is, 
 * hours are in range 0-23, minutes in range 0-59, seconds in range 0-59
 * For example, if a user creates a TimeOfDay with an expression such as 
 * new TimeOfDay(12, 100, 10), meaning 100 minutes past 12 noon, the time 
 * stored is 13:40:10, not 12:100:10.
 * 
 *  @Author(Based on a TimeOfDay class by  Horstmann, updated/modified by McCauley )
*/
public class TimeOfDay
{
   // Use the following constants for known values that are never going
   // change, rather than literals in your code
   private static final int SECONDS_PER_HOUR = 60 * 60;
   private static final int SECONDS_PER_MINUTE = 60;
   private static final int MINUTES_PER_HOUR = 60;
   private static final int HOURS_PER_DAY = 24;
   
   // instance variables
   private int hours;
   private int minutes;
   private int seconds;

   /**
    *  Construct a time of day object 
    *  for 0:0:0
    */
   public TimeOfDay()
   {
      this.hours = 0;
      this.minutes = 0;
      this.seconds = 0;
   }
   /**
    * Construct a time of day object
    * @arg hours hour of the time
    * @arg minutes minutes of the time
    * @arg seconds seconds of the time 
    */
   public TimeOfDay(int hours, int minutes, int seconds)
   {
      // Add a conditional statement to set the time to 00:00:00 if any of the
      // provided values for hours, minutes or seconds is negative.
      // Use the parameterless constructor to set this time, by calling it 
      // as such: this( );
      this(); // sets all times to 0
      if (hours >= 0 && minutes >= 0 && seconds >= 0)
      {
      // If the condition above fails, the time is set by the following assignments
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        normalize();  // verifies that time is stored accurately 
    }
   }
   
   /**
    * Copy constructor - creates a new TimeOfDay from an existing one
    * You complete this method then delete this comment
    */ 
   public TimeOfDay(TimeOfDay t)
   {
       this.hours = t.hours;
       this.minutes = t.minutes;
       this.seconds = t.seconds;
   }
    
    /**
     * Copy method - returns a copy of this
     */ 
    public TimeOfDay copy()
    {
        return new TimeOfDay(hours, minutes, seconds);
    }
    
   /* 
    * Returns true if two objects have same contents,
    * false otherwise.
    */
   public boolean equals(Object other)
   {
       boolean returnResult = false;
       
       if (other != null && other.getClass() == this.getClass()){
           TimeOfDay o = (TimeOfDay)other;
           returnResult = hours == o.hours &&
                          minutes == o.minutes &&
                          seconds == o.seconds;
       }
       return returnResult;
   }

    // Check if this time earlier than another
    public boolean isEarlier(TimeOfDay other)
    {
        
       return !this.equals(other) && // times not the same
               (hours < other.hours || // this hour earlier
               (hours == other.hours && minutes < other.minutes) || 
               (hours == other.hours && minutes == other.minutes && seconds < other.seconds));
        
    }
    
   // Check if this time later than another
    public boolean isLater(TimeOfDay other)
    {    
       return !this.equals(other) && !this.isEarlier(other);
        
    }    
  // return internaal String representation of this object
  // you will change this and this comment - see HW4 specification
   public String toString()
   {
      String meridiem;
      if(hours < 12){
          meridiem = "AM";
      }
      else{
          meridiem = "PM";
      }
      String mins = "" + minutes;
      if(minutes < 10) {
          mins = "0" + mins;
      }
      String secs = "" + seconds;
      if(seconds < 10){
          secs = "0" + secs;
      }
      String hrs;
      if(hours == 12 || hours == 24){
          hrs = "12";
      }
      else{
          hrs = "" + (hours % 12);
      }
      return hrs + ":" + mins + ":" + secs + meridiem;    
   }
   
   /**
    * Create a new time by adding minutes to the implicit parameter
    */
   public TimeOfDay addMinutes(int minutes){
       TimeOfDay later = new TimeOfDay(hours, this.minutes, seconds);
       if(minutes > 0){
           later.minutes = later.minutes + minutes;
           later.normalize();
       }
       return later; 
       
   }
  
   // Change values of all fields so they are within the specified limits
   private void normalize()
   {
       minutes = minutes + seconds / SECONDS_PER_MINUTE;
       seconds = seconds % SECONDS_PER_MINUTE;
       
       hours = hours + minutes / MINUTES_PER_HOUR;
       minutes = minutes % MINUTES_PER_HOUR;
       
       hours = hours % HOURS_PER_DAY;
   }

}
