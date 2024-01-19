package DAO;

import DTO.CiudadDto;
import Genericos.ConexionBD;
import INT.CiudadInt;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CiudadDao implements CiudadInt {

    private String query;
    private String msj;
    private PreparedStatement ps;
    private ResultSet rs;
    private ConexionBD conexion;

    public CiudadDao() {
        conexion = new ConexionBD();
    }

    @Override
    public Boolean agregar(CiudadDto dto) {
        try {
            conexion.Transaccion(ConexionBD.TR.INICIAR);
            query = "INSERT INTO public.ciudad( nombre, comentario) "
                    + "VALUES ( ?, ?);";
            ps = conexion.obtenerConexion().prepareStatement(query);
            ps.setString(1, dto.getNombre());
            ps.setString(2, dto.getComentario());
            if (ps.executeUpdate() > 0) {
                conexion.Transaccion(ConexionBD.TR.CONFIRMAR);
                return true;
            } else {
                conexion.Transaccion(ConexionBD.TR.CANCELAR);
                return false;
            }
        } catch (SQLException ex) {
            conexion.Transaccion(ConexionBD.TR.CANCELAR);
            msj = "Error durante la inserción del registro " + ex.getMessage();
            return false;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
            }
        }
    }

    @Override
    public Boolean modificar(CiudadDto dto) {
        try {
            conexion.Transaccion(ConexionBD.TR.INICIAR);
            query = "UPDATE  public.ciudad SET  nombre = ?, comentario = ? WHERE id = ? ;";
            ps = conexion.obtenerConexion().prepareStatement(query);
            ps.setString(1, dto.getNombre());
            ps.setString(2, dto.getComentario());
            ps.setInt(3, dto.getId());
            if (ps.executeUpdate() > 0) {
                conexion.Transaccion(ConexionBD.TR.CONFIRMAR);
                return true;
            } else {
                conexion.Transaccion(ConexionBD.TR.CANCELAR);
                return false;
            }
        } catch (SQLException ex) {
            conexion.Transaccion(ConexionBD.TR.CANCELAR);
            msj = "Error durante la modificación del registro " + ex.getMessage();
            return false;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
            }
        }
    }

    @Override
    public Boolean eliminar(CiudadDto dto) {
               try {
            conexion.Transaccion(ConexionBD.TR.INICIAR);
            query = "delete from  public.ciudad WHERE id = ? ;";
            ps = conexion.obtenerConexion().prepareStatement(query);
            ps.setInt(1, dto.getId());
            if (ps.executeUpdate() > 0) {
                conexion.Transaccion(ConexionBD.TR.CONFIRMAR);
                return true;
            } else {
                conexion.Transaccion(ConexionBD.TR.CANCELAR);
                return false;
            }
        } catch (SQLException ex) {
            conexion.Transaccion(ConexionBD.TR.CANCELAR);
            msj = "Error durante la modificación del registro " + ex.getMessage();
            return false;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
            }
        }
    }

    @Override
    public List<CiudadDto> seleccionarTodos() {
        try {
            List<CiudadDto> lista;
            CiudadDto dto;
            // PASO 1 - PREPARAR EL QUERY SQL
            //REGLAS
            // 1. Verificar la correcta escritura de la sintaxis ( nombre de tabla, nombres de columnas)
            query = "SELECT id, nombre, comentario FROM public.ciudad;";
            // PASO 2 - UTILIZAR EL OBJETO CONEXION PARA PREPARAR EL STATEMENT
            //REGLA 
            // ENCAPSULAR LAS SINTAXIS EN UN BLOQUE TRY-CATCH
            ps = conexion.obtenerConexion().prepareStatement(query);
            // PASO 3 - EJECUCION DEL STATEMENT Y VERIFICACION DE LA OPERACION
            // REGLA 
            //1. VERIFICAR LA CANTIDAD DE FILAS RESULTANTES DEL ps.executeUpdate() > 0 = OPERACION CORRECTA SI NO ERROR
            rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                // EXITOSA
                dto = new CiudadDto();
                dto.setId(rs.getInt("id"));
                dto.setNombre(rs.getString("nombre"));
                dto.setComentario(rs.getString("comentario"));
               
                lista.add(dto);
            }
            return lista;

        } catch (SQLException ex) {
            Logger.getLogger(MarcaDAO.class.getName()).log(Level.SEVERE, null, ex);
            msj = "ERROR " + ex.getMessage();
            return null;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(MarcaDAO.class.getName()).log(Level.SEVERE, null, ex);
                msj = "NO SE PUDO CERRAR EL STATEMEN " + ex.getMessage();
            }
        }
    
    }

    @Override
    public CiudadDto seleccionarSegunId(Integer id) {
        try {
            CiudadDto dto=null;
            query= "SELECT id, nombre, comentario FROM public.ciudad WHERE id = ?;";
            ps= conexion.obtenerConexion().prepareStatement(query);
            ps.setInt(1, id);
            rs=ps.executeQuery();
            if (rs.next()) {
                dto =  new CiudadDto();
                dto.setId(rs.getInt("id"));
                dto.setNombre(rs.getString("nombre"));
                dto.setComentario(rs.getString("comentario"));
            }
            return  dto;
        } catch (SQLException ex) {
            msj= "No se pudo recuperar el registro " + ex.getMessage();
            return null;
        }
    }

    @Override
    public String getMsj() {
        return msj;
    }

}
