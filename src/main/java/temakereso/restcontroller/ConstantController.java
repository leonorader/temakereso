package temakereso.restcontroller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import temakereso.entity.Category;
import temakereso.helper.SupervisorDto;
import temakereso.helper.TopicStatus;
import temakereso.helper.TopicType;
import temakereso.service.CategoryService;
import temakereso.service.SupervisorService;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/constants")
public class ConstantController {

    private final CategoryService categoryService;

    private final SupervisorService supervisorService;

    // ------------------------ GET -------------------------- //

    @GetMapping(path = "/types")
    public List<TopicType> getTypes() {
        return Arrays.asList(TopicType.values());
    }

    @GetMapping(path = "/statuses")
    public List<TopicStatus> getStatuses() {
        return Arrays.asList(TopicStatus.values());
    }

    @GetMapping(path = "/categories")
    public List<Category> getCategories() {
        return categoryService.getAll();
    }

    @GetMapping(path = "/supervisors")
    public List<SupervisorDto> getSupervisors() {
        return supervisorService.getAll();
    }

    // ------------------------ POST -------------------------- //

    @PostMapping(path = "/categories")
    public void createCategory(@RequestBody Category category) {
        categoryService.createCategory(category);
    }

    // ------------------------ PUT -------------------------- //

    @PutMapping(path = "/categories")
    public void modifyCategory(@RequestBody Category category) {
        categoryService.modifyCategory(category);
    }

}
