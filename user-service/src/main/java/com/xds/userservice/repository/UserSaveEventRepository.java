package com.xds.userservice.repository;

import com.xds.userservice.entity.UserSaveEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSaveEventRepository extends JpaRepository<UserSaveEvent, Long> {
    List<UserSaveEvent> findByStatus(int i);
}
