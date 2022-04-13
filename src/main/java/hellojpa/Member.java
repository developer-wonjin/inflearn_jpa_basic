package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter
public class Member {

    public Member() {
    }

    public Member(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    @Id
    private Long id;//bigint

    @Column(name = "name")
    private String username;////varchar

    private Integer age;//integer

    @Enumerated(EnumType.STRING)
    private RoleType roleType;//varchar

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;//TIMESTAMP

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;//TIMESTAMP

    @Lob
    private String description;//clob



}
