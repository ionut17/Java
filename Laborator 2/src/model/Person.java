package model;

/**
 *
 * @author Anca Adascalitei, Ionut Iacob
 */
abstract public class Person {

    private String name;
    private String email;

    /**
     * @return if the inherited class is free
     */
    abstract public boolean isFree();

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
