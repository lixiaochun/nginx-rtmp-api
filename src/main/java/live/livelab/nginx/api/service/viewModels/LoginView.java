package live.livelab.nginx.api.service.viewModels;

/**
 * Created by kevin on 17/2/7.
 */
public class LoginView {
    private String name;
    private String email;
    private String token;

    public String getToken() {
        return token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LoginView(String name, String email, String token) {
        this.name = name;
        this.email = email;
        this.token = token;
    }

    public LoginView() {

    }

    public void setToken(String token) {
        this.token = token;
    }
}
