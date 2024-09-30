package org.pj.redisspringboot.Model.Request;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@RedisHash("User")
public class User implements Serializable {

    @Id
    private String id;
    private String name;
    private String email;
}

