/**
 * This class tests the TimeOfDay class.
 *    May need additional calls to fully test.
 * 
 * @author (McCauley) 
 * 
 */
public class TimeOfDayTester{
    public static void main(String args[])
    {
        TimeOfDay t = new TimeOfDay(12, 30, 0);
        TimeOfDay u = t.copy();
        TimeOfDay v = new TimeOfDay(t);
        TimeOfDay z = t.addMinutes(20);
    
        // The following statements verify whether the copy
        // constructor and copy method and equals method
        // work properly
        System.out.println(t == u); // should be false
        System.out.println(t == v); // should  be false
        System.out.println(u == v); // should  be false
        // after equals implemented
        // note some of the calls below may generate a null pointer exception
        // before the methods above are implemented - copy initially returns null, for example
        System.out.println(t.equals(u)); // should be true
        System.out.println(t.equals(v)); // should be true
        System.out.println(u.equals(v)); // should be true 
        System.out.println(t.equals(z)); // should be false
    }

}
