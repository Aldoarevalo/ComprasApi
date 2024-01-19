package Pruebas;

import DAO.AlmacenDAO;
import DAO.ComprasDAO;
import DTO.ArticulosDTO;
import DTO.AlmacenDTO;
import DTO.ComprasDTO;
import DTO.DepositoDTO;
import DTO.EmpleadosDTO;
import DTO.ProveedoresDTO;
import DTO.detalle_comprasDTO;
import INT.AlmacenINT;
import INT.ComprasINT;
import java.sql.Date;

public class TestOperacionesSQL {

    enum OPERACIONES {
        AGREGAR, MODIFICAR, ELIMINAR, CONSULTAR_TODOS, CONSULTAR_SEGUN_ID
    };

    public TestOperacionesSQL(OPERACIONES op) {
        ComprasINT dao = new ComprasDAO();
        ComprasDTO dto = null;
         detalle_comprasDTO d = null;
     
        switch (op) {
            case AGREGAR:
                
              dto = new ComprasDTO();
              d = new detalle_comprasDTO();
              dto.setProveedor(new ProveedoresDTO(2));
              dto.setEmpleados(new EmpleadosDTO(2));
               dto.setComprafecha("2022-11-11");
               dto.setTipocompra("CRÉDITO");
              dto.setCompraestado("ACTIVO");
              dto.setNumerofactura((3505959));
              dto.setDeposito(new DepositoDTO(4));
               dto.setArticulo(new ArticulosDTO(3));
                dto.setCompracantidad(new detalle_comprasDTO(10));
                dto.setCompraprecio((6500000));
                if (dao.agregarRegistro(dto) == true) {
                    System.out.println(dao.getMsj() + " ya tengo 5 puntos logrados");
                } else {
                    System.out.println(dao.getMsj() + " ya perdi 5 puntos ");
                }
                break;
            
            
        case MODIFICAR:
                 dto = new ComprasDTO();
                 dto.setComcod((71));
                 dto.setProveedor(new ProveedoresDTO(1));
                 dto.setEmpleados(new EmpleadosDTO(3));
               dto.setComprafecha("2022-11-23");
               dto.setTipocompra("CONTADO");
              dto.setCompraestado("INACTIVO");
              dto.setNumerofactura((1505950));
              dto.setDeposito(new DepositoDTO(4));
               dto.setArticulo(new ArticulosDTO(1));
                dto.setCompracantidad(new detalle_comprasDTO(5));
                dto.setCompraprecio((5300000));
                
                if (dao.modificarRegistro(dto) == true) {
                    System.out.println(dao.getMsj() + " ya tengo 5 puntos logrados");
                } else {
                    System.out.println(dao.getMsj() + " ya perdi 5 puntos ");
                }
                break;        


            case CONSULTAR_TODOS:
                for (ComprasDTO f : dao.consultarTodos()) {
                    System.out.println("com_cod " + f.getComcod());
                    System.out.println("prv_cod " + f.getProveedor().getIdpro());
                     System.out.println("prv_razonsocial " + f.getProveedor().getRazonsocial());
                    System.out.println("id_emp " + f.getEmpleado().getIdemp());
                     System.out.println("emp_nombres " + f.getEmpleado().getNombres());
                    System.out.println("com_fecha " + f.getComprafecha());
                    System.out.println("tipo_compra " + f.getTipocompra());
                    System.out.println("com_estado " + f.getCompraestado());
                    System.out.println("numero_factura " + f.getNumerofactura());
                    System.out.println("com_cod " + f.getComcod());
                    System.out.println("id_dep " + f.getDeposito().getIddep());
                     System.out.println("dep_descri " + f.getDeposito().getDepdescri());
                    System.out.println("id_art " + f.getArticulo().getIdarticulo());
                     System.out.println("art_descri " + f.getArticulo().getDescrip());
                    System.out.println("com_precio " + f.getCompraprecio());
                    System.out.println("---------------------------------------------------");

                }
                break;

            case CONSULTAR_SEGUN_ID:
                ComprasDTO f = dao.consultarSegunID(13);
                System.out.println("com_cod " + f.getComcod());
                    System.out.println("prv_cod " + f.getProveedor().getIdpro());
                     System.out.println("prv_razonsocial " + f.getProveedor().getRazonsocial());
                    System.out.println("id_emp " + f.getEmpleado().getIdemp());
                     System.out.println("emp_nombres " + f.getEmpleado().getNombres());
                    System.out.println("com_fecha " + f.getComprafecha());
                    System.out.println("tipo_compra " + f.getTipocompra());
                    System.out.println("com_estado " + f.getCompraestado());
                    System.out.println("numero_factura " + f.getNumerofactura());
                    System.out.println("com_cod " + f.getComcod());
                    System.out.println("id_dep " + f.getDeposito().getIddep());
                     System.out.println("dep_descri " + f.getDeposito().getDepdescri());
                    System.out.println("id_art " + f.getArticulo().getIdarticulo());
                     System.out.println("art_descri " + f.getArticulo().getDescrip());
                    System.out.println("com_precio " + f.getCompraprecio());
                    System.out.println("---------------------------------------------------");
                break;

        }
    }

    public static void main(String[] args) {
        new TestOperacionesSQL(OPERACIONES.CONSULTAR_TODOS);

    }

}
