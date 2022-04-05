package exstensions;

import java.util.List;

public class ListExtensions {
    public static <T> boolean contains(List<T> list,T element){
        for(T el: list){
            if(element.equals(el))
                return true;
        }
        return false;
    }
}
