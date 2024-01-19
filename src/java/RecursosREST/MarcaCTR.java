package RecursosREST;

import DAO.MarcaDAO;
import DTO.MarcaDTO;
import INT.MarcaINT;
import INT.FuncionarioINT;
import com.google.gson.Gson;
import java.io.InputStream;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("marca")
public class MarcaCTR {

    private MarcaDTO dto;
    private MarcaDAO dao;
    private FuncionarioINT daoFuncionario;
    private MarcaINT daomarca;

    @GET
    public void saludoInicial() {
        System.out.println("Saludo desde REST TEST Marca");
    }

    @GET
    @Path("consultar")
    @Produces(MediaType.APPLICATION_JSON)
    public String consultarRegistros() {
//     dao = new PersonaDAO();
//     dao.consultarTodos();
        daomarca = new MarcaDAO();

        //new Gson().toJson(new PerfilDao().seleccionarTodos());
        return new Gson().toJson(daomarca.consultarTodos());
    }

    @GET
    @Path("unico")
    @Produces(MediaType.APPLICATION_JSON)
    public String consultarRegistro(@QueryParam("id") String id) {
//     dao = new PersonaDAO();
//     dao.consultarTodos();
        daomarca = new MarcaDAO();

        //new Gson().toJson(new PerfilDao().seleccionarTodos());
        return new Gson().toJson(daomarca.consultarSegunID(Integer.parseInt(id)));
    }

    @POST
    @Path("agregar")
    public String agregar(InputStream i) {
        //

        daomarca = new MarcaDAO();
        if (dao.agregarRegistro(dto)) {
            return "OK";
        } else {
            return "ERROR";
        }

    }

    @PUT
    public String modificar(InputStream i) {
        daomarca = new MarcaDAO();
        if (dao.modificarRegistro(dto)) {
            return "OK";
        } else {
            return "ERROR";
        }
    }

    @DELETE
    public String delete(@QueryParam("id") String id) {
        daomarca = new MarcaDAO();
        if (dao.eliminarRegistro(Integer.parseInt(id))) {
            return "OK";
        } else {
            return "ERROR";
        }
    }

    /*
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String consultarTodos() {
        return new Gson().toJson(new PerfilDao().seleccionarTodos());
    }
     */
}
