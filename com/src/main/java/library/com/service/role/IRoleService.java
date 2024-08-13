package library.com.service.role;

import library.com.domain.dtos.privileges.PrivilegeCreationDto;
import library.com.domain.dtos.roles.RoleCreationDto;
import library.com.domain.dtos.roles.RoleResultDto;
import library.com.domain.dtos.roles.RoleUpdateDto;

import java.util.List;

public interface IRoleService {
    RoleResultDto getByName(String name);
    RoleResultDto getById(Long id);
    List<RoleResultDto> getAll();
    RoleResultDto update(Long id, RoleUpdateDto roleDto);
    RoleResultDto create(RoleCreationDto roleDto);
    RoleResultDto addPrivilege(Long roleId, List<PrivilegeCreationDto> privileges);
    void delete(Long id);

}
