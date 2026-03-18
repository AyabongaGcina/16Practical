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
