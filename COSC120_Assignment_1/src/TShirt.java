import java.util.List;

/**
 * The purpose of the TShirt class is to create instances of TShirt objects.
 */
public class TShirt {

    // Create variables that make up a TShirt object
    private final String name;
    private final long productCode;
    private final float price;
    private final String brand;
    private final List<Size> sizes;
    private final String description;

    /*
        The below variables are to store the users input when searching for a t-shirt.
        Allowing the user to search within a price range rather than for a specific price will improve functionality.
        The user will be able to see all the T-Shirts that fall within their price range.
        Having a price rang option prevents the user from having to perform multiple searches to alter the price by a small amount.
    */
    private int minPrice;
    private int maxPrice;
    private List<Size> userSize;

    /**
     * The purpose of this constructor is to create a TShirt object.
     * @param name is a String value representing the name of the TShirt
     * @param productCode is a long value representing the product code of the TShirt.
     * @param price is a float value representing the price of the TShirt.
     * @param brand is a String value representing the brand of the TShirt.
     * @param sizes is a list of Size objects representing the sizes that the TShirt is available in.
     * @param description is a string value representing the description of the TShirt.
     */
    public TShirt(String name, long productCode, float price, String brand, List<Size> sizes, String description) {
        this.name = name;
        this.productCode = productCode;
        this.price = price;
        this.brand = brand;
        this.sizes = sizes;
        this.description = description;
    }


    /**
     * The purpose of this getter is to return the TShirt object's name.
     * @return a string value representing the name of the TShirt object.
     */
    public String getName() {
        return name;
    }

    /**
     * The purpose of this getter is to return the TShirt object's product code.
     * @return a long value representing the TShirt object's product code.
     */
    public long getProductCode() {
        return productCode;
    }

    /**
     * The purpose of this getter is to return the TShirt object's price.
     * @return a float value representing the TShirt object's price.
     */
    public float getPrice() {
        return price;
    }

    /**
     * The purpose of this getter is to return the TShirt object's brand.
     * @return a long value representing the TShirt object's brand.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * The purpose of this getter is to return the TShirt object's size availability.
     * @return a list of Sizes representing the TShirt object's range of sizes.
     */
    public List<Size> getSizes() {
        return sizes;
    }

    /**
     * The purpose of this getter is to return the TShirt object's description.
     * @return a String value representing the TShirt object's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * The purpose of this getter is to return the value determined by the user, that is the minimum amount they are willing to spend on a T-Shirt.
     * @return an integer value representing the minimum price user is willing to spend on a T-Shirt.
     */
    public int getMinPrice() {
        return minPrice;
    }

    /**
     * The purpose of this getter is to return the value determined by the user, that is the maximum amount they are willing to spend on a T-Shirt.
     * @return an integer value representing the maximum price user is willing to spend on a T-Shirt.
     */
    public int getMaxPrice() {
        return maxPrice;
    }

    /**
     * The purpose of this getter is to return the value determined by the user, that is the size of T-Shirt that they require.
     * @return list if Size objects containing only one Size - the size the user requires.
     */
    public List<Size> getUserSize() {
        return userSize;
    }

    /**
     * The purpose of this setter is to set the minimum price that the user is willing to spend on a T-Shirt.
     * @param minPrice is an integer value representing the minimum price the user is willing to spend on a T-Shirt.
     */
    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    /**
     * The purpose of this setter is to set the maximum price that the user is willing to spend on a T-Shirt.
     * @param maxPrice is an integer value representing the maximum price the user is willing to spend on a T-Shirt.
     */
    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    /**
     * The purpose of this setter is to set the value determined by the user, that is the size of T-Shirt that they require.
     * @param userSize is a list if Size objects containing only one Size - the size the user requires.
     */
    public void setUserSize(List<Size> userSize) {
        this.userSize = userSize;
    }

    /**
     * The purpose of the tShirtDescription method is to convert TShirt object information into a user-friendly string to display to the user.
     * @param tShirt is a TShirt object representing a T-Shirt that is compatible with the users search criteria.
     * @return a String representing user-friendly TShirt object data.
     */
    public String tShirtDescription (TShirt tShirt){
        return "Item name: " + tShirt.getName() +
                "\nCaption: " + tShirt.getDescription() +
                "\nProduct Code: " + tShirt.getProductCode() +
                "\nBrand: " + tShirt.getBrand() +
                "\nPrice: " + "$" + tShirt.getPrice();
    }

    }
