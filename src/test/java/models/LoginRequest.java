package models;

import lombok.Data;

@Data
public class LoginRequest {
    private String userName, password;
}