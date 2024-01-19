package DTO;

import java.util.List;

public class PerfilDto {
    private Integer id;
    private String  descrip;
    private String  comentario;
    private List<PermisoDto> permisos;
    
    
    public PerfilDto() {
    }

    public PerfilDto(Integer id) {
        this.id = id;
    }
    
    public PerfilDto(Integer id, String descrip) {
        this.id = id;
        this.descrip = descrip;
    }
    
    
    
    public void setId(Integer id ){
        this.id=id;
    }
    
    public Integer getId(){
        return id;
    } 

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setPermisos(List<PermisoDto> permisos) {
        this.permisos = permisos;
    }

    public List<PermisoDto> getPermisos() {
        return permisos;
    }
    
}
