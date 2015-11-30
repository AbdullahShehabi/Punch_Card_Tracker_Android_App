package com.xerorex.buvit;

/**
 * Created by Aatif Shah on 11/24/2015.
 */
public class QuickSort<AnyType> {

    private static int CUTOFF = 4;

    public static <AnyType extends Comparable<? super AnyType>> void quicksort(AnyType[] a) {
        quicksort(a, 0, a.length - 1);
    }

    private static <AnyType extends Comparable<? super AnyType>> void quicksort(AnyType[] a, int left, int right) {

        if (left + CUTOFF <= right) {
            AnyType pivot = median3(a, left, right);

            // Begin partitioning
            int i = left, j = right - 1;
            for (; ; ) {
                while (a[++i].compareTo(pivot) < 0) {
                }
                while (a[--j].compareTo(pivot) > 0) {
                }
                if (i < j)
                    swapReferences(a, i, j);
                else
                    break;
            }

            swapReferences(a, i, right - 1); // Restore pivot

            quicksort(a, left, i - 1); // Sort small elements
            quicksort(a, i + 1, right); // Sort large elements
        } else
            // Do an insertion sort on the subarray
            insertionSort(a, left, right);
    }

    private static <AnyType extends Comparable<? super AnyType>> AnyType median3(AnyType[] a, int left, int right) {

        int center = (left + right) / 2;
        if (a[center].compareTo(a[left]) < 0)
            swapReferences(a, left, center);
        if (a[right].compareTo(a[left]) < 0)
            swapReferences(a, left, right);
        if (a[right].compareTo(a[center]) < 0)
            swapReferences(a, center, right);

        // Place pivot at position right - 1
        swapReferences(a, center, right - 1);
        return a[right - 1];
    }

    public static <AnyType> void swapReferences(AnyType[] a, int i, int j) {
        AnyType temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static <AnyType extends Comparable<? super AnyType>> void insertionSort(AnyType[] a, int left, int right) {
        int size = a.length - left;

        for (int outerLoopIdx = 1; outerLoopIdx < size; ++outerLoopIdx) {
            for (int innerLoopIdx = outerLoopIdx; innerLoopIdx > 0; --innerLoopIdx) {
                if (a[innerLoopIdx - 1].compareTo(a[innerLoopIdx]) > 0) {
                    AnyType temp = a[innerLoopIdx - 1];
                    a[innerLoopIdx - 1] = a[innerLoopIdx];
                    a[innerLoopIdx] = temp;
                }
            }
        }
    }

}