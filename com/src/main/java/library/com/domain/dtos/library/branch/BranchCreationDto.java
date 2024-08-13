package library.com.domain.dtos.library.branch;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import library.com.domain.entities.library.Library;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BranchCreationDto {


    private String name;

    private Long libraryId;
}
