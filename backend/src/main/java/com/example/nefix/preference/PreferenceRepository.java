package com.example.nefix.preference;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferenceRepository extends JpaRepository<Preference, Long>
{
    Preference getPreferenceByPreferenceId(Long preferenceId);
}
