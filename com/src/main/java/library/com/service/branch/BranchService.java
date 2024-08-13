package library.com.service.branch;
import library.com.domain.dtos.library.branch.BranchCreationDto;
import library.com.domain.dtos.library.branch.BranchResultDto;
import library.com.domain.dtos.library.branch.BranchUpdateDto;
import library.com.domain.entities.library.Branch;
import library.com.domain.entities.library.Library;
import library.com.exception.NotFoundException;
import library.com.repository.library.BranchRepository;
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
public class BranchService implements IBranchService {

    private final ModelMapper modelMapper;
    private final BranchRepository branchRepository;
    private final LibraryRepository libraryRepository;

    @Override
    public BranchResultDto create(BranchCreationDto branchCreationDto) {
        Branch branch = new Branch();
        branch.setName(branchCreationDto.getName());

        Library library = libraryRepository.findById(branchCreationDto.getLibraryId()).orElseThrow(
                () -> new NotFoundException("Library with given id not found")
        );

        branch.setLibrary(library);
        branchRepository.save(branch);

        return modelMapper.map(branch, BranchResultDto.class);
    }

    @Override
    public void delete(Long id) {
        Branch branch = branchRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Branch not found with given id")
        );
        branchRepository.delete(branch);
    }

    @Override
    public BranchResultDto update(Long id, BranchUpdateDto branchUpdateDto) {
        Branch branch = branchRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Branch with given id not found")
        );

        Library library = libraryRepository.findById(branchUpdateDto.getLibraryId()).orElseThrow(
                () -> new NotFoundException("Library with given id not found")
        );

        branch.setLibrary(library);
        branch.setName(branchUpdateDto.getName());
        branchRepository.save(branch);

        return modelMapper.map(branch, BranchResultDto.class);
    }

    @Override
    public BranchResultDto getById(Long id) {
        Branch branch = branchRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Branch with given id not found")
        );

        return modelMapper.map(branch, BranchResultDto.class);
    }

    @Override
    public List<BranchResultDto> getAll() {
        List<Branch> branches = branchRepository.findAll();
        List<BranchResultDto> branchResultDtos = branches.stream()
                .map(branch -> modelMapper.map(branch, BranchResultDto.class))
                .collect(Collectors.toList());
        return branchResultDtos;
    }
}
