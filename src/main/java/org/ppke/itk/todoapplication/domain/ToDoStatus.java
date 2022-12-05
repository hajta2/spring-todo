package org.ppke.itk.todoapplication.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "todo_status")
public class ToDoStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private Boolean isDone;

    public static ToDoStatus valueOf(String toUpperCase) {
        if (toUpperCase.equals("DONE")) {
            return new ToDoStatus(1, true);
        } else {
            return new ToDoStatus(2, false);
        }
    }
}
