package com.example.SpringSecurity.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class ErrorHandle {
    @Getter @Setter
    private String campo;
    @Getter @Setter
    private String message;
}
