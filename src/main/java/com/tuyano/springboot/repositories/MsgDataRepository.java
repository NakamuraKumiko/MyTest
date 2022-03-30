package com.tuyano.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tuyano.springboot.MsgData;

public interface MsgDataRepository extends JpaRepository<MsgData, Long> {

}
