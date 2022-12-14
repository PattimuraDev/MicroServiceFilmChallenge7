package org.example.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(example = "1")
    private Long scheduleId;
    @Schema(example = "1")
    private Long filmCode;
    @Schema(example = "11 Juli")
    private String date;
    @Schema(example = "15.00")
    private String startTime;
    @Schema(example = "17.00")
    private String endTime;
    @Schema(example = "40000")
    private Long ticketPrice;
    @Schema(example = "Studio C")
    private String studioName;
}
