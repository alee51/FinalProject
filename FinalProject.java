import java.util.*;
public class FinalProject {
  //main method
  public static void main(String[] args) {
    ArrayList<Task> taskList = new ArrayList<Task>();
    Scanner in = new Scanner(System.in);
    while (true) {
      System.out.println("Please choose an option from the menu below:");
      System.out.println("[1] Add a task to the to-do list");
      System.out.println("[2] Encrypt the current to-do list");
      System.out.println("[3] Decrypt the current to-do list");
      System.out.println("[4] Sort by alphabetical order");
      System.out.println("[5] Sort by deadline");
      System.out.println("[6] Quit");
      int input = in.nextInt();
      
      //obtains user input, creates a new task, and adds it to the list
      if (input == 1) {
        Scanner in2 = new Scanner(System.in);
        System.out.print("Please enter the name of the task: ");
        String name = in2.nextLine();
        System.out.print("Please enter the deadline for this task (number of days until due date): ");
        int deadline = in2.nextInt();
        Task t = new Task(name, deadline);
        taskList.add(t);
        printList(taskList);
      }
      
      else if(input == 2) {
        modify(taskList, 1);
      }
      else if(input == 3) {
        modify(taskList, -1);
      }
      else if (input == 4) {
        sortName(taskList);
      }
      else if (input == 5) {
        sortDeadline(taskList);
      }
      else if(input == 6) {
        break;
      }
      else {
        System.out.println("That is not a valid option. Please try again.");
      }
    }
  }
  
  //prints the list of tasks in a table
  public static void printList(ArrayList<Task> taskList) {
    System.out.println();
    System.out.printf("%-30s %s %n", "Task", "Deadline");
    System.out.println("------------------------------------------");
    for (Task t:taskList) {
      System.out.println(t);
    }
    System.out.println();
  }
  
  //direction = 1: encrypts tasks;
  //direction = -1: decrypts tasks
  public static void modify(ArrayList<Task> taskList, int direction) {
    String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String lowercase = "abcdefghijklmnopqrstuvwxyz";
    int shift;
    Scanner in = new Scanner(System.in);
    while (true) {
      System.out.print("Enter a shift key value from 0-25: ");
      shift = in.nextInt();
      if (shift >= 0 && shift <= 25) {
        break;
      }
      System.out.println("That is not a valid shift key. Please try again.");
    }
    //encryption: forwards/positive shift
    //decryption: backwards/negative shift
    shift *= direction;
    
    for (Task t:taskList) {
      String modified = "";
      String name = t.getName();
      for (int i = 0; i < name.length(); i++) {
        String character = name.substring(i, i+1);
        int index = uppercase.indexOf(character.toUpperCase());
        //if the character is not a letter (space, apostrophe, etc.), don't modify it, just add it
        if (index < 0) {
          modified += character;
        }
        else {
          int adjustedIndex = (index + shift) % 26;
          //check the case of the character and choose appropriate alphabet
          if (character.equals(character.toUpperCase())) {
            modified += uppercase.substring(adjustedIndex, adjustedIndex + 1);
          }
          else {
            modified += lowercase.substring(adjustedIndex, adjustedIndex + 1);
          }
        }
      }
      t.setName(modified);
    }
    printList(taskList);
  }
  
  
  //insertion sort for the names of tasks
  public static void sortName (ArrayList<Task> taskList) {
    for(int i = 0 ; i < taskList.size(); i++) {
      Task next = taskList.get(i);
      int insertIndex = 0;
      int k = i;
      while( k > 0 && insertIndex == 0 ) {
        if(next.getName().compareTo(taskList.get(k-1).getName()) > 0 ) {
          insertIndex = k;
        }
        else {
          taskList.set(k, taskList.get(k-1));
        }
        k--;
      }
      taskList.set(insertIndex, next);
    }
    System.out.println("\nSorted by Alphabetical Order:");
    printList(taskList);
  }
  
  //selection sort for the deadline of tasks
  public static void sortDeadline (ArrayList<Task> taskList) {
    int i, k, posMax, posMin;
    Task temp;

    for (i = taskList.size()-1; i >= 0; i--) {
      posMax = 0;
      posMin = 0;
      for (k = 0 ; k <= i ; k++) {
        if (taskList.get(k).getDeadline() > taskList.get(posMax).getDeadline()) {
          posMax = k;
        }
        temp = taskList.get(i);
        taskList.set(i, taskList.get(posMax));
        taskList.set(posMax, temp);
      }
    }
    System.out.println("\nSorted by Deadline:");
    printList(taskList);
  }
}
