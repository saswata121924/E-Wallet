package com.example.ewallet;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    private String contact;

    public User to(){
        return User.builder()
                .name(this.name)
                .email(this.email)
                .contact(this.contact)
                .build();
    }
}
