package org.ppke.itk.todoapplication.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer Id;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private Date creationDate;
    @ManyToOne
    @JoinColumn(name = "status_id")
    private UserStatus userStatus;
}
