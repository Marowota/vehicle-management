package com.maha.vehicle_management.Repositories.Redis;

import com.maha.vehicle_management.Entities.Redis.ApiKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiKeyRepository extends CrudRepository<ApiKey, String> {
    ApiKey findByKey(String key);
    void removeByUid(String uid);
}
