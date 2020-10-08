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
    User user = checkCredentials(username, password, accounts);
    if (!user.getUsername().isEmpty()) {
      // Proceed
      System.out.println("Welcome back, " + user.getUsername());
      navMenu(user, scn);
    } else {
      ++counter;
      start(counter);
    }
    scn.close();
  }

  public static void navMenu(User usr, Scanner sc) {
    System.out.println("Please select an option: ");
    System.out.println("1) View your account information \n2) Change your current plan \n3) Exit");
    Integer optionSelected = sc.nextInt();
    if (optionSelected == 1) {
      viewAccountInfo(usr, sc);
      navMenu(usr, sc);
    } else if (optionSelected == 2) {
      changePlan(usr, sc);
    } else if (optionSelected == 3) {
      System.out.println("Thank you for using Spotify.\nNow exiting");
      ellipses(0);
      System.exit(0);
    }
  }

  public static void viewAccountInfo(User u, Scanner s) {
    System.out.println("Username: " + u.getUsername());
    System.out.println("Password: *********");
    System.out.println("Account Type: " + u.getAccountType());
    System.out.println();
  }

  public static void changePlan(User u, Scanner s) {
    System.out.println(
        "You are currently under the " + u.getAccountType() + " plan. Would you like to change your plan? (y/n)");
    String res = s.nextLine();
    // EXITING HERE
    if (res.equals("Y") || res.equals("y")) {
      System.out.println("Would you like to change your plan to:\n1) Student\2) Individual\n3)Family\4) Exit");
      int newPlan = s.nextInt();
      if (newPlan == 4) {
        navMenu(u, s);
      } else if (newPlan == 2) {
        u.setAccountType("Individual");
        System.out.println("Your plan has been changed to the " + u.getAccountType()
            + " plan. You will be billed $9.99 per month beginning the next billing cycle.");
      }

    }
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

  public static User checkCredentials(String usr, String pass, ArrayList<User> accnts) {
    User authenticatedUser = new User();
    String response = "Incorrect username or password. Please try again.";
    if (validateUserInput(usr, pass) == true) {
      System.out.print("Authenticating");
      for (User currentUser : accnts) {
        if (currentUser.getUsername().equals(usr)) {
          if (currentUser.getPassword().equals(pass)) {
            response = "Authenticated!";
            authenticatedUser = currentUser;
          }
        }
      }
    } else {
      System.out.println("User Input Error, exiting program.");
      System.exit(0);
    }
    ellipses(0);
    System.out.println("\n" + response);
    return authenticatedUser;

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

  public void setAccountType(String newAccountType) {
    this.accountType = newAccountType;
  }
}
