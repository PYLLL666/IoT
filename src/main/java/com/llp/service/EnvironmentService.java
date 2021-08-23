package com.llp.service;

/**
 * description:业务逻辑层
 * create by Administrator
 * create on 2021/07/01/11:42
 */
public interface EnvironmentService {

    /**
     * 获得园内温度
     * @return
     */
    String findTemperature();

    /**
     * 获得土壤湿度
     * @return
     */
    String findHumidity();

    /**
     * 得到湿度
     * @return
     */
    String findHumidity_outside();

    /**
     * 获得空气质量
     * @return
     */
    String findMQ();
}
