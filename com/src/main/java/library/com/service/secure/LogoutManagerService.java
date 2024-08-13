package library.com.service.secure;

import library.com.security.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class LogoutManagerService {
    private final JwtProperties jwtProperties;
    public void logout() {

        jwtProperties.setExpiration(Instant.now());
    }
}
