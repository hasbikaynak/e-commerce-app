package com.example.scm.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @Size(max = 50)
    @NotBlank(message = "Please provide your First name")
    private String name;

    @Email(message = "Please provide valid email")
    @Size(min = 5, max = 80)
    private String email;

    @Size(min = 4, max = 20,message="Please Provide Correct Size for Password")
    @NotBlank(message = "Please provide your password")
    private String password;

    @Pattern(regexp = "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{3}$",
            message = "Please provide valid phone number")
    @Size(min = 11, max = 11)
    @NotBlank(message = "Please provide your phone number")
    private String phoneNumber;
}
