package DTO;


import Genericos.BaseDTO;
import java.sql.Date;

public class FuncionariosDTO extends BaseDTO{
    
    private Date fechaIngreso;
    //private Integer idCargo;
    private CargoDTO cargo;
    private PersonaDTO persona;

    
    
    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public CargoDTO getCargo() {
        return cargo;
    }

    public void setCargo(CargoDTO cargo) {
        this.cargo = cargo;
    }

    public PersonaDTO getPersona() {
        return persona;
    }

    public void setPersona(PersonaDTO persona) {
        this.persona = persona;
    }
    
    
    
    
}
