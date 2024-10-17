/**
 * DiaryItemOperationLog object is required for the undo functionality
 * we are going to store in the stack the following objects:  
 * 1) added/updated/removed copy of a DiaryItem
 * 2) DiaryItem Id
 * 3) operation type: add, remove or update
 * so, later when we call 'Undo' feature we can simply restore changed object     
 **/
public class DiaryItemOperationLog {
    // a copy of a DiaryItem object to be stored in the stack, 
    // later, when we need to restore the object - we just copy fields to the new one 
    public DiaryItem diaryItem;
    
    // logged ID is an ID of the Diary Item which was chenged by the operation, it's required to simplify restore of the object 
    int loggedId;
    
    //operation: add, update or remove
    public String operation;

    /**
     * DiaryItemOperationLog() - constructor of a class 
     * @param di diary item
     * @param operation operation
     * @param loggedId logged ID
     */
    public DiaryItemOperationLog(DiaryItem di, String operation, int loggedId)
    {
        diaryItem = new DiaryItem(  di.getTreatmentType(), 
                                    di.getFromDateTime(), 
                                    di.getToDateTime(), 
                                    di.getParticipants());

        this.operation = operation;
        this.loggedId = loggedId;
    }

    /**
     * getDiaryItem() - gets diary item
     * @return diary item 
     */
    public DiaryItem getDiaryItem()
    {
        return this.diaryItem; 
    }

    /**
     * getOperation() - gets operation 
     * @return operation 
     */
    public String getOperation()
    {
        return this.operation;
    }

    /**
     * getLoggedId() - gets logged ID 
     * @return logged ID
     */
    public int getLoggedId()
    {
        return this.loggedId;
    }
}
