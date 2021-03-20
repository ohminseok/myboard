package com.myboard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.myboard.domain.MemberDTO;

@Mapper
public interface MemberMapper {
	
	public int insertMember(MemberDTO member);
	
	public List<MemberDTO> listMember();
	
	public int totalMemberCount();

}
