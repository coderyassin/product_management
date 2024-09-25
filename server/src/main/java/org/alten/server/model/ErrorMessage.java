package org.alten.server.model;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ErrorMessage {
    private HttpStatus httpStatus;
    private Date timestamp;
    private String message;
    private String description;
}
