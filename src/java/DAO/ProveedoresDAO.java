package DAO;

import Conexion.ConexionBD;
import DTO.ProveedoresDTO;
import INT.ProveedoresINT;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProveedoresDAO implements ProveedoresINT {

    private ConexionBD conexion;
    private String query, msj;
    private PreparedStatement ps;
    private ResultSet rs;

    public ProveedoresDAO() {
        conexion = new ConexionBD();
    }

    @Override
    public Boolean agregarRegistro(ProveedoresDTO dto) {
        try {
            //// PASO 1 - PREPARAR TRANSACCION 
            conexion.Transaccion(ConexionBD.TR.INICIAR);
            // PASO 2 - PREPARAR EL QUERY SQL
            //REGLAS
            // 1. Verificar la correcta escritura de la sintaxis ( nombre de tabla, nombres de columnas)
            // 2. Verificar la cantidad de columnas vs los simbolos que representan a los valores parametricos ( ? ) . "Misma cantidad de columnas es igual a misma cantidad de parametros"
            query = "INSERT INTO public.proveedores( prv_razonsocial, prv_ruc, prv_direccion, prv_telefono) VALUES ( ?, ?, ?, ?);";
            // PASO 3 - UTILIZAR EL OBJETO CONEXION PARA PREPARAR EL STATEMENT
            //REGLA 
            // ENCAPSULAR LAS SINTAXIS EN UN BLOQUE TRY-CATCH
            ps = conexion.obtenerConexion().prepareStatement(query);
            // PASO 4 - SET DE VALORES A LOS PARAMETROS INDICADOS EN EL PASO 1
            // REGLAS
            //1. RESPETAR EL TIPO DE DATOS PARA EL SET CORRESPONDIENTE ( EJEMPLO. nro_documento - INTEGER = setInt ; nombres STRING = SETSTRING )
            //2. RESPETAR EL INDICE DE LA COLUMNA SEGUN LA UBICACION DEL PASO 1
            // ps.setInt(1, dto.getIdemp());
            ps.setString(1, dto.getRazonsocial());
            ps.setString(2, dto.getRuc());
            ps.setString(3, dto.getDireccion());
            ps.setInt(4, dto.getTelefono());

            // PASO 5 - EJECUCION DEL STATEMENT Y VERIFICACION DE LA OPERACION
            // REGLA 
            //1. VERIFICAR LA CANTIDAD DE FILAS RESULTANTES DEL ps.executeUpdate() > 0 = OPERACION CORRECTA SI NO ERROR
            //2. SEGUN RESULTADO DE LA VERIFICACION conexion.Transaccion(ConexionBD.TR.CONFIRMAR); O conexion.Transaccion(ConexionBD.TR.CANCELAR);
            if (ps.executeUpdate() > 0) {
                // EXITOSA
                conexion.Transaccion(ConexionBD.TR.CONFIRMAR);
                msj = "OPERACION EXITOSA";
                return true;
            } else {
                // ERROR DURANTE LA OPERACION
                conexion.Transaccion(ConexionBD.TR.CANCELAR);
                msj = "OPERACION ERRONEA";
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProveedoresDAO.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(ConexionBD.TR.CANCELAR);
            msj = "ERROR " + ex.getMessage();
            return false;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProveedoresDAO.class.getName()).log(Level.SEVERE, null, ex);
                msj = "NO SE PUDO CERRAR EL STATEMEN " + ex.getMessage();
            }
        }

    }

    @Override
    public Boolean modificarRegistro(ProveedoresDTO dto) {
        try {
            //// PASO 1 - PREPARAR TRANSACCION 
            conexion.Transaccion(ConexionBD.TR.INICIAR);
            // PASO 2 - PREPARAR EL QUERY SQL
            //REGLAS
            // 1. Verificar la correcta escritura de la sintaxis ( nombre de tabla, nombres de columnas)
            // 2. Verificar la cantidad de columnas vs los simbolos que representan a los valores parametricos ( ? ) . "Misma cantidad de columnas es igual a misma cantidad de parametros"
            query = "UPDATE public.proveedores SET prv_razonsocial = ?, prv_ruc = ?, prv_direccion = ?, prv_telefono = ? "
                    + " WHERE id_cli = ?;";
            // PASO 3 - UTILIZAR EL OBJETO CONEXION PARA PREPARAR EL STATEMENT
            //REGLA 
            // ENCAPSULAR LAS SINTAXIS EN UN BLOQUE TRY-CATCH
            ps = conexion.obtenerConexion().prepareStatement(query);
            // PASO 4 - SET DE VALORES A LOS PARAMETROS INDICADOS EN EL PASO 1
            // REGLAS
            //1. RESPETAR EL TIPO DE DATOS PARA EL SET CORRESPONDIENTE ( EJEMPLO. nro_documento - INTEGER = setInt ; nombres STRING = SETSTRING )
            //2. RESPETAR EL INDICE DE LA COLUMNA SEGUN LA UBICACION DEL PASO 1
            ps.setString(1, dto.getRazonsocial());
            ps.setString(2, dto.getRuc());
            ps.setString(3, dto.getDireccion());
            ps.setInt(4, dto.getTelefono());
            ps.setInt(5, dto.getId());
            // PASO 5 - EJECUCION DEL STATEMENT Y VERIFICACION DE LA OPERACION
            // REGLA 
            //1. VERIFICAR LA CANTIDAD DE FILAS RESULTANTES DEL ps.executeUpdate() > 0 = OPERACION CORRECTA SI NO ERROR
            //2. SEGUN RESULTADO DE LA VERIFICACION conexion.Transaccion(ConexionBD.TR.CONFIRMAR); O conexion.Transaccion(ConexionBD.TR.CANCELAR);
            if (ps.executeUpdate() > 0) {
                // EXITOSA
                conexion.Transaccion(ConexionBD.TR.CONFIRMAR);
                msj = "OPERACION EXITOSA";
                return true;
            } else {
                // ERROR DURANTE LA OPERACION
                conexion.Transaccion(ConexionBD.TR.CANCELAR);
                msj = "OPERACION ERRONEA";
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProveedoresDAO.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(ConexionBD.TR.CANCELAR);
            msj = "ERROR " + ex.getMessage();
            return false;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProveedoresDAO.class.getName()).log(Level.SEVERE, null, ex);
                msj = "NO SE PUDO CERRAR EL STATEMEN " + ex.getMessage();
            }
        }
    }

    @Override
    public Boolean eliminarRegistro(Integer id) {
        try {
            //// PASO 1 - PREPARAR TRANSACCION 
            conexion.Transaccion(ConexionBD.TR.INICIAR);
            // PASO 2 - PREPARAR EL QUERY SQL
            //REGLAS
            // 1. Verificar la correcta escritura de la sintaxis ( nombre de tabla, nombres de columnas)
            // 2. Verificar la cantidad de columnas vs los simbolos que representan a los valores parametricos ( ? ) . "Misma cantidad de columnas es igual a misma cantidad de parametros"
            query = "DELETE FROM  public.proveedores WHERE prv_cod = ?;";
            // PASO 3 - UTILIZAR EL OBJETO CONEXION PARA PREPARAR EL STATEMENT
            //REGLA 
            // ENCAPSULAR LAS SINTAXIS EN UN BLOQUE TRY-CATCH
            ps = conexion.obtenerConexion().prepareStatement(query);
            // PASO 4 - SET DE VALORES A LOS PARAMETROS INDICADOS EN EL PASO 1
            // REGLAS
            //1. RESPETAR EL TIPO DE DATOS PARA EL SET CORRESPONDIENTE ( EJEMPLO. nro_documento - INTEGER = setInt ; nombres STRING = SETSTRING )
            //2. RESPETAR EL INDICE DE LA COLUMNA SEGUN LA UBICACION DEL PASO 1
            ps.setInt(1, id);
            // PASO 5 - EJECUCION DEL STATEMENT Y VERIFICACION DE LA OPERACION
            // REGLA 
            //1. VERIFICAR LA CANTIDAD DE FILAS RESULTANTES DEL ps.executeUpdate() > 0 = OPERACION CORRECTA SI NO ERROR
            //2. SEGUN RESULTADO DE LA VERIFICACION conexion.Transaccion(ConexionBD.TR.CONFIRMAR); O conexion.Transaccion(ConexionBD.TR.CANCELAR);
            if (ps.executeUpdate() > 0) {
                // EXITOSA
                conexion.Transaccion(ConexionBD.TR.CONFIRMAR);
                msj = "OPERACION EXITOSA";
                return true;
            } else {
                // ERROR DURANTE LA OPERACION
                conexion.Transaccion(ConexionBD.TR.CANCELAR);
                msj = "OPERACION ERRONEA";
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProveedoresDAO.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(ConexionBD.TR.CANCELAR);
            msj = "ERROR " + ex.getMessage();
            return false;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProveedoresDAO.class.getName()).log(Level.SEVERE, null, ex);
                msj = "NO SE PUDO CERRAR EL STATEMEN " + ex.getMessage();
            }
        }
    }

    @Override
    public List<ProveedoresDTO> consultarTodos() {
        try {
            List<ProveedoresDTO> lista;
            ProveedoresDTO dto;
            // PASO 1 - PREPARAR EL QUERY SQL
            //REGLAS
            // 1. Verificar la correcta escritura de la sintaxis ( nombre de tabla, nombres de columnas)
            query = "SELECT prv_cod, prv_razonsocial, prv_ruc, prv_direccion, prv_telefono FROM public.proveedores;";
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
                dto = new ProveedoresDTO();
                dto.setId(rs.getInt("prv_cod"));
                dto.setRazonsocial(rs.getString("prv_razonsocial"));
                dto.setRuc(rs.getString("prv_ruc"));
                dto.setDireccion(rs.getString("prv_direccion"));
                dto.setTelefono(rs.getInt("prv_telefono"));
                lista.add(dto);
            }
            return lista;

        } catch (SQLException ex) {
            Logger.getLogger(ProveedoresDAO.class.getName()).log(Level.SEVERE, null, ex);
            msj = "ERROR " + ex.getMessage();
            return null;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProveedoresDAO.class.getName()).log(Level.SEVERE, null, ex);
                msj = "NO SE PUDO CERRAR EL STATEMEN " + ex.getMessage();
            }
        }
    }

    @Override
    public ProveedoresDTO consultarSegunID(Integer id) {
        try {
            ProveedoresDTO dto = null;
            // PASO 1 - PREPARAR EL QUERY SQL
            //REGLAS
            // 1. Verificar la correcta escritura de la sintaxis ( nombre de tabla, nombres de columnas)
            query = "SELECT prv_cod, prv_razonsocial, prv_ruc, prv_direccion, prv_telefono FROM public.proveedores WHERE prv_cod = ? ;";
            // PASO 2 - UTILIZAR EL OBJETO CONEXION PARA PREPARAR EL STATEMENT
            //REGLA 
            // ENCAPSULAR LAS SINTAXIS EN UN BLOQUE TRY-CATCH
            ps = conexion.obtenerConexion().prepareStatement(query);
            //PASO 3 - SETEO 
            ps.setInt(1, id);

            // PASO 4 - EJECUCION DEL STATEMENT Y VERIFICACION DE LA OPERACION
            // REGLA 
            //1. VERIFICAR LA CANTIDAD DE FILAS RESULTANTES DEL ps.executeUpdate() > 0 = OPERACION CORRECTA SI NO ERROR
            rs = ps.executeQuery();
            if (rs.next()) {
                // EXITOSA
                dto = new ProveedoresDTO();
                dto.setId(rs.getInt("prv_cod"));
                dto.setRazonsocial(rs.getString("prv_razonsocial"));
                dto.setRuc(rs.getString("prv_ruc"));
                dto.setDireccion(rs.getString("prv_direccion"));
                dto.setTelefono(rs.getInt("prv_telefono"));

            }
            return dto;

        } catch (SQLException ex) {
            Logger.getLogger(ProveedoresDAO.class.getName()).log(Level.SEVERE, null, ex);
            msj = "ERROR " + ex.getMessage();
            return null;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProveedoresDAO.class.getName()).log(Level.SEVERE, null, ex);
                msj = "NO SE PUDO CERRAR EL STATEMEN " + ex.getMessage();
            }
        }
    }

    @Override
    public String getMsj() {
            return msj;
    }

    @Override
    public Boolean eliminarRegistros(Integer Idart, Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
