package com.llp.service;

/**
 * description:
 * create by Administrator
 * create on 2021/07/11/15:36
 */
public interface ControllerService {

    /**
     * 从云平台获取灯的状态
     * @return
     */
    String getLampSwitch();

    /**
     * 给云平台发送数据控制灯的状态
     */
    void postLampSwitch(String lampStatus);

    /**
     * 从云平台获取门的状态
     * @return
     */
    String getDoorSwitch();

    /**
     * 给云平台发送数据控制门的状态
     * @param doorStatus
     */
    void postDoorSwitch(boolean doorStatus);

    /**
     * 控制水阀状态
     */
    void postWatering();

    /**
     * 光敏状态
     * @return
     */
    String getPhotosensitiveSwitch();

    /**
     * 向云平台传输光敏状态
     * @param photosensitiveSwitch
     */
    void postPhotosensitiveSwitch(String photosensitiveSwitch);
}
