
package DTO;


public class PermisosRecursosDTO {
    //private Integer idRecurso;
    private RecursosRESTDTO recurso;
    private AccionesUsuarioDTO accion;
    private String  comentario;

    public PermisosRecursosDTO() {
    }

    public PermisosRecursosDTO(RecursosRESTDTO recurso, AccionesUsuarioDTO accion, String comentario) {
        this.recurso = recurso;
        this.accion = accion;
        this.comentario = comentario;
    }
    
    
    
    
    public RecursosRESTDTO getRecurso() {
        return recurso;
    }

    public void setRecurso(RecursosRESTDTO recurso) {
        this.recurso = recurso;
    }

    public AccionesUsuarioDTO getAccion() {
        return accion;
    }

    public void setAccion(AccionesUsuarioDTO accion) {
        this.accion = accion;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getComentario() {
        return comentario;
    }
    
    
    
    
    
}
