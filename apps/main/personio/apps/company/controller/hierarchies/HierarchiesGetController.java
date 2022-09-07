package personio.apps.company.controller.hierarchies;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import personio.company.hierarchies.application.find.HierarchyFinder;
import personio.company.hierarchies.application.find.FindHierarchyDTO;
import personio.company.hierarchies.application.ResponseHierarchyDTO;


@RestController
public final class HierarchiesGetController {
    private HierarchyFinder finder;

    public HierarchiesGetController(HierarchyFinder finder) {
        this.finder = finder;
    }

    @GetMapping("/hierarchies")
    public ResponseEntity<String> create(@RequestParam(value = "name") String name) {
        ResponseHierarchyDTO hierarchy = finder.find(new FindHierarchyDTO(name));

        return new ResponseEntity(
                hierarchy.hierarchy(),
                HttpStatus.OK
        );
    }
}
