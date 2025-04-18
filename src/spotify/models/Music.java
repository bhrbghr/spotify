package spotify.models;

import spotify.exceptions.InvalidOperationException;

import java.util.ArrayList;

public class Music {
    private String title;
    private User singer;
    private int numberOfStream;
    private static ArrayList<Music> allMusics = new ArrayList<>();

    public Music(String title, User singer) {
        for (Music music: allMusics)
            if (music.title.equals(title) && music.singer.equals(singer))
                throw new InvalidOperationException("Music already exist");
        this.title = title;
        this.singer = singer;
        this.numberOfStream = 0;
        allMusics.add(this);
    }

    public void play() {
        numberOfStream++;
        System.out.println("Now playing: " + title + " by " + singer.getUsername());
    }

    public static ArrayList<Music> search(String title) {
        ArrayList<Music> result = new ArrayList<>();
        for (Music m : allMusics) {
            if (m.title.equals(title)) result.add(m);
        }
        return result;
    }

    public static Music search(String title, User singer) {
        for (Music m : allMusics) {
            if (m.title.equals(title) && m.singer.equals(singer)) return m;
        }
        return null;
    }

    public String getTitle() { return title; }
    public User getSinger() { return singer; }
    public int getNumberOfStream() { return numberOfStream; }
    public static ArrayList<Music> getAllMusics() { return allMusics;}
}
