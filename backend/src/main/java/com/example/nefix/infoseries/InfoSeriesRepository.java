package com.example.nefix.infoseries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoSeriesRepository extends JpaRepository<InfoSeries, Long>
{
}
