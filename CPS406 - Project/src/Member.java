public class Member extends User {

    private String memberId;
    private String password;
    private String email;

    public Member(String name, String memberId, String password, String email) {
        super(name, email);
        this.memberId = memberId;
        this.password = password;
    }

    public Member() {

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
}
