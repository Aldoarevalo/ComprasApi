package Conexion;

import DTO.PerfilesDTO;
import DTO.UsuariosDTO;

public class TestConexion {
    private UsuariosDTO usuarioDTO;
    private PerfilesDTO perfilDTO;
    
    
    private ConexionBD conexion;
    
    public TestConexion() {
        conexion = new ConexionBD();
        System.out.println(conexion.getMsj());
        
        usuarioDTO = new UsuariosDTO();
        usuarioDTO.setUsuario("j");
        usuarioDTO.setClave("123");
        usuarioDTO.setEstado(Boolean.TRUE);
        
        
        perfilDTO = new PerfilesDTO();
        perfilDTO.setDescrip("Admin");
        perfilDTO.setComentario("Administrador de sistema");
        
    }
    
    
    public void ejemplo(int v,char v2, String v3) {
        
    }
    
    

    public static void main(String[] args) {
        new TestConexion();
    }
    
}
