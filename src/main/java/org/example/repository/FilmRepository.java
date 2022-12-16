package org.example.repository;

import org.example.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Interface repository untuk menghandle semua permintaan ke table film di database
 * @author Dwi Satria Patra
 */
@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

    /**
     * Method repository untuk melakukan custom query yang digunakan untuk mengecek
     * semua film yang sedang tayang saay ini
     * @return list semua film dengan sedang tayang
     */
    @Query(value = "select * from films where films.is_playing=true", nativeQuery = true)
    List<Film> repoGetFilmIsPlaying();

    /**
     * Method repository untuk melakukan custom query yang digunakan untuk
     * mengupdate judul film berdasarkan judul lamanya
     * @param judulFilmAsli parameter untuk judul film yang lama
     * @param judulFilmBaru parameter untuk judul film yang baru
     */
    @Query(value = "update films set film_name = ?2 where films.film_name = ?1", nativeQuery = true)
    @Modifying
    void repoUpdateFilmByName(
            @Param("input_nama_film_lama") String judulFilmAsli,
            @Param("input_nama_film_baru") String judulFilmBaru
    );
}
