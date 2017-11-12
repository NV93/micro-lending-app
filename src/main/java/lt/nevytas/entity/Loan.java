package lt.nevytas.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

/**
 * Created by nevyt
 */
@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private double ammount;
    private int term;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    @JsonIgnore
    private User user;
    private int yearIssued = Calendar.getInstance().get(Calendar.YEAR);
    private int dayIssued = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
    private int timeIssued = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    private String ipAddress;

    public Loan() {
    }

    public Loan(double ammount, int term, User user) {
        Assert.notNull(user, "Loan must be assignet to User");
        this.ammount = ammount;
        this.term = term;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAmmount() {
        return ammount;
    }

    public void setAmmount(double ammount) {
        this.ammount = ammount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public int getDayIssued() {
        return dayIssued;
    }

    public void setDayIssued(int dayIssued) {
        this.dayIssued = dayIssued;
    }

    public int getTimeIssued() {
        return timeIssued;
    }

    public void setTimeIssued(int timeIssued) {
        this.timeIssued = timeIssued;
    }

    public int getYearIssued() {
        return yearIssued;
    }

    public void setYearIssued(int yearIssued) {
        this.yearIssued = yearIssued;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    //TODO set fields for Time issued, Term and Time expires.
}
