// Petiscos

import java.util.ArrayList;
import java.util.List;

public class Registos {
    // Data elements
    private ArrayList<Empregado> empregados; // Stores the employees
    
    public Registos() {
        empregados = new ArrayList<>();
    }
    
    public void insere(Empregado emp) {
        if (!empregados.contains(emp)) {
            empregados.add(emp);
        }
        else {
            System.out.printf("Empregado (%d) já existe!\n", emp.codigo());
        }
    }
    
    public void remove(int codigo) {
        // Code to remove employee
        for (int i=0; i<empregados.size(); i++) {
            if (empregados.get(i).codigo() == codigo) {
                empregados.remove(i);
                return;
            }
        }
        System.out.printf("Empregado (%d) não encontrado!\n", codigo);
    }
    
    public boolean isEmpregado(int codigo) {
        // Code to find employee
        for (Empregado emp : empregados) {
            if (emp.codigo() == codigo) {
                return true;
            }
        }
        return false;
    }
    
    public List<Empregado> listaDeEmpregados() {
        // Code to retrieve collection
        return empregados;
    }
}
