package library.com.domain.dtos.roles;


import library.com.domain.dtos.privileges.PrivilegeUpdateDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleUpdateDto {
    private String name;
    private List<PrivilegeUpdateDto> privileges;
}
