import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class main {

    public static void main(String[] args) throws IOException {

        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("Create Account (1), Login (2) or Quit(3)");
            int choice = input.nextInt();
            if (choice == 1) {
                input = new Scanner(System.in);
                System.out.println("Enter your name: ");
                String name = input.nextLine();

                System.out.println("Enter your email: ");
                String email = input.nextLine();

                System.out.println("Enter your password: ");
                String password = input.nextLine();

                String memberId = generateId();

                Member member = new Member(name, memberId, password, email);
                if (createAccount(member)) {
                    System.out.println("Account Creation Successful\n\n");
                }
                else {
                    System.out.println("Error creating account\n\n");
                }
            }
            else if (choice == 3) {
                break;
            }
        }

        // Testing view members method
//        Coach coach = new Coach();
//        coach.viewMembers();
    }

    /*
    Method opens the existing users.csv file
    Appends appropriate user info to the file separated by commas
    Returns true if successful
     */
    public static boolean createAccount(Member member) {
        boolean success = false;

        File file = new File("users.csv");

        try {
            PrintWriter output = new PrintWriter(new FileWriter(file, true));

            output.append(member.getName()).append(",");
            output.append(member.getEmail()).append(",");
            output.append(member.getPassword()).append(",");
            output.append(member.getMemberId());
            output.println();

            output.close();

            success = true;

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return success;
    }

    /*
    Generates random sequence of letters & numbers for the member id
     */
    public static String generateId() {
        String chars = "ABCDEFGHIJKLMNOPRSTUVWXYZ0123456789";
        StringBuilder id = new StringBuilder();

        Random rand = new Random();

        while (id.length() < 9) {
            int ind = (rand.nextInt(chars.length()));
            id.append(chars.charAt(ind));
        }

        return id.toString();
    }
}
