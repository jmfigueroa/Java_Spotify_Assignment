import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Spotify {
  public static void main(String[] args) {
    clearConsole();
    System.out.println("Welcome to Spotify Account Center\nHere you can check your Spotify account settings.");
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
    if (user.isValid()) {
      clearConsole();
      System.out.println("Welcome back, " + user.getUsername());
      navMenu(user, scn);
    } else if (!user.isValid()) {
      ++counter;
      start(counter);
    }
    scn.close();
  }

  public static void navMenu(User usr, Scanner sc) {
    clearConsole();
    System.out.println("Please select an option: ");
    System.out.println("1) View your account information \n2) Change your current plan \n3) Exit");
    Integer optionSelected = sc.nextInt();
    if (optionSelected == 1) {
      clearConsole();
      viewAccountInfo(usr, sc);
      wait(3000);
      navMenu(usr, sc);
    } else if (optionSelected == 2) {
      clearConsole();
      changePlan(usr, sc);
    } else if (optionSelected == 3) {
      clearConsole();
      System.out.println("Thank you for using Spotify.\nNow exiting");
      ellipses(0);
      System.exit(0);
    }
  }

  public static void viewAccountInfo(User u, Scanner s) {
    System.out.println("Username: " + u.getUsername());
    System.out.println("Password: *********");
    System.out.println("Account Type: " + u.getAccountType());
    System.out.println("Monthly Charges: $" + u.getFeePerMonth() + " per month");
    System.out.println();
  }

  public static void changePlan(User u, Scanner s) {
    String originalPlan = u.getAccountType();
    System.out.println("Your current account settings:");
    System.out.println("Account Type: " + u.getAccountType().toUpperCase());
    System.out.println("Charges: $" + u.getFeePerMonth() + " per month");
    System.out.println();
    System.out.println("Would you like to change your plan? (y/n)");
    s.nextLine();
    String res = s.nextLine();
    clearConsole();
    // Change Plan?
    if (res.equals("Y") || res.equals("y")) {
      System.out.println("Would you like to change your plan to:\n1) Student\n2) Individual\n3) Family\n4) Exit");
      int newPlan = s.nextInt();
      // Exit
      if (newPlan == 4) {
        navMenu(u, s);
        // Individual
      } else if (newPlan == 2) {
        u.setAccountType("Individual");
        // Family
      } else if (newPlan == 3) {
        u.setAccountType("Family");
        // Student
      } else if (newPlan == 1) {
        clearConsole();
        System.out
            .println("In order to qualify for the Spotify Student plan, \nwe'll have to verify your eligibility.");
        wait(2500);
        int c = 0;
        handleStudentOption(u, s, c);
      } else {
        clearConsole();
        System.out.println("Sorry, you are not eligible for the Student plan.");
        navMenu(u, s);
      }
      clearConsole();
      if (u.getAccountType().equals(originalPlan)) {
        clearConsole();
        System.out.println("No changes made.\nAccount Information");
        viewAccountInfo(u, s);
        wait(2500);
        navMenu(u, s);
      } else {
        System.out.println("Your plan has been changed to the " + u.getAccountType() + " plan. You will be billed $"
            + u.getFeePerMonth() + " per month beginning the next billing cycle.");
        wait(3000);
        navMenu(u, s);
      }
    } else {
      navMenu(u, s);
    }
  }

  public static void handleStudentOption(User u, Scanner s, int eduCounter) {
    ++eduCounter;
    clearConsole();
    System.out.print("Please enter your STUDENT email address: ");
    s.nextLine();
    String emailAddress = s.nextLine();
    ellipses(0);
    if (eduCounter < 2) {
      if (verifyEdu(emailAddress)) {
        clearConsole();
        System.out.println("Congratuations. Your student email has been verified!");
        wait(1500);
        u.setAccountType("Student");
        clearConsole();
      } else {
        System.out.println("Sorry, we weren't able to verify your STUDENT email address. \nPlease try again...");
        handleStudentOption(u, s, eduCounter);
      }
    } else {
      System.out.println("We're sorry but you are not eligible for the Spotify Student plan.");
      wait(2500);
    }
  }

  public static Boolean verifyEdu(String email) {
    Boolean isEdu = false;
    String domain = email.split("@")[1];
    String topLvlDomain = domain.toString().split("\\.")[domain.split("\\.").length - 1];
    System.out.println(topLvlDomain);
    if (topLvlDomain.equals("edu")) {
      isEdu = true;
    }
    return isEdu;
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
    // validation logic which checks userInput for dangerous input
    // (e.g., escaped characters)
    // returns boolean
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
    clearConsole();
    System.out.println(response);
    wait(1500);
    return authenticatedUser;
  }

  // Utility Methods
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

  public static void wait(int ms) {
    try {
      Thread.sleep(ms);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void clearConsole() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
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

  public double getFeePerMonth() {
    switch (this.accountType.toLowerCase()) {
      case "student":
        return 4.99;
      case "family":
        return 14.99;
      case "individual":
        return 9.99;
      default:
        return 99.99;
    }
  }

  public boolean isValid() {
    boolean isValid = false;
    if (this.accountType != null && this.password != null && this.username != null) {
      isValid = true;
    }
    return isValid;
  }
}
