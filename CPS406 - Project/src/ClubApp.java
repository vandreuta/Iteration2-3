/**
 * Preprocessor directives
 */

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class ClubApp {
	
	public static void main(String[] args) {

		Scanner console = new Scanner(System.in);
		System.out.print("\033\143");
		System.out.print(">");

		while (console.hasNextLine()) {
			
			String inputLine = console.nextLine();
			if (inputLine == null || inputLine.equals("")) continue;
	
			Scanner commandLine = new Scanner(inputLine);
			String command = commandLine.next();

			if (command == null || command.equals("")) continue;

			else if (command.equalsIgnoreCase("C")){

				System.out.println("Enter member name: ");
				String name = console.nextLine();

				System.out.println("Enter your email: ");
				String email = console.nextLine();

				System.out.println("Enter your password: ");
				String password = console.nextLine();

				String memberId = generateId();

				Member member = new Member(name, memberId, password, email);

				try {
					createAccount(member);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			else if (command.equalsIgnoreCase("L")){
				System.out.println("TODO: login existing account");
			}

			else if (command.equalsIgnoreCase("Q")){
				break;
			}

			commandLine.close();
			System.out.print("\033\143");
			System.out.print("\n>");
		}

		console.close();

	}


	/**
	 * Method opens the existing users.csv file
	 * Appends appropriate user info to the file separated by commas
	 * Returns true if successful
	 * 
	 * @param member
	 * @return	bool 
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

	/**
	 * Generates random sequence of letters & numbers for the member id
	 * 
	 * @return String
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