/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinica;

public class UserData {
    String password;
    String profile;

    public UserData(String pas, String prof) {
        this.password = pas;
        this.profile = prof;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }

    public void setProfile(String prof) {
        this.profile = prof.toUpperCase();
    }

    public String getPassword() {
        return password;
    }

    public String getProfile() {
        return profile.toUpperCase();
    }
}
