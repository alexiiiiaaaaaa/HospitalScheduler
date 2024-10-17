import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.Iterator;

public class DiaryItemsSet {
    private Set<DiaryItem> diaryItemsSet;
    private int id;

    private Stack<DiaryItemOperationLog> undoStack;

    public DiaryItemsSet()
    {
        id = 0;
        diaryItemsSet = new HashSet<DiaryItem>();
        undoStack = new Stack<DiaryItemOperationLog>();
    }

    public boolean add(DiaryItem diaryItem)
    {
        boolean res = false;
        if (diaryItem.getFromDateTime() != null && diaryItem.getToDateTime() != null)
        {
            diaryItem.setId(++id);
            res = diaryItemsSet.add(diaryItem);
        }    
        
        if (res  == true)
        {
            System.out.print(ConsoleColors.GREEN);
            System.out.println("DiaryItem added to the Set"); 
            System.out.print(ConsoleColors.RESET);
            logOperation("add", diaryItem, id);
        }
        else
        {
            System.out.print(ConsoleColors.RED);
            System.out.println("ERROR: Can't add DiaryItem to a DiaryItemsSet");
            System.out.print(ConsoleColors.RESET);
        } 
        return res;
    }

    public boolean removeByID(int id)
    {
        boolean res = false;
        Iterator<DiaryItem> iter =  diaryItemsSet.iterator();
        DiaryItem current = new DiaryItem();
        while (iter.hasNext()) {
            current = iter.next();
            if (current.getId() == id) {
                System.out.print("REMOVED Diary Item by id = "+ id + " :");
                current.print();
                logOperation("remove", current, id);
                iter.remove();
                res = true;
                return res;
            }
        }
        if (!res) {
            System.out.println("id doesn't match so the record can NOT be removed");
        }
        return res;
    }

    public boolean updateByID(int id, DiaryItem newDI)
    {
        boolean res = false;
        for (DiaryItem diaryItem : diaryItemsSet) {
            if (id == diaryItem.getId())
            {
                //since we need to restore the original diaryItem object, then we save it first
                logOperation("update", diaryItem, id);
                res = diaryItem.set(newDI.getTreatmentType(), 
                                    newDI.getFromDateTime(),
                                    newDI.getToDateTime(),
                                    newDI.getParticipants());
                diaryItem.print();
                if (res  == true)
                {
                    System.out.print(ConsoleColors.GREEN);
                    System.out.println("DiaryItem updated by id: " + id);
                    System.out.print(ConsoleColors.RESET);
                    return res;
                }
                else
                {
                    //in case if set() failed then we need to pop() from the stack to clear unsuccesful operation
                    undoStack.pop();  
                    System.out.print(ConsoleColors.RED);
                    System.out.println("ERROR: Can't Update DiaryItem id: " + id);
                    System.out.print(ConsoleColors.RESET);
                    return res;
                } 
            }
        }
        return res;
    }

    // search if professional appears in the DiaryItem and adds it to the diarySet (which is returned as a result of the method)
    // note this method ignores ID's and compares professionals by their name, profession and location
    public Set<DiaryItem> searchDiaryItemsByProfessional(Professional p)
    {   
        Set<DiaryItem> diarySet = new HashSet<DiaryItem>();
        Set<Professional> profSet = new HashSet<Professional>();
        for (DiaryItem diaryItem : diaryItemsSet) {
            profSet = diaryItem.getParticipants();
            for (Professional professional : profSet) {
                if (professional.getName().contains(p.getName()) && 
                    professional.getProfession().contains(p.getProfession()) &&
                    professional.getLocation().contains(p.getLocation()))
                    {
                        diarySet.add(diaryItem);
                    }
            }
        }
        return diarySet;
    }

