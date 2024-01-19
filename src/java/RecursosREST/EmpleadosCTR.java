package RecursosREST;

import DAO.EmpleadosDAO;
import DTO.EmpleadosDTO;
import INT.EmpleadosINT;
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

@Path("empleado")
public class EmpleadosCTR {

    private EmpleadosDTO dto;
    private EmpleadosDAO dao;
      private FuncionarioINT daoFuncionario;
    private EmpleadosINT daoempleado;

    @GET
    public void saludoInicial() {
        System.out.println("Saludo desde REST TEST Empleado");
    }

    @GET
    @Path("consultar")
    @Produces(MediaType.APPLICATION_JSON)
    public String consultarRegistros() {
//     dao = new PersonaDAO();
//     dao.consultarTodos();
        daoempleado = new EmpleadosDAO();
       
        //new Gson().toJson(new PerfilDao().seleccionarTodos());
        return new Gson().toJson(daoempleado.consultarTodos());
    }

    @GET
    @Path("unico")
    @Produces(MediaType.APPLICATION_JSON)
    public String consultarRegistro(@QueryParam("id") String id) {
//     dao = new PersonaDAO();
//     dao.consultarTodos();
        daoempleado = new EmpleadosDAO();

        //new Gson().toJson(new PerfilDao().seleccionarTodos());
        return new Gson().toJson(daoempleado.consultarSegunID(Integer.parseInt(id)));
    }

    @POST
    @Path("agregar")
    public String agregar(InputStream i) {
        //

        daoempleado = new EmpleadosDAO();
        if (dao.agregarRegistro(dto)) {
            return "OK";
        } else {
            return "ERROR";
        }

    }

    @PUT
    public String modificar(InputStream i) {
        daoempleado = new EmpleadosDAO();
        if (dao.modificarRegistro(dto)) {
            return "OK";
        } else {
            return "ERROR";
        }
    }

    @DELETE
    public String delete(@QueryParam("id") String id) {
        daoempleado = new EmpleadosDAO();
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
