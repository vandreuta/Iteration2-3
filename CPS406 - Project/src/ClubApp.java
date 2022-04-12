/**
 * Preprocessor directives
 */

import java.io.*;
// import java.util.ArrayList;
// import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ClubApp {
	
	public static void main(String[] args) {

		boolean admin = false;
		String user;

		Scanner console = new Scanner(System.in);
		//System.out.print("\033\143");
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

				System.out.println("Enter username: ");
				String username = console.nextLine();

				System.out.println("Enter password: ");
				String password = console.nextLine();
				
				try {
					if(login(username, password)){
						System.out.println("Welcome, " + username);
						user = username;
					}
				} catch (Exception e) {	
					e.printStackTrace();
				}
			}

			else if (command.equalsIgnoreCase("Q")){
				break;
			}

			commandLine.close();
			//System.out.print("\033\143");
			System.out.print("\n>");
		}

		console.close();

	}

	/**
	 * Method opens the existing users.csv file
	 * Appends appropriate user info to the file separated by commas
	 * Returns true if successful
	 * 
	 * @param	member
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


	/**
	 * Checks that user exists in user database
	 * 
	 * @param username
	 * @param password
	 * @return Boolean	Indicating success or failure
	 * @throws IOException
	 */

	public static boolean login(String username, String password) throws IOException{
	
		try {
			BufferedReader r = new BufferedReader( new FileReader("src/users.csv"));
			String line = null;

			while ((line = r.readLine()) != null){
				String[] curr_line = line.split(",");

				for (int i = 0 ; i < curr_line.length; i+=4){

					if ((curr_line[i].equals(username))||(curr_line[i+2].equals(password))){
						r.close();
						System.out.println("hello, " + username);
						return true;
					}
				}
			}

			r.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return false;
	}
}