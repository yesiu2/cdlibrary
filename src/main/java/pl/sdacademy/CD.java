package pl.sdacademy;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Klasa CD przechowuje informacje o jednej plycie, m.in. nazwe wykonawcy, tytul, utwory, gatunki
 *
 * @author Wojciech
 * @version 16.11.17
 */
@Data
public class CD {

    /**
     * NAzwa wykonawcy
     */
    private String band;
    /**
     * Tytul plyty
     */
    private String title;
    /**
     * Wydawca plyty
     */
    private String publisher;
    /**
     * Data wydania plyty
     */
    private LocalDate releaseDate;
    /**
     * Gatunki muzyczne
     */
    private Set<Genre> genres;
    /**
     * Lista utworow
     */
    private List<Track> tracks;

    public CD(String band, String title, String publisher, LocalDate releaseDate) {
        this.band = band;
        this.title = title;
        this.publisher = publisher;
        this.releaseDate = releaseDate;
        this.genres = new HashSet<>();
        this.tracks = new ArrayList<>();
    }

    /**
     * Konstruktor tworzy nowy obiekt CD z pusta lista genre oraz pusta lista tracks
     */

    public CD() {
        this.genres = new HashSet<>();
        this.tracks = new ArrayList<>();
    }

    /**
     * Metoda dodaje do płyty gatunek
     * @param genre gatunek dodawany do płyty
     */

    public void addGenre(Genre genre) {
        genres.add(genre);
    }

    /**
     * Usuwa podany gatunek z płyty
     * @see Genre
     *
     * @param genre gatunek do usunięcia
     */

    public void deleteGenre(Genre genre) {
        genres.remove(genre);
    }

    public void addTrack(Track track) {
        tracks.add(track);
    }

    public void deleteTrack(int trackNumber) {
        tracks.remove(trackNumber);
    }

    public int getLength() {
       int sum = 0;

        for (Track track : tracks) {
            sum+= track.getLength();
        }
        return sum;

//        tracks.stream().mapToInt(track -> track.getLength()).sum();
    }

    public int trackCount() {
        return tracks.size();
    }

    public List<Track> searchTracksByTitle(String title) {
        return tracks.stream()
                .filter(track -> track.getTitle().contains(title))
                .collect(Collectors.toList());
    }

    public String toString() {
        return band + ", "
                + title + ", "
                + releaseDate + ", "
                + lengthToString() + ", "
                + genresToString();
    }

    // ma zwracac gatunki w postaci nazwy w opisasch
    public String genresToString() {

        return genres.stream()
                .map(genre -> genre.toString())
                .collect(Collectors.joining(", "));
    }

    //ma zwracac czas w postaci mm:ss
    public String lengthToString() {
        int length = this.getLength();

        int min = length / 60;
        int sec = length % 60;

        return String.format("%d:%02d", min, sec);

//        int minutes = getLength() / 60;
//        int seconds = getLength() % 60;
//
//        if (seconds < 10) {
//            return minutes + ":0" + seconds;
//        } else {
//            return
//        }
//
//        return null;
    }


}
