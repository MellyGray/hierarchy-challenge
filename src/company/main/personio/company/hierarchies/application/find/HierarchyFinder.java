package personio.company.hierarchies.application.find;


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
        Employee employee = repository.searchByName(new EmployeeName(request.name()));
        Employee supervisor = repository.search(employee.supervisorId());
        Employee supervisorSupervisor = repository.search(supervisor.supervisorId());

        Hierarchy hierarchy = new Hierarchy(supervisor);
        hierarchy.addSubordinate(employee);
        hierarchy = hierarchy.setAsSupervisor(supervisorSupervisor);
        
        return new ResponseHierarchyDTO(hierarchy.toString());
    }
}
