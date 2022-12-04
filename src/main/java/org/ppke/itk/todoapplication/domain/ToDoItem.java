package org.ppke.itk.todoapplication.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "todo_item")
public class ToDoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    private String title;
    private String details;
    private Date creationDate;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "priority_id")
    private ToDoPriority priority;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id")
    private ToDoStatus status;
}
