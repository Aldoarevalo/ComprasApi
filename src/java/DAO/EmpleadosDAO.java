package DAO;

import Conexion.ConexionBD;
import DTO.EmpleadosDTO;
import INT.EmpleadosINT;
import DTO.CargoDTO;
import DTO.NacionalidadDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmpleadosDAO implements EmpleadosINT {

    private ConexionBD conexion;
    private String query, msj;
    private PreparedStatement ps;
    private ResultSet rs;

    public EmpleadosDAO() {
        conexion = new ConexionBD();
    }

    @Override
    public Boolean agregarRegistro(EmpleadosDTO dto) {
        try {
            //// PASO 1 - PREPARAR TRANSACCION 
            conexion.Transaccion(ConexionBD.TR.INICIAR);
            // PASO 2 - PREPARAR EL QUERY SQL
            //REGLAS
            // 1. Verificar la correcta escritura de la sintaxis ( nombre de tabla, nombres de columnas)
            // 2. Verificar la cantidad de columnas vs los simbolos que representan a los valores parametricos ( ? ) . "Misma cantidad de columnas es igual a misma cantidad de parametros"
            query = "INSERT INTO public.empleados( emp_nombres, emp_apellidos, emp_fecha_nac, emp_salario, emp_telefono, emp_direccion, emp_sexo,car_codigo, nac_codigo) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            // PASO 3 - UTILIZAR EL OBJETO CONEXION PARA PREPARAR EL STATEMENT
            //REGLA 
            // ENCAPSULAR LAS SINTAXIS EN UN BLOQUE TRY-CATCH
            ps = conexion.obtenerConexion().prepareStatement(query);
            // PASO 4 - SET DE VALORES A LOS PARAMETROS INDICADOS EN EL PASO 1
            // REGLAS
            //1. RESPETAR EL TIPO DE DATOS PARA EL SET CORRESPONDIENTE ( EJEMPLO. nro_documento - INTEGER = setInt ; nombres STRING = SETSTRING )
            //2. RESPETAR EL INDICE DE LA COLUMNA SEGUN LA UBICACION DEL PASO 1
            // ps.setInt(1, dto.getIdemp());
            ps.setString(1, dto.getNombres());
            ps.setString(2, dto.getApellidos());
            ps.setDate(3, dto.getFechaNacimiento());
            ps.setInt(4, dto.getSalario());
            ps.setInt(5, dto.getTelefono());
            ps.setString(6, dto.getDireccion());
            ps.setString(7, dto.getSexo());
            ps.setInt(8, dto.getCargo().getId());
            ps.setInt(9, dto.getNacionalidad().getId());
       
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
            Logger.getLogger(EmpleadosDAO.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(ConexionBD.TR.CANCELAR);
            msj = "ERROR " + ex.getMessage();
            return false;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmpleadosDAO.class.getName()).log(Level.SEVERE, null, ex);
                msj = "NO SE PUDO CERRAR EL STATEMEN " + ex.getMessage();
            }
        }

    }

    @Override
    public Boolean modificarRegistro(EmpleadosDTO dto) {

        try {
            //// PASO 1 - PREPARAR TRANSACCION 
            conexion.Transaccion(ConexionBD.TR.INICIAR);
            // PASO 2 - PREPARAR EL QUERY SQL
            //REGLAS
            // 1. Verificar la correcta escritura de la sintaxis ( nombre de tabla, nombres de columnas)
            // 2. Verificar la cantidad de columnas vs los simbolos que representan a los valores parametricos ( ? ) . "Misma cantidad de columnas es igual a misma cantidad de parametros"
            query = "UPDATE public.empleados SET emp_nombres = ?, emp_apellidos = ?, emp_fecha_nac = ?, emp_salario = ? , emp_telefono = ? , emp_direccion = ?, emp_sexo = ? "
                    + " car_codigo = ? , nac_codigo = ? "
                    + " WHERE id_emp = ?;";
            // PASO 3 - UTILIZAR EL OBJETO CONEXION PARA PREPARAR EL STATEMENT
            //REGLA 
            // ENCAPSULAR LAS SINTAXIS EN UN BLOQUE TRY-CATCH
            ps = conexion.obtenerConexion().prepareStatement(query);
            // PASO 4 - SET DE VALORES A LOS PARAMETROS INDICADOS EN EL PASO 1
            // REGLAS
            //1. RESPETAR EL TIPO DE DATOS PARA EL SET CORRESPONDIENTE ( EJEMPLO. nro_documento - INTEGER = setInt ; nombres STRING = SETSTRING )
            //2. RESPETAR EL INDICE DE LA COLUMNA SEGUN LA UBICACION DEL PASO 1
            ps.setString(1, dto.getNombres());
            ps.setString(2, dto.getApellidos());
            ps.setDate(3, dto.getFechaNacimiento());
            ps.setInt(4, dto.getSalario());
            ps.setInt(5, dto.getTelefono());
            ps.setString(6, dto.getDireccion());
            ps.setString(7, dto.getSexo());
            ps.setInt(8, dto.getCargo().getId());
            ps.setInt(9, dto.getNacionalidad().getId());
            ps.setInt(5, dto.getIdemp());
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
            Logger.getLogger(EmpleadosDAO.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(ConexionBD.TR.CANCELAR);
            msj = "ERROR " + ex.getMessage();
            return false;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmpleadosDAO.class.getName()).log(Level.SEVERE, null, ex);
                msj = "NO SE PUDO CERRAR EL STATEMEN " + ex.getMessage();
            }
        }
    }

    @Override
    public Boolean eliminarRegistro(Integer idemp) {

        try {
            //// PASO 1 - PREPARAR TRANSACCION 
            conexion.Transaccion(ConexionBD.TR.INICIAR);
            // PASO 2 - PREPARAR EL QUERY SQL
            //REGLAS
            // 1. Verificar la correcta escritura de la sintaxis ( nombre de tabla, nombres de columnas)
            // 2. Verificar la cantidad de columnas vs los simbolos que representan a los valores parametricos ( ? ) . "Misma cantidad de columnas es igual a misma cantidad de parametros"
            query = "DELETE FROM  public.empleados WHERE id_emp = ?;";
            // PASO 3 - UTILIZAR EL OBJETO CONEXION PARA PREPARAR EL STATEMENT
            //REGLA 
            // ENCAPSULAR LAS SINTAXIS EN UN BLOQUE TRY-CATCH
            ps = conexion.obtenerConexion().prepareStatement(query);
            // PASO 4 - SET DE VALORES A LOS PARAMETROS INDICADOS EN EL PASO 1
            // REGLAS
            //1. RESPETAR EL TIPO DE DATOS PARA EL SET CORRESPONDIENTE ( EJEMPLO. nro_documento - INTEGER = setInt ; nombres STRING = SETSTRING )
            //2. RESPETAR EL INDICE DE LA COLUMNA SEGUN LA UBICACION DEL PASO 1
            ps.setInt(1, idemp);
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
            Logger.getLogger(EmpleadosDAO.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(ConexionBD.TR.CANCELAR);
            msj = "ERROR " + ex.getMessage();
            return false;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmpleadosDAO.class.getName()).log(Level.SEVERE, null, ex);
                msj = "NO SE PUDO CERRAR EL STATEMEN " + ex.getMessage();
            }
        }
    }

    @Override
    public List<EmpleadosDTO> consultarTodos() {
        try {
            List<EmpleadosDTO> lista;
            EmpleadosDTO dto;
            // PASO 1 - PREPARAR EL QUERY SQL
            //REGLAS
            // 1. Verificar la correcta escritura de la sintaxis ( nombre de tabla, nombres de columnas)
            query = "SELECT F.id_emp, F.emp_nombres,F.emp_apellidos,F.emp_fecha_nac,F.emp_salario,F.emp_telefono,F.emp_direccion,F.emp_sexo,F.car_codigo, P.car_descri, F.nac_codigo,C.nac_descri\n"
                    + "  FROM public.empleados F INNER JOIN public.cargos P ON F.car_codigo=P.car_codigo\n"
                    + "		                    INNER JOIN nacionalidad C ON F.nac_codigo=C.nac_codigo ;";
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
                dto = new EmpleadosDTO();
                dto.setIdemp(rs.getInt("id_emp"));
                dto.setNombres(rs.getString("emp_nombres"));
                dto.setApellidos(rs.getString("emp_apellidos"));
                dto.setFechaNacimiento(rs.getDate("emp_fecha_nac"));
                dto.setSalario(rs.getInt("emp_salario"));
                dto.setTelefono(rs.getInt("emp_telefono"));
                dto.setDireccion(rs.getString("emp_direccion"));
                dto.setSexo(rs.getString("emp_sexo"));
                CargoDTO cargo = new CargoDTO();
                cargo.setId(rs.getInt("car_codigo"));
                cargo.setDescrip(rs.getString("car_descri"));
                dto.setCargo(cargo);
                /////////////////////////////////
                NacionalidadDTO nacionalidad = new NacionalidadDTO();
                nacionalidad.setId(rs.getInt("nac_codigo"));
                nacionalidad.setDescrip(rs.getString("nac_descri"));
                dto.setNacionalidad(nacionalidad);
         
                lista.add(dto);
            }
            return lista;

        } catch (SQLException ex) {
            Logger.getLogger(EmpleadosDAO.class.getName()).log(Level.SEVERE, null, ex);
            msj = "ERROR " + ex.getMessage();
            return null;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmpleadosDAO.class.getName()).log(Level.SEVERE, null, ex);
                msj = "NO SE PUDO CERRAR EL STATEMEN " + ex.getMessage();
            }
        }

    }

    @Override
    public EmpleadosDTO consultarSegunID(Integer id) {
         try {
            EmpleadosDTO dto = null;
            // PASO 1 - PREPARAR EL QUERY SQL
            //REGLAS
            // 1. Verificar la correcta escritura de la sintaxis ( nombre de tabla, nombres de columnas)
            query = "SELECT F.id_emp, F.emp_nombres,F.emp_apellidos,F.emp_fecha_nac,F.emp_salario,F.emp_telefono,F.emp_direccion,F.emp_sexo,F.car_codigo, P.car_descri, F.nac_codigo,C.nac_descri\n"
                    + "  FROM public.empleados F INNER JOIN public.cargos P ON F.car_codigo=P.car_codigo\n"
                    + "		                    INNER JOIN nacionalidad C ON F.nac_codigo=C.nac_codigo "
                    + "WHERE F.id_emp = ? ;";
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
              dto = new EmpleadosDTO();
                dto.setIdemp(rs.getInt("id_emp"));
                dto.setNombres(rs.getString("emp_nombres"));
                dto.setApellidos(rs.getString("emp_apellidos"));
                dto.setFechaNacimiento(rs.getDate("emp_fecha_nac"));
                dto.setSalario(rs.getInt("emp_salario"));
                dto.setTelefono(rs.getInt("emp_telefono"));
                dto.setDireccion(rs.getString("emp_direccion"));
                dto.setSexo(rs.getString("emp_sexo"));
                CargoDTO cargo = new CargoDTO();
                cargo.setId(rs.getInt("car_codigo"));
                cargo.setDescrip(rs.getString("car_descri"));
                dto.setCargo(cargo);
                ///////
                NacionalidadDTO nacionalidad = new NacionalidadDTO();
                nacionalidad.setId(rs.getInt("nac_codigo"));
                cargo.setDescrip(rs.getString("nac_descri"));
                dto.setNacionalidad(nacionalidad);
                /////////////////////////////////
               
            }
            return dto;

        } catch (SQLException ex) {
            Logger.getLogger(EmpleadosDAO.class.getName()).log(Level.SEVERE, null, ex);
            msj = "ERROR " + ex.getMessage();
            return null;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmpleadosDAO.class.getName()).log(Level.SEVERE, null, ex);
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
