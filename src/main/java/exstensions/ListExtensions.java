package exstensions;

import java.util.ArrayList;
import java.util.List;

public class ListExtensions {
    public static <T> boolean contains(List<T> list, T element) {
        for (T el : list)
            if (element.equals(el))
                return true;
        return false;
    }

    public static <T> List<T> distinct(List<T> list) {
        List<T> newList = new ArrayList<>();
        for (T el : list)
            if (!contains(newList, el))
                newList.add(el);
        return newList;
    }

    public static <T> List<T> remove(List<T> list, T element) {
        List<T> newList = new ArrayList<>(list);
        for (T el : list)
            if (el.equals(element))
                newList.remove(el);
        return newList;
    }
}
