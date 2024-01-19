package RecursosREST;

import DAO.ComprasDAO;
import DAO.PerfilesDAO;
import DAO.TokenDao;
import DTO.ComprasDTO;
import DTO.PerfilesDTO;
import DTO.RespuestaDTO;
import DTO.TokenDTO;
import DTO.UsuarioWSDTO;
import Genericos.Respuesta;
import static Genericos.Respuesta.RESPUESTA;
import Genericos.Util;
import INT.Token;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("transferencia")
public class TransferenciaWS {

    private ComprasDAO dao;
    private ComprasDTO dto;
    private UsuarioWSDTO usuarioWSDto;
    private Token token;
    private Gson gsonSerializador;
    private BufferedReader br;
    private RespuestaDTO respuestaDto;

    public TransferenciaWS() {
        respuestaDto = new RespuestaDTO();
    }

    
    @GET
     @Path("consultar")
    @Produces(MediaType.APPLICATION_JSON)
      public String getJson(@QueryParam("ip") String ip, @QueryParam("user") String user, @QueryParam("clave") String clave) {
       // return new Gson().toJson(new PerfilesDAO().seleccionarTodos());
        
           usuarioWSDto = new UsuarioWSDTO();
        usuarioWSDto.setIp(ip);
        usuarioWSDto.setUsuario(user);
        usuarioWSDto.setClave(clave);
        token = new TokenDao();
        if (token.grabarToken(usuarioWSDto)) {
            dao = new ComprasDAO();
           // dto = (CiudadDto) dao.seleccionarTodos();
//            dto.setToken(token.obtenerTokenGenerado().getToken());
           // return new Gson().toJson(dto);
             return new Gson().toJson(dao.consultarTodos());
        } else {
            return null;
        }

    }

    
    
    @GET
    @Path("unico")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@QueryParam("id") String id, @QueryParam("ip") String ip, @QueryParam("user") String user, @QueryParam("clave") String clave) {
        System.out.println("Valor obtenido " + id);
        System.out.println("Valor obtenido " + ip);
        System.out.println("Valor obtenido " + user);
        System.out.println("Valor obtenido " + clave);
        usuarioWSDto = new UsuarioWSDTO();
        usuarioWSDto.setIp(ip);
        usuarioWSDto.setUsuario(user);
        usuarioWSDto.setClave(clave);
        token = new TokenDao();
        if (token.grabarToken(usuarioWSDto)) {
            dao = new ComprasDAO();
            dto = dao.consultarSegunID(Integer.parseInt(id));
           // dto.SE(token.obtenerTokenGenerado().getToken());
            return new Gson().toJson(dto);
        } else {
            return null;
        }

    }

//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    //@Produces(MediaType.APPLICATION_JSON) 
//    public void crear(InputStream i) {
//        if (respuestaDto == null) {
//            respuestaDto =  new RespuestaDTO();
//        }
//        gsonSerializador = new Gson();
//        System.out.println("Json " + Util.getJson(i));
//        dto = gsonSerializador.fromJson(Util.getJson(i), CiudadDto.class);
//        
//        System.out.println("nombre " + dto.getNombre());
//        System.out.println("comentario " + dto.getComentario());
//        System.out.println("token " + dto.getToken());
//        
//        if (dto.getToken() != null) {
//            token = new TokenDao();
//            TokenDTO tokenDto = new TokenDTO();
//            tokenDto.setToken(dto.getToken());
//            if (token.verificarToken(tokenDto)) {
//                dao = new CiudadDao();
//                if (dao.agregar(dto)) {
//                    respuestaDto.setId(4);
//                    respuestaDto.setRespuesta("Inserción exitosa");
//                } else {
//                    respuestaDto.setId(3);
//                    respuestaDto.setRespuesta("Error durante la inserción del registro");
//                }
//            } else {
//                respuestaDto.setId(2);
//                respuestaDto.setRespuesta("Token no válido");
//            }
//        } else {
//            respuestaDto.setId(1);
//            respuestaDto.setRespuesta("No se pudo obtener el Token");
//        }
//
//    }
//
//    @POST
//    @Path("update")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON) 
//    public String update(InputStream i) {
//         if (respuestaDto == null) {
//            respuestaDto =  new RespuestaDTO();
//        }
//        gsonSerializador = new Gson();
//        dto = gsonSerializador.fromJson(Util.getJson(i), CiudadDto.class);
//        if (dto.getToken() != null) {
//            token = new TokenDao();
//            TokenDTO tokenDto = new TokenDTO();
//            tokenDto.setToken(dto.getToken());
//            if (token.verificarToken(tokenDto)) {
//                dao = new CiudadDao();
//                if (dao.modificar(dto)) {
//                    respuestaDto.setRespuesta(RESPUESTA.getOperacionExitosa());
//                } else {
//                    respuestaDto.setRespuesta(RESPUESTA.getOperacionErronea());
//                }
//            } else {
//                System.out.println("respuesta token " + token.getMsj());
//                respuestaDto.setRespuesta(RESPUESTA.getTokenIncorrecto());
//            }
//        } else {
//            respuestaDto.setRespuesta(RESPUESTA.getTokenNoLocalizado());
//        }
//         return new Gson().toJson(respuestaDto);
//    }
//    
}
