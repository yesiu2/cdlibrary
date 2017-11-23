package pl.sdacademy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@NoArgsConstructor
@Builder

public class LibraryTest {




    @Test
    void searchByGenreOnEmptyCDShouldReturnEmptyList() {
        Library library = new Library();
        List<CD> result = library.searchByGenre(Genre.ROCK);
        assertTrue(result.isEmpty());
    }

    @Test
    void searchByGenreWithNonExistingGenreShouldReturnEmptyList() {
        Library library = new Library();

        CD cd = new CD("band", "title", "pabliszer", LocalDate.now());
        List<CD> result = new ArrayList<>();

        Set<Genre> genres = new HashSet<>();

        genres.add(Genre.AFRICAN_HEAVY_METAL);
        cd.setGenres(genres);

        library.addCD(cd);
        assertTrue(library.searchByGenre(Genre.ROCK).isEmpty());
        assertTrue(library.searchByGenre(Genre.AFRICAN_HEAVY_METAL).size() == 1);

    }

    @Test
    void searchByGenreShouldReturnSomeResults() {
        Library library = new Library();
        CD cd1 = createCDWithGenres(Genre.ROCK, Genre.POP);
        CD cd2 = createCDWithGenres(Genre.ROCK);
        CD cd3 = createCDWithGenres(Genre.CLASSIC);

        List<CD> cds = new ArrayList<>();

        cds.add(cd1);
        cds.add(cd2);
        cds.add(cd3);

        library.setCDs(cds);
        List<CD> result = library.searchByGenre(Genre.ROCK);
        assertTrue(result.size() == 2);
        assertTrue(result.get(0).getGenres().contains(Genre.ROCK));
        assertTrue(result.get(1).getGenres().contains(Genre.ROCK));
    }

    @Test
    void searchInEmptyLibraryShouldFindNothing() {
        Library lib = new Library();

        assertTrue(lib.searchByCDTitle("Dupa").isEmpty());
    }

    @Test
    void inLibraryWithTwoCDsWithSameTitleShouldBeFoundTwoCDs() {
        Library lib = new Library();
        List<CD> cds = new ArrayList<>();

        CD cd1 = createCDWithTitle("Number of the beast");
        CD cd2 = cd1;
        CD cd3 = createCDWithTitle("Seventh Son of the seventh son");
        cds.add(cd1);
        cds.add(cd2);
        cds.add(cd3);

        lib.setCDs(cds);

        List<CD> result = lib.searchByCDTitle("Number of the beast");
        assertTrue(result.size() == 2);
        assertTrue(result.get(0).getTitle().equals("Number of the beast"));
        assertTrue(result.get(1).getTitle().equals("Number of the beast"));
    }

    @Test
    void inLibraryThereIsNoCDsWithSearchedTitle() {
        Library lib = new Library();
        List<CD> cds = new ArrayList<>();

        CD cd1 = createCDWithTitle("Number of the beast");
        CD cd2 = cd1;
        CD cd3 = createCDWithTitle("Seventh Son of the seventh son");
        CD cd4 = createCDWithTitle("Fear of the dark");


        cds.add(cd1);
        cds.add(cd2);
        cds.add(cd3);
        cds.add(cd4);

        lib.setCDs(cds);

        assertTrue(lib.searchByCDTitle("Dance of Death").isEmpty());
    }

    /*
    public List<CD> searchByTrackTitle(String title) {

        return CDs.stream()
                .filter(cd -> !cd.searchTracksByTitle(title).isEmpty())
                .collect(Collectors.toList());
    }
     */

    @Test
    void emptyLibraryShouldFindNoTracks() {
        Library lib = new Library();
        assertTrue(lib.searchByTrackTitle("dupa").isEmpty());
    }

    @Test
    void libraryWithTwoCDsWithSameTrackTitlesShouldReturnTwoCDs() {
        Library lib = createLibraryWithTwoCDs();

        assertTrue(lib.searchByTrackTitle("Strange World").size() == 2);
        assertTrue(!lib.searchByTrackTitle("Strange World").get(0).searchTracksByTitle("Strange World").isEmpty());
        assertTrue(!lib.searchByTrackTitle("Strange World").get(1).searchTracksByTitle("Strange World").isEmpty());

    }

    @Test
    void inLibraryThereIsNoCDsWithSearchedTrackTitles() {
        Library lib = createLibraryWithTwoCDs();

        assertTrue(lib.searchByTrackTitle("Chryzantemy złociste").isEmpty());
    }

    private Library createLibraryWithTwoCDs() {
        Library lib = new Library();

        List<CD> cds = new ArrayList<>();

        List<Track> trackList1 = new ArrayList<>();
        List<Track> trackList2 = new ArrayList<>();

        Track track = new Track();
        track.setTitle("blabla Strange World");
        Track track2 = new Track();
        track2.setTitle("Strange World cośtam");

        trackList1.add(track);
        trackList2.add(track2);


        CD cd1 = new CD();
        CD cd2 = new CD();

        cd1.setTracks(trackList1);
        cd2.setTracks(trackList2);

        cds.add(cd1);
        cds.add(cd2);

        lib.setCDs(cds);
        return lib;
    }

    private CD createCDWithGenres(Genre ... genres) {
        CD cd = new CD();
        Set<Genre> setOfGenres = new HashSet<>();

        for (Genre genre: genres) {
            setOfGenres.add(genre);
        }
        cd.setGenres(setOfGenres);
        return cd;
    }

    private CD createCDWithTitle(String title) {
        CD cd = new CD();
        cd.setTitle(title);
        return cd;
    }
}
