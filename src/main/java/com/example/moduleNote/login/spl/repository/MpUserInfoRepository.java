package com.example.moduleNote.login.spl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.example.moduleNote.login.spl.entity.MpUserInfo;

@Repository
public interface MpUserInfoRepository extends JpaRepository<MpUserInfo, Integer> {
	MpUserInfo findByUserId(String userid);
}
