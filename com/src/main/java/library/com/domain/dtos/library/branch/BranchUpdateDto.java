package library.com.domain.dtos.library.branch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BranchUpdateDto {
    private Long id;
    private String name;

    private Long libraryId;
}
