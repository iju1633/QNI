package com.pipecoding.ImJaeWook_QNI_Server.repository;

import com.pipecoding.ImJaeWook_QNI_Server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<User, Long> {

    User getUserByUid(String uid);
}