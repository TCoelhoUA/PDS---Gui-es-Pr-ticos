public interface AdapterInterface {
    public boolean addEmpregado(Empregado empregado);
    public void deleteEmpregado(int codigo);
    public boolean isEmpregado(int codigo);
    public Employee[] getAllEmpregados();
}
