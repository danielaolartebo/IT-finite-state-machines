package model;

import java.util.Arrays;

/**
 *
 * @author Daniela Olarte Borja A00368359
 * @author Gabriel Suarez Baron A00368589
 *
 */

public class GenericArray<I> {
    private final Object[] obj_array;
    public final int length;

    public GenericArray(int length) {
        obj_array = new Object[length];
        this.length = length;
    }

    public I get(int i) {
        @SuppressWarnings("unchecked")
        final I e = (I) obj_array[i];
        return e;
    }

    public void set(int i, I e) {
        obj_array[i] = e;
    }

    @Override
    public String toString() {
        return Arrays.toString(obj_array);
    }
}
