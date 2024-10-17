import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Scheduler {
    private HealthProfessionals healthProfessionalsDataStore;
    protected DiaryItemsSet diaryItemsSet;

    public Scheduler()
    {
        healthProfessionalsDataStore = new HealthProfessionals();
        diaryItemsSet = new DiaryItemsSet();
    }

    public void genarateHealthProfsDataStore()
    {       
        System.out.println("GENERATE PROFESSIONALS DATA STORE (for testing purposes)");
        System.out.println("-----------------------------------------------");
        Professional pro = new Professional();         

        System.out.println("[GENERATE PROs] Add 1st professional via set() - Johnny Depp");
        pro.set("Johnny Depp", "Doctor", "Dundee");
        healthProfessionalsDataStore.add(pro);
        //healthProfessionalsDataStore.print();
        System.out.println();

        System.out.println("[GENERATE PROs] Add 2nd profesional via class constructor - Will Smith");
        pro = new Professional("Will Smith", "Nurse", "Edinburgh");
        healthProfessionalsDataStore.add(pro);
        //healthProfessionalsDataStore.print();
        System.out.println();

        System.out.println("[GENERATE PROs] Add 3nd profesional via class constructor - John Travola");
        pro = new Professional("John Travola", "Doctor", "Dundee");
        healthProfessionalsDataStore.add(pro);
        //healthProfessionalsDataStore.print();
        System.out.println();    

        System.out.println("[GENERATE PROs] Add 4th profesional via class constructor - John Wick");
        pro = new Professional("John Wick", "Nurse", "Dundee");
        healthProfessionalsDataStore.add(pro);
        healthProfessionalsDataStore.print();
        System.out.println(); 
    }

    public void generateDiarySet()
    {
        System.out.println("GENERATE DIARY ITEMS DATA STORE (for testing purposes)");

        LocalDateTime fromDateTime = null;
        LocalDateTime toDateTime = null;
        
        int year = 2024;   
        int month = 1;
        int dayOfMonth = 1;
        
        int hour = 10;
        int minute = 00;
        fromDateTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
        minute = 30;
        toDateTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute);

        DiaryItem di = new DiaryItem();
        HealthProfessionals participants;

        System.out.println("Adding one participant Will Smith to the Consultation diary");
        participants = new HealthProfessionals();
        //participants.add(new Professional("Will Smith", "Nurse", "Dundee"));
        Professional pro = new Professional(healthProfessionalsDataStore.searchById(1).getName(), 
                                            healthProfessionalsDataStore.searchById(1).getProfession(), 
                                            healthProfessionalsDataStore.searchById(1).getLocation());
        participants.add(pro);

        di.set("Consultation", fromDateTime, toDateTime, participants.getAll());
        diaryItemsSet.add(di);
        diaryItemsSet.print(); 
        System.out.println("");

        System.out.println("Adding two participants Tom Cruse and Will Smith to the Operation diary");
        participants = new HealthProfessionals();
        //participants.add(new Professional("Tom Cruse", "Nurse", "Edinburgh"));
        //participants.add(new Professional("Will Smith", "Nurse", "Dundee"));
        pro = new Professional( healthProfessionalsDataStore.searchById(1).getName(), 
                                healthProfessionalsDataStore.searchById(1).getProfession(), 
                                healthProfessionalsDataStore.searchById(1).getLocation());
        participants.add(pro);
        pro = new Professional( healthProfessionalsDataStore.searchById(2).getName(), 
                                healthProfessionalsDataStore.searchById(2).getProfession(), 
                                healthProfessionalsDataStore.searchById(2).getLocation());
        participants.add(pro);
        
        hour = 10;
        minute = 00;
        fromDateTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
        hour = 11;
        minute = 00;
        toDateTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute);

        diaryItemsSet.add(new DiaryItem("Operation", fromDateTime, toDateTime, participants.getAll()));

        participants = new HealthProfessionals();
        pro = new Professional( healthProfessionalsDataStore.searchById(2).getName(), 
                                healthProfessionalsDataStore.searchById(2).getProfession(), 
                                healthProfessionalsDataStore.searchById(2).getLocation());
        participants.add(pro);

        pro = new Professional( healthProfessionalsDataStore.searchById(3).getName(), 
                                healthProfessionalsDataStore.searchById(3).getProfession(), 
                                healthProfessionalsDataStore.searchById(3).getLocation());
        participants.add(pro);
        fromDateTime = LocalDateTime.of(year, month, dayOfMonth, 14, 00);
        toDateTime = LocalDateTime.of(year, month, dayOfMonth, 16, 00);

        diaryItemsSet.add(new DiaryItem("Operation", fromDateTime, toDateTime, participants.getAll()));


        diaryItemsSet.print(); 
        System.out.println("");
    }

    public void healthProfessionalDiary()
    {
        System.out.println("Health professional's Diary");
        System.out.print("Enter professional's name to find their diary items: ");
        String name = Genio.getString();
        //String name = "Tom Cruse";
        Set<DiaryItem> searchRes = new HashSet<DiaryItem>();
        searchRes = diaryItemsSet.searchDiaryItemsByProfessionalName(name);
        
        System.out.println("Search Results:");
        for (DiaryItem diaryItem : searchRes) {
            diaryItem.print(); 
        }
    }

    public void addHealthProfessional()
    {
        //input professional's: 
        //name
        //profession
        //location
        // repeat if user want to add more profs          
        System.out.println("Health professionals data store");
        Professional p;
        char c;
        do 
        {
            p = new Professional();
            System.out.println("ADD Health professional");
            
            System.out.println("Enter name:");
            String name = Genio.getString();
    
            System.out.println("Enter profession:");
            String profession = Genio.getString();
    
            System.out.println("Enter location:");
            String location = Genio.getString();
            
            p.set(name, profession, location);
    
            healthProfessionalsDataStore.add(p);
            System.out.print("Add another professional? (y/n): ");
            c = Genio.getCharacter();
            if ( c=='n' || c == 'N')
                break; 
        } while  (true);

    } 

    public void deleteHealthProfessional()
    {
        System.out.println("Health professionals data store");
        System.out.println("DELETE Health professional by id");

        System.out.println("Enter professional id:");
        int id = Genio.getInteger();

        boolean res = healthProfessionalsDataStore.removeById(id);
        if (!res)
            System.out.println("Failed to remove professional by id " + id);
    }

    public void updateHealthProfessional()
    {
        System.out.println("Health professionals data store");
        System.out.println("UPDATE Health professional by id");

        System.out.println("Enter professional id:");
        int id = Genio.getInteger();

        System.out.println("Enter new name:");
        String name = Genio.getString();

        System.out.println("Enter new profession:");
        String profession = Genio.getString();

        System.out.println("Enter new location:");
        String location = Genio.getString();

        Professional p = new Professional();
        p.set(name, profession, location);

        boolean res = healthProfessionalsDataStore.updateById(id, p);
        if (!res)
            System.out.println("Failed to update professional by id " + id);
    }
    
    public void printProfessionalDataStore()
    {
        System.out.println("Health professionals data store");
        healthProfessionalsDataStore.print();
    }

    public int enterIntHelper(String message, int min, int max)
    {
        int res = 0;
        System.out.print(ConsoleColors.GREEN_BOLD_BRIGHT);
        System.out.print(message + " ");
        System.out.print(ConsoleColors.RESET);
        do {
            res = Genio.getInteger();       
        } while ((res < min) && (res > max));

        return res; 
    }

    public void addDiaryItemToDiaryItemsSet()
    {
        System.out.println("Diary Items data store");
        System.out.println("Add Diary Item to the data store");
        LocalDateTime fromDateTime = null;
        LocalDateTime toDateTime = null;

        DiaryItem diaryItem = new DiaryItem();
        HealthProfessionals participants = new HealthProfessionals();
        char c;
        do {
            System.out.print("Enter participant by their professional id: ");
            int id = Genio.getInteger();
    
            Professional p = healthProfessionalsDataStore.searchById(id);
            if (p!=null)
                participants.add(p);
            else 
                System.out.println("WARN: Participant was not added"); 
                
            System.out.print("Add another participant? (Y/N): ");
            c = Genio.getCharacter();
            if ( c=='n' || c == 'N')
                break; 
        } while  (true);

        System.out.println("");
        System.out.print("Enter treatment type: ");
        String treatmentType = Genio.getString();

        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        boolean continue_loop = false;  
        do {
            System.out.print("Enter 'From' date/time (format: dd-MM-yyyy HH:mm): ");
            String tmpFrom = Genio.getString();
            continue_loop = false;
            try {
                fromDateTime = LocalDateTime.parse(tmpFrom, myFormatObj);
            } catch (DateTimeParseException e)
            {
                System.out.println("ERROR: Date/Format is incorrect, please try again");
                continue_loop = true;
            }
        } while  (continue_loop);

        continue_loop = false;  
        do {
            System.out.print("Enter 'To'  date/time  (format: dd-MM-yyyy HH:mm): ");
            String tmpTo = Genio.getString();
            //continue_loop = false;
            try {
                toDateTime = LocalDateTime.parse(tmpTo, myFormatObj); 
            } catch (DateTimeParseException e)
            {
                System.out.println("ERROR: Date/Format is incorrect, please try again");
                continue_loop = true;
            }

            if (toDateTime != null)
            {
                if (toDateTime.isAfter(fromDateTime))
                    continue_loop = false;
                else
                {
                    System.out.println("ERROR: 'To' date/time must be after 'From', please try again");
                    continue_loop = true;  
                }
            }
        } while  (continue_loop);

        //TODO add a check if for the specified timeslot participant(s) are busy, so the appointment can't be booked

        diaryItem.set(treatmentType, fromDateTime, toDateTime, participants.getAll());
        diaryItemsSet.add(diaryItem);
        diaryItemsSet.print(); 
        System.out.println("");
    }
    
    public void updateDiaryItemInDiaryItemsSet()
    {
        //Note: hard-coded data for the update 
        //TODO implement interaction with the user to get  
        // updated data for the Diary Item: Treatment type, from, to, participants

        System.out.println("Diary Items data store");
        System.out.println("Update Diary Item in the data store");
        System.out.print("Enter Diary Item id to update: ");
        int idToUpdate = Genio.getInteger();
        
        DiaryItem diSerachResult = new DiaryItem();
        diSerachResult = diaryItemsSet.searchDiaryItemsById(idToUpdate);
        diSerachResult.print();

        LocalDateTime fromDateTime = null;
        LocalDateTime toDateTime = null;
        
        DiaryItem diaryItem = new DiaryItem();
        HealthProfessionals participants = new HealthProfessionals();
        char c;
        do {
            System.out.print("Enter participant by their professional id: ");
            int id = Genio.getInteger();
    
            Professional p = healthProfessionalsDataStore.searchById(id);
            if (p!=null)
                participants.add(p);
            else 
                System.out.println("WARN: Participant was not added"); 
                
            System.out.print("Add another participant? (Y/N): ");
            c = Genio.getCharacter();
            if ( c=='n' || c == 'N')
                break; 
        } while  (true);

        System.out.println("");
        System.out.print("Enter treatment type: ");
        String treatmentType = Genio.getString();

        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        boolean continue_loop = false;  
        do {
            System.out.print("Enter 'From' date/time (format: dd-MM-yyyy HH:mm): ");
            String tmpFrom = Genio.getString();
            continue_loop = false;
            try {
                fromDateTime = LocalDateTime.parse(tmpFrom, myFormatObj);
            } catch (DateTimeParseException e)
            {
                System.out.println("ERROR: Date/Format is incorrect, please try again");
                continue_loop = true;
            }
        } while  (continue_loop);

        continue_loop = false;  
        do {
            System.out.print("Enter 'To'  date/time  (format: dd-MM-yyyy HH:mm): ");
            String tmpTo = Genio.getString();
            //continue_loop = false;
            try {
                toDateTime = LocalDateTime.parse(tmpTo, myFormatObj); 
            } catch (DateTimeParseException e)
            {
                System.out.println("ERROR: Date/Format is incorrect, please try again");
                continue_loop = true;
            }
            if (toDateTime != null)
            {
                if (toDateTime.isAfter(fromDateTime))
                    continue_loop = false;
                else
                {
                    System.out.println("ERROR: 'To' date/time must be after 'From', please try again");
                    continue_loop = true;  
                }
            }
/*             else
            {
                System.out.println("ERROR: Date/Format is incorrect, please try again");
                continue_loop = true;  
            } */
        } while  (continue_loop);

        //TODO add a check if for the specified timeslot participant(s) are busy, so the appointment can't be booked

        diaryItem.set(treatmentType, fromDateTime, toDateTime, participants.getAll());
        //TODO add a check if for the specified timeslot participant(s) are busy, so the appointment can't be updated 

        boolean res = diaryItemsSet.updateByID(idToUpdate, diaryItem);
        System.out.println("Res = " + res);
        diaryItemsSet.print();
    }

    public void deleteDiaryItem()
    {
        System.out.println("Diary Items data store");
        diaryItemsSet.print();
        System.out.print("Enter Diary Item id to delete: ");
        int id = Genio.getInteger();
        diaryItemsSet.removeByID(id);
        diaryItemsSet.print();
    }

    public void printDiaryItems()
    {
        diaryItemsSet.print();
    }

    public void AdvancedDiaryItemsSearch()
    {
        Set<Professional> participants = new HashSet<Professional>();
        char c;
        do {
            System.out.print("Enter participant by their professional id: ");
            int id = Genio.getInteger();
    
            Professional p = healthProfessionalsDataStore.searchById(id);
            if (p!=null)
                participants.add(p);
            else 
                System.out.println("WARN: Participant was not added"); 
                
            System.out.print("Add another participant? (Y/N): ");
            c = Genio.getCharacter();
            if ( c=='n' || c == 'N')
                break; 
        } while  (true);
        
        LocalDateTime fromDateTime = null;
        LocalDateTime toDateTime = null;

        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        boolean continue_loop = false;  
        do {
            System.out.print("Enter 'From' date/time (format: dd-MM-yyyy HH:mm): ");
            String tmpFrom = Genio.getString();
            continue_loop = false;
            try {
                fromDateTime = LocalDateTime.parse(tmpFrom, myFormatObj);
            } catch (DateTimeParseException e)
            {
                System.out.println("ERROR: Date/Format is incorrect, please try again");
                continue_loop = true;
            }
        } while  (continue_loop);

        continue_loop = false;  
        do {
            System.out.print("Enter 'To'  date/time  (format: dd-MM-yyyy HH:mm): ");
            String tmpTo = Genio.getString();
            //continue_loop = false;
            try {
                toDateTime = LocalDateTime.parse(tmpTo, myFormatObj); 
            } catch (DateTimeParseException e)
            {
                System.out.println("ERROR: Date/Format is incorrect, please try again");
                continue_loop = true;
            }

            if (toDateTime.isAfter(fromDateTime))
                continue_loop = false;
            else
            {
                System.out.println("ERROR: 'To' date/time must be after 'From', please try again");
                continue_loop = true;  
            }
        } while  (continue_loop);

        diaryItemsSet.searchAdvanced(participants, fromDateTime, toDateTime); 
    }

    public void undoLastOperation()
    {
        System.out.println("Undo last operation");
        System.out.println("Diary item set Before Undo:");
        diaryItemsSet.print();

        System.out.println("Undo......................");
        diaryItemsSet.undoLastOperation();

        System.out.println("Diary item set After Undo:");
        diaryItemsSet.print();
    }

 public void saveToFile()
    {
        try {
            //Saving the professionals data to the text file:
            BufferedWriter pr = new BufferedWriter(new FileWriter("healthProfessionals.txt"));
            for(Professional p : healthProfessionalsDataStore.getAll())
            {
                pr.write(p.getId()+","+p.getName()+","+p.getProfession()+","+p.getLocation());
                pr.newLine();
            }
            pr.close();
            System.out.println("Professionals data is saved to a text file");


            //saving the diaryItemsSet data to the text file:
            pr = new BufferedWriter(new FileWriter("diaryItems.txt"));
            for(DiaryItem d : diaryItemsSet.getAll()){
                pr.write(d.getId()+","+
                           d.getFromDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "," +
                           d.getToDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "," +
                           d.getTreatmentType()+","+
                           d.getParticipantsString());  
                pr.newLine();
            }
            pr.close();
            System.out.println("Diary items data is saved to a text file");

        } catch (Exception e)
        {
            System.out.println("it seems as we have an error here: " + e);
        }
    }

    public void loadFromFile()
    {
        try{
            String line;

            //reading professional from the text file
            BufferedReader br = new BufferedReader(new FileReader("healthProfessionals.txt"));
            while ((line = br.readLine()) != null)
            {
                String[] data = line.split(",");

                //reading values from the text file
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String profession = data[2];
                String location = data[3];

                //giving the professional its values got from the text file
                Professional p = new Professional(name, profession, location);
                p.setId(id);
                //adding this professional to the data store
                healthProfessionalsDataStore.add(p);
            }
            br.close();
            System.out.println("Professionals data is loaded from a text file");

            //reading diary items from the text file
            br = new BufferedReader(new FileReader("diaryItems.txt"));
            while((line = br.readLine()) != null)
            {
                Set<Professional> participants = new HashSet<>();

                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                LocalDateTime fromDateTime = LocalDateTime.parse(data[1], DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                LocalDateTime toDateTime = LocalDateTime.parse(data[2], DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                String treatmentType = data[3];
                //reading participants values from the text file
                for(int i = 4; i<data.length; i++)
                {
                    Professional p = healthProfessionalsDataStore.searchById(Integer.parseInt(data[i]));
                    if(p != null)
                    {
                        participants.add(p);
                    }
                }

                DiaryItem diaryItem = new DiaryItem(treatmentType, fromDateTime, toDateTime, participants);
                diaryItem.setId(id);
                diaryItemsSet.add(diaryItem);
            }
            br.close();
            System.out.println("Diary items data is loaded from a text file");

        } catch (Exception e)
        {
            System.out.println("it seems as we have an error here: " + e);
        }
    }
}
