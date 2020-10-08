import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Spotify {
  public static void main(String[] args) {
    start();
  }

  public static void start() {
    ArrayList<User> accounts = readFileToArrayList();
    System.out.println(accounts);
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
}

class User {
  String username;
  String password;
  String accountType;
}
