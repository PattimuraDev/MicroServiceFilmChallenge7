package org.example.repository;

import org.example.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    @Query(value = "select * from films where films.is_playing=true", nativeQuery = true)
    List<Film> repoGetFilmIsPlaying();

    @Query(value = "update films set film_name = ?2 where films.film_name = ?1", nativeQuery = true)
    @Modifying
    void repoUpdateFilmByName(
            @Param("input_nama_film_lama") String judulFilmAsli,
            @Param("input_nama_film_baru") String judulFilmBaru
    );
}
