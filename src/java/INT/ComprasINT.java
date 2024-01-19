package INT;

import DTO.detalle_comprasDTO;
import DTO.ComprasDTO;
import Genericos.OperacionesSQL;
import java.util.List;

public interface ComprasINT extends OperacionesSQL<ComprasDTO>{
    public Boolean anularPedido(Integer id);
    public Boolean agregarDetaPedido(Integer idVenta,List<detalle_comprasDTO> listaPedido);
}
