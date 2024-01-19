
package INT;

import DTO.PerfilesDTO;
import Genericos.OperacionesSQL;


public interface PerfilesINT extends OperacionesSQL<PerfilesDTO>{
    boolean anularPerfil(Integer idPerfil);
    
}
