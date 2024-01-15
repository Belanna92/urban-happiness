/**
 * The purpose of this class is to create Geek objects.
 */
public class Geek {

    // Create the variables of a Geek objects
    private final String name;
    private final long phoneNumber;
    private final String emailAddress;

    /**
     * The purpose of this constructor is to create a Geek object.
     * @param name is a String value representing the user's name.
     * @param phoneNumber is a long value representing the user's phone number.
     * @param emailAddress is a String value representing the user's email address.
     */
    public Geek(String name, long phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    /**
     * The purpose if this getter is to return the Geek object (user)'s name.
     * @return the String value representing the user's name.
     */
    public String getName() {
        return name;
    }

    /**
     * The purpose if this getter is to return the Geek object (user)'s phone number.
     * @return the long value representing the user's phone number.
     */
    public long getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * The purpose if this getter is to return the Geek object (user)'s email address.
     * @return the String value representing the user's email address.
     */
    public String getEmailAddress() {
        return emailAddress;
    }
}
