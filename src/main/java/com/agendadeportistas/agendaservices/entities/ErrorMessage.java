package com.agendadeportistas.agendaservices.entities;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {
    String type;
    String message;
    Date timestamp;
}
