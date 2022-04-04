import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Coach extends User {

    public Coach(String name, String email) {
        super(name, email);
    }

    public Coach() {

    }

    public void viewMembers() throws IOException {
        FileReader fr = new FileReader("users.csv");
        BufferedReader br = new BufferedReader(fr);

        String line;
        int counter = 1;

        while ((line = br.readLine()) != null) {
            String[] info = line.split(",");
            System.out.println(readMember(counter, info));
            counter++;
        }
    }

    private String readMember(int num, String[] info) {
        String name = info[0];
        String email = info[1];
        String memberId = info[3];

        String s1 = String.format("Member %d: \n\tName: %s\n\tEmail: %s", num, name, email);
        String s2 = String.format("\n\tMember ID: %s", memberId);

        return s1 + s2;
    }
}
