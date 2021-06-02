package com.lguplus.homeshoppingmoa;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@Slf4j
public class Application {

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
        int numOfCores = Runtime.getRuntime().availableProcessors();
        log.debug("> 가용 프로세스 수: {}", numOfCores);
    }

}
