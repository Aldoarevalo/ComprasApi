package DAO;

import Conexion.ConexionBD;
import DTO.AlmacenDTO;
import INT.AlmacenINT;
import DTO.DepositoDTO;
import DTO.ArticulosDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AlmacenDAO implements AlmacenINT {

    private ConexionBD conexion;
    private String query, msj;
    private PreparedStatement ps;
    private ResultSet rs;

    public AlmacenDAO() {
        conexion = new ConexionBD();
    }

    @Override
    public Boolean agregarRegistro(AlmacenDTO dto) {
        try {
            //// PASO 1 - PREPARAR TRANSACCION 
            conexion.Transaccion(ConexionBD.TR.INICIAR);
            // PASO 2 - PREPARAR EL QUERY SQL
            //REGLAS
            // 1. Verificar la correcta escritura de la sintaxis ( nombre de tabla, nombres de columnas)
            // 2. Verificar la cantidad de columnas vs los simbolos que representan a los valores parametricos ( ? ) . "Misma cantidad de columnas es igual a misma cantidad de parametros"
            query = "INSERT INTO public.Almacen (id_art, id_dep, stock_existencia, existencia_minima, existencia_maxima) VALUES ( ?, ?, ?, ?, ?);";
            // PASO 3 - UTILIZAR EL OBJETO CONEXION PARA PREPARAR EL STATEMENT
            //REGLA 
            // ENCAPSULAR LAS SINTAXIS EN UN BLOQUE TRY-CATCH
            ps = conexion.obtenerConexion().prepareStatement(query);
            // PASO 4 - SET DE VALORES A LOS PARAMETROS INDICADOS EN EL PASO 1
            // REGLAS
            //1. RESPETAR EL TIPO DE DATOS PARA EL SET CORRESPONDIENTE ( EJEMPLO. nro_documento - INTEGER = setInt ; nombres STRING = SETSTRING )
            //2. RESPETAR EL INDICE DE LA COLUMNA SEGUN LA UBICACION DEL PASO 1
            // ps.setInt(1, dto.getIdemp());
            ps.setInt(1, dto.getDeposito().getId());
            ps.setInt(2, dto.getArticulo().getIdarticulo());
            ps.setInt(3, dto.getStockexistencia());
            ps.setInt(4, dto.getStockexistenciaminima());
            ps.setInt(5, dto.getStockexistenciamaxima());

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
            Logger.getLogger(AlmacenDAO.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(ConexionBD.TR.CANCELAR);
            msj = "ERROR " + ex.getMessage();
            return false;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(AlmacenDAO.class.getName()).log(Level.SEVERE, null, ex);
                msj = "NO SE PUDO CERRAR EL STATEMEN " + ex.getMessage();
            }
        }

    }

    @Override
    public Boolean modificarRegistro(AlmacenDTO dto) {
        try {
            //// PASO 1 - PREPARAR TRANSACCION 
            conexion.Transaccion(ConexionBD.TR.INICIAR);
            // PASO 2 - PREPARAR EL QUERY SQL
            //REGLAS
            // 1. Verificar la correcta escritura de la sintaxis ( nombre de tabla, nombres de columnas)
            // 2. Verificar la cantidad de columnas vs los simbolos que representan a los valores parametricos ( ? ) . "Misma cantidad de columnas es igual a misma cantidad de parametros"
            query = "UPDATE public.Almacen SET stock_existencia=?, existencia_minima=?, existencia_maxima=? WHERE id_art= ? AND id_dep=?;";
            // PASO 3 - UTILIZAR EL OBJETO CONEXION PARA PREPARAR EL STATEMENT
            //REGLA 
            // ENCAPSULAR LAS SINTAXIS EN UN BLOQUE TRY-CATCH
            ps = conexion.obtenerConexion().prepareStatement(query);
            // PASO 4 - SET DE VALORES A LOS PARAMETROS INDICADOS EN EL PASO 1
            // REGLAS
            //1. RESPETAR EL TIPO DE DATOS PARA EL SET CORRESPONDIENTE ( EJEMPLO. nro_documento - INTEGER = setInt ; nombres STRING = SETSTRING )
            //2. RESPETAR EL INDICE DE LA COLUMNA SEGUN LA UBICACION DEL PASO 1
            ps.setInt(1, dto.getStockexistencia());
            ps.setInt(2, dto.getStockexistenciaminima());
            ps.setInt(3, dto.getStockexistenciamaxima());
            ps.setInt(4, dto.getArticulo().getIdarticulo());
            ps.setInt(5, dto.getDeposito().getId());

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
            Logger.getLogger(AlmacenDAO.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(ConexionBD.TR.CANCELAR);
            msj = "ERROR " + ex.getMessage();
            return false;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(AlmacenDAO.class.getName()).log(Level.SEVERE, null, ex);
                msj = "NO SE PUDO CERRAR EL STATEMEN " + ex.getMessage();
            }
        }
    }

    @Override
    public Boolean eliminarRegistros(Integer idart, Integer id) {
        try {
            //// PASO 1 - PREPARAR TRANSACCION 
            conexion.Transaccion(ConexionBD.TR.INICIAR);
            // PASO 2 - PREPARAR EL QUERY SQL
            //REGLAS
            // 1. Verificar la correcta escritura de la sintaxis ( nombre de tabla, nombres de columnas)
            // 2. Verificar la cantidad de columnas vs los simbolos que representan a los valores parametricos ( ? ) . "Misma cantidad de columnas es igual a misma cantidad de parametros"
            query = "DELETE FROM  public.almacen WHERE id_art = ? and id_dep = ?;";
            // PASO 3 - UTILIZAR EL OBJETO CONEXION PARA PREPARAR EL STATEMENT
            //REGLA 
            // ENCAPSULAR LAS SINTAXIS EN UN BLOQUE TRY-CATCH
            ps = conexion.obtenerConexion().prepareStatement(query);
            // PASO 4 - SET DE VALORES A LOS PARAMETROS INDICADOS EN EL PASO 1
            // REGLAS
            //1. RESPETAR EL TIPO DE DATOS PARA EL SET CORRESPONDIENTE ( EJEMPLO. nro_documento - INTEGER = setInt ; nombres STRING = SETSTRING )
            //2. RESPETAR EL INDICE DE LA COLUMNA SEGUN LA UBICACION DEL PASO 1
            ps.setInt(1, idart);
            ps.setInt(2, id);
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
            Logger.getLogger(AlmacenDAO.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(ConexionBD.TR.CANCELAR);
            msj = "ERROR " + ex.getMessage();
            return false;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(AlmacenDAO.class.getName()).log(Level.SEVERE, null, ex);
                msj = "NO SE PUDO CERRAR EL STATEMEN " + ex.getMessage();
            }
        }
    }

    @Override
    public List<AlmacenDTO> consultarTodos() {
        try {
            List<AlmacenDTO> lista;
            AlmacenDTO dto;
            // PASO 1 - PREPARAR EL QUERY SQL
            //REGLAS
            // 1. Verificar la correcta escritura de la sintaxis ( nombre de tabla, nombres de columnas)
            query = "SELECT F.stock_existencia, F.existencia_minima,F.existencia_maxima,F.id_art,F.id_dep, P.art_descri,C.dep_descri\n"
                    + "                  FROM public.\"Almacen\"  F INNER JOIN public.articulos P ON F.id_art=P.id_art\n"
                    + "                  	                    INNER JOIN deposito C ON F.id_dep=C.id_dep ;";
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
                dto = new AlmacenDTO();
                ArticulosDTO articulo = new ArticulosDTO();
                articulo.setIdarticulo(rs.getInt("id_art"));
                articulo.setDescrip(rs.getString("art_descri"));
                dto.setArticulo(articulo);
                DepositoDTO deposito = new DepositoDTO();
                deposito.setId(rs.getInt("id_dep"));
                deposito.setDescrip(rs.getString("dep_descri"));
                dto.setDeposito(deposito);
                dto.setStockexistencia(rs.getInt("stock_existencia"));
                dto.setStockexistenciaminima(rs.getInt("existencia_minima"));
                dto.setStockexistenciamaxima(rs.getInt("existencia_maxima"));

                lista.add(dto);
            }
            return lista;

        } catch (SQLException ex) {
            Logger.getLogger(AlmacenDAO.class.getName()).log(Level.SEVERE, null, ex);
            msj = "ERROR " + ex.getMessage();
            return null;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(AlmacenDAO.class.getName()).log(Level.SEVERE, null, ex);
                msj = "NO SE PUDO CERRAR EL STATEMEN " + ex.getMessage();
            }
        }

    }

    @Override
    public AlmacenDTO consultarSegunID(Integer id) {
        try {
            AlmacenDTO dto = null;
            // PASO 1 - PREPARAR EL QUERY SQL
            //REGLAS
            // 1. Verificar la correcta escritura de la sintaxis ( nombre de tabla, nombres de columnas)
            query = "SELECT F.stock_existencia, F.existencia_minima,F.existencia_maxima,F.id_art,F.id_dep, P.art_descri,C.dep_descri\n"
                    + "                  FROM public.\"Almacen\"  F INNER JOIN public.articulos P ON F.id_art=P.id_art\n"
                    + "                  	                    INNER JOIN deposito C ON F.id_dep=C.id_dep where P.id_art  = ?  ;";
            // PASO 2 - UTILIZAR EL OBJETO CONEXION PARA PREPARAR EL STATEMENT
            //REGLA 
            // ENCAPSULAR LAS SINTAXIS EN UN BLOQUE TRY-CATCH
            ps = conexion.obtenerConexion().prepareStatement(query);

            ps.setInt(1, id);
            // PASO 3 - EJECUCION DEL STATEMENT Y VERIFICACION DE LA OPERACION
            // REGLA 
            //1. VERIFICAR LA CANTIDAD DE FILAS RESULTANTES DEL ps.executeUpdate() > 0 = OPERACION CORRECTA SI NO ERROR
            rs = ps.executeQuery();
            if (rs.next()) {
                // EXITOSA
                dto = new AlmacenDTO();
                ArticulosDTO articulo = new ArticulosDTO();
                articulo.setIdarticulo(rs.getInt("id_art"));
                articulo.setDescrip(rs.getString("art_descri"));
                dto.setArticulo(articulo);
                DepositoDTO deposito = new DepositoDTO();
                deposito.setId(rs.getInt("id_dep"));
                deposito.setDescrip(rs.getString("dep_descri"));
                dto.setDeposito(deposito);
                dto.setStockexistencia(rs.getInt("stock_existencia"));
                dto.setStockexistenciaminima(rs.getInt("existencia_minima"));
                dto.setStockexistenciamaxima(rs.getInt("existencia_maxima"));
            }
            return dto;

        } catch (SQLException ex) {
            Logger.getLogger(AlmacenDAO.class.getName()).log(Level.SEVERE, null, ex);
            msj = "ERROR " + ex.getMessage();
            return null;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(AlmacenDAO.class.getName()).log(Level.SEVERE, null, ex);
                msj = "NO SE PUDO CERRAR EL STATEMEN " + ex.getMessage();
            }
        }

    }

    @Override
    public String getMsj() {
        return msj;
    }

    @Override
    public Boolean eliminarRegistro(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
