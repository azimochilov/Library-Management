package library.com.api.user;

import library.com.domain.dtos.users.UserCreationDto;
import library.com.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegisterController {
    private final UserService userService;

    @GetMapping
    public String showCreateUserForm(Model model) {
        model.addAttribute("userCreationDto", new UserCreationDto());
        return "register";
    }

    @PostMapping
    public String createUser(@ModelAttribute("userCreationDto") UserCreationDto userCreationDto) {
        userService.create(userCreationDto);
        return "redirect:/verification";
    }
}

