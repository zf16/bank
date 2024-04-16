package com.chuwa.bank.exception.error;

import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails <T> {

    private Date timestamp;

    private T message;

    private String details;


}
