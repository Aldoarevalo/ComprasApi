package DAO;

import Conexion.ConexionBD;
import DTO.DepositoDTO;
import DTO.ComprasDTO;
import DTO.ArticulosDTO;
import DTO.EmpleadosDTO;
import DTO.ProveedoresDTO;
import DTO.detalle_comprasDTO;
import INT.ComprasINT;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransferenciaDAO implements ComprasINT {

    private ConexionBD conexion;
    private String query, msj;
    private PreparedStatement ps;
    private ResultSet rs;

    public TransferenciaDAO() {
        conexion = new ConexionBD();
    }

    @Override
    public Boolean agregarRegistro(ComprasDTO dto) {
        try {
            //// PASO 1 - PREPARAR TRANSACCION 
            conexion.Transaccion(ConexionBD.TR.INICIAR);
            // PASO 2 - PREPARAR EL QUERY SQL
            //REGLAS
            // 1. Verificar la correcta escritura de la sintaxis ( nombre de tabla, nombres de columnas)
            // 2. Verificar la cantidad de columnas vs los simbolos que representan a los valores parametricos ( ? ) . "Misma cantidad de columnas es igual a misma cantidad de parametros"
            query = "INSERT INTO public.compras(prv_cod, id_emp, com_fecha, tipo_compra, com_estado, numero_factura) VALUES (?, ?, ?, ?, ?, ? );";
            // PASO 3 - UTILIZAR EL OBJETO CONEXION PARA PREPARAR EL STATEMENT
            //REGLA 
            // ENCAPSULAR LAS SINTAXIS EN UN BLOQUE TRY-CATCH
            ps = conexion.obtenerConexion().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            // PASO 4 - SET DE VALORES A LOS PARAMETROS INDICADOS EN EL PASO 1
            // REGLAS
            //1. RESPETAR EL TIPO DE DATOS PARA EL SET CORRESPONDIENTE ( EJEMPLO. nro_documento - INTEGER = setInt ; nombres STRING = SETSTRING )
            //2. RESPETAR EL INDICE DE LA COLUMNA SEGUN LA UBICACION DEL PASO 1
            ps.setInt(1, dto.getProveedor().getIdpro());
            ps.setInt(2, dto.getEmpleado().getIdemp());
            ps.setString(3, dto.getComprafecha());
            ps.setString(4, dto.getTipocompra());
            ps.setString(5, dto.getCompraestado());
            ps.setInt(6, dto.getNumerofactura());
            // PASO 5 - EJECUCION DEL STATEMENT Y VERIFICACION DE LA OPERACION
            // REGLA 
            //1. VERIFICAR LA CANTIDAD DE FILAS RESULTANTES DEL ps.executeUpdate() > 0 = OPERACION CORRECTA SI NO ERROR
            //2. SEGUN RESULTADO DE LA VERIFICACION conexion.Transaccion(ConexionBD.TR.CONFIRMAR); O conexion.Transaccion(ConexionBD.TR.CANCELAR);

            if (ps.executeUpdate() > 0) {
                // EXITOSA
                rs = ps.getGeneratedKeys();
                ResultSet result = ps.getGeneratedKeys();
                if (result.next()) {
                    int idDevuelto = rs.getInt(1);
                    System.out.println(idDevuelto);

                    idDevuelto = rs.getInt("com_cod");
                    //for (detalle_comprasDTO d : dto.getListaProductosdeposito()) {

                    query = "INSERT INTO public.detalle_compras(com_cod, id_dep, id_art, com_cant, com_precio)VALUES (?, ?, ?, ?, ?);";
                    ps = conexion.obtenerConexion().prepareStatement(query);
                    ps.setInt(1, idDevuelto);
                    ps.setInt(2, dto.getDeposito().getId());
                    ps.setInt(3, dto.getArticulo().getIdarticulo());
                    ps.setInt(4, dto.getCompracantidad().getCompracantidad());
                    ps.setInt(5, dto.getCompraprecio());

                    if (ps.executeUpdate() <= 0) {
                        // ERROR DURANTE LA OPERACION
                        // conexion.Transaccion(ConexionBD.TR.CANCELAR);
                        msj = "OPERACION ERRONEA";
                        return true;
                    }
                    // }
                    ///////
                    if (rs.next()) {
                        idDevuelto = rs.getInt("com_cod");
                        System.out.println(idDevuelto);
                        //prepara el bloque para la insercion del/os detalles

                    }
                    /////

                }
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
            Logger.getLogger(TransferenciaDAO.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(ConexionBD.TR.CANCELAR);
            msj = "ERROR " + ex.getMessage();
            return false;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(TransferenciaDAO.class.getName()).log(Level.SEVERE, null, ex);
                msj = "NO SE PUDO CERRAR EL STATEMEN " + ex.getMessage();
            }
        }
    }

    @Override
    public Boolean modificarRegistro(ComprasDTO dto) {
        try {
            //// PASO 1 - PREPARAR TRANSACCION 
            conexion.Transaccion(ConexionBD.TR.INICIAR);
            // PASO 2 - PREPARAR EL QUERY SQL
            //REGLAS
            // 1. Verificar la correcta escritura de la sintaxis ( nombre de tabla, nombres de columnas)
            // 2. Verificar la cantidad de columnas vs los simbolos que representan a los valores parametricos ( ? ) . "Misma cantidad de columnas es igual a misma cantidad de parametros"
            query = "UPDATE public.compras SET prv_cod = ?,id_emp = ?, com_fecha= ?, tipo_compra = ?, com_estado = ?, numero_factura = ?  WHERE com_cod = ? ;";
            // PASO 3 - UTILIZAR EL OBJETO CONEXION PARA PREPARAR EL STATEMENT
            //REGLA 
            // ENCAPSULAR LAS SINTAXIS EN UN BLOQUE TRY-CATCH
            ps = conexion.obtenerConexion().prepareStatement(query);
            
            // PASO 4 - SET DE VALORES A LOS PARAMETROS INDICADOS EN EL PASO 1
            // REGLAS
            //1. RESPETAR EL TIPO DE DATOS PARA EL SET CORRESPONDIENTE ( EJEMPLO. nro_documento - INTEGER = setInt ; nombres STRING = SETSTRING )
            //2. RESPETAR EL INDICE DE LA COLUMNA SEGUN LA UBICACION DEL PASO 1
            ps.setInt(1, dto.getProveedor().getIdpro());
            ps.setInt(2, dto.getEmpleado().getIdemp());
            ps.setString(3, dto.getComprafecha());
            ps.setString(4, dto.getTipocompra());
            ps.setString(5, dto.getCompraestado());
            ps.setInt(6, dto.getNumerofactura());
            ps.setInt(7, dto.getComcod());
            // PASO 5 - EJECUCION DEL STATEMENT Y VERIFICACION DE LA OPERACION
            // REGLA 
            //1. VERIFICAR LA CANTIDAD DE FILAS RESULTANTES DEL ps.executeUpdate() > 0 = OPERACION CORRECTA SI NO ERROR
            //2. SEGUN RESULTADO DE LA VERIFICACION conexion.Transaccion(ConexionBD.TR.CONFIRMAR); O conexion.Transaccion(ConexionBD.TR.CANCELAR);
     
                if (ps.executeUpdate() <= 0) {
                    // ERROR DURANTE LA OPERACION
                    
                    msj = "OPERACION ERRONEA";
                    return false;
                }
             // ps = conexion.obtenerConexion().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);    
            if (ps.executeUpdate() > 0) {
                // EXITOSA
            
              
                 
                  query = "UPDATE public.detalle_compras SET id_art = ?,id_dep = ?, com_cant= ?, com_precio = ?  WHERE com_cod = ? ;";
       
                    //query = "INSERT INTO public.detalle_compras(com_cod, id_dep,id_art, com_cant, com_precio)VALUES ( ?, ?, ?, ?, ?);";
               
               // ps.setInt(1, comcod);
                ps.setInt(1, dto.getProveedor().getIdpro());
                ps.setInt(2, dto.getArticulo().getIdarticulo());
                ps.setInt(3, dto.getCompracantidad().getCompracantidad());
                ps.setInt(4, dto.getCompraprecio());
                ps.setInt(5, dto.getComcod());
                
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
            Logger.getLogger(TransferenciaDAO.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(ConexionBD.TR.CANCELAR);
            msj = "ERROR " + ex.getMessage();
            return false;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(TransferenciaDAO.class.getName()).log(Level.SEVERE, null, ex);
                msj = "NO SE PUDO CERRAR EL STATEMEN " + ex.getMessage();
            }
        }
    }
    
  
    @Override
    public Boolean eliminarRegistro(Integer id) {
        try {
            //// PASO 1 - PREPARAR TRANSACCION 
            // PASO 2 - PREPARAR EL QUERY SQL
            //REGLAS
            // 1. Verificar la correcta escritura de la sintaxis ( nombre de tabla, nombres de columnas)
            // 2. Verificar la cantidad de columnas vs los simbolos que representan a los valores parametricos ( ? ) . "Misma cantidad de columnas es igual a misma cantidad de parametros"
            query = "DELETE FROM  public.detalle_compras WHERE com_cod = ?;";

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
                msj = "OPERACION EXITOSA";
                return true;
            } else {
                // ERROR DURANTE LA OPERACION
                msj = "OPERACION ERRONEA";
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(TransferenciaDAO.class.getName()).log(Level.SEVERE, null, ex);
            msj = "ERROR " + ex.getMessage();
            return false;
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(TransferenciaDAO.class.getName()).log(Level.SEVERE, null, ex);
                msj = "NO SE PUDO CERRAR EL STATEMEN " + ex.getMessage();
            }
        }
    }

    @Override
    public Boolean agregarDetaPedido(Integer comcod, List<detalle_comprasDTO> listacompras) {
        try {
            //prepara el bloque para la insercion del/os detalles
           // for (detalle_comprasDTO d : listacompras) {
                query = "INSERT INTO public.detalle_compras(com_cod, id_dep,id_art, com_cant, com_precio)VALUES ( ?, ?, ?, ?, ?);";
                ps = conexion.obtenerConexion().prepareStatement(query);
                ps.setInt(1, comcod);
//                ps.setInt(2, d.getDeposito().getIddep());
//                ps.setInt(3, d.getArticulo().getIdarticulo());
//                ps.setInt(4, d.getCompracantidad());
//                ps.setInt(5, d.getCompraprecio());
                if (ps.executeUpdate() <= 0) {
                    // ERROR DURANTE LA OPERACION
                    msj = "OPERACION ERRONEA";
                    return false;
                }
          //  }

            // EXITOSA
            msj = "OPERACION EXITOSA";
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(TransferenciaDAO.class.getName()).log(Level.SEVERE, null, ex);
            msj = "ERROR " + ex.getMessage();
            return false;
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(TransferenciaDAO.class.getName()).log(Level.SEVERE, null, ex);
                msj = "NO SE PUDO CERRAR EL STATEMEN " + ex.getMessage();
            }
        }
    }

    @Override
    public List<ComprasDTO> consultarTodos() {
        try {
            List<ComprasDTO> lista;
            ComprasDTO dto;
            // PASO 1 - PREPARAR EL QUERY SQL
            //REGLAS
            // 1. Verificar la correcta escritura de la sintaxis ( nombre de tabla, nombres de columnas)
            query = " SELECT a.com_cod, a.prv_cod, c.prv_razonsocial, a.id_emp, d.emp_nombres, a.com_fecha,a.tipo_compra,a.com_estado,a.numero_factura, b.com_cod,b.id_dep,dep.dep_descri, b.id_art,e.art_descri,b.com_precio\n"
                    + "FROM public.compras a inner join public.detalle_compras b on b.com_cod=a.com_cod\n"
                    + "		            inner join public.proveedores c on a.prv_cod = c.prv_cod and a.com_estado = 'ACTIVO' \n"
                    + "		            inner join public.empleados d on a.id_emp = d.id_emp \n"
                    + "		            inner join public.articulos e on e.id_art = b.id_art\n"
                    + "                       inner join public.deposito dep on b.id_dep = dep.id_dep\n"
                    + "where a.com_estado = 'ACTIVO' ;";
            // PASO 2 - UTILIZAR EL OBJETO CONEXION PARA PREPARAR EL STATEMENT
            //REGLA 
            // ENCAPSULAR LAS SINTAXIS EN UN BLOQUE TRY-CATCH
            ps = conexion.obtenerConexion().prepareStatement(query);

            // PASO 3 - EJECUCION DEL STATEMENT Y VERIFICACION DE LA OPERACION
            // REGLA 
            //1. VERIFICAR LA CANTIDAD DE FILAS RESULTANTES DEL ps.executeUpdate() > 0 = OPERACION CORRECTA SI NO ERROR
            rs = ps.executeQuery();
            lista = new ArrayList<>();
            if (rs.next()) {
                // EXITOSA
                dto = new ComprasDTO();
                dto.setComcod(rs.getInt("com_cod"));
                //dto.setComprafecha(rs.getDate("fecha"));
                dto.setProveedor(new ProveedoresDTO(rs.getInt("prv_cod"), rs.getString("prv_razonsocial")));
                dto.setEmpleados(new EmpleadosDTO(rs.getInt("id_emp"), rs.getString("emp_nombres")));
                dto.setComprafecha(rs.getString("com_fecha"));
                dto.setTipocompra(rs.getString("tipo_compra"));
                dto.setCompraestado(rs.getString("com_estado"));
                dto.setNumerofactura(rs.getInt("numero_factura"));
                dto.setDeposito(new DepositoDTO(rs.getInt("id_dep"), rs.getString("dep_descri")));
                dto.setArticulo(new ArticulosDTO(rs.getInt("id_art"), rs.getString("art_descri")));
                dto.setCompraprecio(rs.getInt("com_precio"));
                /* rs.first();
                List<detalle_comprasDTO> detalle= new ArrayList<>();
                while (rs.next()) {
                    detalle_comprasDTO d=new detalle_comprasDTO();
                    d.setComcod(rs.getInt("com_cod"));
                    d.setDeposito(new DepositoDTO(rs.getInt("id_dep"), rs.getString("dep_descri")));
                    d.setArticulo(new ArticulosDTO(rs.getInt("id_art"), rs.getString("art_descri")));
                    d.setCompracantidad(rs.getInt("com_cant"));
                    d.setCompraprecio(rs.getInt("com_precio"));
                    detalle.add(d);
                }
                dto.setListaProductosdeposito(detalle);*/

                lista.add(dto);
            }
            return lista;

        } catch (SQLException ex) {
            Logger.getLogger(TransferenciaDAO.class.getName()).log(Level.SEVERE, null, ex);
            msj = "ERROR " + ex.getMessage();
            return null;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(TransferenciaDAO.class.getName()).log(Level.SEVERE, null, ex);
                msj = "NO SE PUDO CERRAR EL STATEMEN " + ex.getMessage();
            }
        }
    }

    @Override
    public ComprasDTO consultarSegunID(Integer id) {
        try {
            ComprasDTO dto = null;
            // PASO 1 - PREPARAR EL QUERY SQL
            //REGLAS
            // 1. Verificar la correcta escritura de la sintaxis ( nombre de tabla, nombres de columnas)
            query = " SELECT a.com_cod, a.prv_cod, c.prv_razonsocial, a.id_emp, d.emp_nombres, a.com_fecha,a.tipo_compra,a.com_estado,a.numero_factura, b.com_cod,b.id_dep,dep.dep_descri, b.id_art,e.art_descri,b.com_precio\n"
                    + "FROM public.compras a inner join public.detalle_compras b on b.com_cod=a.com_cod\n"
                    + "		            inner join public.proveedores c on a.prv_cod = c.prv_cod and a.com_estado = 'ACTIVO' \n"
                    + "		            inner join public.empleados d on a.id_emp = d.id_emp \n"
                    + "		            inner join public.articulos e on e.id_art = b.id_art\n"
                    + "                       inner join public.deposito dep on b.id_dep = dep.id_dep\n"
                    + "where a.com_estado = 'ACTIVO' and a.com_cod= ? ;";
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
                dto = new ComprasDTO();
                dto.setComcod(rs.getInt("com_cod"));
                //dto.setComprafecha(rs.getDate("fecha"));
                dto.setProveedor(new ProveedoresDTO(rs.getInt("prv_cod"), rs.getString("prv_razonsocial")));
                dto.setEmpleados(new EmpleadosDTO(rs.getInt("id_emp"), rs.getString("emp_nombres")));
                dto.setComprafecha(rs.getString("com_fecha"));
                dto.setTipocompra(rs.getString("tipo_compra"));
                dto.setCompraestado(rs.getString("com_estado"));
                dto.setNumerofactura(rs.getInt("numero_factura"));
                dto.setDeposito(new DepositoDTO(rs.getInt("id_dep"), rs.getString("dep_descri")));
                dto.setArticulo(new ArticulosDTO(rs.getInt("id_art"), rs.getString("art_descri")));
                dto.setCompraprecio(rs.getInt("com_precio"));

                /* rs.first();
                List<detalle_comprasDTO> detalle= new ArrayList<>();
                while (rs.next()) {
                    detalle_comprasDTO d=new detalle_comprasDTO();
                      d.setComcod(rs.getInt("id_detalle"));
                    d.setDeposito(new DepositoDTO(rs.getInt("id_dep"), rs.getString("dep_descri")));
                    d.setArticulo(new ArticulosDTO(rs.getInt("id_art"), rs.getString("art_descri")));
                    d.setCompracantidad(rs.getInt("com_cant"));
                    d.setCompraprecio(rs.getInt("com_precio"));
                    detalle.add(d);
                }
                dto.setListaProductosdeposito(detalle);*/
            }
            return dto;

        } catch (SQLException ex) {
            Logger.getLogger(TransferenciaDAO.class.getName()).log(Level.SEVERE, null, ex);
            msj = "ERROR " + ex.getMessage();
            return null;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(TransferenciaDAO.class.getName()).log(Level.SEVERE, null, ex);
                msj = "NO SE PUDO CERRAR EL STATEMEN " + ex.getMessage();
            }
        }
    }

    @Override
    public String getMsj() {
        return msj;
    }

    @Override
    public Boolean anularPedido(Integer id) {
        try {
            //// PASO 1 - PREPARAR TRANSACCION 
            conexion.Transaccion(ConexionBD.TR.INICIAR);
            // PASO 2 - PREPARAR EL QUERY SQL
            //REGLAS
            // 1. Verificar la correcta escritura de la sintaxis ( nombre de tabla, nombres de columnas)
            // 2. Verificar la cantidad de columnas vs los simbolos que representan a los valores parametricos ( ? ) . "Misma cantidad de columnas es igual a misma cantidad de parametros"
            query = "UPDATE public.compras SET com_estado =  ? WHERE com_cod = ?;";
            // PASO 3 - UTILIZAR EL OBJETO CONEXION PARA PREPARAR EL STATEMENT
            //REGLA 
            // ENCAPSULAR LAS SINTAXIS EN UN BLOQUE TRY-CATCH
            ps = conexion.obtenerConexion().prepareStatement(query);
            // PASO 4 - SET DE VALORES A LOS PARAMETROS INDICADOS EN EL PASO 1
            // REGLAS
            //1. RESPETAR EL TIPO DE DATOS PARA EL SET CORRESPONDIENTE ( EJEMPLO. nro_documento - INTEGER = setInt ; nombres STRING = SETSTRING )
            //2. RESPETAR EL INDICE DE LA COLUMNA SEGUN LA UBICACION DEL PASO 1
            ps.setBoolean(1, Boolean.FALSE);
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
            Logger.getLogger(TransferenciaDAO.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(ConexionBD.TR.CANCELAR);
            msj = "ERROR " + ex.getMessage();
            return false;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(TransferenciaDAO.class.getName()).log(Level.SEVERE, null, ex);
                msj = "NO SE PUDO CERRAR EL STATEMEN " + ex.getMessage();
            }
        }
    }

    @Override
    public Boolean eliminarRegistros(Integer Idart, Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
