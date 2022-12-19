package com.loginPage.LoginPageBackend.domain;


import com.google.cloud.spring.data.firestore.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Manager extends User{
    private List<String> users = new ArrayList<>();
    public Manager(User u) {
        super(u.getUsername(), u.getFullName(),u.getPassword(), u.getAuthorityList());
    }

}
