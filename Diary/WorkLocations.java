import java.util.ArrayList;

//Idea: to have a list of locations, which can be selected when scheduling a diary  
public class WorkLocations {
    private final int LOCATIONS_NUMBER = 10;
    private ArrayList<String> workLocations;
    
    public WorkLocations()
    {
        workLocations = new ArrayList<String>();
        workLocations.add("Location 1");
        workLocations.add("Location 2");
        workLocations.add("Location 3");
        workLocations.add("Location 4");
    }
}
