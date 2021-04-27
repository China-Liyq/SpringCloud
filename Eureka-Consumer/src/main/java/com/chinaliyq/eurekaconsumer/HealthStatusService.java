package com.chinaliyq.eurekaconsumer;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Service;

/**
 * @Author: liyq
 * @Description: eureka-sercer
 * @Date: 2021/4/22 - 9:55
 * @Version: 1.0
 **/
@Service
public class HealthStatusService implements HealthIndicator {

    private Boolean status = true;

    public void setStatus(Boolean status) {
        this.status  = status;
    }

    @Override
    public Health health() {
        if(status)
            return new Health.Builder().up().build();
        return new Health.Builder().down().build();
    }

    public String getStatus() {
        return this.status.toString();
    }
}
