package com.llp.service;

import com.llp.dao.LogMapper;
import com.llp.pojo.DoorLog;
import com.llp.pojo.LampLog;
import com.llp.pojo.WateringLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description:
 * create by Administrator
 * create on 2021/07/11/16:24
 */
@Service
public class LogServiceImpl implements LogService{
    @Autowired
    LogMapper logMapper;

    /**
     * 存储门状态日志
     * @param currentTime
     */
    @Override
    public void storeDoorLog(String currentTime,String doorSwitch) {
        logMapper.storeDoorLog(currentTime,doorSwitch);
    }

    /**
     * 存储灯状态日志
     * @param currentTime
     */
    @Override
    public void storeLampLog(String currentTime,String lampSwitch) {
        logMapper.storeLampLog(currentTime,lampSwitch);
    }

    /**
     * 存储浇水日志
     * @param currentTime
     */
    @Override
    public void storeWateringLog(String currentTime) {
        logMapper.storeWateringLog(currentTime);
    }

    /**
     * 查询浇水日志
     * @return
     */
    @Override
    public List<WateringLog> findWateringLog() {
        return logMapper.findWateringLog();
    }

    /**
     * 查询灯开关日志
     * @return
     */
    @Override
    public List<LampLog> findLampLog() {
        return logMapper.findLampLog();
    }

    /**
     * 查询门开关
     * @return
     */
    @Override
    public List<DoorLog> findDoorLog() {
        return logMapper.findDoorLog();
    }
}
