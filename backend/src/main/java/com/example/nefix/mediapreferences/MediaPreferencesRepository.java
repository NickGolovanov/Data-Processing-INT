package com.example.nefix.mediapreferences;

import com.example.nefix.preference.Preference;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaPreferencesRepository extends JpaRepository<MediaPreferences, Long>
{
    List<MediaPreferences> findAllByPreference_PreferenceId(Long preferenceId);
}
