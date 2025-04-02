public class Adapter implements AdapterInterface {
    // Vamos manter as duas bases de dados mas qualquer adição de funcionários será feita em Registos

    private Database db;
    private Registos r;

    public Adapter(Database database, Registos registos) {
        this.db = database;
        this.r = registos;
    }

    public boolean addEmpregado(Empregado empregado) {
        // Checking Database
        for (Employee emp : db.getAllEmployees()) {
            if (emp.getEmpNum() == (long) empregado.codigo()) {
                return false;
            }
        }

        // Checking Registos
        if (r.isEmpregado(empregado.codigo())) {
            return false;
        }
        r.insere(empregado);
        System.out.println("\nEmpregado adicionado com sucesso!");
        return true;
    }

    public void deleteEmpregado(int codigo){
        // Checking Database
        for (Employee emp : db.getAllEmployees()) {
            if (emp.getEmpNum() == (long) codigo) {
                db.deleteEmployee((long) codigo);
                System.out.printf("Empregado (%d) removido com sucesso!\n", codigo);
                return;
            }
        }

        // Checking Registos
        if (r.isEmpregado(codigo)) {
            r.remove(codigo);
            System.out.printf("Empregado (%d) removido com sucesso!\n", codigo);
            return;
        }

        // Failed to find Empregado
        System.out.printf("Empregado (%d) não encontrado!\n", codigo);
    }

    public boolean isEmpregado(int emp_num) {
        // Checking Database
        for (Employee emp : db.getAllEmployees()) {
            if (emp.getEmpNum() == (long) emp_num) {
                return true;
            }
        }

        // Checking Registos
        return r.isEmpregado(emp_num);
    }
    
    public Employee[] getAllEmpregados() {
        int size = r.listaDeEmpregados().size() + db.getAllEmployees().length;
        Employee[] employees = new Employee[size];

        // Adding Database Employees
        int i=0;
        for (; i<db.getAllEmployees().length; i++) {
            employees[i] = db.getAllEmployees()[i];
        }

        // Adding Registos Empregados (creating equivalent Employees)
        Empregado emp;
        for (int j=i; j<size; j++) {
            emp = r.listaDeEmpregados().get(j-i);
            employees[j] = new Employee(emp.nome() + " " + emp.apelido(), emp.codigo(), emp.salario());
        }
        
        return employees;
    }
}
