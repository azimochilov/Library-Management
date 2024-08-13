package library.com.domain.dtos.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserUpdateDto {
    private String username;

    private String password;

    private String street;

    private String city;

    private String email;

    private String role;

    private boolean isActive;
}
