package com.example.nefix.mediapreferences;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaPreferencesRepository extends JpaRepository<MediaPreferences, Long>
{
}
