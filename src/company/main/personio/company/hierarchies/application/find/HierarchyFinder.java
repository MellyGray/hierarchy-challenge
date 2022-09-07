package personio.company.hierarchies.application.find;

import java.util.Optional;

import org.springframework.stereotype.Service;

import personio.company.employees.domain.Employee;
import personio.company.employees.domain.EmployeeName;
import personio.company.employees.domain.EmployeeRepository;
import personio.company.hierarchies.application.ResponseHierarchyDTO;
import personio.company.hierarchies.domain.Hierarchy;


@Service
public final class HierarchyFinder {
    private EmployeeRepository repository;

    public HierarchyFinder(EmployeeRepository repository){
        this.repository = repository;
    }

    public ResponseHierarchyDTO find(FindHierarchyDTO request) {
        Optional<Employee> employee = repository.searchByName(new EmployeeName(request.name()));
        
        if (employee.isPresent()) {
            Hierarchy hierarchy = new Hierarchy(employee.get()); // TODO - Get supervisor and supervisorSupervisor
            
            return new ResponseHierarchyDTO(hierarchy.toString());
        }

        throw new RuntimeException("The employee does not exist."); // TODO - Custom error
    }
}
