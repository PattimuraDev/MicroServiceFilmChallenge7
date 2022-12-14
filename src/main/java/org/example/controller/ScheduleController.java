package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.model.CustomResponseJson;
import org.example.model.Schedule;
import org.example.model.ScheduleDto;
import org.example.service.FilmServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "SCHEDULES")
@RestController
@RequestMapping("/schedules")
public class ScheduleController {
    @Autowired
    FilmServiceImpl filmServices;

    /**
     * Method controller untuk mengakomodasi kebutuhan menambahkan schedule baru
     *
     * @param scheduleDto parameter data transfer object untuk schedule
     * @return response entity hasil dari response endpoint API
     */
    @Operation(summary = "Endpoint untuk menambahkan schedule baru")
    @ApiResponse(
            responseCode = "201",
            description = "Schedule baru berhasil ditambahkan",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ScheduleDto.class)
            )
    )
    @PostMapping("/create_schedule")
    public ResponseEntity<Schedule> createSchedule(@RequestBody ScheduleDto scheduleDto) {
        final Schedule result = filmServices.addSchedule(filmServices.scheduleDtoMapToEntity(scheduleDto));
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Method controller untuk mengakomodasi kebutuhan menghapus schedule
     *
     * @param idSchedule parameter untuk id dari schedule yang ingin dihapus
     * @return response entity hasil dari response endpoint API
     */
    @Operation(summary = "Endpoint untuk menghapus schedule berdasarkan id")
    @ApiResponse(
            responseCode = "200",
            description = "Schedule berhasil dihapus",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CustomResponseJson.class)
            )
    )
    @DeleteMapping("/delete_schedule/{id}")
    public ResponseEntity<CustomResponseJson> deleteSchedule(@PathVariable("id") Long idSchedule) {
        try {
            filmServices.deleteSchedule(idSchedule);
            return new ResponseEntity<>(
                    new CustomResponseJson(
                            "Schedule berhasil dihapus",
                            "200"
                    ),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new CustomResponseJson(
                            "Operasi menghapus schedule gagal",
                            "500"
                    ),
                    HttpStatus.OK
            );
        }
    }
}
