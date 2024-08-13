package library.com.domain.dtos.library.book;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import library.com.domain.entities.library.Branch;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookCreationDto {

    private Long id;
    private String nameOfBook;

    private String title;

    private String author;

    private Long branchId;
}
