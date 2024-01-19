package DTO;

public class MenuItemDto {
    private Integer id;
    private String  descrip;
    private MenuDto menu;

    public MenuItemDto() {
    }

    public MenuItemDto(Integer id) {
        this.id = id;
    }
    
    public MenuItemDto(Integer id, String descrip) {
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

    public MenuDto getMenu() {
        return menu;
    }

    public void setMenu(MenuDto menu) {
        this.menu = menu;
    }
    
    
    
    
}
