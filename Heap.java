// Ayabonga Gcina
// 4446494
// Practical 6 - Heap implementation for heapsort
// Consulted DeepSeek for Heapify Logic

public class Heap {
    private String[] heapArray;
    private int currentSize;

    public Heap(int capacity) {
        heapArray = new String[capacity];
        currentSize = 0;
    }

    // ---------- Bottom-up construction ----------
    public void buildUp(String[] inputArray) {
        // Copy array into heap
        for (int i = 0; i < inputArray.length; i++) {
            heapArray[i] = inputArray[i];
        }
        currentSize = inputArray.length;

        // Heapify from last parent down to root
        for (int i = (currentSize / 2) - 1; i >= 0; i--) {
            heapify(i, currentSize);
        }
    }

    // ---------- Top-down construction via inserts ----------
    public void insert(String value) {
        if (currentSize >= heapArray.length) {
            throw new IllegalStateException("Heap is full");
        }
        heapArray[currentSize] = value;
        swim(currentSize);
        currentSize++;
    }

    // ---------- Heap operations ----------
    private void heapify(int index, int heapSize) {
        int largest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if (left < heapSize && heapArray[left].compareTo(heapArray[largest]) > 0) {
            largest = left;
        }
        if (right < heapSize && heapArray[right].compareTo(heapArray[largest]) > 0) {
            largest = right;
        }

        if (largest != index) {
            swap(index, largest);
            heapify(largest, heapSize);
        }
    }

    private void swim(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heapArray[index].compareTo(heapArray[parent]) > 0) {
                swap(index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        String temp = heapArray[i];
        heapArray[i] = heapArray[j];
        heapArray[j] = temp;
    }

    // ---------- Sorting ----------
    public String[] sort() {
        String[] workingHeap = heapArray.clone(); // Work entirely on the copy
        int heapSize = currentSize;
        String[] sorted = new String[currentSize];

        // Heapsort: repeatedly remove max
        for (int i = 0; i < sorted.length; i++) {
            // Swap root with last element in workingHeap
            String temp = workingHeap[0];
            workingHeap[0] = workingHeap[heapSize - 1];
            workingHeap[heapSize - 1] = temp;

            // Save max element
            sorted[i] = workingHeap[heapSize - 1];
            heapSize--;

            // Heapify the copied heap
            heapifyOnCopy(workingHeap, 0, heapSize);
        }

        // Reverse to get ascending order
        for (int i = 0; i < sorted.length / 2; i++) {
            String temp = sorted[i];
            sorted[i] = sorted[sorted.length - 1 - i];
            sorted[sorted.length - 1 - i] = temp;
        }

        return sorted;
    }

    // Helper method to heapify on copied array
    private void heapifyOnCopy(String[] heap, int index, int heapSize) {
        int largest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if (left < heapSize && heap[left].compareTo(heap[largest]) > 0) {
            largest = left;
        }
        if (right < heapSize && heap[right].compareTo(heap[largest]) > 0) {
            largest = right;
        }

        if (largest != index) {
            String temp = heap[index];
            heap[index] = heap[largest];
            heap[largest] = temp;

            heapifyOnCopy(heap, largest, heapSize);
        }
    }

    // ---------- Getters ----------
    public int size() {
        return currentSize;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }
}