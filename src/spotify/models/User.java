package spotify.models;
import spotify.behaviors.*;
import spotify.exceptions.*;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private ArrayList<User> followerList;
    private ArrayList<User> followingList;
    private UserBehavior behavior;
    private ArrayList<Playlist> playlists;
    private static ArrayList<User> allUsers = new ArrayList<>();

    public User(String username, String password) {
        if (usernameExists(username)) {
            throw new InvalidOperationException("Username already exists");
        }
        if (password.length() < 8) {
            throw new InvalidOperationException("Password must be at least 8 characters");
        }
        this.username = username;
        this.password = password;
        this.followerList = new ArrayList<>();
        this.followingList = new ArrayList<>();
        this.behavior = new RegularBehavior();
        this.playlists = new ArrayList<>();

        allUsers.add(this);
    }

    private boolean usernameExists(String username) {
        for (User u : allUsers) {
            if (u.username.equals(username)) return true;
        }
        return false;
    }

    public void follow(User user) {
        if (user == null || user == this) {
            throw new InvalidOperationException("Invalid user to follow");
        }
        if (!followingList.contains(user)) {
            followingList.add(user);
            user.followerList.add(this);
        }
    }

    public void createPlaylist(String title) {
        this.behavior.createPlaylist(title, this);
    }

    public void playMusic(Music music) {
        this.behavior.playMusic(music);
    }

    public void buyPremium(int month) {
        this.behavior.buyPremium(this,month);
    }
    public void addPlaylist(Playlist playlist) {
        this.playlists.add(playlist);
    }
    public UserBehavior getBehavior() {
        return behavior;
    }
    public void setBehavior(UserBehavior behavior) {
        this.behavior = behavior;
    }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public ArrayList<Playlist> getPlaylists() { return playlists; }
    public ArrayList<User> getFollowerList() { return followerList; }
    public ArrayList<User> getFollowingList() { return followingList; }
    public static ArrayList<User> getAllUsers() { return allUsers; }


}

