
package Genericos;

import java.util.List;


public interface OperacionesSQL<T>  {
    public Boolean agregarRegistro(T dto );
    public Boolean modificarRegistro(T dto);
    public Boolean eliminarRegistro(Integer id);
    public Boolean eliminarRegistros(Integer Idart,Integer id);
    public List<T> consultarTodos();
    public T consultarSegunID(Integer id);
    public String getMsj();
    
}
