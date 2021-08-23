package com.llp.service;

import com.llp.dao.MsgDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description:控制业务逻辑
 * create by Administrator
 * create on 2021/07/11/15:37
 */
@Service
public class ControllerServiceImpl implements ControllerService{
    @Autowired
    MsgDao msgDao;

    /**
     * 从云平台获取灯的状态
     * @return
     */
    @Override
    public String getLampSwitch() {
        return msgDao.getLampSwitch();
    }

    /**
     * 给云平台发送数据来控制灯的状态
     */
    @Override
    public void postLampSwitch(String lampStatus) {
        msgDao.postLampSwitch(lampStatus);
    }

    /**
     * 从云平台获取门的状态
     * @return
     */
    @Override
    public String getDoorSwitch() {
        return msgDao.getDoorSwitch();
    }

    /**
     * 给云平台发送数据来控制门的状态
     * @param doorStatus
     */
    @Override
    public void postDoorSwitch(boolean doorStatus) {
        msgDao.postDoorSwitch(doorStatus);
    }

    /**
     * 给云平台发送数据来控制水阀的状态
     */
    @Override
    public void postWatering() {
        msgDao.postWatering();
    }

    /**
     * 获得光敏状态
     * @return
     */
    @Override
    public String getPhotosensitiveSwitch() {
        return msgDao.getPhotosensitiveSwitch();
    }

    /**
     * 向云平台传输光敏状态
     * @param photosensitiveSwitch
     */
    @Override
    public void postPhotosensitiveSwitch(String photosensitiveSwitch) {
        msgDao.postPhotosensitiveSwitch(photosensitiveSwitch);
    }
}
