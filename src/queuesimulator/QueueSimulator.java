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

    // Constructor
    public QueueSimulator(int size) {
        capacity = size;
        queue = new int[capacity];
        front = 0;
        rear = -1;
        count = 0; 
    }

    // Enqueue operation
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

    // Dequeue operation
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

    // Check if queue is empty
    public boolean isEmpty() {
        return count == 0;
    }
    
    //check current queue size
    public int getSize(){
        return count;
    }
    
    //view front without removing it
    public int peek(){
        if (isEmpty()){
            System.out.println("Queue is empty! no front element to view");
            return -1;
        }
        else
            return queue[front];
    }

 // Display queue contents in a user-friendly format 
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
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        QueueSimulator queue = new QueueSimulator(4);

        int choice;

        do {
            System.out.println("\n===== Queue Operation Simulator =====");
            System.out.println("1. Enqueue");
            System.out.println("2. Dequeue");
            System.out.println("3. Display Queue");
            System.out.println("4. check queue size");
            System.out.println("5. view front element (peek)");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = scan.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter value to enqueue: ");
                    int value = scan.nextInt();
                    queue.enqueue(value);
                    break;

                case 2:
                    queue.dequeue();
                    break;

                case 3:
                    queue.displayQueue();
                    break;
                    
                case 4:
                    System.out.println("current queue size is" + queue.getSize());
                    break;
                    
                case 5:
                    int frontElement = queue.peek();
                    if (frontElement != -1){
                        System.out.println("the front element is: " + frontElement);
                    }
                    break;

                case 6:
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 6);

        scan.close();
    }
}