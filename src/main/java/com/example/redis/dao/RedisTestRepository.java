package com.example.redis.dao;

import com.example.redis.aggregate.Member;

public interface RedisTestRepository {
    Member save(Member member);
    Member findOne(Long memberId);
    void remove(Member member);
}