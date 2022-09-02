package personio.apps.company.controller.health_check;

import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class HealthCheckGetController {
    @GetMapping("/health-check")
    public HashMap<String, String> index(){
        HashMap<String, String> status = new HashMap<>();
        status.put("status", "ok");

        return status;
    }
}
