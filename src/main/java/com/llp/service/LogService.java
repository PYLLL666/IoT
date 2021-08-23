package com.llp.service;

import com.llp.pojo.DoorLog;
import com.llp.pojo.LampLog;
import com.llp.pojo.WateringLog;

import java.util.List;

/**
 * description:日志
 * create by Administrator
 * create on 2021/07/11/16:24
 */
public interface LogService {
    /**
     * 存储门状态日志
     * @param currentTime
     */
    void storeDoorLog(String currentTime,String doorSwitch);

    /**
     * 存储灯状态日志
     * @param currentTime
     */
    void storeLampLog(String currentTime,String lampSwitch);

    /**
     * 存储浇水日志
     * @param currentTime
     */
    void storeWateringLog(String currentTime);

    /**
     * 查询浇水日志
     * @return
     */
    List<WateringLog> findWateringLog();

    /**
     * 查询灯日志
     * @return
     */
    List<LampLog> findLampLog();

    /**
     * 查询门日志
     * @return
     */
    List<DoorLog> findDoorLog();
}
