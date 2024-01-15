import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * The purpose of the TShirtSearcher is to allow users to enter criteria of a desired T-Shirt and then be shows options of T-Shirts that match their search criteria.
 * This class uses the TShirt, Geek and Inventory classes, which allows users to:
 * Enter search criteria for their desired T-Shirt.
 * Choose from T-Shirts that are available in the inventory that match their search criteria.
 * Enter their information to place an order for a T-Shirt.
 */
public class TShirtSearcher {

    // Create a variable to store the appName for easy reference
    private final static String appName = "Greek Geek's Geek T-Shirt Getter";

    /**
     * The purpose of the main method is to request information from the user and display appropriate information back accordingly.
     * loadInventoryData and writeGeekOrderRequestToFile are called in this method.
     * @param args not required
     */
    public static void main (String[] args){

        // Load inventory data.
        Inventory inventory = loadInventoryData("./inventory.txt");

        // Create a variable to store the users size.
        List<Size> theUsersSize = new ArrayList<>();
        // Ask the user to select their size using a JOption pane with a drop-down menu as the size options never change.
        Size userSize = (Size) JOptionPane.showInputDialog(null, "Please select your size", appName, JOptionPane.QUESTION_MESSAGE, null, Size.values(), Size.XS);
        // Handle the null pointer exception error.
        if(userSize == null) System.exit(0);
        // Add the users size to the variable created above
        theUsersSize.add(userSize);

        // Create a variable to store the brands of T-Shirts that are currently available in the inventory.
        List<String> allTShirtsBrands = new ArrayList<>();
        // Use a for loop to loop through all the TShirts in inventory.
        for(TShirt shirt: inventory.getInventory()){
            // Add all the TShirt brands to the allTShirtBrands array list.
            allTShirtsBrands.add(shirt.getBrand());
        }
        // Remove duplicates from the list of brands.
        allTShirtsBrands = new ArrayList<>(new HashSet<>(allTShirtsBrands));
        // Create a variable to store the brands in a String format to be displayed to the user.
        String[] availableBrands = new String[inventory.getInventory().size()];
        // Loop through all the TShirt brands in the allTShirtBrands array list.
        for (int t = 0; t < allTShirtsBrands.size(); t++) {
            // Add the names and product codes of each compatible T-Shirt to the availableBrands variable created above.
            availableBrands[t] = allTShirtsBrands.get(t);
        }

        // Display all the available brands to the user with a JOption input dialog and have the user select their preferred brand from the drop-down menu.
        String brand = (String) JOptionPane.showInputDialog(null, "What is your preferred brand?", appName, JOptionPane.QUESTION_MESSAGE, null, availableBrands, availableBrands[0]);
        // Handle null pointer exception.
        if (brand == null) System.exit(0);

        // Create a variable to store the minimum price the user is willing to spend on a T-Shirt.
        int minPrice = -1;
        // Use a loop to ask the user their minimum price by looping util a valid integer is entered, or they exit the program.
        do{
            // Collect a string from the JOptionPane input dialog for the minimum price.
            String userMinPrice = JOptionPane.showInputDialog("What is the minimum price you would like to spend?");
            // Handle the null pointer exception.
            if (userMinPrice == null) System.exit(0);
            // Use try is check if the user has entered a valid non-negative integer.
            try {
                // Change the string the user entered into an integer value.
                minPrice = Integer.parseInt(userMinPrice.strip());
            // Catch the number format exception, and ask the user to correct their mistake.
            } catch (NumberFormatException n) {
                // Display a message to the user to have them amend the number format error
                JOptionPane.showMessageDialog(null, "Please enter a non-negative integer value." +
                        "\n(In the format: 12)");
            }
        // Ensure the minimum price is not a negative number. Loop until this is achieved.
        } while(minPrice < 0 );

        // Create a variable to store the maximum price the user is willing to spend on a T-Shirt.
        int maxPrice = 0;
        // Use a loop to ask the user their maximum price by looping util a valid integer is entered, or they exit the program.
        do{
            // Collect a string from the JOptionPane input dialog for the maximum price.
            String userMaxPrice = JOptionPane.showInputDialog("What is the maximum price you would like to spend?");
            // Handle the null pointer exception.
            if (userMaxPrice == null) System.exit(0);
            // Use try is check if the user has entered a valid non-negative integer.
            try {
                // Change the string the user entered into an integer value.
                maxPrice = Integer.parseInt(userMaxPrice.strip());
                if (maxPrice < minPrice) JOptionPane.showMessageDialog(null, "Please enter a non-negative integer value greater than " + minPrice +
                        "\n(In the format: 24)");
                // Catch the number format exception, and ask the user to correct their mistake.
            } catch (NumberFormatException n) {
                // Display a message to the user to have them amend the number format error
                JOptionPane.showMessageDialog(null, "Please enter a non-negative integer value greater than " + minPrice +
                        "\n(In the format: 24)");
            }
        // Ensure that the maximum price is greater than the minimum price. Loop until this is achieved.
        } while(maxPrice < minPrice);

        // Create an instance of a TShirt based on the users entries.
        TShirt userDreamTShirt = new TShirt(null, 0, 0, brand, theUsersSize, null);
        // Update user variables in the TShirt class.
        userDreamTShirt.setMinPrice(minPrice);
        userDreamTShirt.setMaxPrice(maxPrice);
        userDreamTShirt.setUserSize(theUsersSize);

        // Create a variable to store a list of TShirts that are compatible with the users criteria.
        List<TShirt> compatibleShirts;
        // Fill the list with compatible shirts by calling the findDreamTShirt method from the Inventory class.
        compatibleShirts = inventory.findDreamTShirt(userDreamTShirt);

        // If there are no compatible shirt in the inventory then the user will be notified with a JOptionPane message.
        if(compatibleShirts.size() == 0){
            // Display message to the user to notify them that their search has returned no results.
            JOptionPane.showMessageDialog(null, "Sorry, there are no T-Shirts matching your search criteria.");
            // Exit the program.
            System.exit(0);
        }
        else{
            // Create a variable to store the name and product code of the T-Shirts that are compatible with the user's criteria.
            String[] tShirtOptions = new String[compatibleShirts.size()];
            // Create a Map that keeps track of which T-Shirts belong to which name and product code.
            HashMap<String, TShirt> keepTrackOfTShirtOptions = new HashMap<>(compatibleShirts.size());

            // Create a StringBuilder object that will be used to display the compatible shirts to the user.
            StringBuilder infoToShow= new StringBuilder("AWESOME! The following t-shirts meet your criteria: \n \n");

            // Loop through all the compatible shirts to append them to the StringBuilder for displaying to the user.
            for (int i = 0; i < compatibleShirts.size(); i++){
                // Call the tShirtDescription method from the TShirt class to append the user-friendly description to the StringBuilder.
                infoToShow.append(compatibleShirts.get(i).tShirtDescription(compatibleShirts.get(i)) + "\n \n");
                // Add the names and product codes of each compatible T-Shirt to the TShirtOptions variable created above.
                tShirtOptions[i] = compatibleShirts.get(i).getName() + " (" + compatibleShirts.get(i).getProductCode() + ")";
                // Map the names and product codes to each compatible T-Shirt.
                keepTrackOfTShirtOptions.put(compatibleShirts.get(i).getName() + " (" + compatibleShirts.get(i).getProductCode() + ")", compatibleShirts.get(i));
            }

            // Display all the compatible T-Shirts to the user and ask the user which one they would like to purchase. The user can select the appropriate T-Shirt from a drop-down menu.
            String userTShirtOrder = (String) JOptionPane.showInputDialog(null, infoToShow + "\nPlease select the t-shirt that you would like to purchase.", appName, JOptionPane.QUESTION_MESSAGE, null, tShirtOptions, tShirtOptions[0]);
            // Handle null pointer exception.
            if (userTShirtOrder == null){
                // Display a message to the user before the program exists.
                JOptionPane.showMessageDialog(null, "Come back if you change your mind!");
                System.exit(0);
            }

            // Collect the user's name using a JOptionPane input dialogue box and store in a String variable
            String name = JOptionPane.showInputDialog(null, "Please enter your full name.");
            // Handle null pointer exception.
            if (name == null) System.exit(0);

            // Collect the user's email address using a JOptionPane input dialogue box and store in a String variable
            String emailAddress = JOptionPane.showInputDialog(null, "Please enter your email address.");
            // Handle null pointer exception.
            if (emailAddress == null) System.exit(0);

            // Create a variable to store the user's phone number.
            long phoneNumber = 0;
            // Create a variable to store the String entry of the phone number.
            String phoneNumberInput;
            // Use a loop to ask the user for their phone number until a valid entry is input.
            do{
                // Collect a String of the user's phone number
                phoneNumberInput = JOptionPane.showInputDialog("Please enter your phone number." +
                        "\n(Ten digits in the format: 0412345678)");
                // Handle null pointer exception.
                if (phoneNumberInput == null) System.exit(0);
                // Use try to check if the user has entered a valid long number
                try{
                    // convert the String to a long and store it in phone number
                    phoneNumber = Long.parseLong(phoneNumberInput.strip());
                // Catch the number format error if the user did not enter a valid long.
                } catch (NumberFormatException n){
                    // Display a message to the user to have them amend the number format error
                    JOptionPane.showMessageDialog(null, "Please enter a valid phone number." +
                            "\n(Ten digits in the format: 0412345678)");
                }
            // Ensure that the phone number is a valid 10-digit phone number. Continue looping until achieved.
            } while (phoneNumber == 0 && phoneNumberInput.length() < 10);

            // Create a new Geek to store the user's information for their T-Shirt Order.
            Geek geek = new Geek(name, phoneNumber, emailAddress);

            // Call the writeGeekOrderToFile to store the user's order and personal information in a text file.
            writeGeekOrderRequestToFile(geek, keepTrackOfTShirtOptions.get(userTShirtOrder), userDreamTShirt.getUserSize().get(0));
        }
    }

