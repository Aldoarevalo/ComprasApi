
package DTO;

import Genericos.BaseDTO;


public class AlmacenDTO extends BaseDTO {
    
    private Integer stockexistencia;
    private Integer stockexistenciaminima;
    private Integer stockexistenciamaxima;
    private ArticulosDTO articulo;
    private DepositoDTO deposito;

    public Integer getStockexistencia() {
        return stockexistencia;
    }

    public void setStockexistencia(Integer stockexistencia) {
        this.stockexistencia = stockexistencia;
    }

    public Integer getStockexistenciaminima() {
        return stockexistenciaminima;
    }

    public void setStockexistenciaminima(Integer stockexistenciaminima) {
        this.stockexistenciaminima = stockexistenciaminima;
    }

    public Integer getStockexistenciamaxima() {
        return stockexistenciamaxima;
    }

    public void setStockexistenciamaxima(Integer stockexistenciamaxima) {
        this.stockexistenciamaxima = stockexistenciamaxima;
    }

    public ArticulosDTO getArticulo() {
        return articulo;
    }

    public void setArticulo(ArticulosDTO articulo) {
        this.articulo = articulo;
    }

    public DepositoDTO getDeposito() {
        return deposito;
    }

    public void setDeposito(DepositoDTO deposito) {
        this.deposito = deposito;
    }

   
    
}
