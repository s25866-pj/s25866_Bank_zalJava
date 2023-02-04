package com.example.s25866_bank.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response {
    public enum status{
        ACCEPTED,
        DECLINED
    }
}
