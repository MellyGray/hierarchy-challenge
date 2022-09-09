package personio.company.hierarchies.application.create;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personio.company.employees.application.create.EmployeeCreator;
import personio.company.hierarchies.application.ResponseHierarchyDTO;

import static org.mockito.Mockito.mock;

public class HierarchyCreatorTest {
    private HierarchyCreator creator;

    @BeforeEach
    protected void setUp() {
        creator = new HierarchyCreator(mock(EmployeeCreator.class));
    }

    @Test
    void create_a_valid_hierarchy() {
        Map<String, String> employeeSupervisorList  = new HashMap<>();
        employeeSupervisorList.put("Pete", "Nick");
        employeeSupervisorList.put("Barbara", "Nick");
        employeeSupervisorList.put("Nick", "Sophie");
        employeeSupervisorList.put("Sophie", "Jonas");

        CreateHierarchyDTO request = new CreateHierarchyDTO(employeeSupervisorList);

        ResponseHierarchyDTO response = creator.create(request);

        assertEquals("{\"Jonas\":{\"Sophie\":{\"Nick\":{\"Pete\":{},\"Barbara\":{}}}}}",
                response.hierarchy().replaceAll("\\s+", "")
        );
    }
}
