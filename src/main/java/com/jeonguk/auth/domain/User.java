package com.jeonguk.auth.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "user")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    @Id
    private String id;

    @Indexed(unique = true)
    private String login;

    @Size(min = 60, max = 60)
    private String password;

    @Size(min = 512, max = 512)
    private String userDesc;

    @JsonIgnore
    private Set<Authority> authorities = new HashSet<>();
}
