package lt.nevytas.entity;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.List;

/**
 * Created by nevyt
 */


@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="USER_ID")
    private long id;
    @Column(name = "PERSONS_ID", unique = true)
    private String personsId;
    private String name;
    private String surname;
    private String address;
    private String phoneNumber;
    private String email;

    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
    private List<Loan> loans;

    public User(){

    }

    public User(String personsId, String name, String surname, String address, String phoneNumber, String email) {
        this.personsId = personsId;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public User(long id, String personsId, String name, String surname, String address, String phoneNumber, String email) {
        this.id = id;
        this.personsId = personsId;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPersonsId() {
        return personsId;
    }

    @Required
    public void setPersonsId(String personsId) {
        this.personsId = personsId;
    }

    public String getName() {
        return name;
    }

    @Required
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    @Required
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }


}
