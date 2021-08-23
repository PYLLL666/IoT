package com.llp.service;

import com.llp.dao.MsgDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description:
 * create by Administrator
 * create on 2021/07/01/11:43
 */
@Service
public class EnvironmentServiceImpl implements EnvironmentService {
    @Autowired
    MsgDao msgDao;

    /**
     * 获得园内温度
     * @return
     */
    @Override
    public String findTemperature() {
        return msgDao.findTemperature();
    }

    /**
     * 获得土壤湿度
     * @return
     */
    @Override
    public String findHumidity() {
        return msgDao.findHumidity();
    }

    @Override
    public String findHumidity_outside() {
        return msgDao.findHumidity_outside();
    }

    @Override
    public String findMQ() {
        return msgDao.findMQ();
    }
}
