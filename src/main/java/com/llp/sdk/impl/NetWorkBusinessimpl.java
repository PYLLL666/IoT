package com.llp.sdk.impl;

import com.llp.sdk.responseEntity.base.BaseResponseEntity;
import com.llp.sdk.util.NetWorkBusiness;
import rx.Observer;

/**
 * @program: JAVA-SDK-master
 * @description:
 * @author: Dale
 * @create: 2021-06-25 10:21
 */
public class NetWorkBusinessimpl extends NetWorkBusiness {
    public NetWorkBusinessimpl(String accessToken, String baseUrl) {
        super(accessToken, baseUrl);
    }

    @Override
    public void getSensorData(String deviceId, String ApiTags, String Method, String TimeAgo, String StartDate, String EndDate, String Sort, String PageSize, String PageIndex, Observer<BaseResponseEntity> callback) {
        super.getSensorData(deviceId, ApiTags, Method, TimeAgo, StartDate, EndDate, Sort, PageSize, PageIndex, callback);
    }

}
