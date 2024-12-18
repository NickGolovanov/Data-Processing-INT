package com.example.nefix.liveinfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiveInfoRepository extends JpaRepository<LiveInfo, Long>
{
}
