//Consulted DeepSeek for Heapify Logic
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
        for (int index = 0; index < inputArray.length; index++) {
            heapArray[index] = inputArray[index];
        }
        currentSize = inputArray.length;

        // Heapify from last parent down to root
        for (int index = (currentSize / 2) - 1; index >= 0; index--) {
            heapify(index, currentSize);
        }
    }
     // ---------- Top-down construction via inserts ----------
    public void insert(String newValue) {
        if (currentSize >= heapArray.length) {
            throw new IllegalStateException("Heap is full");
        }
        heapArray[currentSize] = newValue;
        swim(currentSize);
        currentSize++;
    }
      // ---------- Heap operations ----------
    private void heapify(int rootIndex, int heapLimit) {
        int largestIndex = rootIndex;
        int leftChildIndex = 2 * rootIndex + 1;
        int rightChildIndex = 2 * rootIndex + 2;

        if (leftChildIndex < heapLimit &&
            heapArray[leftChildIndex].compareTo(heapArray[largestIndex]) > 0) {
            largestIndex = leftChildIndex;
        }

        if (rightChildIndex < heapLimit &&
            heapArray[rightChildIndex].compareTo(heapArray[largestIndex]) > 0) {
            largestIndex = rightChildIndex;
        }

        if (largestIndex != rootIndex) {
            swap(rootIndex, largestIndex);
            heapify(largestIndex, heapLimit);
        }
    }

    private void swim(int childIndex) {
        while (childIndex > 0) {
            int parentIndex = (childIndex - 1) / 2;

            if (heapArray[childIndex].compareTo(heapArray[parentIndex]) > 0) {
                swap(childIndex, parentIndex);
                childIndex = parentIndex;
            } else {
                break;
            }
        }
    }
