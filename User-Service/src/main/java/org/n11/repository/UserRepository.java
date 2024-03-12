package org.n11.repository;

import org.n11.entity.User;
import org.n11.entity.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Modifying
    @Query("UPDATE User u SET u.status = 'ACTIVE'  WHERE u.id = :id")
    void changeStatusToActive(@Param("id") Long id);

    @Modifying
    @Query("UPDATE User u SET u.status = 'DEACTIVE'  WHERE u.id = :id")
    void changeStatusToDeactive(@Param("id") Long id);
}
