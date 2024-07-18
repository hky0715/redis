package com.example.redis.service;

import com.example.redis.aggregate.Member;

public interface RedisTestService {
    void joinMember(Member member);
    Member updateMember(Member member, Long memberId);
    Member getMemberInfo(Long memberId);
    void removeMember(Long memberId);
}
