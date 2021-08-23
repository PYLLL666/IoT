package com.llp.dao;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.llp.sdk.requestEntity.SignInEntity;
import com.llp.sdk.responseEntity.User;
import com.llp.sdk.responseEntity.base.BaseResponseEntity;
import com.llp.sdk.util.MyObserver;
import com.llp.sdk.util.NetWorkBusiness;
import org.springframework.stereotype.Repository;
import rx.Observer;


/**
 * description:
 * create by Administrator
 * create on 2021/06/29/12:24
 */
@Repository
public class MsgDao{
    private static NetWorkBusiness netWorkBusiness;


    private static void loginNlecloud() {
        final String baseUrl = "http://api.nlecloud.com/";
        NetWorkBusiness loginNetWorkBs = new NetWorkBusiness("", baseUrl);
        loginNetWorkBs.signIn(new SignInEntity("18974748772", "maguazhandui"), new MyObserver<User>() {
            @Override
            public void onNext(BaseResponseEntity<User> baseResponseEntity) {
                String token = baseResponseEntity.getResultObj().getAccessToken();
                netWorkBusiness = new NetWorkBusiness(token, baseUrl);
            }
        });
    }


    /**
     * 获得园内温度
     * @return
     */
    public String findTemperature() {
        final String[] result = {""};
        loginNlecloud();
        netWorkBusiness.getSensor("265718", "Temp_outside", new Observer<BaseResponseEntity>(){
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable throwable) {
            }
            @Override
            public void onNext(BaseResponseEntity baseResponseEntity) {
                Gson gson = new Gson();
                String s = gson.toJson(baseResponseEntity.getResultObj());
                JsonObject jo = (JsonObject) new JsonParser().parse(s);
                int aa= jo.get("Value").getAsInt();
                result[0]=aa+"";
            }
        });
        return result[0];
    }

    /**
     * 获得土壤湿度
     * @return
     */
    public String findHumidity() {
        final String[] result = {""};
        loginNlecloud();
        netWorkBusiness.getSensor("265718", "Humi_Soil", new Observer<BaseResponseEntity>(){
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable throwable) {
            }
            @Override
            public void onNext(BaseResponseEntity baseResponseEntity) {
                Gson gson = new Gson();
                String s = gson.toJson(baseResponseEntity.getResultObj());
                JsonObject jo = (JsonObject) new JsonParser().parse(s);
                int aa= jo.get("Value").getAsInt();
                result[0]=aa+"";
            }
        });
        return result[0];
    }

    /**
     * 获得灯的状态
     * @return
     */
    public String getLampSwitch() {
        final String[] result = {""};
        loginNlecloud();
        netWorkBusiness.getSensor("265718", "Light", new Observer<BaseResponseEntity>(){
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable throwable) {
            }
            @Override
            public void onNext(BaseResponseEntity baseResponseEntity) {
                Gson gson = new Gson();
                String s = gson.toJson(baseResponseEntity.getResultObj());
                JsonObject jo = (JsonObject) new JsonParser().parse(s);
                result[0]= jo.get("Value").getAsString();
            }
        });
        return result[0];
    }

    /**
     * 从云平台获取光敏开关状态
     * @return
     */
    public String getPhotosensitiveSwitch() {
        final String[] result = {""};
        loginNlecloud();
        netWorkBusiness.getSensor("265718", "Light_mode", new Observer<BaseResponseEntity>(){
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable throwable) {
            }
            @Override
            public void onNext(BaseResponseEntity baseResponseEntity) {
                Gson gson = new Gson();
                String s = gson.toJson(baseResponseEntity.getResultObj());
                JsonObject jo = (JsonObject) new JsonParser().parse(s);
                result[0]= jo.get("Value").getAsString();
            }
        });
        return result[0];
    }

    /**
     * 给云平台发送数据来控制灯的开关
     * @param lampStatus
     */
    public void postLampSwitch(String lampStatus) {
        int a;
        if (lampStatus.equals("true")){
            a=1;
        }else {
            a=0;
        }
            netWorkBusiness.control("265718", "clight", a, new MyObserver());
    }

    /**
     * 获得门的状态
     * @return
     */
    public String getDoorSwitch() {
        final String[] result = {""};
        loginNlecloud();
        netWorkBusiness.getSensor("265718", "Switch", new Observer<BaseResponseEntity>(){
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable throwable) {
            }
            @Override
            public void onNext(BaseResponseEntity baseResponseEntity) {
                Gson gson = new Gson();
                String s = gson.toJson(baseResponseEntity.getResultObj());
                JsonObject jo = (JsonObject) new JsonParser().parse(s);
                result[0] = jo.get("Value").getAsString();
            }
        });
        return result[0];
    }

    /**
     * 向云平台传输光敏状态
     * @param
     */
    public void postPhotosensitiveSwitch(String photosensitiveSwitch) {
        boolean ss = Boolean.parseBoolean(photosensitiveSwitch);
        netWorkBusiness.control("265718", "Light_mode", ss, new MyObserver());
    }

    /**
     * 给云平台发送数据来控制门的开关
     */
    public void postDoorSwitch(Boolean doorStatus) {
        netWorkBusiness.control("265718", "Switch", doorStatus, new MyObserver());
    }

    /**
     * 给云平台发送数据来控制水阀的开关
     */
    public void postWatering() {
        Boolean water = true;
        netWorkBusiness.control("265718", "Watering", water, new MyObserver());
    }

    /**
     * 获得湿度
     * @return
     */
    public String findHumidity_outside() {
        final String[] result = {""};
        loginNlecloud();
        netWorkBusiness.getSensor("265718", "Humi_outside", new Observer<BaseResponseEntity>(){
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable throwable) {
            }
            @Override
            public void onNext(BaseResponseEntity baseResponseEntity) {
                Gson gson = new Gson();
                String s = gson.toJson(baseResponseEntity.getResultObj());
                JsonObject jo = (JsonObject) new JsonParser().parse(s);
                int aa= jo.get("Value").getAsInt();
                result[0]=aa+"";
            }
        });
        return result[0];
    }

    /**
     * 获得空气质量
     * @return
     */
    public String findMQ() {
        final String[] result = {""};
        loginNlecloud();
        netWorkBusiness.getSensor("265718", "MQ", new Observer<BaseResponseEntity>(){
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable throwable) {
            }
            @Override
            public void onNext(BaseResponseEntity baseResponseEntity) {
                Gson gson = new Gson();
                String s = gson.toJson(baseResponseEntity.getResultObj());
                JsonObject jo = (JsonObject) new JsonParser().parse(s);
                int aa= jo.get("Value").getAsInt();
                result[0]=aa+"";
            }
        });
        return result[0];
    }



}
