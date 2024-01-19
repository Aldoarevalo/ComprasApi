package DTO;

public class MenuDto {
    private Integer id;
    private String  descrip;

    public MenuDto() {
    }

    public MenuDto(Integer id) {
        this.id = id;
    }
    public MenuDto(Integer id, String descrip) {
        this.id = id;
        this.descrip = descrip;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }
    
}
