package personio.apps.company.controller.hierarchies;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public final class HierarchiesPostController {

    public HierarchiesPostController() {
    }

    @PostMapping("/hierarchies")
    public ResponseEntity<String> create(@RequestBody Request request) {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}

final class Request {
    private HashMap<String, String> hierarchy;

    HashMap<String, String> hierarchy() {
        return hierarchy;
    }

    public void setHierarchy(HashMap<String, String> hierarchy) {
        this.hierarchy = hierarchy;
    }
}