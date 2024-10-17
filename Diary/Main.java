import java.io.IOException;

/**
 * Main class
 */
public class Main {

    private Scheduler scheduler;

    public Main()
    {
        scheduler = new Scheduler();
    }

    public static void main(String[] args) {        
        Main m = new Main();
        m.run();
    }

    /**
     * run() - main loop of the program which processes main menu logic
     */
    public void run ()
    {
        Tester tester = new Tester();

        int menuItem = 0;
        boolean keeplooping = true;
        do {
            PrintMenu();
            menuItem=Genio.getInteger();
            switch (menuItem) {
                case 1:
                    scheduler.addHealthProfessional();
                    clearScreen();
                    break;
                case 2:
                    scheduler.updateHealthProfessional();
                    clearScreen();
                    break;  
                case 3:
                    scheduler.deleteHealthProfessional();
                    clearScreen();
                    break;
                case 4:
                    scheduler.printProfessionalDataStore();
                    clearScreen();
                    break;    
                case 5:
                    scheduler.addDiaryItemToDiaryItemsSet();
                    clearScreen();
                    break;
                case 6:
                    scheduler.updateDiaryItemInDiaryItemsSet();
                    clearScreen();
                    break;
                case 7:
                    scheduler.deleteDiaryItem();
                    clearScreen();
                    break;
                case 8:
                    scheduler.healthProfessionalDiary();
                    clearScreen();
                    break;
                case 9:
                    scheduler.printDiaryItems();
                    clearScreen();
                    break;    
                case 10:
                    scheduler.AdvancedDiaryItemsSearch();
                    clearScreen();
                    break;   
                case 11:
                    scheduler.undoLastOperation();
                    clearScreen();
                    break;
                case 12:
                    scheduler.saveToFile();
                    clearScreen();
                    break;                
                case 13:
                    scheduler.loadFromFile();
                    clearScreen();
                    break;                 
                case 14:
                    scheduler.genarateHealthProfsDataStore();
                    scheduler.generateDiarySet();
                    clearScreen();
                    break;         
                case 15:
                    tester.testDiary();
                    tester.testProffessionals();
                    tester.testDate();
                    clearScreen();
                    break;    
                case 16:
                    keeplooping = false;
                    break;        
                default: 
                    System.out.println("Please enter a number from 1 to 16! ");
                    break;
            }

        } while (keeplooping == true);
        System.out.print(ConsoleColors.RESET);
        System.out.println("Exiting...");
    }


    /** 
     * PrintMenu() - prints main program menu
     * **/
    public void PrintMenu()
    {
        System.out.print(ConsoleColors.CYAN_BRIGHT);
        System.out.println("Health professionals Scheduler");
        System.out.println(" 1. Add new professional to the datastore");
        System.out.println(" 2. Update professional in the datastore");
        System.out.println(" 3. Delete professional from datastore");
        System.out.println(" 4. Print Professionals data store");
        System.out.println(" 5. Add new item to the diary");
        System.out.println(" 6. Update item in the diary by id");
        System.out.println(" 7. Delete item from the diary by id");
        System.out.println(" 8. Print Health professional's Diary");
        System.out.println(" 9. Print ALL Diary items");
        System.out.println("10. Advanced Diary Search of available time slots");
        System.out.println("11. Undo last add/update/remove DiaryItem operation");
        System.out.println("12. Save datastores to a file");
        System.out.println("13. Load datastores from the file");
        System.out.println("14. Generate Professional & Diary Items data stores");
        System.out.println("15. AUTO-TESTS");
        System.out.println("16. Exit");
        System.out.println("");
        System.out.print(ConsoleColors.RESET);

        System.out.print(ConsoleColors.YELLOW_BOLD_BRIGHT);
        //System.out.print(ConsoleColors.CYAN_BACKGROUND);
        System.out.print("Please select an option [1-16]: ");
        System.out.print(ConsoleColors.RESET);
    }


    public void healthProfsDataStore()
    {
        scheduler.genarateHealthProfsDataStore();
    }

    /**
    * clrscr() - clears the screen, method code taken from internet 
    */
    public static void clrscr(){
        //Clears Screen in java console
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else {
                System.out.print("\033\143");
            }
        } catch (IOException | InterruptedException ex) {}
        }
    
        /** 
         * clearScreen()  This method promts user to press 'y' to continue programm and clear the screen 
         * **/
        public void clearScreen()
        {
            System.out.print(ConsoleColors.YELLOW_BOLD_BRIGHT);
            System.out.print("Enter 'y' to continue: ");
            System.out.print(ConsoleColors.RESET);
            char c; 
            do {
                c = Genio.getCharacter();       
            } while ((c != 'y') && (c != 'Y'));
            clrscr(); 
        }
}
