package library.com.api.user;

import library.com.service.secure.LogoutManagerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LogoutController {

    private final LogoutManagerService logoutManagerService;

    @GetMapping("/logout")
    public String showLogoutPage() {
        return "logout";
    }
    @PostMapping("/logout")
    public String logout() {
        logoutManagerService.logout();
        return "redirect:/login?logout";
    }
}

