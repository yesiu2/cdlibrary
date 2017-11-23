package pl.sdacademy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Track {

    private String author;
    private int length;
    private String title;
    private String notes;
}
