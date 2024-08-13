package library.com.service.branch;

import library.com.domain.dtos.library.branch.BranchCreationDto;
import library.com.domain.dtos.library.branch.BranchResultDto;
import library.com.domain.dtos.library.branch.BranchUpdateDto;

import java.util.List;

public interface IBranchService {
    BranchResultDto create(BranchCreationDto branchCreationDto);
    void delete(Long id);
    BranchResultDto update(Long id, BranchUpdateDto branchUpdateDto);
    BranchResultDto getById(Long id);
    List<BranchResultDto> getAll();
}
