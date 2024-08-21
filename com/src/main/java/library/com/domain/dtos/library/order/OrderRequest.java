package library.com.domain.dtos.library.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderRequest {
    private Long bookId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
