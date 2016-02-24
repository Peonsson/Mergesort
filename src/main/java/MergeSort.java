import java.util.ArrayList;
import java.util.List;

/**
 * Created by Peonsson on 24/02/16.
 */
public class MergeSort {
    public static <E> ArrayList<E> sort(ArrayList<E> myList) {

        if(myList.size() > 1) {
            ArrayList<E> left = (ArrayList) myList.subList(0, myList.size()/2);
            ArrayList<E> right = (ArrayList)  myList.subList(myList.size()/2 + 1, myList.size());

            sort(left);
            sort(right);

            if(left.get(0) > right.get(0))

        }
    }
}
