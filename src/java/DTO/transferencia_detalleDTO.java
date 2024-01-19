
package DTO;
public class transferencia_detalleDTO {
  
   
       private ComprasDTO comcodi;
       private Integer comcod;
       private DepositoDTO iddep;
       private ArticulosDTO idarticulo;
       private Integer comcant;
       private Integer comprecio;
      
        public transferencia_detalleDTO() {
    }
    
     public Integer getComcod() {
        return comcod;
    }

    public void setComcod(Integer comcod) {
        this.comcod = comcod;
    }
        
        
     public ComprasDTO getCompras() {
        return comcodi;
    }
   
      public void setCompras(ComprasDTO comcodi) {
        this.comcodi = comcodi;
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

   
        public Integer getCompracantidad() {
        return comcant;
    }

    public void setCompracantidad(Integer comcant) {
        this.comcant = comcant;
    }
      
        public Integer getCompraprecio() {
        return comprecio;
    }

    public void setCompraprecio(Integer comprecio) {
        this.comprecio = comprecio;
    }
    
 public transferencia_detalleDTO(Integer comcant) {
        this.comcant = comcant;
    }
   
}
