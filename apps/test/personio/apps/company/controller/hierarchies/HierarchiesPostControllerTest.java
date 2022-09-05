package personio.apps.company.controller.hierarchies;

import personio.apps.RequestTestCase;
import org.junit.jupiter.api.Test;

class HierarchiesPostControllerTest extends RequestTestCase {
    @Test
    void create_new_hierarchy() throws Exception {
        assertRequestWithBody(
                "POST",
                "/hierarchies",
                "{\"Pete\": \"Nick\",\"Barbara\": \"Nick\",\"Nick\": \"Sophie\",\"Sophie\": \"Jonas\"}",
                201,
                "{\"Jonas\": {\"Sophie\": {\"Nick\": {\"Pete\": {},\"Barbara\": {}}}}}"
        );
    }

    @Test
    void create_new_hierarchy_disordered_json() throws Exception {
        assertRequestWithBody(
                "POST",
                "/hierarchies",
                "{\"Sophie\":\"Jonas\",\"Barbara\":\"Nick\",\"Pete\":\"Nick\",\"Nick\":\"Sophie\"}",
                201,
                "{\"Jonas\": {\"Sophie\": {\"Nick\": {\"Pete\": {},\"Barbara\": {}}}}}"
        );
    }
}