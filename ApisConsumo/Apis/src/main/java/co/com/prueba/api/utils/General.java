package co.com.prueba.api.utils;

public enum  General {
    URL_BASE("https://gorest.co.in/public/v1"),
    ACCES_TOKEN("778885261f225d0762fa420baa5893891dd268a3690828c12bc517199b5f2ea3"),
    CREAR("/users"),
    CONSULTAR_USUARIOS("/posts"),
    COMENTARIO("/comments"),
    CONSULTAR_TODOS("/todos");

    private String uri;
    General(String uri) {this.uri = uri;}

    @Override
    public String toString() {return uri; }
}
