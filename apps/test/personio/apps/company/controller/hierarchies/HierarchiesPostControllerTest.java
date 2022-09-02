package personio.apps.company.controller.hierarchies;

import personio.apps.RequestTestCase;
import org.junit.jupiter.api.Test;

class HierarchiesPostControllerTest extends RequestTestCase {
    @Test
    void retrieve_formatted_hierarchy() throws Exception {
        assertRequestWithBody(
                "POST",
                "/hierarchies",
                "{\"Pete\": \"Nick\",\"Barbara\": \"Nick\",\"Nick\": \"Sophie\",\"Sophie\": \"Jonas\"}",
                200,
                "{\"Jonas\": {\"Sophie\": {\"Nick\": {\"Pete\": {},\"Barbara\": {}}}}}"
        );
    }
}