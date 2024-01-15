import java.util.*;

/**
 * The purpose of this Class is to store all the TShirt objects in a single Inventory list object.
 */
public class Inventory {

    // Create the variable of the Inventory object.
    private final List<TShirt> inventory = new ArrayList<>();

    /**
     * The purpose of the addTShirt method, is to add a TShirt to the Inventory object.
     * @param tshirt is a TShirt object that represents one item in the inventory.
     */
    public void addTShirt(TShirt tshirt){
        this.inventory.add(tshirt);
    }

    /**
     * The purpose of the findDreamTShirt method is to search through the inventory and compare what is in stock with the user's criteria in order to find appropriate matches.
     * @param userDreamTShirt is a TShirt object representing the user's search criteria.
     * @return a list of TShirt objects that match the user's search criteria.
     */
    public List<TShirt> findDreamTShirt(TShirt userDreamTShirt){

        List<TShirt> compatibleTShirtsForUser = new ArrayList<>();

        for (TShirt tshirt: this.inventory){
            if (!tshirt.getBrand().equals(userDreamTShirt.getBrand())) continue;
            if (tshirt.getPrice() < userDreamTShirt.getMinPrice() || tshirt.getPrice() > userDreamTShirt.getMaxPrice()) continue;
            Set<Size> matchingSizes = new HashSet<>(tshirt.getSizes());
            matchingSizes.retainAll(userDreamTShirt.getSizes());
            if(matchingSizes.size()>0) compatibleTShirtsForUser.add(tshirt);
            }
        return compatibleTShirtsForUser;
    }

    /**
     * The purpose of this getter is to get a list of all the TShirt objects in the inventory.
     * @return a list of TShirt objects.
     */
    public List<TShirt> getInventory() {
        return inventory;
    }
}
