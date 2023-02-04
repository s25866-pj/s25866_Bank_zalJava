package com.example.s25866_bank.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserValidationException extends RuntimeException{
    private final String message;
}
