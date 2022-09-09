package personio.apps.company.controller.hierarchies;

import personio.apps.RequestTestCase;

import org.junit.jupiter.api.Test;

import org.springframework.security.test.context.support.WithMockUser;

public final class HierarchiesGetControllerTest extends RequestTestCase {
    @Test
    @WithMockUser(username = "user", password = "password", roles = "USER")
    void get_hierarchy_by_name() throws Exception {
        assertRequest(
                    "GET",
                    "/hierarchies?name=Nick",
                    200,
                    "{\"Jonas\": {\"Sophie\": {\"Nick\": {}}}}"
            );
    }
}
