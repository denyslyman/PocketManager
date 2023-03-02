package com.pocketmanager.dto;

import com.pocketmanager.email.ValidEmail;
import com.pocketmanager.passwordMatchAnnotation.PasswordValueMatch;
import com.pocketmanager.passwordMatchAnnotation.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@PasswordValueMatch.List({
        @PasswordValueMatch(
                field = "password",
                fieldMatch = "confirmPassword",
                message = "Passwords do not match!"
        )
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    @Size(min = 2, max = 20, message = "Name should be between 2 and 20 characters")
    private String name;

    @ValidEmail(message = "Invalid email")
    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @ValidPassword
    private String password;

    @ValidPassword
    private String confirmPassword;

    public void setName(String value){
        this.name = value.trim();
    }

    public String getName(){
        return this.name;
    }

    public void setEmail(String value){
        this.email = value.trim();
    }

    public String getEmail(){
        return this.email;
    }
}
