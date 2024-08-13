package library.com.service.privilege;



import library.com.domain.dtos.privileges.PrivilegeCreationDto;
import library.com.domain.dtos.privileges.PrivilegeResultDto;

import java.util.List;

public interface IPrivilegeServicec {
    PrivilegeResultDto create(PrivilegeCreationDto privilegeDto);
    PrivilegeResultDto getById(Long id);
    PrivilegeResultDto update(Long id, PrivilegeCreationDto privilegeDto);
    List<PrivilegeResultDto> getAll();
    void delete(Long id);
}
