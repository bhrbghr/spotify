package spotify.behaviors;

import spotify.exceptions.InvalidOperationException;
import spotify.models.*;

public class RegularBehavior implements UserBehavior {
    private int playingLimit = 5;

    @Override
    public void createPlaylist(String title, User owner) {
        throw new InvalidOperationException("Regular users cannot create playlists");
    }

    @Override
    public void playMusic(Music music) {
        if (playingLimit <= 0) {
            throw new InvalidOperationException("Regular user play limit reached (5 songs per session)");
        }
        music.play();
        playingLimit--;
    }

    @Override
    public void buyPremium(User owner, int month) {
        owner.setBehavior(new PremiumBehavior(month));
    }

}
