package DAO;

import DTO.FuncionarioDto;
import DTO.PerfilDto;
import DTO.UsuarioDto;
import Genericos.ConexionBD;
import Genericos.Util;
import INT.UsuarioInt;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDao implements UsuarioInt{
    private ConexionBD conexion;
    private PreparedStatement ps;
    private ResultSet rs;
    private String query, msj;

    public UsuarioDao() {
        conexion = new ConexionBD();
    }
    
    @Override
    public Boolean validarUsuario(UsuarioDto usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean agregar(UsuarioDto dto) {
          try {
            //paso 1 - preparar query sql 
            conexion.Transaccion(ConexionBD.TR.INICIAR);
            query= "INSERT INTO public.usuarios(usuario, clave, id_perfil, id_funcionario)VALUES (  ?, ?, ?, ?);";
            //paso 2 -  preparar el statement
            ps=conexion.obtenerConexion().prepareStatement(query);
            //paso 3 - seteat los valores al query
            ps.setString(1, dto.getUsuario());
            ps.setString(2, dto.getClave());
            ps.setInt(3, dto.getPerfil().getId());
            ps.setInt(4, dto.getFuncionario().getId());
            //paso 4 - executar el ps
            if ( ps.executeUpdate() > 0) {
                conexion.Transaccion(ConexionBD.TR.CONFIRMAR);
                msj= "Operación Exitosa";
                return true;
            } else {
                conexion.Transaccion(ConexionBD.TR.CANCELAR);
                return false;   
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            msj="Error durante la inserción ";
            conexion.Transaccion(ConexionBD.TR.CANCELAR);
            return false;
        }
        finally{
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Boolean modificar(UsuarioDto dto) {
            try {
            //paso 1 - preparar query sql 
            conexion.Transaccion(ConexionBD.TR.INICIAR);
            query= "UPDATE public.usuarios SET  usuario=?, clave=?,  id_perfil=?, id_funcionario=? WHERE id=?;";
            //paso 2 -  preparar el statement
            ps=conexion.obtenerConexion().prepareStatement(query);
            //paso 3 - seteat los valores al query
            ps.setString(1, dto.getUsuario());
            ps.setString(2, dto.getClave());
            ps.setInt(3, dto.getPerfil().getId());
            ps.setInt(4, dto.getFuncionario().getId());
            ps.setInt(5, dto.getId());
            //paso 4 - executar el ps
            if ( ps.executeUpdate() > 0) {
                conexion.Transaccion(ConexionBD.TR.CONFIRMAR);
                msj= "Operación Exitosa";
                return true;
            } else {
                conexion.Transaccion(ConexionBD.TR.CANCELAR);
                return false;   
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            msj="Error durante la inserción ";
            conexion.Transaccion(ConexionBD.TR.CANCELAR);
            return false;
        }
        finally{
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    @Override
    public Boolean anularUsuario(Integer id) {
              try {
            //paso 1 - preparar query sql 
            conexion.Transaccion(ConexionBD.TR.INICIAR);
            query= "UPDATE public.usuarios SET  estado=? WHERE id=?;";
            //paso 2 -  preparar el statement
            ps=conexion.obtenerConexion().prepareStatement(query);
            //paso 3 - seteat los valores al query
            ps.setBoolean(1, false);
            ps.setInt(2, id);
            //paso 4 - executar el ps
            if ( ps.executeUpdate() > 0) {
                conexion.Transaccion(ConexionBD.TR.CONFIRMAR);
                msj= "Operación Exitosa";
                return true;
            } else {
                conexion.Transaccion(ConexionBD.TR.CANCELAR);
                return false;   
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            msj="Error durante la inserción ";
            conexion.Transaccion(ConexionBD.TR.CANCELAR);
            return false;
        }
        finally{
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Boolean eliminar(UsuarioDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UsuarioDto> seleccionarTodos() {
        try {
            List<UsuarioDto> lista;
            UsuarioDto usuario;
            query="SELECT id, usuario, clave, estado, id_perfil, id_funcionario FROM public.usuarios;";
            ps=conexion.obtenerConexion().prepareStatement(query);
            rs=ps.executeQuery();
            lista= new ArrayList<>();
            while (rs.next()) {                
                usuario = new UsuarioDto();
                usuario.setId(   rs.getInt("id")  );
                usuario.setUsuario(Util.VerificarNull(rs.getString("usuario")) );
                usuario.setClave(Util.VerificarNull(rs.getString("clave")));
                usuario.setEstado(rs.getBoolean("estado"));
                usuario.setPerfil(new PerfilDto(rs.getInt("id_perfil"), ""));
                usuario.setFuncionario(new FuncionarioDto(rs.getInt("id_funcionario"), ""));
                lista.add(usuario);
            }
            
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(PerfilDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        finally{
            try {
                conexion.cerrarConexion();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(PerfilDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public UsuarioDto seleccionarSegunId(Integer id) {
        try {
            UsuarioDto usuario = null;
            query="SELECT id, usuario, clave, estado, id_perfil, id_funcionario FROM public.usuarios WHERE id = ? ;";
            ps=conexion.obtenerConexion().prepareStatement(query);
            ps.setInt(1, id);
            rs=ps.executeQuery();
            while (rs.next()) {                
                usuario = new UsuarioDto();
                usuario.setId(   rs.getInt("id")  );
                usuario.setUsuario(Util.VerificarNull(rs.getString("usuario")) );
                usuario.setClave(Util.VerificarNull(rs.getString("clave")));
                usuario.setEstado(rs.getBoolean("estado"));
                usuario.setPerfil(new PerfilDto(rs.getInt("id_perfil"), ""));
                usuario.setFuncionario(new FuncionarioDto(rs.getInt("id_funcionario"), ""));
            }
            
            return usuario;
        } catch (SQLException ex) {
            Logger.getLogger(PerfilDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        finally{
            try {
                conexion.cerrarConexion();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(PerfilDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public String getMsj() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
