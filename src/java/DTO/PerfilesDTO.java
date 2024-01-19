package DTO;

import Genericos.BaseDTO;
import java.util.List;

public class PerfilesDTO extends BaseDTO{
    
  private List<PermisosRecursosDTO> permisos;

    public void setPermisos(List<PermisosRecursosDTO> permisos) {
        this.permisos = permisos;
    }

    public List<PermisosRecursosDTO> getPermisos() {
        return permisos;
    }
    
    
    
}
