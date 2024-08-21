package library.com.api.user;


import library.com.domain.dtos.users.UserCreationDto;
import library.com.domain.dtos.users.UserResultDto;
import library.com.domain.dtos.users.UserUpdateDto;
import library.com.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/create")
    public String showCreateUserForm(Model model) {
        model.addAttribute("userCreationDto", new UserCreationDto());
        return "register";
    }

    @PostMapping("/create")
    public String createUser(@RequestBody UserCreationDto userCreationDto) {
        userService.create(userCreationDto);
        return "redirect:/verification";
    }

    @GetMapping("/list")
    public String listUsers(Model model) {
        List<UserResultDto> users = userService.getAll();
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/update/{id}")
    public String showUpdateUserForm(@PathVariable Long id, Model model) {
        UserResultDto user = userService.getById(id);
        UserUpdateDto userUpdateDto = new UserUpdateDto();
        userUpdateDto.setUsername(user.getUsername());
        userUpdateDto.setPassword(user.getPassword());
        userUpdateDto.setEmail(user.getEmail());
        userUpdateDto.setStreet(user.getStreet());
        userUpdateDto.setCity(user.getCity());
        userUpdateDto.setRole(user.getRole());

        model.addAttribute("userUpdateDto", userUpdateDto);
        model.addAttribute("user", user);
        return "update-user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute UserUpdateDto userUpdateDto) {
        userService.update(id, userUpdateDto);
        return "redirect:/users/list";
    }
}