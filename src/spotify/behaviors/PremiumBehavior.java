package spotify.behaviors;
import spotify.exceptions.InvalidOperationException;
import spotify.models.*;

public class PremiumBehavior implements UserBehavior {
    private int month;

    public PremiumBehavior(int month) {
        this.month = month;
    }

    @Override
    public void createPlaylist(String title, User owner) {
        if (title == null || title.trim().isEmpty()) {
            throw new InvalidOperationException("Playlist title must not be empty");
        }
        if (owner == null) {
            throw new InvalidOperationException("Owner must not be null");
        }
        Playlist playlist = new Playlist(title, owner);
        owner.getPlaylists().add(playlist);
    }

    @Override
    public void playMusic(Music music) {
        if (music == null) {
            throw new InvalidOperationException("Music must not be null");
        }
        music.play();
    }

    @Override
    public void buyPremium(User owner, int month) {
        if (month <= 0) {
            throw new InvalidOperationException("Month must be positive");
        }
        this.month += month;
    }
    public int getMonth() {
        return month;
    }
}

