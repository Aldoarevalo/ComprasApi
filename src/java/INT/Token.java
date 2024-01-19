package INT;

import DTO.TokenDTO;
import DTO.UsuarioWSDTO;

public interface Token {
    public Boolean  verificarToken(TokenDTO token);
    public Boolean grabarToken(UsuarioWSDTO filtro);
    public TokenDTO obtenerTokenGenerado();
    public String getMsj();
}
