package personio.apps.company.controller.hierarchies;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import personio.company.hierarchies.application.create.HierarchyCreator;
import personio.company.hierarchies.application.create.CreateHierarchyDTO;
import personio.company.hierarchies.application.ResponseHierarchyDTO;


@RestController
public final class HierarchiesPostController {
    private HierarchyCreator creator;

    public HierarchiesPostController(HierarchyCreator creator) {
        this.creator = creator;
    }

    @PostMapping("/hierarchies")
    public ResponseEntity<String> create(@RequestBody Map<String, String> request) {
        ResponseHierarchyDTO hierarchy = creator.create(new CreateHierarchyDTO(request));

        return new ResponseEntity(
                hierarchy.hierarchy(),
                HttpStatus.CREATED
        );
    }
}
