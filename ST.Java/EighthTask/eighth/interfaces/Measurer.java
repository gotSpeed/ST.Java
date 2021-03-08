package eighth.interfaces;


@FunctionalInterface
public interface Measurer<T, CT> {

    T repeater(T a, CT b);

}
