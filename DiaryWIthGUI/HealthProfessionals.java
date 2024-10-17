import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;

public class HealthProfessionals {
    private Set<Professional> professionals;
     
    private int id;
    //private Iterator<Professional> ip;

    public HealthProfessionals()
    {
        professionals = new HashSet<Professional>();
        id = 0;
    }

    public void add(Professional person)
    {
        person.setId(++id);
        boolean res = professionals.add(person);
        if (res)
            System.out.println("Professional added, their id = " + id);
        else 
            System.out.println("Error: Unable to add professional");
    }
    
    public Professional searchById(int id)
    {
        Professional res = null;
        Iterator<Professional> iter = professionals.iterator();
        Professional current = new Professional();

        while (iter.hasNext()) {
            current = iter.next();
            if (current.getId() == id) {
                System.out.print("Found professional by id: "+ id + " :");
                current.print();
                res = current;
                return res;
            }
        }
        if (res == null) {
            System.out.println("ERROR: Can't find professional by the id: " + id);
        }
        return res;
    }
    

    public boolean updateById(int id, Professional person)
    {
        boolean res = false;
        for (Professional p : professionals) {
            if (p.getId() == id) {

                System.out.print("INPUT OBJECT      : ");
                person.print();
                System.out.print("OBJECT TO UPDATE  : ");
                p.print();

                System.out.print("UPDATED OBJECT    : ");
                p.set(person.getName(), person.getProfession(), person.getLocation());;
                p.print();
                res = true;
            }
        }
        if (!res) {
            System.out.println("Can't Update any record because id: " + id + " was not found");
        }
        return res;
    }
    public boolean removeById(int id)
    {
        boolean res = false;
        Iterator<Professional> iter = professionals.iterator();
        Professional current = new Professional();

        while (iter.hasNext()) {
            current = iter.next();
            if (current.getId() == id) {
                
                System.out.print("REMOVED Professional by id: "+ id + " :");
                current.print();
                
                iter.remove();
                res = true;
            }
        }
        if (!res) {
            System.out.println("id doesn't match so the record can NOT be removed");
        }
        return res;
    }
    
    public boolean removeProfessional(Professional person)
    {
        boolean res = false;
        Iterator<Professional> iter = professionals.iterator();
        Professional current = new Professional();

        while (iter.hasNext()) {
            current = iter.next();
            if (current.getName() == person.getName() && 
                current.getLocation() == person.getLocation() && 
                current.getProfession() == person.getProfession()) {
                
                System.out.print("INPUT OBJECT: ");
                person.print();

                System.out.print("HARD REMOVED: ");
                current.print();

                iter.remove();
                res = true;
            }
        }
        if (!res) {
            System.out.println("ERROR: Name, location and profession doesn't match so the record can NOT be removed");
        }
        return res;
    }

    /**
     * softRemove - method soft-removes a record from the Set by setting the deleted field to true  
     * @param person proffesional 
     * @return true if soft remove was succesful, false otherwise 
     */
/*     public boolean softRemove(Professional person)
    {
 
        boolean res = false;
        for (Professional p : professionals) {
            if (p.getName() == person.getName() && 
                p.getLocation() == person.getLocation() && 
                p.getProfession() == person.getProfession()) {
                p.setDeleted(true);

                System.out.print("INPUT OBJECT: ");
                person.print();
                System.out.print("SOFT REMOVED: ");
                p.print();
                res = true;
            }
        }
        if (!res) {
            System.out.println("Name, location and profession doesn't match so the record can NOT be SOFT-removed");
        }
        return res;
    }  */

    public void print()
    {
        System.out.println("PRINT ALL HEALTH PROFESSIONALS:");
        for (Professional p : professionals) {
            p.print();
        }
    }

    public Set<Professional> getAll()
    {
        return this.professionals;
    }

}
