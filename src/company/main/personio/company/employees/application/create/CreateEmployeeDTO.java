package personio.company.employees.application.create;

import java.util.Map;

public final class CreateEmployeeDTO {
    private Map<String, String> employeeSupervisorList;

    public CreateEmployeeDTO(Map<String, String> employeeSupervisorList) {
        this.employeeSupervisorList = employeeSupervisorList;
    }

    public Map<String, String> employeeSupervisorList() {
        return employeeSupervisorList;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((employeeSupervisorList == null) ? 0 : employeeSupervisorList.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CreateEmployeeDTO other = (CreateEmployeeDTO) obj;
        if (employeeSupervisorList == null) {
            if (other.employeeSupervisorList != null)
                return false;
        } else if (!employeeSupervisorList.equals(other.employeeSupervisorList))
            return false;
        return true;
    }
}
