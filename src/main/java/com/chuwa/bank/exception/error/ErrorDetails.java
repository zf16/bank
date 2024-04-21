package com.chuwa.bank.exception.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails <T> {

    @JsonProperty("timestamp")
    private Date timestamp;

    @JsonProperty("URL")
    private String URL;

    @JsonProperty("message")
    private String message;

    @JsonProperty("details")
    private T details;

}
