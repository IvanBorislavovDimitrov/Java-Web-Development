package app.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(generator = "uuid-string")
    @GenericGenerator(name = "uuid-string", strategy = "uuid")
    @Column(name = "uuid", unique = true)
    private String uuid;

    @Column(name = "username", unique = true, nullable = false)
    @NotNull
    private String username;

    @Column(name = "email", unique = true, nullable = false)
    @Pattern(regexp = "^[A-Za-z][A-Za-z.0-9]+@([A-Za-z]+(\\.)){1,}[A-Za-z0-9]+$")
    private String email;

    @OneToMany(mappedBy = "uploader")
    private List<Tube> tubes;

    public User() {
        tubes = new ArrayList<>();
    }

    public User(@NotNull String username, @Pattern(regexp = "^[A-Za-z][A-Za-z.0-9]+@([A-Za-z]+(\\.)){1,}[A-Za-z0-9]+$") String email, List<Tube> tubes) {
        this.username = username;
        this.email = email;
        this.tubes = tubes;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Tube> getTubes() {
        return tubes;
    }

    public void setTubes(List<Tube> tubes) {
        this.tubes = tubes;
    }
}
