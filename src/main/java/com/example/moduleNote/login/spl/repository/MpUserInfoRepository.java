package com.example.moduleNote.login.spl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.moduleNote.login.spl.entity.MpUserInfo;

@Repository
public interface MpUserInfoRepository extends JpaRepository<MpUserInfo, String> {
}
