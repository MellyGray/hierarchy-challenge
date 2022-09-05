package personio.company.hierarchies.application.create;

import java.util.Map;

public final class CreateHierarchyDTO {
    private final Map<String, String> employeeSupervisorList;

    public CreateHierarchyDTO(Map<String, String> employeeSupervisorList) {
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
        CreateHierarchyDTO other = (CreateHierarchyDTO) obj;
        if (employeeSupervisorList == null) {
            if (other.employeeSupervisorList != null)
                return false;
        } else if (!employeeSupervisorList.equals(other.employeeSupervisorList))
            return false;
        return true;
    }
}
