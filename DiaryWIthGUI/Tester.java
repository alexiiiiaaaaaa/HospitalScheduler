import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class Tester {
    DiaryItem di;
    DiaryItemsSet dr;
    HealthProfessionals participants;

    public Tester()
    {
        di = new DiaryItem();
        dr = new DiaryItemsSet();
        participants = new HealthProfessionals();
    }
    
    public void testDiary()
    {
        LocalDateTime fromDateTime = null;
        LocalDateTime toDateTime = null;

        int year = 2024;   
        int month = 1;
        int dayOfMonth = 1;
        int hour = 10;
        int minute = 00;
        
        fromDateTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
        // set 'to' to equal 'from' + 30 minutes
        minute = 30;
        toDateTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute);

        participants.add(new Professional("John Smith", "Doctor", "Clinic #1"));
        //Person person1 = new Person("John Smith", "Doctor", "Clinic #1");

        System.out.println("Positive test: 'from' date is before 'to' date");
        di.set("POSITIVE test appointment 1", fromDateTime, toDateTime, participants.getAll());
        dr.add(di);
        dr.print(); 
        System.out.println("");

        participants = new HealthProfessionals();
        participants.add(new Professional("Tom Cruse", "Nurse", "Clinic #1"));
 
        // set 'to' to equal 'from' + 60 minutes
        hour = 11;
        minute = 00; 
        toDateTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute);

        System.out.println("Positive test: 'from' date is before 'to' date");
        //di.set("POSITIVE test appointment", dateFrom, dateTo);
        dr.add(new DiaryItem("POSITIVE test appointment 2", fromDateTime, toDateTime, participants.getAll()));
        dr.print(); 
        System.out.println("");
        
        System.out.println("Negative test: 'from' date is later than 'to' date");
        //di.set("NEGATIVE test appointment", dateTo, dateFrom);
        dr.add(new DiaryItem("NEGATIVE test appointment", toDateTime, fromDateTime, participants.getAll()));
        dr.print();

        hour = 7;
        minute = 00; 
        fromDateTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute);

        hour = 18;
        minute = 00; 
        toDateTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute);

        System.out.println("Negative test: 'from' date is out of working hours");
        //di.set("NEGATIVE test appointment", dateTo, dateFrom);
        dr.add(new DiaryItem("NEGATIVE test appointment from", fromDateTime, toDateTime, participants.getAll()));
        dr.print();

        hour = 8;
        minute = 00; 
        fromDateTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute);

        hour = 19;
        minute = 00; 
        toDateTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute);

        System.out.println("Negative test: 'to' date is out of working hours");
        //di.set("NEGATIVE test appointment", dateTo, dateFrom);
        dr.add(new DiaryItem("NEGATIVE test appointment to", fromDateTime, toDateTime, participants.getAll()));
        dr.print();

        hour = 8;
        minute = 00; 
        fromDateTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute);

        hour = 18;
        minute = 00; 
        toDateTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute);

        System.out.println("Negative test: 'from' & 'to' date are working hours start and end");
        //di.set("NEGATIVE test appointment", dateTo, dateFrom);
        dr.add(new DiaryItem("NEGATIVE test appointment to", fromDateTime, toDateTime, participants.getAll()));
        dr.print();

        System.out.println("dr size = " + dr.size());
    }

    public void testProffessionals()
    {
        System.out.println("[TESTS SET] Add, Soft/Hard-remove professionals");
        System.out.println("-----------------------------------------------");
        Professional pro = new Professional(); 
        HealthProfessionals profs  = new HealthProfessionals();

        System.out.println("[TEST 1] Add 1st professional via set() - John S");
        pro.set("John S", "Doctor", "Location #1");
        profs.add(pro);
        profs.print();
        System.out.println();

        System.out.println("[TEST 2] Add 2nd profesional via class constructor - Will S");
        pro = new Professional("Will S", "Nurse", "Location #1");
        profs.add(pro);
        profs.print();
        System.out.println();

        System.out.println("[TEST 3] Add 3nd profesional via class constructor - Robert W");
        pro = new Professional("Robert W", "Doctor", "Location #1");
        profs.add(pro);
        profs.print();
        System.out.println();        

        System.out.println("[TEST 4] HARD-Remove professional - John S");
        Professional proToRemove = new Professional("John S", "Doctor", "Location #1");
        profs.removeProfessional(proToRemove);
        profs.print();
        System.out.println();

/*         System.out.println("[TEST 5] SOFT-Remove professional - Will S");
        proToRemove = new Professional("Will S", "Nurse", "Location #1");
        profs.softRemove(proToRemove);
        profs.print();
        System.out.println(); */

        System.out.println("[TEST 6] HARD-Remove professional - Will S");
        proToRemove = new Professional("Will S", "Nurse", "Location #1");
        profs.removeProfessional(proToRemove);
        profs.print();
        System.out.println();

        System.out.println("[TEST 7] Attempt to HARD-Remove non-existing professional - Will S");
        proToRemove = new Professional("Will S", "Nurse", "Location #1");
        profs.removeProfessional(proToRemove);
        profs.print();
        System.out.println();

/*         System.out.println("[TEST 8] Attempt to SOFT-Remove non-existing professional - Will S");
        proToRemove = new Professional("Will S", "Nurse", "Location #1");
        profs.softRemove(proToRemove);
        profs.print();
        System.out.println(); */

        System.out.println("[TEST 9] Update by id professional's name, profession and location - Robert W (id = 3)");
        Professional proToUpdate = new Professional("Will S", "Nurse", "Location #2");
        int id = 3;
        profs.updateById(id, proToUpdate);
        profs.print();
        System.out.println();
    }

    public void testDate()
    {
        LocalDateTime myDateObj = LocalDateTime.now();
        System.out.println("Before formatting: " + myDateObj);
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now(); 

        int year = 2024;   
        int month = 1;
        int dayOfMonth = 1;
        int hour = 10;
        int minute = 00;
        
        myDateObj = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
        String formattedDate = myDateObj.format(myFormatObj);
        System.out.println("After formatting: " + formattedDate);

        year = date.getYear();
        month = date.getMonthValue();
        dayOfMonth = date.getDayOfMonth();

        hour = time.getHour();
        minute = time.getMinute();

        myDateObj = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
        formattedDate = myDateObj.format(myFormatObj);
        System.out.println("After formatting: " + formattedDate);
    }
}
