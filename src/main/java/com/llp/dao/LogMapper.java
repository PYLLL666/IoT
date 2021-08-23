package com.llp.dao;

import com.llp.pojo.DoorLog;
import com.llp.pojo.LampLog;
import com.llp.pojo.WateringLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * description:数据持久 存储日志
 * create by Administrator
 * create on 2021/07/11/16:23
 */
@Mapper
@Repository
public interface LogMapper {

    /**
     * 存储门状态日志
     * @param currentTime 当前时间
     * @param doorSwitch 当前状态
     */
    @Insert("insert into doorLog (time,status) values(#{currentTime},#{doorSwitch})")
    void storeDoorLog(@Param("currentTime") String currentTime,@Param("doorSwitch") String doorSwitch);

    /**
     * 存储灯状态日志
     * @param currentTime 当前时间
     * @param lampSwitch 当前状态
     */
    @Insert("insert into lampLog (time,status) values(#{currentTime},#{lampSwitch})")
    void storeLampLog(@Param("currentTime") String currentTime,@Param("lampSwitch") String lampSwitch);

    /**
     * 存储浇水日志
     * @param currentTime 当前时间
     */
    @Insert("insert into wateringLog (time) values(#{currentTime})")
    void storeWateringLog(String currentTime);

    /**
     * 查询浇水日志
     * @return
     */
    @Select("select * from wateringLog order by id desc")
    List<WateringLog> findWateringLog();

    /**
     * 查询灯开关日志
     * @return
     */
    @Select("select * from lampLog order by id desc")
    List<LampLog> findLampLog();

    /**
     * 查询门开关
     * @return
     */
    @Select("select * from doorLog order by id desc")
    List<DoorLog> findDoorLog();
}
