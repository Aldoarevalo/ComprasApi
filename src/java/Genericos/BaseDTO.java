package Genericos;

public class BaseDTO {
    private Integer id;
    private String  descrip;
    private String  comentario;

    public BaseDTO() {
    }
    
    public BaseDTO(Integer id) {
        this.id= id;
    }

    
    
    public Integer getId() {
        
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    
    
    
}
