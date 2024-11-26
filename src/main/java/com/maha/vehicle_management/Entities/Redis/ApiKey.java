package com.maha.vehicle_management.Entities.Redis;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;
import java.util.Date;

@RedisHash(value = "ApiKey", timeToLive = 24 * 60 * 60)
public class ApiKey {
    @Id
    private String key;
    private String uid;

    public ApiKey(){}

    public ApiKey(String key, String uid){
        this.key = key;
        this.uid = uid;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
