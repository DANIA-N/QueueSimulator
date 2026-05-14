/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package queuesimulator;
import java.util.Scanner;
public class QueueSimulator {

    private int[] queue;
    private int front;
    private int rear;
    private int capacity;
    private int count; 

    

    /**
 * Initializes the queue simulator with a specified capacity.
 * @param size The maximum number of elements the queue can hold.
 */
    public QueueSimulator(int size) {
        capacity = size;
        queue = new int[capacity];
        front = 0;
        rear = -1;
        count = 0; 
    }

    

    /**
 * Adds a new integer to the rear of the queue.
 * Includes a check to prevent overflow if the queue is full.
 * @param value The integer to be added to the queue.
 */
    public void enqueue(int value) {
    if (count == capacity) {
        System.out.println("ERROR: Queue is full! Cannot enqueue " + value + ". Please dequeue an element first.");
        return;
    }

    rear = (rear + 1) % capacity;
    queue[rear] = value;
    count++;
    System.out.println(value + " added to the queue.");
}

    
    /**
     * Removes and returns the element at the front of the queue.
     * This method first checks for an "Underflow" condition to ensure 
     * the queue is not empty before attempting to remove an item.
     */
public void dequeue() {
    if (isEmpty()) {
        System.out.println("Queue is empty! Nothing to dequeue.");
        return;
    }

    int removedValue = queue[front];
    front = (front + 1) % capacity;
    count--;
    System.out.println(removedValue + " removed from the queue.");
}

   
    /**
     * Checks whether the queue is currently empty.
     * @return true if the queue contains no elements, false otherwise.
     */
    public boolean isEmpty() {
        return count == 0;
    }
    
   
/**
     * Returns the total number of elements currently stored in the queue.
     * @return the current size of the queue.
     */
    public int getSize(){
        return count;
    }
    
    // Returns the front element without removing it
    /**
     * Returns the front element of the queue without removing it.
     * This method allows the user to see the next item to be processed.
     * @return the value of the front element, or -1 if the queue is empty.
     */
    public int peek(){
    if (isEmpty()){
        System.out.println("Queue is empty! No front element to view");
        return -1;
    }
    // Removed unnecessary 'else' to reduce cyclomatic complexity
    return queue[front];
}

 
 /**
     * Displays all the elements currently in the queue in a user-friendly format.
     * If the queue is empty, it notifies the user.
     */
public void displayQueue() {
    if (isEmpty()) {
        System.out.println(">>> Queue Status: Empty");
        return;
    }
    System.out.print(">>> Queue Contents: [ ");
    for (int i = front; i <= rear; i++) {
        System.out.print(queue[i] + (i == rear ? "" : ", "));
    }
    System.out.println(" ]");
}

    // Main method
    // Extracted menu display logic to a separate method
public static void displayMenu() {
    System.out.println("\n===== Queue Operation Simulator =====");
    System.out.println("1. Enqueue");
    System.out.println("2. Dequeue");
    System.out.println("3. Display Queue");
    System.out.println("4. Check Queue Size");
    System.out.println("5. View Front Element (Peek)");
    System.out.println("6. Exit");
    System.out.print("Enter your choice: ");
}

public static void main(String[] args) {
    // try-with-resources to ensure scanner is closed automatically
    try (Scanner scan = new Scanner(System.in)) {
        QueueSimulator queue = new QueueSimulator(4);
        int choice;
        do {
            displayMenu(); // Cleaner main method
            choice = scan.nextInt();

            // Using enhanced switch (->) for better readability
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter value to enqueue: ");
                    int value = scan.nextInt();
                    queue.enqueue(value);
                }
                case 2 -> queue.dequeue();
                case 3 -> queue.displayQueue();
                case 4 -> System.out.println("Current size: " + queue.getSize());
                case 5 -> {
                    int frontElement = queue.peek();
                    if (frontElement != -1) System.out.println("Front: " + frontElement);
                }
                case 6 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 6);
    }
}

}