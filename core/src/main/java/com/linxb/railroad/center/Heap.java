package com.linxb.railroad.center;

import com.linxb.railroad.center.utils.AssertUtil;

public class Heap<T extends Comparable> {
   public enum Sort {
        /**
         * from max to min.
         */
        DESC,
        /**
         * from min to max.
         */
        ASC
    }

    public void buildHeap(T[] array, Sort sort) {
        if (array == null || array.length == 1)
            return;

        switch (sort) {
            case DESC:
                buildMinHeap(array);
                break;

            case ASC:
                buildMaxHeap(array);
                break;

            default:
        }
    }

    public void sort(T[] array, Sort sort) {
        buildHeap(array, sort);

        for (int i = array.length - 1; i >= 1; i--) {
            // swap values of array[0] and array[i].
            // after swapping, value of  tail element will be max or min.
            swap(array, 0, i);
            switch (sort) {
                case DESC:
                    minHeap(array, i, 0);
                    break;

                case ASC:
                    maxHeap(array, i, 0);
                    break;
            }

        }
    }

    private void buildMaxHeap(T[] array) {
        if (array == null || array.length == 1)
            return;

        //  int root = 0, int left = 2*i+1, int right = 2*i+2;
        int cursor = array.length / 2;
        for (int i = cursor; i >= 0; i--) {
            maxHeap(array, array.length, i);
        }
    }

    private void maxHeap(T[] array, int heapSize, int index) {
        int left = index * 2 + 1; // left node
        int right = index * 2 + 2; // right node
        int max = index; // max value.

        T tm = array[max];
        AssertUtil.checkNull(tm);
        // if left value large than max value.

        if (left < heapSize ) {
            T tl = array[left];
            AssertUtil.checkNull(tl);
            if(tl.compareTo(tm) > 0){
                max = left;
                tm = tl;
            }
        }

        // if right value large than max value.
        if (right < heapSize ) {
            T tr = array[right];
            AssertUtil.checkNull(tr);
            if(tr.compareTo(tm) > 0){
                max = right;
                tm = tr;
            }
        }

        // has change index.
        if (max != index) {
            swap(array, index, max); // swap values of root and child.
            // check buildHeap.
            maxHeap(array, heapSize, max);
        }
    }

    private void buildMinHeap(T[] array) {
        if (array == null || array.length == 1)
            return;

        // int root = 0, int left = 2*i+1, int right = 2*i+2;
        int cursor = array.length / 2;
        for (int i = cursor; i >= 0; i--) {
            minHeap(array, array.length, i);
        }
    }

    private void minHeap(T[] array, int heapSize, int index) {
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        int min = index;

        T tm = array[min];
        AssertUtil.checkNull(tm);


        if(left < heapSize){
            T tl = array[left];
            AssertUtil.checkNull(tl);
            if(tl.compareTo(tm) < 0){
                min = left;
                tm = tl;
            }
        }

        if (right < heapSize ) {
            T tr = array[right];
            AssertUtil.checkNull(tr);
            if(tr.compareTo(tm) < 0){
                min = right;
                tm = tr;
            }
        }

        if (min != index) {
            swap(array, index, min);
            minHeap(array, heapSize, min);
        }
    }

    private void swap(T[] array, int index1, int index2) {
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

}
