package co.com.prueba.api.builders;

public interface Builder<T> {
    default T build() {
        return null;
    }
}
