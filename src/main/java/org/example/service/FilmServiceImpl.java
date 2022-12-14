package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Film;
import org.example.model.FilmDto;
import org.example.model.Schedule;
import org.example.model.ScheduleDto;
import org.example.repository.FilmRepository;
import org.example.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class FilmServiceImpl implements FilmService {
    @Autowired
    FilmRepository filmRepository;

    @Autowired
    ScheduleRepository scheduleRepository;
    // mapper untuk keperluan pemetaan objek
    ObjectMapper mapper = new ObjectMapper();

    /**
     * Method yang digunakan untuk add film
     *
     * @param film paramater berisi data object film
     * @return film hasil save
     */
    @Override
    public Film addFilms(Film film) {
        return filmRepository.save(film);
    }


    /**
     * Method yang digunakan untuk mengupdate nama/judul film
     *
     * @param oldFilmName parameter untuk nama/judul awal film
     * @param newFilmName parameter untuk nama/judul film yang baru
     */
    @Override
    public void updateFilmByName(String oldFilmName, String newFilmName) {
        filmRepository.repoUpdateFilmByName(oldFilmName, newFilmName);
    }

    /**
     * Method untuk mendapatkan film berdasarkan id film
     *
     * @param idFilm parameter untuk id dari film
     * @return film hasil pencarian berdasarkan id
     */
    @Override
    public Film getFilmById(Long idFilm) {
        if (filmRepository.findById(idFilm).isPresent()) {
            return filmRepository.findById(idFilm).get();
        } else {
            return null;
        }
    }

    /**
     * Method yang digunakan untuk menghapus film berdasarkan id dari film
     *
     * @param filmId parameter untuk id dari film
     */
    @Override
    public void deleteFilmById(Long filmId) {
        filmRepository.deleteById(filmId);
    }

    /**
     * Method untuk mengambil semua film dengan status tayang saat ini
     *
     * @return semua film yang sedang tayang (list)
     */
    @Override
    public List<Film> getFilmIsPlaying() {
        return filmRepository.repoGetFilmIsPlaying();
    }


    /**
     * Method untuk melakukan pemetaan dari objek FilmsDto menjadi films
     *
     * @param filmDto parameter untuk objek FilmsDto
     * @return hasil pemetaan menjadi objek Films
     */
    @Override
    public Film filmDtoMapToEntity(FilmDto filmDto) {
        return mapper.convertValue(filmDto, Film.class);
    }


    /**
     * Method yang digunakan untuk mengambil semua jadwal tersedia untuk film dengan judul tertentu
     *
     * @param filmName parameter nama/judul film
     * @return list schedule film terkait
     */
    @Override
    public List<Schedule> schedulesOfFilmsByName(String filmName) {
        List<Schedule> listOfAllSchedules = scheduleRepository.findAll();
        List<Schedule> result = new ArrayList<>();
        for (Schedule schedules : listOfAllSchedules) {
            if (Objects.equals(schedules.getFilmCode(), findFilmIdByName(filmName))) {
                result.add(schedules);
            }
        }
        return result;
    }

    /**
     * Method untuk mendapatkan schedule berdasarkan id-nya
     *
     * @param idSchedule parameter untuk id dari schedule yang dimaksud
     * @return objek schedule hasil pencarian berdasarkan id-nya
     */
    @Override
    public Schedule findScheduleById(Long idSchedule) {
        if (scheduleRepository.findById(idSchedule).isPresent()) {
            return scheduleRepository.findById(idSchedule).get();
        } else {
            return null;
        }
    }

    /**
     * Method yang digunakna untuk mengupdate kode film pada table jadwal/schedule
     *
     * @param scheduleId parameter untuk id dari schedule yang dimaksud
     * @param filmId     parameter untuk id film yang ingin dimasukkan
     */
    @Override
    public void updateFilmCodeOfSchedules(Long scheduleId, Long filmId) {
        scheduleRepository.repoUpdateFilmsCodeOfSchedules(filmId, scheduleId);
    }

    /**
     * Method yang digunakan untuk menambahkan jadwal/schedule baru
     *
     * @param schedule parameter untuk objek schedule
     * @return objek schedule yang berhasil tersimpan
     */
    @Override
    public Schedule addSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    /**
     * Method yang digunakan untuk menghapus schedule film berdasarkan id dari schedule
     *
     * @param idSchedule parameter untuk id schedule yang dimaksud
     */
    @Override
    public void deleteSchedule(Long idSchedule) {
        scheduleRepository.deleteById(idSchedule);
    }

    /**
     * Method yang digunakan untuk menghapus schedule film berdasarkan id film
     *
     * @param filmId parameter untuk id dari film
     */
    @Override
    public void deleteSchedulesByFilmId(Long filmId) {
        scheduleRepository.repoDeleteScheduleByFilmId(filmId);
    }

    @Override
    public Schedule scheduleDtoMapToEntity(ScheduleDto schedulesDto) {
        return null;
    }

    /**
     * Method uang digunakan untuk mencari id film berdasarkan nama/judulnya
     *
     * @param filmName parameter untuk nama dari film
     * @return hasil pencarian id film
     */
    public Long findFilmIdByName(String filmName) {
        List<Film> listOfAllFilms = filmRepository.findAll();
        Long filmCode = 0L;
        for (Film films : listOfAllFilms) {
            if (Objects.equals(films.getFilmName(), filmName)) {
                filmCode = films.getFilmCode();
            }
        }
        return filmCode;
    }
}
