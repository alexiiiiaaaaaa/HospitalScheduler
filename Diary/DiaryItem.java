import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

/**
 * DiaryItem() сlass - 
 */
public class DiaryItem {
    public static LocalTime WORKING_HOURS_FROM    = LocalTime.of(7, 59);
    public static LocalTime WORKING_HOURS_TO      = LocalTime.of(18, 01);
    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;
    private String treatmentType;
    private int id;

    private Set<Professional> participants;

    /**
     * DiaryItem() - constructor of a class 
     */
    public DiaryItem()
    {
        this.fromDateTime = null;
        this.toDateTime = null;
        this.treatmentType = new String("EMPTY DIARY");
        participants = new HashSet<Professional>();
    }

    /**
     * Consider removing of this constructor, since we can't control it's return type and errors correspondingly  
     * @param treatmentType
     * @param from
     * @param to
     * @param participants
     */
    public DiaryItem(String treatmentType, LocalDateTime from, LocalDateTime to, Set<Professional> participants)
    {
        this.fromDateTime = null;
        this.toDateTime = null;
        this.treatmentType = new String("EMPTY DIARY");
        this.participants = new HashSet<Professional>();
        if (to.isAfter(from)                                && 
            from.toLocalTime().isAfter(WORKING_HOURS_FROM)  &&
            from.toLocalTime().isBefore(WORKING_HOURS_TO)   && 
            to.toLocalTime().isAfter(WORKING_HOURS_FROM)    &&
            to.toLocalTime().isBefore(WORKING_HOURS_TO)        ) 
            {
                this.treatmentType = treatmentType;
                this.fromDateTime = from;
                this.toDateTime = to;
                this.participants = participants;
            }
        else {
            System.out.print(ConsoleColors.RED);
            System.out.println("ERROR: 'from' date can't be later then 'to' / or dates are out of working hours");
            System.out.println("from: " + from);
            System.out.println("  to: " + to);
            System.out.println("WORKING_HOURS_FROM: " + WORKING_HOURS_FROM);
            System.out.println("  WORKING_HOURS_TO: " + WORKING_HOURS_TO);
            System.out.print(ConsoleColors.RESET);
        }
    }

    /**
     * setId() - sets ID number
     * @param id ID
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * getId() - gets ID 
     * @return ID
     */
    public int getId()
    {
        return this.id;
    }

    /**
     * getFromDateTime() - method to get 'from' date time
     * @return 'from' date time 
     */
    public LocalDateTime getFromDateTime()
    {
        return this.fromDateTime;
    }

    /**
     * getToDateTime() - method to get 'to' date time
     * @return 'to' date time
     */
    public LocalDateTime getToDateTime()
    {
        return this.toDateTime;
    }

    /**
     * getParticipants() - gets partisipants from set 
     * @return partisipants 
     */
    public Set<Professional> getParticipants()
    {
        return this.participants;
    }

    /**
     * getTreatmentType() - method to get treatment type
     * @return treatment type
     */
    public String getTreatmentType()
    {
        return this.treatmentType;
    }

    /**
     * set() use set method to fill the DIaryItem 
     * @param treatmentType treatment type
     * @param from from time
     * @param to to time
     * @param participants partisipants 
     * @return result, true or false 
     */
    public boolean set(String treatmentType, LocalDateTime from, LocalDateTime to, Set<Professional> participants)
    {
        boolean res = false;
        //TODO: add check if 'from' and 'to' are from the same date
        //TODO: add working hours feature and check if 'from' and 'to' are within working hours    
        if (to.isAfter(from)                                && 
            from.toLocalTime().isAfter(WORKING_HOURS_FROM)  &&
            from.toLocalTime().isBefore(WORKING_HOURS_TO)   && 
            to.toLocalTime().isAfter(WORKING_HOURS_FROM)    &&
            to.toLocalTime().isBefore(WORKING_HOURS_TO)        ) 
            {
                this.treatmentType = treatmentType;
                this.fromDateTime = from;
                this.toDateTime = to;
                this.participants = participants;
                res = true;
            }
        else {
            System.out.print(ConsoleColors.RED);
            System.out.println("ERROR: 'from' date can't be later then 'to' / or dates are out of working hours");
            System.out.println("from: " + from);
            System.out.println("  to: " + to);
            System.out.println("WORKING_HOURS_FROM: " + WORKING_HOURS_FROM);
            System.out.println("  WORKING_HOURS_TO: " + WORKING_HOURS_TO);
            System.out.print(ConsoleColors.RESET);
            res = false;
        }
        return res;
    }

    /**
     * print() - method to print diary item with ID, treatment type, from to type and name
     */
    public void print()
    {
        String names = new String("{participants: ");
        for (Professional p : participants) {
            names = names + p.getName() + ", ";
        }

        //removes last ", " from the string 
        names = names.substring(0, names.length()-2);
        names = names + "}";
        
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String formattedFromDate;
        if (fromDateTime  != null)
            formattedFromDate = fromDateTime.format(myFormatObj);
        else 
            formattedFromDate = "N/A";
        
        String formattedToDate;
        if (toDateTime != null)
            formattedToDate = toDateTime.format(myFormatObj);
        else 
            formattedToDate = "N/A";

        System.out.println("DiaryItem: {id: " + id +
                                        ", treatmentType: " + treatmentType + 
                                        ", from: " + formattedFromDate + 
                                        ", to: " + formattedToDate + 
                                        ", " + names + 
                                        "}");
    }

    /**
     * getParticipantsString() - generate a single String that contains the IDs of all the Professional objects in the participants collection, separated by commas
     * @return returns IDs of the professionals delimited by ',' in from the participants set in form of String. 
     */
    public String getParticipantsString() {
        StringBuilder sb = new StringBuilder();
        for (Professional p : participants) {
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append(p.getId());
        }
        return sb.toString();
    }
}