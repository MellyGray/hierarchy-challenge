package personio.company.hierarchies.application.create;

import personio.company.hierarchies.application.ResponseHierarchyDTO;
import personio.company.hierarchies.domain.Employee;
import personio.company.hierarchies.domain.Hierarchy;

import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public final class HierarchyCreator {

    public ResponseHierarchyDTO create(CreateHierarchyDTO request) {
        Hierarchy hierarchy = createHierarchyFromEmployeeSupervisorList(
            request.employeeSupervisorList()
        );

        return new ResponseHierarchyDTO(hierarchy.toString());
    }

    private Hierarchy createHierarchyFromEmployeeSupervisorList(Map<String, String> employeeSupervisorList){
        Employee firstEmployee = new Employee(getFirstEmployeeName(employeeSupervisorList));
        Hierarchy hierarchy = new Hierarchy(firstEmployee);

        for (var employeeSupervisor : employeeSupervisorList.entrySet()) {
            Employee employee = new Employee(employeeSupervisor.getKey());
            Employee supervisor = new Employee(employeeSupervisor.getValue());
            
            if (hierarchy.hierarchy(supervisor) != null) {
                hierarchy.hierarchy(supervisor).addSubordinate(employee);
            } else if (hierarchy.hierarchy(employee) != null) {
                hierarchy = hierarchy.hierarchy(employee).setAsSupervisor(supervisor);
            } else {
                hierarchy.addSubordinate(supervisor, employee);
            }
        }

        return hierarchy;
    }

    private String getFirstEmployeeName(Map<String, String> employeeSupervisorList) throws RuntimeException { // TODO - Handle exception
        Optional<String> firstKey = employeeSupervisorList.keySet().stream().findFirst();

        if (firstKey.isPresent()) {
            return firstKey.get();
        }

        throw new RuntimeException("The first name do not exist");
    }
}
