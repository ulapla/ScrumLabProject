package pl.coderslab.model;

import org.mindrot.jbcrypt.BCrypt;

public class Admin {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int superadmin;
    private int emable;

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", superadmin=" + superadmin +
                ", emable=" + emable +
                '}';
    }

    public Admin() {
    }

    public Admin(String firstName, String lastName, String email, String password, int superadmin, int emable) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.superadmin = superadmin;
        this.emable = emable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean verifyPassword(String candidate) {
        return(BCrypt.checkpw(candidate, password));
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSuperadmin() {
        return superadmin;
    }

    public void setSuperadmin(int superadmin) {
        this.superadmin = superadmin;
    }

    public int getEmable() {
        return emable;
    }

    public void setEmable(int emable) {
        this.emable = emable;
    }
}
