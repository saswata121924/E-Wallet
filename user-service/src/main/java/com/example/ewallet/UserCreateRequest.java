package com.example.ewallet;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreateRequest {

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    private String contact;

    public User to() {
        return User.builder()
                .name(name)
                .email(email)
                .contact(contact)
                .build();
    }
}
