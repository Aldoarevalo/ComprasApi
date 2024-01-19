/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.ConexionBD;
import DTO.ArticulosDTO;
import INT.ArticulosINT;
import DTO.MarcaDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ArticulosDAO implements ArticulosINT{
    
     private ConexionBD conexion;
    private String query, msj;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public ArticulosDAO() {
        conexion = new ConexionBD();
    }
    @Override
    public Boolean agregarRegistro(ArticulosDTO dto) {
       
        try {
            //// PASO 1 - PREPARAR TRANSACCION 
            conexion.Transaccion(ConexionBD.TR.INICIAR);
            // PASO 2 - PREPARAR EL QUERY SQL
            //REGLAS
            // 1. Verificar la correcta escritura de la sintaxis ( nombre de tabla, nombres de columnas)
            // 2. Verificar la cantidad de columnas vs los simbolos que representan a los valores parametricos ( ? ) . "Misma cantidad de columnas es igual a misma cantidad de parametros"
            query = "INSERT INTO public.articulos( art_descri, art_precio, id_mar) VALUES ( ?, ?, ?);";
            // PASO 3 - UTILIZAR EL OBJETO CONEXION PARA PREPARAR EL STATEMENT
            //REGLA 
            // ENCAPSULAR LAS SINTAXIS EN UN BLOQUE TRY-CATCH
            ps = conexion.obtenerConexion().prepareStatement(query);
            // PASO 4 - SET DE VALORES A LOS PARAMETROS INDICADOS EN EL PASO 1
            // REGLAS
            //1. RESPETAR EL TIPO DE DATOS PARA EL SET CORRESPONDIENTE ( EJEMPLO. nro_documento - INTEGER = setInt ; nombres STRING = SETSTRING )
            //2. RESPETAR EL INDICE DE LA COLUMNA SEGUN LA UBICACION DEL PASO 1
           
            ps.setString(1, dto.getDescrip());
            ps.setInt(2, dto.getPrecio());
            ps.setInt(3, dto.getMarca().getId());
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
            Logger.getLogger(ArticulosDAO.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(ConexionBD.TR.CANCELAR);
            msj = "ERROR " + ex.getMessage();
            return false;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(ArticulosDAO.class.getName()).log(Level.SEVERE, null, ex);
                msj = "NO SE PUDO CERRAR EL STATEMEN " + ex.getMessage();
            }
        }

    }

    @Override
    public Boolean modificarRegistro(ArticulosDTO dto) {
      
     try {
            //// PASO 1 - PREPARAR TRANSACCION 
            conexion.Transaccion(ConexionBD.TR.INICIAR);
            // PASO 2 - PREPARAR EL QUERY SQL
            //REGLAS
            // 1. Verificar la correcta escritura de la sintaxis ( nombre de tabla, nombres de columnas)
            // 2. Verificar la cantidad de columnas vs los simbolos que representan a los valores parametricos ( ? ) . "Misma cantidad de columnas es igual a misma cantidad de parametros"
            query = "UPDATE public.articulos SET art_descri = ?, art_precio = ?, id_mar = ? "
                    + " WHERE id_art = ?;";
            // PASO 3 - UTILIZAR EL OBJETO CONEXION PARA PREPARAR EL STATEMENT
            //REGLA 
            // ENCAPSULAR LAS SINTAXIS EN UN BLOQUE TRY-CATCH
            ps = conexion.obtenerConexion().prepareStatement(query);
            // PASO 4 - SET DE VALORES A LOS PARAMETROS INDICADOS EN EL PASO 1
            // REGLAS
            //1. RESPETAR EL TIPO DE DATOS PARA EL SET CORRESPONDIENTE ( EJEMPLO. nro_documento - INTEGER = setInt ; nombres STRING = SETSTRING )
            //2. RESPETAR EL INDICE DE LA COLUMNA SEGUN LA UBICACION DEL PASO 1
            
            ps.setString(1, dto.getDescrip());
            ps.setInt(2, dto.getPrecio());
            ps.setInt(3, dto.getMarca().getId());
            ps.setInt(4, dto.getIdarticulo());
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
            Logger.getLogger(ArticulosDAO.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(ConexionBD.TR.CANCELAR);
            msj = "ERROR " + ex.getMessage();
            return false;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(ArticulosDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            query = "DELETE FROM  public.articulos WHERE id_art = ?;";
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
            Logger.getLogger(ArticulosDAO.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(ConexionBD.TR.CANCELAR);
            msj = "ERROR " + ex.getMessage();
            return false;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(ArticulosDAO.class.getName()).log(Level.SEVERE, null, ex);
                msj = "NO SE PUDO CERRAR EL STATEMEN " + ex.getMessage();
            }
        }
    }

    @Override
    public List<ArticulosDTO> consultarTodos() {
        try {
            List<ArticulosDTO> lista;
            ArticulosDTO dto;
            // PASO 1 - PREPARAR EL QUERY SQL
            //REGLAS
            // 1. Verificar la correcta escritura de la sintaxis ( nombre de tabla, nombres de columnas)
            query = "SELECT id_art, art_descri, art_precio,"
                    + " id_mar FROM public.articulos;";
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
                dto = new ArticulosDTO();
                dto.setIdarticulo(rs.getInt("id_art"));
                dto.setDescrip(rs.getString("art_descri"));
                dto.setPrecio(rs.getInt("art_precio"));
                 MarcaDTO marca = new MarcaDTO();
                marca.setId(rs.getInt("id_mar"));
                 dto.setMarca(marca);
                lista.add(dto);
            }
            return lista;

        } catch (SQLException ex) {
            Logger.getLogger(ArticulosDAO.class.getName()).log(Level.SEVERE, null, ex);
            msj = "ERROR " + ex.getMessage();
            return null;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(ArticulosDAO.class.getName()).log(Level.SEVERE, null, ex);
                msj = "NO SE PUDO CERRAR EL STATEMEN " + ex.getMessage();
            }
        }
    }

    @Override
    public ArticulosDTO consultarSegunID(Integer id) {
       try {
            ArticulosDTO dto = null;
            // PASO 1 - PREPARAR EL QUERY SQL
            //REGLAS
            // 1. Verificar la correcta escritura de la sintaxis ( nombre de tabla, nombres de columnas)
            query = "SELECT id_art, art_descri, art_precio, id_mar FROM public.articulos WHERE id_art = ? ;";
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
                 dto = new ArticulosDTO();
                dto.setIdarticulo(rs.getInt("id_art"));
                dto.setDescrip(rs.getString("art_descri"));
                dto.setPrecio(rs.getInt("art_precio"));
                 MarcaDTO marca = new MarcaDTO();
                marca.setId(rs.getInt("id_mar"));
                 dto.setMarca(marca);
            }
            return dto;

        } catch (SQLException ex) {
            Logger.getLogger(ArticulosDAO.class.getName()).log(Level.SEVERE, null, ex);
            msj = "ERROR " + ex.getMessage();
            return null;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(ArticulosDAO.class.getName()).log(Level.SEVERE, null, ex);
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
