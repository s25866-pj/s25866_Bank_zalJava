package com.example.s25866_bank.exceptions;
import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper = false)
public class NotEnoughtMoneyException extends RuntimeException {
    private final String message;
    private final long value;
}
