package utils;

import java.util.List;

public class BinarySearch {

    public static <T extends Comparable<T>> int search(List<T> sortedList, T target) {
        int left = 0;
        int right = sortedList.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            T midVal = sortedList.get(mid);
            int cmp = midVal.compareTo(target);

            if (cmp == 0) {
                return mid;
            } else if (cmp < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}