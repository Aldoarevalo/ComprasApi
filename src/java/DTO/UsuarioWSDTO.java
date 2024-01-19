package DTO;

public class UsuarioWSDTO {
    private Integer id;
    private String ip;
    private String usuario;
    private String clave;
    private String fechaCreacion;
    private String fechaSession;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaSession() {
        return fechaSession;
    }

    public void setFechaSession(String fechaSession) {
        this.fechaSession = fechaSession;
    }
    
    
    
}
