package com.example.bank.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private final int id;
    private final String name;
    private final String surrname;
    private  long money;
    private long pesel;
}
