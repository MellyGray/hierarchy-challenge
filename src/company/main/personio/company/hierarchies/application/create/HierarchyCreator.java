package personio.company.hierarchies.application.create;

import personio.company.hierarchies.application.ResponseHierarchyDTO;
import personio.company.hierarchies.domain.Employee;
import personio.company.hierarchies.domain.Hierarchy;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public final class HierarchyCreator {

    public ResponseHierarchyDTO create(CreateHierarchyDTO request) {
        Employee firstEmployee = new Employee(getFirstEmployeeName(request.employeeSupervisorList()));
        Hierarchy hierarchy = new Hierarchy(firstEmployee);

        for (var employeeSupervisor : request.employeeSupervisorList().entrySet()) {
            Employee employee = new Employee(employeeSupervisor.getKey());
            Employee supervisor = new Employee(employeeSupervisor.getValue());

            if (Objects.equals(hierarchy.employee().name(), employee.name())) {
                 hierarchy = hierarchy.setAsSupervisor(supervisor);
            } else {
                hierarchy.addSubordinate(supervisor, employee);
            }
        }

        return new ResponseHierarchyDTO(hierarchy.toString());
    }

    private String getFirstEmployeeName(Map<String, String> employeeSupervisorList) throws RuntimeException { // TODO - Handle exception
        Optional<String> firstKey = employeeSupervisorList.keySet().stream().findFirst();

        if (firstKey.isPresent()) {
            return firstKey.get();
        }

        throw new RuntimeException("The first name do not exist");
    }
}
