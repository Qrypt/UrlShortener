package qrypt.work;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "url")
public class MyUrl implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String original;
    private String shortened;
    private String accountUsername;

    public MyUrl() {

    }

    public MyUrl(String original, String shortened) {
        this.original = original;
        this.shortened = shortened;
    }

    @Override
    public String toString() {
        return "MyUrl{" +
                "id=" + id +
                ", original='" + original + '\'' +
                ", shortened='" + shortened + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getShortened() {
        return shortened;
    }

    public void setShortened(String shortened) {
        this.shortened = shortened;
    }

    public String getAccountUsername() {
        return accountUsername;
    }

    public void setAccountUsername(String accountUsername) {
        this.accountUsername = accountUsername;
    }

}
