import java.util.HashSet;
import java.util.Set;

public class Professional {

    // id needs to be unique, it is required and set by the HealthProfessional class  
    private int id;
    private String name;
    private String profession;
    private String location;

    //deleted field for soft-delete  
    //private boolean deleted;
    
    public Professional()
    {
        name = new String();
        profession = new String();
        location = new String();
        //deleted = false;
    }

    public Professional(String name, String profession, String location)
    {
        this.name = name;
        this.profession = profession;
        this.location = location;
        //deleted = false;
    }

    public void set(String name, String profession, String location)
    {
        this.name = name;
        this.profession = profession;
        this.location = location;
    }

/*     public void setDeleted (boolean val)
    {
        this.deleted = val;
    }  */

    public void setId(int id)
    {
        this.id = id;
    }

    public void print()
    {
        //System.out.println("Professional = {id: " + id + ", name: " + name + ", profession: " + profession + ", location: " + location + "}");
        System.out.println("Professional = {" + 
                                            "id: " + id +
                                            ", name: " + name + 
                                            ", profession: " + profession + 
                                            ", location: " + location + 
                                            //", deleted: " + deleted + 
                                            "}");
    }

    public String getName()
    {
        return this.name;
    }

    public String getProfession()
    {
        return this.profession;
    }

    public String getLocation()
    {
        return this.location;
    }
    public int getId()
    {
        return this.id;
    }

/*     public Set<String> get()
    {
        Set<String> p = new HashSet<String>();
        p.add(name);
        p.add(profession);
        p.add(location);
        return p;
    }
*/

}