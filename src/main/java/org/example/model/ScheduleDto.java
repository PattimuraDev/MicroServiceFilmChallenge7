package org.example.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ScheduleDto {
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
    @Schema(example = "25000")
    private Long ticketPrice;
    @Schema(example = "Studio A")
    private String studioName;
}
