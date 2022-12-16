package org.example.repository;

import org.example.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Interface repository untuk menghandle semua permintaan ke table schedule di database
 * @author Dwi Satria Patra
 */
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    /**
     * Method repository untuk melakukan custom query yang digunakan untuk menghapus
     * schedule/jadwal berdasarkan id filmnya
     * @param filmId parameter untuk id dari film
     */
    @Query(value = "delete from schedules where film_code = ?1", nativeQuery = true)
    @Modifying
    void repoDeleteScheduleByFilmId(@Param("input_kode_film") Long filmId);

    /**
     * Method repository untuk melakukan custom query yang digunakan untuk mengupdate kode film dari
     * schedule dengan id tertentu
     * @param kodeFilm parameter untuk kode inputan kode film yang akan diinputkan
     * @param shedulesId parameter untuk id dari schedule yang dituju
     */
    @Query(value = "update schedules set film_code = ?1 where schedule_id = ?2", nativeQuery = true)
    @Modifying
    void repoUpdateFilmsCodeOfSchedules(
            @Param("kode_film") Long kodeFilm,
            @Param("input_id_schedule") Long shedulesId
    );
}
