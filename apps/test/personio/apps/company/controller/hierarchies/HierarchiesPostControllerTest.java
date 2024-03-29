package personio.apps.company.controller.hierarchies;

import personio.apps.RequestTestCase;

import org.junit.jupiter.api.Test;

import org.springframework.security.test.context.support.WithMockUser;

class HierarchiesPostControllerTest extends RequestTestCase {
    @Test
    @WithMockUser(username = "user", password = "password", roles = "USER")
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
    @WithMockUser(username = "user", password = "password", roles = "USER")
    void create_new_hierarchy_disordered_json() throws Exception {
        assertRequestWithBody(
                "POST",
                "/hierarchies",
                "{\"Sophie\":\"Jonas\",\"Barbara\":\"Nick\",\"Pete\":\"Nick\",\"Nick\":\"Sophie\"}",
                201,
                "{\"Jonas\": {\"Sophie\": {\"Nick\": {\"Pete\": {},\"Barbara\": {}}}}}"
        );
    }

    @Test
    @WithMockUser(username = "user", password = "password", roles = "USER")
    void create_new_hierarchy_duplicated_information_json() throws Exception {
        assertRequestWithBody(
                "POST",
                "/hierarchies",
                "{\"Sophie\":\"Jonas\",\"Barbara\":\"Nick\",\"Pete\":\"Nick\",\"Nick\":\"Sophie\",\"Pete\":\"Nick\",\"Barbara\":\"Nick\"}",
                201,
                "{\"Jonas\": {\"Sophie\": {\"Nick\": {\"Pete\": {},\"Barbara\": {}}}}}"
        );
    }
}