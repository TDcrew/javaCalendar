
/**
 *  This class runs test on the Calendar class to test each input as a case 
 *  and see how each method in the Calendar class reacts to given input.
 *
 * @author (Stephen Davies)
 * @version ()
 */
public class CalendarTester
{

    public static void main(String [] args){
        
        Calendar c = new Calendar();   
    
        // test add & toString
        System.out.println("Calendar should be empty.");
        System.out.println(c);
        
        //test adding first appointment to the caledner
        c.addAppointment("Dentist", new CalendarDate (2019, 11, 1), new TimeOfDay(10, 0, 0), new TimeOfDay(11, 0, 0));
        System.out.println("Calendar should have one appt - to see dentist.");
        System.out.println(c);
        
        //test adding a second appointment to the calendar
        c.addAppointment("Hair cut", new CalendarDate (2019, 11, 1), new TimeOfDay(11, 0, 0), new TimeOfDay(12, 0, 0));
        System.out.println("Calendar should have two appts - dentist and haircut.");
        System.out.println(c);
        
        //test adding a third appointment to the calendar
        c.addAppointment("Liposuction", new CalendarDate (2019, 11, 1), new TimeOfDay(10, 30, 0), new TimeOfDay(11, 0, 0));
        System.out.println("Calendar should have two appts - dentist and haircut.");
        System.out.println(c);
        
        //test add appointment not given end time
        c.addAppointment("Hair cut", new CalendarDate (2019, 12, 5), new TimeOfDay(10,35,45), 45);
        System.out.println("Calendar should have 3 appointments - dentist, hair cut, and dentist");
        System.out.println(c);
        
        //test findAppointment method
        c.findAppointment("Hair cut");
        
        //test cancelAppointment method
        c.cancelAppointment("Dentist", new CalendarDate (2019, 11, 1));
        System.out.println(c);
    }
}
