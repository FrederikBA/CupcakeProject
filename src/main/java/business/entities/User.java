package business.entities;

public class User {

    public User(String email, String password, String role, AccountBalance balance) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.accountBalance = balance;
    }

    public User(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.accountBalance = null;
    }

    public User(int id, String email, String role, AccountBalance balance){
        this.id = id;
        this.email = email;
        this.role = role;
        this.accountBalance = balance;
    }

    private int id;
    private String email;
    private String password;
    private String role;
    private AccountBalance accountBalance;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AccountBalance getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(AccountBalance accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", accountBalance=" + accountBalance +
                '}';
    }
}
