package personio.company.hierarchies.application.create;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personio.company.hierarchies.application.ResponseHierarchyDTO;

public final class HierarchyCreatorTest {
    private HierarchyCreator creator;

    @BeforeEach
    protected void setUp() {
        creator = new HierarchyCreator();
    }

    @Test
    void create_a_valid_hierarchy() {
        Map<String, String> employeeSupervisorList  = new HashMap<>();
        employeeSupervisorList.put("Pete", "Nick");
        employeeSupervisorList.put("Barbara", "Nick");
        employeeSupervisorList.put("Nick", "Sophie");
        employeeSupervisorList.put("Sophie", "Jonas");

        System.out.println(employeeSupervisorList);
        CreateHierarchyDTO request = new CreateHierarchyDTO(employeeSupervisorList);

        ResponseHierarchyDTO response = creator.create(request);

        assertEquals("{\"Jonas\": {\"Sophie\": {\"Nick\": {\"Pete\": {},\"Barbara\": {}}}}}", response.hierarchy());
    }
}
