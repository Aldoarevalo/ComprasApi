
package DTO;
public class ArticulosDTO {
    
    private Integer idarticulo;
    private String  articulodescrip;
     private Integer  articuloprecio;
      private MarcaDTO marca;
      
        public ArticulosDTO() {
    }
       public ArticulosDTO(Integer idarticulo,String  articulodescrip) {
        this.idarticulo = idarticulo;
        this.articulodescrip = articulodescrip;
    }
    public ArticulosDTO(Integer idarticulo) {
        this.idarticulo = idarticulo;
    }

    public Integer getIdarticulo() {
        return idarticulo;
    }

    public void setIdarticulo(Integer idarticulo) {
        this.idarticulo = idarticulo;
    }
      
      public String getDescrip() {
        return articulodescrip;
    }

    public void setDescrip(String articulodescri) {
        this.articulodescrip = articulodescri;
    }
    
      public Integer getPrecio() {
        return articuloprecio;
    }

    public void setPrecio(Integer articuloprecio) {
        this.articuloprecio = articuloprecio;
    }
        public MarcaDTO getMarca() {
        return marca;
    }

    public void setMarca(MarcaDTO marca) {
        this.marca = marca;
    }
}
