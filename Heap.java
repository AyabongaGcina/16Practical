//Consulted DeepSeek for Heapify Logic
public class Heap {
    private String[] heapArray;
    private int currentSize;

    public Heap(int capacity) {
        heapArray = new String[capacity];
        currentSize = 0;
    }