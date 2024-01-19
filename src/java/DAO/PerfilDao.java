package DAO;

import DTO.MenuItemDto;
import DTO.PerfilDto;
import DTO.PermisoDto;
import Genericos.ConexionBD;
import Genericos.Util;
import INT.PerfilInt;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PerfilDao implements PerfilInt{
    private ConexionBD conexion;
    private PreparedStatement ps;
    private ResultSet rs;
    private String query,msj;

    public PerfilDao() {
        conexion = new ConexionBD();
    }
    
    @Override
    public Boolean agregar(PerfilDto dto) {
        try {
            int idPerfil=0;
            //paso 1 - preparar query sql 
            conexion.Transaccion(ConexionBD.TR.INICIAR);
            query= "INSERT INTO public.perfil( descrip, comentario) VALUES (?, ?); ";
            //paso 2 -  preparar el statement
            
            ps=conexion.obtenerConexion().prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
            //paso 3 - seteat los valores al query
            ps.setString(1, dto.getDescrip());
            ps.setString(2, dto.getComentario());
            //paso 4 - executar el ps
            if ( ps.executeUpdate() > 0) {
                rs= ps.executeQuery("SELECT LAST_INSERT_ID()");;
                //rs=ps.getGeneratedKeys();
                if (rs.next()) {
                    idPerfil=rs.getInt("id");
                    for (PermisoDto permiso : dto.getPermisos()) {
                        query= "INSERT INTO public.permisos(id_perfil, id_menu_items, comentario) VALUES (?, ?, ?);";
                        ps=conexion.obtenerConexion().prepareStatement(query);
                        ps.setInt(1, idPerfil);
                        ps.setInt(2, permiso.getMenuItem().getId());
                        ps.setString(3, permiso.getComentario());
                        if ( ps.executeUpdate() <= 0) {
                            conexion.Transaccion(ConexionBD.TR.CANCELAR);
                            return false; 
                        }
                    }
                    conexion.Transaccion(ConexionBD.TR.CONFIRMAR);
                    msj= "Operación Exitosa";
                    return true;
                }else{
                    conexion.Transaccion(ConexionBD.TR.CANCELAR);
                    return false; 
                }
            } else {
                conexion.Transaccion(ConexionBD.TR.CANCELAR);
                return false;   
            }
        } catch (SQLException ex) {
            Logger.getLogger(PerfilDao.class.getName()).log(Level.SEVERE, null, ex);
            msj="Error durante la inserción ";
            conexion.Transaccion(ConexionBD.TR.CANCELAR);
            return false;
        }
        finally{
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(PerfilDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Boolean modificar(PerfilDto dto) {
            try {
            //paso 1 - preparar query sql 
            conexion.Transaccion(ConexionBD.TR.INICIAR);
            query= "UPDATE public.perfil SET  descrip=?, comentario=? WHERE id=?;";
            //paso 2 -  preparar el statement
            ps=conexion.obtenerConexion().prepareStatement(query);
            //paso 3 - seteat los valores al query
            ps.setString(1, dto.getDescrip());
            ps.setString(2, dto.getComentario());
            ps.setInt(3, dto.getId());
            //paso 4 - executar el ps
            if ( ps.executeUpdate() > 0) {
                query = " DELETE FROM public.permisos WHERE id_perfil = ? ; ";
                ps=conexion.obtenerConexion().prepareStatement(query);
                ps.setInt(1, dto.getId());
                if (ps.executeUpdate() > 0) {
                     for (PermisoDto permiso : dto.getPermisos()) {
                        query= "INSERT INTO public.permisos(id_perfil, id_menu_items, comentario) VALUES (?, ?, ?);";
                        ps=conexion.obtenerConexion().prepareStatement(query);
                        ps.setInt(1, dto.getId());
                        ps.setInt(2, permiso.getMenuItem().getId());
                        ps.setString(3, permiso.getComentario());
                        if ( ps.executeUpdate() <= 0) {
                            conexion.Transaccion(ConexionBD.TR.CANCELAR);
                            return false; 
                        }
                    }
                    conexion.Transaccion(ConexionBD.TR.CONFIRMAR);
                    msj= "Operación Exitosa";
                    return true;
                }else{
                    conexion.Transaccion(ConexionBD.TR.CANCELAR);
                    return false; 
                }                
            } else {
                conexion.Transaccion(ConexionBD.TR.CANCELAR);
                return false;   
            }
        } catch (SQLException ex) {
            Logger.getLogger(PerfilDao.class.getName()).log(Level.SEVERE, null, ex);
            msj="Error durante la modificación ";
            conexion.Transaccion(ConexionBD.TR.CANCELAR);
            return false;
        }
        finally{
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(PerfilDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Boolean eliminar(PerfilDto dto) {
                try {
            //paso 1 - preparar query sql 
            conexion.Transaccion(ConexionBD.TR.INICIAR);
            query= "DELETE FROM public.perfil WHERE id=?;";
            //paso 2 -  preparar el statement
            ps=conexion.obtenerConexion().prepareStatement(query);
            //paso 3 - seteat los valores al query
            ps.setInt(1, dto.getId());
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
            Logger.getLogger(PerfilDao.class.getName()).log(Level.SEVERE, null, ex);
            msj="Error durante la eliminación ";
            conexion.Transaccion(ConexionBD.TR.CANCELAR);
            return false;
        }
        finally{
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(PerfilDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public List<PerfilDto> seleccionarTodos() {
        try {
            List<PerfilDto> lista;
            PerfilDto perfil;
            query="SELECT id, descrip, comentario FROM public.perfil; ";
            ps=conexion.obtenerConexion().prepareStatement(query);
            rs=ps.executeQuery();
            lista= new ArrayList<>();
            while (rs.next()) {                
                perfil = new PerfilDto();
                perfil.setId(   rs.getInt("id")  );
                perfil.setDescrip( Util.VerificarNull(rs.getString("descrip")) );
                perfil.setComentario(Util.VerificarNull(rs.getString("comentario")));
                lista.add(perfil);
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
    public PerfilDto seleccionarSegunId(Integer id) {
        try {
            PerfilDto perfil = null;
            query="SELECT id, descrip, comentario FROM public.perfil WHERE id = ? ; ";
            ps=conexion.obtenerConexion().prepareStatement(query);
            ps.setInt(1, id);
            rs=ps.executeQuery();
            while (rs.next()) {                
                perfil = new PerfilDto();
                perfil.setId(   rs.getInt("id")  );
                perfil.setDescrip( Util.VerificarNull(rs.getString("descrip")) );
                perfil.setComentario(Util.VerificarNull(rs.getString("comentario")));
            }
            return perfil;
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
        return msj;
    }

    
}
