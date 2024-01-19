package DTO;

public class FuncionarioDto {
    private Integer id;
    private String nombreApellido;
    
    
    public FuncionarioDto() {
    }

    public FuncionarioDto(Integer id) {
        this.id = id;
    }

    public FuncionarioDto(Integer id, String nombreApellido) {
        this.id = id;
        this.nombreApellido = nombreApellido;
    }

    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreApellido() {
        return nombreApellido;
    }

    public void setNombreApellido(String nombreApellido) {
        this.nombreApellido = nombreApellido;
    }
    
    
    
    
}
