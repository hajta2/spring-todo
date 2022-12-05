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
@Table(name = "todo_priority")
public class ToDoPriority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private ToDoPriorityType priorityType;

    public static ToDoPriority valueOf(String toUpperCase) {
        return switch (toUpperCase) {
            case "MINOR" -> new ToDoPriority(1, ToDoPriorityType.MINOR);
            case "MAJOR" -> new ToDoPriority(2, ToDoPriorityType.MAJOR);
            case "CRITICAL" -> new ToDoPriority(3, ToDoPriorityType.CRITICAL);
            default -> null;
        };
    }
}
