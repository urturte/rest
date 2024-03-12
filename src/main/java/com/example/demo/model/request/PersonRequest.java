package com.example.demo.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonRequest {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phone;
}
