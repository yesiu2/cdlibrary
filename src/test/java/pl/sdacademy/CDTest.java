package pl.sdacademy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import  static org.junit.jupiter.api.Assertions.*;

public class CDTest {

    private String TITLE = "title";
    private String BAND = "band";
    private String PUBLISHER = "publisher";
    private LocalDate RELEASE_DATE = LocalDate.of(2017, 1, 1);
    private Genre GENRE = Genre.ROCK;
    private String AUTHOR = "author";
    private int LENGTH =  2;
    private String TITLE_TRACK = "title track";
    private String NOTES = "notes";


    private CD cd;
    private Track track;

    @BeforeEach
    void setup() {
        cd = new CD(TITLE, BAND, PUBLISHER, RELEASE_DATE);
        track = new Track(AUTHOR, LENGTH, TITLE_TRACK, NOTES);
    }

    @Test
    void addingFirstGenreToNewCDShouldAddOneGenre() {

        assertTrue(cd.getGenres().isEmpty(), "New CD should have no genres");
        cd.addGenre(GENRE);
        assertTrue(cd.getGenres().size() == 1);
        assertTrue(cd.getGenres().contains(GENRE));

    }

    @Test
    void deleteGenre() {
        cd.addGenre(GENRE);
        cd.deleteGenre(GENRE);
        assertTrue(cd.getGenres().isEmpty());
    }

    @Test
    void addTrack() {
        cd.addTrack(track);
        assertTrue(cd.getTracks().size() == 1);
        assertTrue(cd.getTracks().contains(track));
    }

    @Test
    void deleteTrack() {
        cd.addTrack(track);
        cd.deleteTrack(0);
        assertTrue(cd.getTracks().isEmpty());
    }

    @Test
    void length() {
        cd.addTrack(track);
        assertTrue(cd.length() == 2);
    }

    @Test
    void trackCount() {
        cd.addTrack(track);
        cd.addTrack(track);
        cd.addTrack(track);
        assertTrue(cd.trackCount() == 3);
    }
}
