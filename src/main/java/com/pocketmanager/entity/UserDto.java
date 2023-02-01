package com.pocketmanager.entity;

import com.pocketmanager.passwordMatchAnnotation.PasswordValueMatch;
import com.pocketmanager.passwordMatchAnnotation.ValidPassword;
import com.pocketmanager.phoneAnnotation.ContactNumberConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@PasswordValueMatch.List({
        @PasswordValueMatch(
                field = "password",
                fieldMatch = "confirmPassword",
                message = "Passwords do not match!"
        )
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;


    @Size(min = 2, max = 20, message = "Name should be between 2 and 20 characters")
    private String name;

    @NotEmpty(message = "Email can't be empty")
    @Email(message = "Email is not valid")
    private String email;

    @ValidPassword(message = "Password is not valid")
    private String password;


    @ValidPassword(message = "Passwords do not match")
    private String confirmPassword;

    @ContactNumberConstraint(message = "Invalid messages")
    private String phone;
}
