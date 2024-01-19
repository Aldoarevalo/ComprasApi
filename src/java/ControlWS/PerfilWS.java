package ControlWS;

import DAO.PerfilesDAO;
import DTO.PerfilesDTO;
import Genericos.Util;
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
import javax.ws.rs.core.MediaType;

@Path("perfil")
public class PerfilWS {
    private PerfilesDAO dao;
    private PerfilesDTO dto;
    private Gson gsonSerializador;
    private BufferedReader br;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String consultarTodos(){
        return new Gson().toJson(new PerfilesDAO().seleccionarTodos());
        
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
   // @Produces(MediaType.APPLICATION_JSON) 
    public void crear(InputStream i) {
        try {
            String json = "";
            gsonSerializador=new Gson();
            br = new BufferedReader(new InputStreamReader(i));
            if (br.ready()) {
                json = br.readLine();
            }
            System.out.println("Json " + json);
            
            dto = gsonSerializador.fromJson(json, PerfilesDTO.class);
            dao = new PerfilesDAO();
            System.out.println("V1 " + dto.getDescrip());
            System.out.println("V2 " + dto.getComentario());
            //dao.agregar(dto);
            br.close();
        } catch (IOException ex) {
        }
    }
    
    @POST
    @Path("aux")
    @Consumes(MediaType.APPLICATION_JSON)
   // @Produces(MediaType.APPLICATION_JSON) 
    public void crearAux(InputStream i) {
        try {
            gsonSerializador=new Gson();
            dto = gsonSerializador.fromJson(Util.getJson(i), PerfilesDTO.class);
            dao = new PerfilesDAO();
            System.out.println("V1 " + dto.getDescrip());
            System.out.println("V2 " + dto.getComentario());
            //dao.agregar(dto);
            br.close();
        } catch (IOException ex) {
        }
    }
}
