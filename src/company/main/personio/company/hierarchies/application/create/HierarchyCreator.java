package personio.company.hierarchies.application.create;

import personio.company.employees.application.EmployeeResponseDTO;
import personio.company.employees.application.create.CreateEmployeeDTO;
import personio.company.employees.application.create.EmployeeCreator;
import personio.company.employees.domain.Employee;
import personio.company.employees.domain.EmployeeId;
import personio.company.employees.domain.EmployeeName;
import personio.company.hierarchies.application.ResponseHierarchyDTO;
import personio.company.hierarchies.domain.Hierarchy;

import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public final class HierarchyCreator {
    private EmployeeCreator employeeCreator;

    public HierarchyCreator(EmployeeCreator employeeCreator){
        this.employeeCreator = employeeCreator;
    }

    public ResponseHierarchyDTO create(CreateHierarchyDTO request) {
        Hierarchy hierarchy = createFromEmployeeSupervisorList(
                request.employeeSupervisorList()
        );

        employeeCreator.createFromEmployeeSupervisorList(new CreateEmployeeDTO(request.employeeSupervisorList()));

        return new ResponseHierarchyDTO(hierarchy.toString());
    }

    private Hierarchy createFromEmployeeSupervisorList(Map<String, String> employeeSupervisorList) {
        Employee firstEmployee = new Employee(new EmployeeName(getFirstEmployeeName(employeeSupervisorList)));
        Hierarchy hierarchy = new Hierarchy(firstEmployee);

        for (var employeeSupervisor : employeeSupervisorList.entrySet()) {
            Employee employee = new Employee(new EmployeeName(employeeSupervisor.getKey()));
            Employee supervisor = new Employee(new EmployeeName(employeeSupervisor.getValue()));

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
