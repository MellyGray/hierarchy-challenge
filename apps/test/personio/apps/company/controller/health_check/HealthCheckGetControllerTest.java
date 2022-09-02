package personio.apps.company.controller.health_check;

import personio.apps.RequestTestCase;
import org.junit.jupiter.api.Test;

class HealthCheckGetControllerTest extends RequestTestCase {
    @Test
    void check_health_check_is_working() throws Exception {
        assertResponse("/health-check", 200, "{'status': 'ok'}");
    }
}