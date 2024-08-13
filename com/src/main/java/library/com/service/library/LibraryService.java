package library.com.service.library;

import library.com.domain.dtos.library.library.LibraryCreationDto;
import library.com.domain.dtos.library.library.LibraryResultDto;
import library.com.domain.dtos.library.library.LibraryUpdateDto;
import library.com.domain.entities.library.Library;
import library.com.exception.NotFoundException;
import library.com.repository.library.LibraryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class LibraryService implements ILibraryService {

    private final ModelMapper modelMapper;
    private final LibraryRepository libraryRepository;

    @Override
    public LibraryResultDto create(LibraryCreationDto libraryCreationDto) {
        Library library = new Library();
        library.setName(libraryCreationDto.getName());
        libraryRepository.save(library);

        return modelMapper.map(library, LibraryResultDto.class);
    }

    @Override
    public void delete(Long id) {
        Library library = libraryRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Library not found with given id")
        );
        libraryRepository.delete(library);
    }

    @Override
    public LibraryResultDto update(Long id, LibraryUpdateDto libraryUpdateDto) {
        Library library = libraryRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Library with given id not found")
        );

        library.setName(libraryUpdateDto.getName());
        libraryRepository.save(library);

        return modelMapper.map(library, LibraryResultDto.class);
    }

    @Override
    public LibraryResultDto getById(Long id) {
        Library library = libraryRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Library with given id not found")
        );

        return modelMapper.map(library, LibraryResultDto.class);
    }

    @Override
    public List<LibraryResultDto> getAll() {
        List<Library> libraries = libraryRepository.findAll();
        List<LibraryResultDto> libraryResultDtos = libraries.stream()
                .map(library -> modelMapper.map(library, LibraryResultDto.class))
                .collect(Collectors.toList());
        return libraryResultDtos;
    }
}
