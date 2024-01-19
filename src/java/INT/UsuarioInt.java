package INT;

import DTO.UsuarioDto;
import Genericos.BaseCRUD;

public interface UsuarioInt extends BaseCRUD<UsuarioDto>{
    public Boolean validarUsuario(UsuarioDto usuario);
    public Boolean anularUsuario(Integer id);
}
