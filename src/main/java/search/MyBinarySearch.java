package search;

import java.util.List;

public class MyBinarySearch<T>{
    private final List<? extends Comparable<? super T>> list;
    private final T search;

    public MyBinarySearch(List<? extends Comparable<? super T>> list, T search) {
        this.list=list;
        this.search=search;
    }

    public int getIndexedBinarySearch() {
        int low = 0;
        int high = list.size()-1;

        while (low <= high) {
            int middle = (low + high) >>> 1;
            Comparable<? super T> midValue = list.get(middle);
            int tmp = midValue.compareTo(search);

            if (tmp < 0)
                low = middle + 1;
            else if (tmp > 0)
                high = middle - 1;
            else
                return middle; // found
        }
        return -(low + 1);  //not found
    }
}

