package co.com.memoodm.tenpo.service.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_information")
public class UserEntity
{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique=true)
    private String email;
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

}
