package DAO;

import DTO.TokenDTO;
import DTO.UsuarioWSDTO;
import Genericos.ConexionBD;
import Genericos.Util;
import INT.Token;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TokenDao implements Token {

    private ConexionBD conexion;
    private String query, msj, tokenGenerado;
    private ResultSet rs;
    private PreparedStatement ps;
    private TokenDTO token;

    public TokenDao() {
        conexion = new ConexionBD();
    }

    @Override
    public Boolean verificarToken(TokenDTO token) {
          try {
            query= "SELECT id FROM public.usuarios WHERE token = ? ;";
            ps= conexion.obtenerConexion().prepareStatement(query);
            ps.setString(1, token.getToken());
            rs=ps.executeQuery();
              
            if (rs.next()) {
               return true;   
              } else {
                msj= "Token no existe";
                return false;
              }
            
        } catch (SQLException ex) {
            msj= "No se pudo recuperar el registro " + ex.getMessage();
            return false;
        }
    }

    @Override
    public Boolean grabarToken(UsuarioWSDTO filtro) {
        try {
            conexion.Transaccion(ConexionBD.TR.INICIAR);
            tokenGenerado = Util.generarToken();
            System.out.println("Token generado " + tokenGenerado );
            if (tokenGenerado != null) {
                query = "UPDATE usuarios SET token = ? WHERE ip = ? AND usuario = ? AND clave= ? AND estado=TRUE;";
                ps = conexion.obtenerConexion().prepareStatement(query);
                ps.setString(1, tokenGenerado);
                ps.setString(2, filtro.getIp());
                ps.setString(3, filtro.getUsuario());
                ps.setString(4, filtro.getClave());
                if (ps.executeUpdate() > 0) {
                    conexion.Transaccion(ConexionBD.TR.CONFIRMAR);
                    token = new TokenDTO();
                    token.setToken(tokenGenerado);
                    return true;
                } else {
                    conexion.Transaccion(ConexionBD.TR.CANCELAR);
                    token = null;
                    return false;
                }
            }else{
                conexion.Transaccion(ConexionBD.TR.CANCELAR);
                token = null;
                return false;
            }
        } catch (SQLException ex) {
            conexion.Transaccion(ConexionBD.TR.CANCELAR);
            msj = "Error durante la inserción del registro " + ex.getMessage();
            token = null;
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
    public TokenDTO obtenerTokenGenerado() {
        if (token == null) {
            msj= "Error durante la generación del token";
        }
        return token;
    }

    @Override
    public String getMsj() {
        return msj;
    }

}
