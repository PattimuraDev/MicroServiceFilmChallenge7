package org.example.repository;

import org.example.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query(value = "delete from schedules where film_code = ?1", nativeQuery = true)
    @Modifying
    void repoDeleteScheduleByFilmId(@Param("input_kode_film") Long filmId);

    @Query(value = "update schedules set film_code = ?1 where schedule_id = ?2", nativeQuery = true)
    @Modifying
    void repoUpdateFilmsCodeOfSchedules(
            @Param("kode_film") Long kodeFilm,
            @Param("input_id_schedule") Long shedulesId
    );
}
