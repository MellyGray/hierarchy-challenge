package personio.company.employees.application.find;

import org.springframework.stereotype.Service;

import personio.company.employees.domain.Employee;
import personio.company.employees.domain.EmployeeId;
import personio.company.employees.domain.EmployeeName;
import personio.company.employees.domain.EmployeeRepository;

@Service
public final class EmployeeFinder {
    private EmployeeRepository repository;

    public EmployeeFinder(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Employee find(FindEmployeeDTO request) {
        return repository.search(new EmployeeId(request.id()));
    }

    public Employee findByName(FindeEmployeeByNameDTO request) {
        return repository.searchByName(new EmployeeName(request.name()));
    }
}
