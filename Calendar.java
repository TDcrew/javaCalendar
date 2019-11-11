
/**
 * Calendar that keeps track of appointments that are scheduled and 
 * stores them in a ArrayList.
 *
 * @author (Stephen Davies)
 * @version ()
 */
import java.util.ArrayList;
public class Calendar
{
    //instance variables
    public ArrayList<Appointment> appointments;
    // A constructor that initializes the list to be empty (list must be 
    // an ArrayList of Appointment objects). Default capacity of 10 is OK. 
    public Calendar(){
        appointments = new ArrayList<Appointment>(10);
    }
    // method that returns a labeled, print-ready string that shows
    // all appointments, each appointment on a separate line. Sample
    // output is shown later in this document.
    
    // String.format() need to be used maybe?  preffered way to format?
    // use TimeOfDay().toString and CalendarDate().toString to format also use "/t" for tab and "/n" for new line
    public String toString(){
        String updateText = "";
        System.out.println("Day \t\tStartTime \tEndtime \tDescription \n");
        for(Appointment i: appointments){
            System.out.println(i.getDate().toString() + "\t" + i.getStart().toString() + "\t" + i.getEnd().toString() + "\t" + i.getDescription());
        }
        return updateText;
    }
    // A method to verify that the time and date requested are 
    // available – note that a new Appointment cannot overlap with 
    // the time of another appointment.  (Note: while testing, it 
    // might make sense to make this method public, so it can be 
    // tested from outside of the class. Later, make it private.)
    
    // return true if appointment is available and false if appointment is not avaible
    public boolean checkAvailability(CalendarDate d, TimeOfDay start, TimeOfDay end){
        boolean availability = true;
        for(int i = 0; i < appointments.size(); i++){
            //set variables for implicit appointment variables each time loop is iterated for each seperate appointment
            CalendarDate date = appointments.get(i).getDate();
            TimeOfDay starting = appointments.get(i).getStart();
            TimeOfDay ending = appointments.get(i).getEnd();
            // if statement to see if date, start times, and end times are taken
            // test to make sure start does not fall anywhere within time of appointment from appointments at given index
            if(date.equals(d) &&
               start.isEarlier(ending) &&
               start.isLater(starting))
            {
                availability = false;
            }
            // test to make sure end does not fall anywhere within time of appointment from appointments at given index
            else if(date.equals(d) &&
                    end.isLater(starting) &&
                    end.isEarlier(ending))
            {
                availability = false;
            }
            //test to make sure time does not start before the start and end before the end
            else if(date.equals(d) &&
                    end.isLater(ending) &&
                    start.isEarlier(starting))
            {
                availability = false;
            }
        }
        return availability;
    }
    // stores appointments in chronological order, that is in the order 
    // that they will occur, assuming that the appointment date and time 
    // are available (see checkAvailability above). Note, CalendarDate and
    // TimeOfDay include methods that can be used to figure out where to 
    // store this new appointment. (Note, your code must search for proper 
    // location/index at which to insert the new Appointment – it may not 
    // add it to the end or elsewhere, then sort the ArrayList).  An 
    // appointment should only be added, if the date/times sought is available. See checkAvailability above.
    
