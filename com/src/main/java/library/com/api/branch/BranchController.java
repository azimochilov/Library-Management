package library.com.api.branch;

import library.com.domain.dtos.library.branch.BranchCreationDto;
import library.com.domain.dtos.library.branch.BranchResultDto;
import library.com.domain.dtos.library.branch.BranchUpdateDto;
import library.com.service.branch.IBranchService;
import library.com.service.library.ILibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/branches")
@RequiredArgsConstructor
public class BranchController {

    private final IBranchService branchService;
    private final ILibraryService libraryService;

    @GetMapping
    public String listBranches(Model model) {
        List<BranchResultDto> branches = branchService.getAll();
        model.addAttribute("branches", branches);
        return "branches/list";
    }

    @GetMapping("/{id}")
    public String getBranch(@PathVariable Long id, Model model) {
        BranchResultDto branch = branchService.getById(id);
        model.addAttribute("branch", branch);
        return "branches/view";
    }

    @GetMapping("/create")
    public String createBranchForm(Model model) {
        model.addAttribute("branchCreationDto", new BranchCreationDto());
        model.addAttribute("libraries", libraryService.getAll());
        return "branches/create";
    }

    @PostMapping("/create")
    public String createBranch(@ModelAttribute BranchCreationDto branchCreationDto) {
        branchService.create(branchCreationDto);
        return "redirect:/branches";
    }

    @GetMapping("/edit/{id}")
    public String editBranchForm(@PathVariable Long id, Model model) {
        BranchResultDto branch = branchService.getById(id);
        BranchUpdateDto branchUpdateDto = new BranchUpdateDto();
        branchUpdateDto.setName(branch.getName());
        branchUpdateDto.setLibraryId(branch.getLibraryId());
        model.addAttribute("branchUpdateDto", branchUpdateDto);
        model.addAttribute("libraries", libraryService.getAll());
        model.addAttribute("branchId", id);
        return "branches/edit";
    }

    @PostMapping("/edit/{id}")
    public String editBranch(@PathVariable Long id, @ModelAttribute BranchUpdateDto branchUpdateDto) {
        branchService.update(id, branchUpdateDto);
        return "redirect:/branches";
    }

    @PostMapping("/delete/{id}")
    public String deleteBranch(@PathVariable Long id) {
        branchService.delete(id);
        return "redirect:/branches";
    }
}