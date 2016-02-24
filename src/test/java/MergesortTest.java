import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Peonsson on 24/02/16.
 */
public class MergesortTest {

    @Test
    public void testMergeSortSort() throws Exception {
        ArrayList<Integer> myList = new ArrayList<Integer>();
        myList.add(5);
        myList.add(6);
        myList.add(3);
        myList.add(1);
        myList.add(9);
        myList.add(7);
        myList.add(2);
        myList.add(1);
        myList.add(11);
        myList.add(5);
        ArrayList<Integer> sortedList = MergeSort.sort(myList);

        boolean isSorted = true;

        for (int i = 1; i < sortedList.size(); i++) {
            if(sortedList.get(i - 1) > sortedList.get(i))
                isSorted = false;
        }

        assert(isSorted);
    }
}