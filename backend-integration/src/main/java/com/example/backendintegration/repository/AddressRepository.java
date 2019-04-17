package com.example.backendintegration.repository;

import com.example.backendintegration.entity.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends CustomizedRepository<Address, Integer> {
    @Query("SELECT a FROM Address a where a.user.id=:uid")
    List<Address> list(@Param("uid") int uid);
    // 先查询与用户的关系，即判断是否有权限
    @Query("FROM Address a WHERE a.user.id=:uid AND a.id=:aid")
    Address find(@Param("uid") int uid, @Param("aid") int aid);
}
