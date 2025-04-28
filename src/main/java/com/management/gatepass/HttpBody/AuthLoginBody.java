package com.management.gatepass.HttpBody;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter @Getter
public class AuthLoginBody {
    private String email;
    private String password;
}