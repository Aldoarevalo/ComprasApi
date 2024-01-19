package Genericos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    public enum TR {
        INICIAR, CONFIRMAR, CANCELAR
    };
    private Connection conexion;
    private String msj;
    
    public ConexionBD() {
        if (conexion == null) {
            establecerConexion(TipoMotorBD.POSTGRESQL);
        }
    }
    
    private void establecerConexion(TipoMotorBD tipoMotorBD){
          String url = "jdbc:" + tipoMotorBD.getMotorBD() + "://" + tipoMotorBD.getHost()
                + "/" + tipoMotorBD.getNombreBD();
        try {
            Class.forName(tipoMotorBD.getControlador());
            conexion = DriverManager.getConnection(url, tipoMotorBD.getUsuarioBD(),
                    tipoMotorBD.getClaveBD());
            if (conexion == null) {
                msj = " CONEXION NO ESTABLECIDA - Parametros de conexión no válidos ";
            } else {
                msj = " CONEXION ESTABLECIDA ";  
            }
            System.out.println("Aviso " + msj);
        } catch (ClassNotFoundException | SQLException e) {
            msj= "Error durante la conexión a la base de datos " + e.getMessage();
            System.out.println("Error "  + msj);
        }
    }

    public Connection obtenerConexion(){
        if (conexion == null) {
            establecerConexion(TipoMotorBD.POSTGRESQL);
        }
        return conexion;
    }
    
    public void Transaccion(TR accion) {
        try {
            switch (accion) {
                case INICIAR:
                    conexion.setTransactionIsolation(
                            Connection.TRANSACTION_SERIALIZABLE);
                    conexion.setAutoCommit(false);
                    break;
                case CONFIRMAR:
                    conexion.commit();
                    conexion.setAutoCommit(true);
                    break;
                case CANCELAR:
                    conexion.rollback();
                    conexion.setAutoCommit(true);
                    break;
            }
        } catch (SQLException ex) {
            //msg = "Error al establecer estado de transacciones.";
        }
    }
    
    public void cerrarConexion() {
        try {
            conexion.close();
        } catch (SQLException ex) {
            msj="No se pudo cerrar la conexion " + ex.getMessage();
        }
    }

    public String getMsj() {
        return msj;
    }
    
    

}
