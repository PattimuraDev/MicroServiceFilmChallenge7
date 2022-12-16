package org.example.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

/**
 * Kelas pemodelan DTO (data transfer object) dari objek film
 * @author Dwi Satria Patra
 */
@Data
public class FilmDto {
    @Schema(example = "1")
    private Long filmCode;
    @Schema(example = "Batman Gotham City")
    private String filmName;
    @Schema(example = "true")
    private Boolean isPlaying;
    private List<Schedule> schedulesList;
}
