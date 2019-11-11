
/**
 * An Appointment records an event, and the date and time
 * that it will occur.
 *  
 * @author (McCauley) 
 */
public class Appointment
{
    // instance variables - replace the example below with your own
    private String description;
    private CalendarDate day;
    private TimeOfDay start;
    private TimeOfDay end;

    public Appointment(String desc, CalendarDate d, TimeOfDay start, TimeOfDay end)
    {
        // initialize instance variables
        description = desc;
        day = d;
        this.start = start;
        this.end = end;
    }
    
    public String toString()
    {
        return description + " " + day + " " + start + " " + end;
    }
    
    public String getDescription()
    {
        return description.trim();
    }
    
    public CalendarDate getDate()
    {
        // return a copy not the original
        CalendarDate d = new CalendarDate(day.getYear(), day.getMonth(), day.getDay());
        return d;
    }
    
    public TimeOfDay getStart()
    {
        // return a copy
        return new TimeOfDay(start);
    }

    public TimeOfDay getEnd()
    {
        // return a copy
        return new TimeOfDay(end);
    }
    
    /**
     * Return true if this appointment comes
     * before appointment x - the explicit parameter
     */
    public boolean isBefore(Appointment x)
    {
        return (day.isEarlier(x.day) ||
            (day.equals(x.day) && start.isEarlier(x.start)));
            
    }

}
