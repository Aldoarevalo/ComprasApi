package DTO;


import Genericos.BaseDTO;
import java.sql.Date;

public class EmpleadosDTO extends BaseDTO{
    private Integer idemp;
    private String nombres;
    private String apellidos;
    private Date fechanacimiento;
    private Integer salario;
    private Integer telefono;
    private String direccion;
    private String sexo;
    //private Integer idCargo;
    private CargoDTO cargo;
    private NacionalidadDTO nacionalidad;

     public EmpleadosDTO() {
    }

        public EmpleadosDTO(Integer idemp) {
        this.idemp = idemp;
    } 
     
    public EmpleadosDTO(Integer idemp, String nombres) {
        this.idemp = idemp;
        this.nombres = nombres;
    }
    
     public Integer getIdemp() {
        return idemp;
    }

    public void setIdemp(Integer idemp) {
        this.idemp = idemp;
    }
     public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    
     public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
    public Integer getSalario() {
        return salario;
    }

    public void setSalario(Integer salario) {
        this.salario = salario;
    }
    
    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }
    
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public Date getFechaNacimiento() {
        return fechanacimiento;
    }

    public void setFechaNacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }
    

    public CargoDTO getCargo() {
        return cargo;
    }

    public void setCargo(CargoDTO cargo) {
        this.cargo = cargo;
    }

    public NacionalidadDTO getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(NacionalidadDTO nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
    
    
    
    
}
