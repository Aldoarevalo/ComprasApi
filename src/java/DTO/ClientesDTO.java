package DTO;

import Genericos.BaseDTO;

public class ClientesDTO extends BaseDTO {

    private Integer idcli;
    private String nombres;
    private String apellidos;
    private Integer cicli;
    private Integer clitel;
    private Integer telefono;
    private String direccion;
    private NacionalidadDTO nacionalidad;

    public Integer getIdcli() {
        return idcli;
    }

    public void setIdcli(Integer idcli) {
        this.idcli = idcli;
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

    public Integer getCicli() {
        return cicli;
    }

    public void setCicli(Integer cicli) {
        this.cicli = cicli;
    }

    public Integer getClitel() {
        return clitel;
    }

    public void setClitel(Integer clitel) {
        this.clitel = clitel;
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
    
     public NacionalidadDTO getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(NacionalidadDTO nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

}
