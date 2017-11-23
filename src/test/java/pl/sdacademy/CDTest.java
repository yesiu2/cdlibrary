package pl.sdacademy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import  static org.junit.jupiter.api.Assertions.*;

public class CDTest {

    private int NUMBER_OF_TRACKS = 5;
    private String TITLE = "title";
    private String BAND = "band";
    private String PUBLISHER = "publisher";
    private LocalDate RELEASE_DATE = LocalDate.of(2017, 1, 1);
    private Genre GENRE = Genre.ROCK;
    private String AUTHOR = "author";
    private int LENGTH =  2;
    private String TITLE_TRACK = "title track";
    private String NOTES = "notes";
    private Track TRACK = new Track(AUTHOR, LENGTH, TITLE_TRACK, NOTES);
    private String AUTHOR2 = "autor dwa";
    private int LENGTH2 = 55;
    private String TITLE2 = "tytuł dwa";
    private String NOTES2 = "notes dwa";


    private CD cd;
    private Track track;

    @BeforeEach
    void setup() {
        cd = new CD(TITLE, BAND, PUBLISHER, RELEASE_DATE);
        track = new Track(AUTHOR, LENGTH, TITLE_TRACK, NOTES);
    }

    private List<Track> createTrackList(int numberofTracks) {
        List<Track> trackList = new ArrayList<>();
        for (int i = 0; i < numberofTracks; i++) {
            trackList.add(createTrack());
        }
        return trackList;
    }

    private Track createTrack() {
        Track track = new Track(AUTHOR, LENGTH, TITLE_TRACK, NOTES);

        return track;
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
    void deleteTrackOnOneTrackCDShouldResultInEmptyCD() {
//        cd.addTrack(track);
//        cd.addTrack(track);
//        assertTrue(cd.trackCount() == 2);
//        cd.deleteTrack(1);
//        cd.deleteTrack(0);
//        assertTrue(cd.getTracks().isEmpty());

        List<Track> tracks = createTrackList(1);
        cd.setTracks(tracks);
        cd.deleteTrack(0);
        assertTrue(cd.getTracks().isEmpty());
    }

    @Test
    void deleteTrackOnTwoTrackCDSHouldResultInOneTrackCD() {
        List<Track> tracks = new ArrayList<>();
        Track firstTrack = new TrackBuilder()
                .setAuthor(AUTHOR)
                .setLength(LENGTH)
                .setNotes(NOTES2)
                .setTitle(TITLE_TRACK)
                .build();
        tracks.add(firstTrack);
//        tracks.add(new Track(AUTHOR, LENGTH, TITLE_TRACK, NOTES));
//        tracks.add(new Track(AUTHOR2, LENGTH2, TITLE2, NOTES2));
        Track secondTrack = new TrackBuilder()
                .setLength(4)
                .setTitle("dasdada")
                .setAuthor("dd")
                .setNotes("notes")
                .build();
//        cd.setTracks(tracks);
//        tracks.add(firstTrack);
        tracks.add(secondTrack);
        cd.setTracks(tracks);
        cd.deleteTrack(0);
        assertEquals(cd.getTracks().size(), 1);
        assertEquals(cd.getTracks().get(0).getAuthor(), "dd");
        assertEquals(cd.getTracks().get(0).getLength(), 4);
        assertEquals(cd.getTracks().get(0).getTitle(), "dasdada");
        assertEquals(cd.getTracks().get(0).getNotes(), "notes");
    }

    @Test
    void getLength() {
//        cd.addTrack(track);
//        assertTrue(cd.length() == 2);

        cd.setTracks(createTrackList(NUMBER_OF_TRACKS));
        assertTrue(cd.getLength() == NUMBER_OF_TRACKS * LENGTH);
    }

    @Test
    void trackCount() {
//        cd.addTrack(track);
//        cd.addTrack(track);
//        cd.addTrack(track);
//        assertTrue(cd.trackCount() == 3);

        cd.setTracks(createTrackList(NUMBER_OF_TRACKS));
        assertTrue(cd.trackCount() == NUMBER_OF_TRACKS);
    }

    @Test
    void searchOnEmptyCDShouldReturnEmptyList() {
        assertTrue(cd.searchTracksByTitle("Sabbath Bloody Sabbath").isEmpty());
    }

    @Test
    void searchOnCDWithoutTitlesShouldReturnEmptyList() {
        List<Track> tracks = new ArrayList<>();
        Track firstTrack = new TrackBuilder()
                .setTitle("Fluff")
                .setNotes("sdas")
                .setLength(2)
                .setAuthor("add")
                .build();

        tracks.add(firstTrack);

        cd.setTracks(tracks);

        assertTrue(cd.searchTracksByTitle("Sabbath Bloody Sabbath").isEmpty());


    }

    @Test
    void searchOnCDWithTwoTitlesShouldReturnListOfSizeTwo() {
        List<Track> tracks = new ArrayList<>();
        Track firstTrack = new TrackBuilder()
                .setTitle("Sabbath Bloody Sabbath")
                .setNotes("sdas")
                .setLength(2)
                .setAuthor("Black Sabbath")
                .build();
        Track secondTrack = firstTrack;
        tracks.add(firstTrack);
        tracks.add(secondTrack);

        cd.setTracks(tracks);

        assertTrue(cd.searchTracksByTitle("Sabbath Bloody Sabbath").size() == 2);
        assertTrue(cd.searchTracksByTitle("Sabbath Bloody Sabbath").get(0).getTitle().contains("Sabbath Bloody Sabbath"));
        assertTrue(cd.searchTracksByTitle("Sabbath Bloody Sabbath").get(1).getTitle().contains("Sabbath Bloody Sabbath"));

    }
}