    /**
     * method to search items by date, it is needed in the GUI to display diary items in the specific date
     * @param date
     * @return DiaryItems
     */
    public Set<DiaryItem> searchDiaryItemsByDate(LocalDate date) {
        Set<DiaryItem> diarySet = new HashSet<>();
        for (DiaryItem diaryItem : diaryItemsSet) {
            if (diaryItem.getFromDateTime().toLocalDate().equals(date) || diaryItem.getToDateTime().toLocalDate().equals(date)) {
                diarySet.add(diaryItem);
            }
        }
        return diarySet;
    }

    public Set<DiaryItem> searchDiaryItemsByProfessionalName(String name)
    {   
        Set<DiaryItem> diarySet = new HashSet<DiaryItem>();
        Set<Professional> profSet = new HashSet<Professional>();
        for (DiaryItem diaryItem : diaryItemsSet) {
            profSet = diaryItem.getParticipants();
            for (Professional professional : profSet) {
                if (professional.getName().contains(name))     
                    diarySet.add(diaryItem);
            }
        }
        return diarySet;
    }

    public DiaryItem searchDiaryItemsById(int id)
    {   
        DiaryItem res = null; 
        for (DiaryItem diaryItem : diaryItemsSet) {
            if (id == diaryItem.getId())
                {
                    res = diaryItem;
                    return res;
                }
        }
        return res;
    }

    public Set<FromToDateTime> searchAdvanced(Set<Professional> participants, LocalDateTime from, LocalDateTime to)
    {
        FromToDateTime fromToObj = null;
        Set<FromToDateTime> res = new HashSet<FromToDateTime>();
        Set<DiaryItem> diaryItems = new HashSet<DiaryItem>();; 
        Set<DiaryItem> diaryItemsAll = new HashSet<DiaryItem>();

        for (Professional p : participants) {
            diaryItems = searchDiaryItemsByProfessional(p); 
            diaryItemsAll.addAll(diaryItems);
        }

        //printing of found results
        System.out.println("Found Diary Items");
        for (DiaryItem di : diaryItemsAll) {
            fromToObj = new FromToDateTime(di.getFromDateTime(), di.getToDateTime());
            res.add(fromToObj);
            di.print();
        }
        
        System.out.println("from /to set");
        for (FromToDateTime ftDateTime : res) {
            ftDateTime.print();
        }

        return res;
    }

    public void print()
    {
        System.out.println("PRINT ALL DIARY ITEMS:");
        for (DiaryItem diaryItem : diaryItemsSet) {
            diaryItem.print();
        }
    }

    public int size()
    {
        return this.diaryItemsSet.size();
    }

    public void logOperation(String operation, DiaryItem di, int loggedId)
    {
        if (operation.contentEquals("add")      ||
            operation.contentEquals("update")   || 
            operation.contentEquals("remove")     )
            {
                DiaryItemOperationLog diLog = new DiaryItemOperationLog(di, operation, loggedId);
                undoStack.push(diLog);
            }
        else
            System.out.println("WARN: unknown log operation: " + operation);
    }

    public boolean undoLastOperation()
    {
        boolean res = false;
        DiaryItemOperationLog diLog = null;
        if (!undoStack.empty())
        {
            diLog = undoStack.pop();
        }
        else
        {
            System.out.println("WARN: recent operations stack is empty, nothing to Undo");
            return res;
        }  
        DiaryItem diaryItem = diLog.getDiaryItem();
        String operation = diLog.getOperation();
        int loggedId = diLog.getLoggedId();
        switch (operation) {
            case "add":
                removeByID(loggedId);
                System.out.println("Undo recent 'add' operation: Diary item was removed by id: " + loggedId);
                break;
            case "update":
                updateByID(loggedId, diaryItem);
                System.out.println("Undo recent 'update' operation: Diary item was updated by id: " + loggedId);
                break;
            case "remove":
                add(diaryItem);
                System.out.println("Undo recent 'remove' operation: Diary item was added back, new id " + this.id);
                break;
            default:
                break;
        }
        return res;
    }

    public Set<DiaryItem> getAll()
    {
        return this.diaryItemsSet;
    }
}
