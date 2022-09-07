package personio.apps.company.controller.hierarchies;

import personio.apps.RequestTestCase;

import org.junit.jupiter.api.Test;

public final class HierarchiesGetControllerTest extends RequestTestCase {
    @Test
    void get_hierarchy_by_name() throws Exception {
        assertRequest(
                    "GET",
                    "/hierarchies?name=Nick&direction=supervisors",
                    200,
                    "{\"Jonas\": {\"Sophie\": {\"Nick\": {}}}}"
            );
    }
}
