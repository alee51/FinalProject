//object class for tasks
public class Task {
  private String name;
  private int deadline;
  
  //constructor
  public Task (String name, int deadline) {
    this.name = name;
    this.deadline = deadline;
  }
  
  public String getName() {
    return this.name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public int getDeadline() {
    return this.deadline;
  }
  
  public String toString() {
    return String.format("%-30s %d", getName(), getDeadline());
  }
}
