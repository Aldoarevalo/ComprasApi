package Genericos;

import java.util.List;

public interface BaseCRUD<T> {
    public Boolean agregar(T dto);
    public Boolean modificar(T dto);
    public Boolean eliminar(T dto);
    
    public List<T> seleccionarTodos();
    public T seleccionarSegunId(Integer id);
    public String getMsj();
    
}
