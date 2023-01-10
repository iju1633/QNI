package com.pipecoding.ImJaeWook_QNI_Server.repository;

import com.pipecoding.ImJaeWook_QNI_Server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User getUserById(Long userid);
}