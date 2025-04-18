import spotify.behaviors.*;
import spotify.exceptions.InvalidOperationException;
import spotify.models.*;


public class Main {
    public static void main(String[] args) {

        User u1 = new User("Sina", "12345678");
        User u2 = new User("Amir", "87654321");
        User a1 = new User("Mehrad jam", "artistpass1");
        User a2 = new User("Tataloo", "artistpass2");

        u1.follow(a1);
        u2.follow(u1);

        Music m1 = new Music("To Chi Midooni", a1);
        Music m2 = new Music("Delam Gerefte", a2);
        Music m3 = new Music("Rainy Mode", a2);

        System.out.println("Sina is following: " + u1.getFollowingList().size() + " users");
        System.out.println("Mehrad jam has: " + a1.getFollowerList().size() + " followers");


        try {
            u1.playMusic(m1);
            u1.playMusic(m2);
            u1.playMusic(m3);
            u1.playMusic(m1);
            u1.playMusic(m2);
            u1.playMusic(m3);
        } catch (InvalidOperationException e) {
            System.out.println("error: " + e.getMessage());
        }

        try {
            System.out.println("\nAttempting to create playlist with regular user:");
            u1.createPlaylist("My Playlist");
        } catch (InvalidOperationException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("\nUpgrading user1 to premium...");
        u1.buyPremium( 3);
        System.out.println("Sina is now premium: " + (u1.getBehavior() instanceof PremiumBehavior));
        System.out.println("\nTesting premium features:");
        System.out.println("\nTesting unlimited plays for premium user:");
        for (int i = 1; i <= 10; i++) {
            System.out.print("Play " + i + ": ");
            u1.playMusic(m1);
        }

        u1.createPlaylist("Favorites");
        Playlist favorites = u1.getPlaylists().get(0);
        favorites.addMusic(m1, "12345678");
        favorites.addMusic(m2, "12345678");


        System.out.println("\n------ Play Playlist ------");
        favorites.playPlaylist();


        System.out.println("\n------ Shuffle Playlist ------");
        favorites.shuffle();

        favorites.removeMusic(m1, "12345678");


        System.out.println("\nSearch results:");
        for (Music m : favorites.searchInPlaylist("Delam Gerefte")) {
            System.out.println("- " + m.getTitle() + " by " + m.getSinger().getUsername());
        }

        System.out.println("\nTesting error cases:");
        try {
            new User("Sina", "pass");
        } catch (InvalidOperationException e) {
            System.out.println("1. " + e.getMessage());
        }

        try {
            new User("user3", "short");
        } catch (InvalidOperationException e) {
            System.out.println("2. " + e.getMessage());
        }

        try {
            favorites.addMusic(m1, "wrongpass");
        } catch (InvalidOperationException e) {
            System.out.println("3. " + e.getMessage());
        }

        try {
            favorites.addMusic(m2, "12345678");
        } catch (InvalidOperationException e) {
            System.out.println("4. " + e.getMessage());
        }
        System.out.println("\n------ Final Stats ------");
        System.out.println("Total users: " + User.getAllUsers().size());
        System.out.println("Total musics: " + Music.getAllMusics().size());
        System.out.println(u1.getUsername() + "'s playlists: " + u1.getPlaylists().size());
        System.out.println(m1.getTitle() + " streams: " + m1.getNumberOfStream());
    }

}
