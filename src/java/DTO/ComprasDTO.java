package DTO;

import Genericos.BaseDTO;
import java.sql.Date;
import java.util.List;

public class ComprasDTO extends BaseDTO {

    private Integer comcod;
    private ProveedoresDTO prvcod;
    private EmpleadosDTO idemp;
    private Integer idemps;
    private String comfecha;
    private String tipocompra;
    private String comestado;
    private Integer numfactura;
    private List<detalle_comprasDTO> listaProductosdeposito;
    private DepositoDTO iddep;
    private ArticulosDTO idarticulo;
    private Integer comprecio;
    private detalle_comprasDTO comcant;

    public Integer getComcod() {
        return comcod;
    }

    public void setComcod(Integer comcod) {
        this.comcod = comcod;
    }

    public ProveedoresDTO getProveedor() {
        return prvcod;
    }

    public void setProveedor(ProveedoresDTO prvcod) {
        this.prvcod = prvcod;
    }

    public EmpleadosDTO getEmpleado() {
        return idemp;
    }

    public void setEmpleados(EmpleadosDTO idemp) {
        this.idemp = idemp;
    }
    
       public Integer getEmpleadoss() {
        return idemps;
    }

    public void setEmpleadosss(Integer idemp) {
        this.idemps = idemps;
    }

    public String getComprafecha() {
        return comfecha;
    }

    public void setComprafecha(String comfecha) {
        this.comfecha = comfecha;
    }

    public String getTipocompra() {
        return tipocompra;
    }

    public void setTipocompra(String tipocompra) {
        this.tipocompra = tipocompra;
    }

    public String getCompraestado() {
        return comestado;
    }

    public void setCompraestado(String comestado) {
        this.comestado = comestado;
    }

    public Integer getNumerofactura() {
        return numfactura;
    }

    public void setNumerofactura(Integer numfactura) {
        this.numfactura = numfactura;
    }

    public List<detalle_comprasDTO> getListaProductosdeposito() {
        return listaProductosdeposito;
    }

    public void setListaProductosdeposito(List<detalle_comprasDTO> listaProductosdeposito) {
        this.listaProductosdeposito = listaProductosdeposito;
    }

    public DepositoDTO getDeposito() {
        return iddep;
    }

    public void setDeposito(DepositoDTO iddep) {
        this.iddep = iddep;
    }
    
   

    public ArticulosDTO getArticulo() {
        return idarticulo;
    }

    public void setArticulo(ArticulosDTO idarticulo) {
        this.idarticulo = idarticulo;
    }

    public Integer getCompraprecio() {
        return comprecio;
    }

    public void setCompraprecio(Integer comprecio) {
        this.comprecio = comprecio;
    }

    public detalle_comprasDTO getCompracantidad() {
        return comcant;
    }

    public void setCompracantidad(detalle_comprasDTO comcant) {
        this.comcant = comcant;
    }
}
