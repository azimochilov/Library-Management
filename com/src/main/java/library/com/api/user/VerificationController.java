package library.com.api.user;

import library.com.exception.NotFoundException;
import library.com.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/verification")
@RequiredArgsConstructor
public class VerificationController {

    private final UserService userService;

    @GetMapping
    public String showVerificationForm(Model model) {
        model.addAttribute("username", "");
        return "verification/verify";
    }

    @PostMapping
    public String verifyUser(@RequestParam("username") String username,
                             @RequestParam("code") String code,
                             RedirectAttributes redirectAttributes,
                             Model model) {
        try {
            boolean isVerified = userService.verification(username, code);
            if (isVerified) {
                redirectAttributes.addFlashAttribute("message", "Verification successful!");
                return "redirect:/login";
            }else {
                redirectAttributes.addFlashAttribute("error", "Verification failed! Please try again.");
                return "redirect:/verification";
            }

        } catch (NotFoundException e) {
            model.addAttribute("errorMessage", "Verification failed:");
            return "verification/verification_failed";
        }

    }

    @PostMapping("/resend")
    public String resendVerificationCode(@RequestParam String username, RedirectAttributes redirectAttributes) {
        userService.resendVerificationCode(username);
        redirectAttributes.addFlashAttribute("message", "Verification code resent!");
        return "redirect:/verification";
    }
}
