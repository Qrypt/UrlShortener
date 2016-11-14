package qrypt.work;

import javax.persistence.*;

@Entity
@Table(name="url")
public class MyUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String original;
    private String shortened;

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
}
