package DTO;

public class UsuarioDto {
    private Integer id;
    private String usuario;
    private String clave;
    private Boolean estado;
    private Integer idPerfil;
    private Integer idFuncionario;
    private PerfilDto perfil;
    private FuncionarioDto funcionario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public PerfilDto getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilDto perfil) {
        this.perfil = perfil;
    }

    public FuncionarioDto getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(FuncionarioDto funcionario) {
        this.funcionario = funcionario;
    }
    
    
    
    
}
