package personio.company.hierarchies.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class Hierarchy {
    private Employee employee;
    private ArrayList<Hierarchy> subordinates = new ArrayList<Hierarchy>();
    private Hierarchy supervisor = null;
    private HashMap<Employee, Hierarchy> locate = new HashMap<Employee, Hierarchy>();

    public Hierarchy(Employee employee) {
        this.employee = employee;
        locate.put(employee, this);
    }

    public Employee employee() {
        return employee;
    }

    public Collection<Hierarchy> subordinates() {
        return subordinates;
    }

    public Hierarchy hierarchy(Employee element) {
        return locate.get(element);
    }

    public Hierarchy supervisor() {
        return supervisor;
    }

    public void addSubordinate(Employee root, Employee subordinate) {
        if (locate.containsKey(root)) {
            locate.get(root).addSubordinate(subordinate);
        } else {
            addSubordinate(root).addSubordinate(subordinate);
        }
    }

    public Hierarchy addSubordinate(Employee subordinate) {
        Hierarchy hierarchy = new Hierarchy(subordinate);
        subordinates.add(hierarchy);
        hierarchy.supervisor = this;
        hierarchy.locate = this.locate;
        locate.put(subordinate, hierarchy);
        return hierarchy;
    }

    public Hierarchy setAsSupervisor(Employee supervisorRoot) {
        Hierarchy hierarchyRoot = new Hierarchy(supervisorRoot);
        hierarchyRoot.subordinates.add(this);
        this.supervisor = hierarchyRoot;    
        hierarchyRoot.locate = this.locate;
        hierarchyRoot.locate.put(employee, this);
        hierarchyRoot.locate.put(supervisorRoot, hierarchyRoot);
        return hierarchyRoot;
    } 

    public Collection<Employee> getSuccessors(Employee root) {
        Collection<Employee> successors = new ArrayList<Employee>();
        Hierarchy hierarchy = hierarchy(root);
        if (null != hierarchy) {
            for (Hierarchy subordinate : hierarchy.subordinates) {
                successors.add(subordinate.employee);
            }
        }
        return successors;
    }

    @Override
    public String toString() {
        return "{" + hierarchyJSON(0) + "}}";
    }

    private static final int indent = 2;

    private String hierarchyJSON(int increment) {
        String hierarchyJSON = "";
        String inc = "";
        for (int i = 0; i < increment; ++i) {
            inc = inc + " ";
        }
        hierarchyJSON = inc + "\"" + employee.name() + "\":{";
        for (Hierarchy child : subordinates) {
            hierarchyJSON += "\n" + child.hierarchyJSON(increment + indent) + "}";
            if (child.subordinates.isEmpty()) {
                hierarchyJSON += ",";
            }
        }

        return hierarchyJSON.replace(",}", "}");
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((employee == null) ? 0 : employee.hashCode());
        result = prime * result + ((locate == null) ? 0 : locate.hashCode());
        result = prime * result + ((subordinates == null) ? 0 : subordinates.hashCode());
        result = prime * result + ((supervisor == null) ? 0 : supervisor.hashCode());
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
        Hierarchy other = (Hierarchy) obj;
        if (employee == null) {
            if (other.employee != null)
                return false;
        } else if (!employee.equals(other.employee))
            return false;
        if (locate == null) {
            if (other.locate != null)
                return false;
        } else if (!locate.equals(other.locate))
            return false;
        if (subordinates == null) {
            if (other.subordinates != null)
                return false;
        } else if (!subordinates.equals(other.subordinates))
            return false;
        if (supervisor == null) {
            if (other.supervisor != null)
                return false;
        } else if (!supervisor.equals(other.supervisor))
            return false;
        return true;
    }
}
