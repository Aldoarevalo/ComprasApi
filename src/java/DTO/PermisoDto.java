package DTO;

public class PermisoDto {
    private MenuItemDto menuItem;
    private String comentario;

    public PermisoDto() {
    }

    public PermisoDto(MenuItemDto menuItem) {
        this.menuItem = menuItem;
    }

    public MenuItemDto getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItemDto menuItem) {
        this.menuItem = menuItem;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    
}
