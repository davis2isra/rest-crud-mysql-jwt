package net.guides.springrest.springrestcrudsqlserverexample.model;

public class AppUserAuth {

    private String userName;
    private String bearerToken;
    private boolean isAuthenticated;

    private boolean canListar;
    private boolean canAgregar;
    private boolean canEditar;
    private boolean canBorrar;
    private boolean canVerDetalle;

    public AppUserAuth() {
        this.userName = "No autorizado";
        bearerToken = "";
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBearerToken() {
        return bearerToken;
    }

    public void setBearerToken(String bearerToken) {
        this.bearerToken = bearerToken;
    }

    public boolean isIsAuthenticated() {
        return isAuthenticated;
    }

    public void setIsAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }

    public boolean isCanListar() {
        return canListar;
    }

    public void setCanListar(boolean canListar) {
        this.canListar = canListar;
    }

    public boolean isCanAgregar() {
        return canAgregar;
    }

    public void setCanAgregar(boolean canAgregar) {
        this.canAgregar = canAgregar;
    }

    public boolean isCanEditar() {
        return canEditar;
    }

    public void setCanEditar(boolean canEditar) {
        this.canEditar = canEditar;
    }

    public boolean isCanBorrar() {
        return canBorrar;
    }

    public void setCanBorrar(boolean canBorrar) {
        this.canBorrar = canBorrar;
    }

    public boolean isCanVerDetalle() {
        return canVerDetalle;
    }

    public void setCanVerDetalle(boolean canVerDetalle) {
        this.canVerDetalle = canVerDetalle;
    }
}
