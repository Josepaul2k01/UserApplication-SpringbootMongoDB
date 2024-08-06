package com.project.one.projOne.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
@Document(collection="usersData")
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private List<Task> tasks;

}
