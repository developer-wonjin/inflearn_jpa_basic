package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter
public class Member1 {

    public Member1() {
    }

    public Member1(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;//bigint

    @Column(name = "USERNAME")
    private String username;////varchar

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="TEAM_ID")
    private Team team;
}
