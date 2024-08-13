package library.com.domain.dtos.library.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookUpdateDto {
    private Long id;
    private String nameOfBook;

    private String title;

    private String author;

    private Long branchId;
}
