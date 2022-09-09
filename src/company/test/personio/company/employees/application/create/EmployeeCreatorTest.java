package personio.company.employees.application.create;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personio.company.employees.application.find.EmployeeFinder;
import personio.company.employees.domain.Employee;
import personio.company.employees.domain.EmployeeName;
import personio.company.employees.domain.EmployeeRepository;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.atLeastOnce;

public class EmployeeCreatorTest {
    private EmployeeCreator creator;
    private EmployeeRepository repository;

    @BeforeEach
    protected void setUp() {
        repository = mock(EmployeeRepository.class);
        creator = new EmployeeCreator(repository, mock(EmployeeFinder.class));
    }

    @Test
    void create_employees_from_employee_supervisor_list() {
        Map<String, String> employeeSupervisorList  = new HashMap<>();
        employeeSupervisorList.put("Pete", "Nick");
        employeeSupervisorList.put("Barbara", "Nick");
        employeeSupervisorList.put("Nick", "Sophie");
        employeeSupervisorList.put("Sophie", "Jonas");

        Employee employeeNick = new Employee(new EmployeeName("Nick"));
        Employee employeePete = new Employee(new EmployeeName("Pete"));
        Employee employeeBarbara = new Employee(new EmployeeName("Barbara"));
        Employee employeeSophie = new Employee(new EmployeeName("Sophie"));
        Employee employeeJonas = new Employee(new EmployeeName("Jonas"));

        CreateEmployeeDTO request = new CreateEmployeeDTO(employeeSupervisorList);
        
        creator.createFromEmployeeSupervisorList(request);

        verify(repository, atLeastOnce()).save(employeeNick);
        verify(repository, atLeastOnce()).save(employeePete);
        verify(repository, atLeastOnce()).save(employeeBarbara);
        verify(repository, atLeastOnce()).save(employeeSophie);
        verify(repository, atLeastOnce()).save(employeeJonas);
    }
}
