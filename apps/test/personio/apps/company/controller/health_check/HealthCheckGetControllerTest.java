package personio.apps.company.controller.health_check;

import personio.apps.RequestTestCase;

import org.junit.jupiter.api.Test;

import org.springframework.security.test.context.support.WithMockUser;

class HealthCheckGetControllerTest extends RequestTestCase {
    @Test
    @WithMockUser(username = "user", password = "password", roles = "USER")
    void check_health_check_is_working() throws Exception {
        assertResponse("/health-check", 200, "{'status': 'ok'}");
    }
}