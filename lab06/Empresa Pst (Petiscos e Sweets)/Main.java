import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Test Sweets
        System.out.println("\n---------------- Sweets ----------------");

        // Creating "Database"
        Database db = new Database();

        // Creating 7 employees
        Employee e1 = new Employee("Tiago Coelho", 1, 2000);
        Employee e2 = new Employee("Vasco Pereira", 2, 2500);
        Employee e3 = new Employee("Ana Francisco", 3, 1900);
        Employee e4 = new Employee("Catarina Ribeiro", 4, 2100);
        Employee e5 = new Employee("Mariana Marques", 5, 1800);
        Employee e6 = new Employee("Matilde Rodrigues", 6, 2200);
        Employee e7 = new Employee("Gonçalo Fonseca", 7, 1950);

        // Hiring 7 employees
        db.addEmployee(e1);
        db.addEmployee(e2);
        db.addEmployee(e3);
        db.addEmployee(e4);
        db.addEmployee(e5);
        db.addEmployee(e6);
        db.addEmployee(e7);

        // Getting employees
        Employee[] employees = db.getAllEmployees();

        // Printing employees
        for (Employee emp : employees) {
            System.out.println("Employee " + emp.getEmpNum() +
                                ": " + emp.getName() +
                                ", " + emp.getSalary());
        }

        // Firing some employees because why not
        System.out.println("\nFiring: Catarina Ribeiro");
        System.out.println("Firing: Matilde Rodrigues");
        db.deleteEmployee(4);   // Catarina Ribeiro
        db.deleteEmployee(6);   // Matilde Rodrigues

        // Getting new employees list
        employees = db.getAllEmployees();

        // Printing new employees list
        for (Employee emp : employees) {
            System.out.print("\nEmployee " + emp.getEmpNum() +
                                ": " + emp.getName() +
                                ", " + emp.getSalary());
        }
        System.out.println("\n----------------------------------------");

        // Test Petiscos
        System.out.println("\n--------------- Petiscos ---------------");

        // Creating "Registos"
        Registos r = new Registos();

        // Creating 7 employees
        Empregado emp1 = new Empregado("Josefino", "Carneiro", 8, 2000);
        Empregado emp2 = new Empregado("Aurélio", "Branco", 9, 2500);
        Empregado emp3 = new Empregado("Gertrudes", "Silva", 10, 1900);
        Empregado emp4 = new Empregado("Joaquim", "Amaro", 11, 2100);
        Empregado emp5 = new Empregado("Rosa", "Peralta", 12, 1800);
        Empregado emp6 = new Empregado("Afonso", "Bacia", 13, 2200);
        Empregado emp7 = new Empregado("Santana", "Lopes", 14, 1950);

        // Hiring 7 employees
        r.insere(emp1);
        r.insere(emp2);
        r.insere(emp3);
        r.insere(emp4);
        r.insere(emp5);
        r.insere(emp6);
        r.insere(emp7);

        // Getting employees
        List<Empregado> empregados = r.listaDeEmpregados();

        // Printing employees
        for (Empregado emp : empregados) {
            System.out.println("Empregado " + emp.codigo() +
                                ": " + emp.nome() + " " + emp.apelido() +
                                ", " + emp.salario());
        }

        // Firing some employees because why not
        System.out.println("\nDespedindo: Joaquim Amaro");
        System.out.println("Despedindo: Afonso Bacia");
        r.remove(11);    // Joaquim Amaro
        r.remove(13);    // Afonso Bacia

        // Getting new employees list
        empregados = r.listaDeEmpregados();

        // Printing new employees list
        for (Empregado emp : empregados) {
            System.out.print("\nEmpregado " + emp.codigo() +
                                ": " + emp.nome() + " " + emp.apelido() +
                                ", " + emp.salario());
        }
        System.out.println("\n----------------------------------------");

        // Fusão Empresas
        System.out.println("\n------------ Fusão Empresas ------------");
        Adapter fusao = new Adapter(db, r);

        // Printing employees list
        for (Employee emp : fusao.getAllEmpregados()) {
            System.out.println(emp);
        }

        // Attempt to add new Empregado with invalid codigo
        fusao.addEmpregado(new Empregado("Jay", "Low", 1, 20000));

        fusao.addEmpregado(new Empregado("Jay", "Low", 15, 20000));

        // Firing some employees because why not
        System.out.println("\nDespedindo: Ana Francisco");
        System.out.println("Despedindo: Gertrudes Silva\n");
        fusao.deleteEmpregado(3);    // Ana Francisco
        fusao.deleteEmpregado(10);   // Gertrudes Silva

        // Printing new employees list
        System.out.println();
        for (Employee emp : fusao.getAllEmpregados()) {
            System.out.println(emp);
        }

        // Checking if Employees exist
        System.out.println();
        System.out.println("assert (fusao.isEmpregado(1) == true) : " + String.valueOf(fusao.isEmpregado(1)==true));
        System.out.println("assert (fusao.isEmpregado(10) == false) : " + String.valueOf(fusao.isEmpregado(10)==false));
        System.out.println("assert (fusao.isEmpregado(15) == true) : " + String.valueOf(fusao.isEmpregado(15)==true));

        System.out.println("\n----------------------------------------");
    }
}
