package library.com.service.user;

import library.com.domain.dtos.users.UserCreationDto;
import library.com.domain.dtos.users.UserResultDto;
import library.com.domain.dtos.users.UserUpdateDto;

import java.util.List;

public interface IUserService {
    UserResultDto create(UserCreationDto userDto);
    List<UserResultDto> getAll();
    UserResultDto getUserByUsername(String username);
    UserResultDto getById(Long id);
    UserResultDto update(Long id, UserUpdateDto userDto);
//    public boolean verification(Long id, String code);
//    public void resendVerificationCode(Long id);
    void delete(Long id);
}
