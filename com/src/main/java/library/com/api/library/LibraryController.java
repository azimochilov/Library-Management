package library.com.api.library;
import library.com.domain.dtos.library.library.LibraryCreationDto;
import library.com.domain.dtos.library.library.LibraryResultDto;
import library.com.domain.dtos.library.library.LibraryUpdateDto;
import library.com.service.library.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/libraries")
public class LibraryController {

    private final LibraryService libraryService;

    @GetMapping
    public String getAllLibraries(Model model) {
        List<LibraryResultDto> libraries = libraryService.getAll();
        model.addAttribute("libraries", libraries);
        return "libraries/list";
    }

    @GetMapping("/create")
    public String createLibraryForm(Model model) {
        model.addAttribute("library", new LibraryCreationDto());
        return "libraries/create";
    }

    @PostMapping("/create")
    public String createLibrary(@ModelAttribute LibraryCreationDto libraryCreationDto) {
        libraryService.create(libraryCreationDto);
        return "redirect:/libraries";
    }

    @GetMapping("/{id}")
    public String getLibraryById(@PathVariable Long id, Model model) {
        LibraryResultDto library = libraryService.getById(id);
        model.addAttribute("library", library);
        return "libraries/view";
    }

    @GetMapping("/edit/{id}")
    public String editLibraryForm(@PathVariable Long id, Model model) {
        LibraryResultDto library = libraryService.getById(id);
        model.addAttribute("library", library);
        return "libraries/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateLibrary(@PathVariable Long id, @ModelAttribute LibraryUpdateDto libraryUpdateDto) {
        libraryService.update(id, libraryUpdateDto);
        return "redirect:/libraries";
    }

    @PostMapping("/delete/{id}")
    public String deleteLibrary(@PathVariable Long id) {
        libraryService.delete(id);
        return "redirect:/libraries";
    }
}
