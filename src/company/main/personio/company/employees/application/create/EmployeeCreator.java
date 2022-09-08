package personio.company.employees.application.create;


import org.springframework.stereotype.Service;

import personio.company.employees.application.find.EmployeeFinder;
import personio.company.employees.application.find.FindeEmployeeByNameDTO;
import personio.company.employees.domain.Employee;
import personio.company.employees.domain.EmployeeName;
import personio.company.employees.domain.EmployeeRepository;

@Service
public final class EmployeeCreator {
    private EmployeeRepository repository;
    private EmployeeFinder finder;

    public EmployeeCreator(EmployeeRepository repository, EmployeeFinder finder) {
        this.repository = repository;
        this.finder = finder;
    }

    public void createFromEmployeeSupervisorList(CreateEmployeeDTO request){
        for (var employeeSupervisor : request.employeeSupervisorList().entrySet()) {
            Employee employee =  new Employee(new EmployeeName(employeeSupervisor.getKey()));
            Employee supervisor = finder.findByName(new FindeEmployeeByNameDTO(employeeSupervisor.getValue()));
           
            if (supervisor == null) {
                supervisor = new Employee(new EmployeeName(employeeSupervisor.getValue()));
            } else {
                employee.setSupervisorId(supervisor.id());
            }
        
            repository.save(employee);
            repository.save(supervisor);
        }
    }
}
