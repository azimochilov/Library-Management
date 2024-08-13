package library.com.service.library;

import library.com.domain.dtos.library.branch.BranchCreationDto;
import library.com.domain.dtos.library.branch.BranchResultDto;
import library.com.domain.dtos.library.branch.BranchUpdateDto;
import library.com.domain.dtos.library.library.LibraryCreationDto;
import library.com.domain.dtos.library.library.LibraryResultDto;
import library.com.domain.dtos.library.library.LibraryUpdateDto;

import java.util.List;

public interface ILibraryService {
    LibraryResultDto create(LibraryCreationDto libraryCreationDto);
    void delete(Long id);
    LibraryResultDto update(Long id, LibraryUpdateDto libraryUpdateDto);
    LibraryResultDto getById(Long id);
    List<LibraryResultDto> getAll();
}
