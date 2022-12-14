package org.example.service;

import org.example.model.Film;
import org.example.model.FilmDto;
import org.example.model.Schedule;
import org.example.model.ScheduleDto;
import java.util.List;

public interface FilmService {
    Film addFilms(Film films);

    void updateFilmByName(String oldFilmName, String newFilmName);

    Film getFilmById(Long idFilm);

    void deleteFilmById(Long idFilm);

    List<Film> getFilmIsPlaying();

    Film filmDtoMapToEntity(FilmDto filmsDto);

    //schedule
    List<Schedule> schedulesOfFilmsByName(String filmName);

    Schedule findScheduleById(Long idSchedule);

    void updateFilmCodeOfSchedules(Long schedulesId, Long filmId);

    Schedule addSchedule(Schedule schedules);

    void deleteSchedule(Long idSchedule);

    void deleteSchedulesByFilmId(Long filmId);

    Schedule scheduleDtoMapToEntity(ScheduleDto schedulesDto);
}
