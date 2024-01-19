/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INT;

import DTO.FuncionariosDTO;
import Genericos.OperacionesSQL;

/**
 *
 * @author Juan
 */
public interface FuncionarioINT extends OperacionesSQL<FuncionariosDTO>{
    
    public String consultarSegunLegajo(Integer nroLegajo);
    
}