    public void addAppointment(String purpose, CalendarDate d, TimeOfDay start, TimeOfDay end){
       // if the appointment passes the test to see if it is available then we send it into a while loop to test for where the 
       // appointment will go.  we do this by using a loop to compare from the start of the list to the end of the list to see where it would go.
       
       if (checkAvailability(d, start, end)){
          // makes new appointemnt if appointment time is avaible by using the checkAvailability method
          Appointment newAppointment = new Appointment(purpose, d , start, end);
          CalendarDate date = newAppointment.getDate();
          TimeOfDay startTime = newAppointment.getStart();
          TimeOfDay endTime= newAppointment.getEnd();
          
          // loop to test if exactly were the newAppointment will go in the array list of appointments
          // if loop is empty input the appointment
          if(appointments.isEmpty()){
              appointments.add(newAppointment);
          }
          // if there is one appointment test to see if the appointment is earlier or later than the date given
          else if(appointments.size() == 1){
              if (date.isEarlier(appointments.get(0).getDate())){
                  appointments.add(0, newAppointment);
              }
              else if (date.isTodayOrLater() &&
                       endTime.isEarlier(appointments.get(0).getEnd())){
                           appointments.add(0, newAppointment);
              }
              else{
                  appointments.add(1, newAppointment);
              }
          }
          // once there is more than one appoitment in the list it must find were in the list the new Appointment goes
          else{
              for(int i = 0; i < appointments.size(); i++){
                  if (i == 0 &&
                      date.isEarlier(appointments.get(i).getDate())){
                      appointments.add(0, newAppointment);
                      break;
                  }
                  else if(date.isEarlier(appointments.get(i).getDate())){
                      appointments.add((i - 1), newAppointment);
                      break;
                  }
                  else if(date.isEarlier(appointments.get(i).getDate()) &&
                          endTime.isEarlier(appointments.get(i).getEnd())){
                      appointments.add((i - 1), newAppointment);
                      break;
                  }
                  else{
                      appointments.add(newAppointment);
                      break;
                  }
              }
          }
       }
    }
    // same as previous version, but must compute ending time. Note 
    // TimeOfDay now has a method to handle this – see Part 1 of assignment. 
    public void addAppointment(String purpose, CalendarDate d, TimeOfDay start, int minutes){
        // if the appointment passes the test to see if it is available then we send it into a while loop to test for where the 
       // appointment will go.  we do this by using a loop to compare from the start of the list to the end of the list to see where it would go.
       
       CalendarDate date = d;
       TimeOfDay startTime = start;
       TimeOfDay endTime= start.addMinutes(minutes);
       Appointment newAppointment = new Appointment(purpose, date , startTime, endTime);
       
       if (checkAvailability(date, startTime, endTime)){
          // makes new appointemnt if appointment time is avaible by using the checkAvailability method
          
          // loop to test if exactly were the newAppointment will go in the array list of appointments
          // if loop is empty input the appointment
          if(appointments.isEmpty()){
              appointments.add(newAppointment);
          }
          // if there is one appointment test to see if the appointment is earlier or later than the date given
          else if(appointments.size() == 1){
              if (date.isEarlier(appointments.get(0).getDate())){
                  appointments.add(0, newAppointment);
              }
              else if (date.isTodayOrLater() &&
                       endTime.isEarlier(appointments.get(0).getEnd())){
                           appointments.add(0, newAppointment);
              }
              else{
                  appointments.add(1, newAppointment);
              }
          }
          else{
              for(int i = 0; i < appointments.size(); i++){
                  if (i == 0 &&
                      date.isEarlier(appointments.get(i).getDate())){
                      appointments.add(0, newAppointment);
                      break;
                  }
                  else if(date.isEarlier(appointments.get(i).getDate())){
                      appointments.add((i - 1), newAppointment);
                      break;
                  }
                  else if(date.isEarlier(appointments.get(i).getDate()) &&
                          endTime.isEarlier(appointments.get(i).getEnd())){
                       appointments.add((i - 1), newAppointment);
                       break;
                  }
                  else{
                      appointments.add(newAppointment);
                      break;
                  }
              }
          }
       }
    }
    // prints the Appointment details for all appointments in this
    // Calendar scheduled for this purpose. This list should be
    // meaningfully labeled. 
    
    // does this class print every appointment or just the parameter that is given appointment?
    public void findAppointment(String purpose){
        String textPurpose = "";
        System.out.println("Here is all the appointments for " + purpose);
        for(int i = 0; i < appointments.size(); i++){
            Appointment currAppt = appointments.get(i);
            if(currAppt.getDescription() == purpose){
               System.out.println(currAppt.getDate() + "\t" + currAppt.getStart() + "\t" + currAppt.getEnd());
            }
        }
    }
    // removes an appointment the with the given purpose and on the
    // given day, if it exists. If there is no appointment scheduled 
    // for this purpose on this day, this method does nothing.
    public void cancelAppointment(String purpose, CalendarDate d){
        for(int i = 0; i < appointments.size(); i++){
            if(appointments.get(i).getDescription() == purpose &&
               appointments.get(i).getDate() == d)
            {
                appointments.remove(i);
                break;
            }
        }
    }
    
  
}
