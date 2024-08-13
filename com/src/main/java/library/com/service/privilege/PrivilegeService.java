package library.com.service.privilege;

import jakarta.transaction.Transactional;
import library.com.domain.dtos.privileges.PrivilegeCreationDto;
import library.com.domain.dtos.privileges.PrivilegeResultDto;
import library.com.domain.entities.user.Privilege;
import library.com.exception.NotFoundException;
import library.com.repository.privilege.PrivilegeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PrivilegeService implements IPrivilegeServicec{
    private final PrivilegeRepository privilegeRepository;
    private final ModelMapper modelMapper;
    @Override
    public PrivilegeResultDto create(PrivilegeCreationDto privilegeDto) {
        Privilege privilege = modelMapper.map(privilegeDto, Privilege.class);
        Privilege savedPrivilege = privilegeRepository.save(privilege);
        return modelMapper.map(savedPrivilege, PrivilegeResultDto.class);
    }


    @Override
    public PrivilegeResultDto getById(Long id) {

        Privilege privilege = privilegeRepository.findById(id).orElseThrow(
                () -> new NotFoundException("not found with given id")
        );

        return modelMapper.map(privilege, PrivilegeResultDto.class);
    }

    @Override
    public PrivilegeResultDto update(Long id, PrivilegeCreationDto privilegeDto) {
        Privilege privilege = privilegeRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Not found privilege with id: " + id));

        privilege.setName(privilegeDto.getName());
        privilege.setUpdatedAt(Instant.now());

        Privilege updatedPrivilege = privilegeRepository.save(privilege);

        return modelMapper.map(updatedPrivilege, PrivilegeResultDto.class);
    }

    @Override
    public List<PrivilegeResultDto> getAll() {

        List<Privilege> privileges = privilegeRepository.findAll();

        List<PrivilegeResultDto> privilegeResultDTOs = privileges.stream()
                .map(priv -> modelMapper.map(priv, PrivilegeResultDto.class))
                .collect(Collectors.toList());

        return privilegeResultDTOs;
    }

    @Override
    public void delete(Long id) {
        Privilege privilegeForDeletion = privilegeRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Privilege not found")
                );
        privilegeRepository.delete(privilegeForDeletion);
    }

}
