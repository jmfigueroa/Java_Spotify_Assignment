import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Spotify {
  public static void main(String[] args) {
    System.out.println("Welcome to Spotify/nHere you can check your Spotify account settings.");
    Integer loginCounter = 0;
    start(loginCounter);
  }

  public static void start(Integer counter) {
    if (counter >= 3) {
      System.out.println("You have exceeded the number of attempts. Exiting");
      System.exit(0);
    }
    ArrayList<User> accounts = readFileToArrayList();
    Scanner scn = new Scanner(System.in);
    System.out.print("Username: ");
    String username = scn.nextLine();
    System.out.print("Password: ");
    String password = scn.nextLine();
    Boolean userAuthenticated = checkCredentials(username, password, accounts);
    if (userAuthenticated) {
      // Proceed
      System.out.println("proceeding");
    } else {
      ++counter;
      start(counter);
    }
    scn.close();
  }

  public static ArrayList<User> readFileToArrayList() {
    ArrayList<User> accountsList = new ArrayList<User>();
    try {
      Scanner scan = new Scanner(new FileReader("./spotify_database.txt"));
      while (scan.hasNextLine()) {
        String arr[] = scan.nextLine().split(" ");
        User temp = new User();
        temp.username = arr[0];
        temp.password = arr[1];
        temp.accountType = arr[2];
        accountsList.add(temp);
      }
      scan.close();

    } catch (

    IOException e) {
      System.out.println("File read error");
    }
    return accountsList;
  }

  public static Boolean validateUserInput(String usernm, String passwd) {
    // validation logic which returns boolean
    return true;
  }

  public static Boolean checkCredentials(String usr, String pass, ArrayList<User> accnts) {
    Boolean authenticated = false;
    String response = "Incorrect username or password. Please try again.";
    if (validateUserInput(usr, pass) == true) {
      System.out.print("Authenticating");
      for (User currentUser : accnts) {
        if (currentUser.getUsername().equals(usr)) {
          if (currentUser.getPassword().equals(pass)) {
            response = "Authenticated!";
            authenticated = true;
          }
        }
      }
    } else {
      System.out.println("User Input Error, exiting program.");
      System.exit(0);
    }
    ellipses(0);
    System.out.println("\n" + response);
    return authenticated;

  }

  public static void ellipses(int accumulator) {
    if (accumulator < 4) {
      try {
        Thread.sleep(500);
        System.out.print(".");
        accumulator++;
        ellipses(accumulator);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}

class User {
  String username;
  String password;
  String accountType;

  public String getUsername() {
    return this.username;
  }

  public String getPassword() {
    return this.password;
  }

  public String getAccountType() {
    return this.accountType;
  }
}
