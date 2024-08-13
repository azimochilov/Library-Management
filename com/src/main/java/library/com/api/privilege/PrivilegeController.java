package library.com.api.privilege;



import library.com.domain.dtos.privileges.PrivilegeCreationDto;
import library.com.domain.dtos.privileges.PrivilegeResultDto;
import library.com.service.privilege.PrivilegeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/privilege")
//@PreAuthorize("hasAuthority('ROLE_PRIVILEGE_SERVICE')")
public class PrivilegeController {
    private final PrivilegeService privilegeService;

    @PostMapping
    public void create(@RequestBody PrivilegeCreationDto privilegeDto)
    {
        privilegeService.create(privilegeDto);
    }

    @GetMapping
    public List<PrivilegeResultDto> getAll()
    {
        return privilegeService.getAll();
    }

    @GetMapping("/{id}")
    public PrivilegeResultDto getById(@PathVariable Long id) {
        return privilegeService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        privilegeService.delete(id);
    }
}