    /**
     * The purpose of the loadInventoryData method is to load the inventory data from a text file.
     * @param filePath is a String that is taken representing the file location of the inventory text file.
     * @return an Inventory object which is a list of TShirt objects.
     */
    public static Inventory loadInventoryData(String filePath) {

        Inventory inventory = new Inventory();

        Path path = Path.of(filePath);
        List<String> fileContents = null;

        try {
            fileContents = Files.readAllLines(path);
        } catch (IOException io) {
            System.out.println("File could not be loaded " +
                    "\nError message: " + io +
                    "\nSystem exiting.");
            System.exit(0);
        }

        for (int i = 1; i < fileContents.size(); i++) {
            var tShirtDetails = fileContents.get(i).split(",");
            var otherTShirtDetails = fileContents.get(i).split("\\[");

            String name = tShirtDetails[0].toLowerCase();

            long productCode = Long.parseLong(tShirtDetails[1]); // Need to handle errors here

            float price = Float.parseFloat(tShirtDetails[2]); // Need to handle errors here

            String brand = tShirtDetails[3].toLowerCase();

            String sizes = otherTShirtDetails[1].toUpperCase().replace("]", "");
            String[] splitSizes = sizes.split(",");
            List<Size> availableSizes = new ArrayList<>();
            for (int s = 0; s < splitSizes.length; s++){
                availableSizes.add(Size.valueOf(splitSizes[s]));
            }

            String description = otherTShirtDetails[2].replace("]", "");

            TShirt t = new TShirt(name, productCode, price, brand, availableSizes, description);
            inventory.addTShirt(t);
        }

        return inventory;

    }

    /**
     * The purpose of the writeGeekOrderRequestToFile is to create a text file with the order information of the user.
     * @param geek is a Geek object that represents the user and their information.
     * @param tshirt is a TShirt object that represents the T-Shirt the user wishes to purchase.
     * @param userSize is a Size object that represents the user's size.
     */
    private static void writeGeekOrderRequestToFile(Geek geek, TShirt tshirt, Size userSize){
        String filePath = geek.getName().replace(" ", "_") + "_" + tshirt.getProductCode() + "_" + userSize + ".txt";
        Path path = Path.of(filePath);
        String toPutInFile = "Order Details:" +
                "\nName: " + geek.getName() +
                "\nPhone number: " + "0" + geek.getPhoneNumber() +
                "\nEmail address: " + geek.getEmailAddress() +
                "\nItem: " + tshirt.getName() + "(" + tshirt.getProductCode() +")" +
                "\nSize: " + userSize;

        try{
            Files.writeString(path, toPutInFile);
        }catch(IOException io){
            System.out.println("File could not be written. +" +
                    "\nError Message: " + io.getMessage() +
                    "\nSystem exiting.");
            System.exit(0);
        }
    }

}
