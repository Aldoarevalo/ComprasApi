
package DTO;

import Genericos.BaseDTO;

public class ProveedoresDTO extends BaseDTO {
     private Integer idpro;
    private String razonsocial;
    private String ruc;
    private String direccion;
    private Integer telefono;
    
        public ProveedoresDTO() {
    }

    public ProveedoresDTO(Integer idpro) {
        this.idpro = idpro;
    }     
    public ProveedoresDTO(Integer idpro, String razonsocial) {
        this.idpro = idpro;
        this.razonsocial = razonsocial;
    }

    public Integer getIdpro() {
        return idpro;
    }

    public void setIdpro(Integer idpro) {
        this.idpro = idpro;
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }
 
}
