package library.com.api.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import library.com.service.secure.LoginManagerService;
import library.com.domain.dtos.login.LoginResponseDto;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginManagerService loginManagerService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestParam String username, @RequestParam String password) {
        try {
            LoginResponseDto response = loginManagerService.attemptLogin(username, password);
            return ResponseEntity.ok(response); // Return the token in the response
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}