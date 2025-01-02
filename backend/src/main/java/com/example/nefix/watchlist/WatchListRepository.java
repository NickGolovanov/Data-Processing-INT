package com.example.nefix.watchlist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WatchListRepository extends JpaRepository<WatchList, Long> {

    List<WatchList> getWatchListByProfileProfileId(Long id);

    @Query(value = "SELECT * FROM watchlist WHERE profile_id = :profileId AND movie_id is not null", nativeQuery = true)
    List<WatchList> getWatchListsProfileMovies(Long profileId);

    @Modifying
    @Query(value = "DELETE FROM watchlist WHERE profile_id = :profileId AND movie_id = :movieId", nativeQuery = true)
    int deleteFromWatchList(Long profileId, Long movieId);
}
