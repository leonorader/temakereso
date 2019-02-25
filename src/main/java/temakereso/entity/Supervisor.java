package temakereso.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@ToString(exclude = {"topics"})
@EqualsAndHashCode(exclude = {"topics"})
public class Supervisor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 250, nullable = false)
    private String name;

    @Column(length = 250)
    private String title;

    @Column(length = 250)
    private String workplace;

    @Column(length = 50)
    private String phone;

    @Column(length = 250)
    private String website;

    @Column(length = 100)
    private String room;

    @Column(length = 250)
    private String officeHours;

    @Column(length = 250)
    private Boolean external = Boolean.FALSE;

    @Column(length = 250)
    private Boolean confirmed = Boolean.FALSE;

    @Column(length = 250)
    private Boolean deleted = Boolean.FALSE;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account")
    private Account account;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "supervisor")
    private Set<Topic> topics;

}
