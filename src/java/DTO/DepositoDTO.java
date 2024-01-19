
package DTO;


import Genericos.BaseDTO;

public class DepositoDTO extends BaseDTO{
   private Integer iddep;
   private String  depdescri;
   
   
    public DepositoDTO() {
        
    }
    
    
    
     public DepositoDTO(Integer iddep,String  depdescri) {
        this.iddep = iddep;
        this.depdescri = depdescri;
    }
    
    public DepositoDTO(Integer id) {
        super.setId(id);
        
    }
    
   
     public Integer getIddep() {
        return iddep;
    }

    public void setIddep(Integer iddep) {
        this.iddep = iddep;
    }
      
      public String getDepdescri() {
        return depdescri;
    }

    public void setDepdescri(String depdescri) {
        this.depdescri = depdescri;
    }
    
}

