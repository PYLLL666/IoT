package com.llp.controller;

import com.llp.pojo.DoorLog;
import com.llp.pojo.LampLog;
import com.llp.pojo.WateringLog;
import com.llp.service.ControllerService;
import com.llp.service.EnvironmentService;
import com.llp.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * description:
 * create by Administrator
 * create on 2021/07/01/12:04
 */
@Controller
public class MyController {

    //纵坐标
    String[] arr1=new String[20];//温度
    String[] arr33=new String[20];//空气质量
    String[] arr44=new String[20];//室外湿度
    String[] arr2=new String[20];
    //环境接口
    @Autowired
    EnvironmentService environmentService;

    //控制器接口
    @Autowired
    ControllerService controllerService;

    //日志接口
    @Autowired
    LogService logService;

    /**
     * 灯页面
     * @return
     */
    @RequestMapping("/lamp")
    public String toLamp(HttpServletRequest request,Model model){
        //获得灯的目前状态
        String status = controllerService.getLampSwitch();
        System.out.println("灯从云平台获取的状态"+status);
        model.addAttribute("status",status);
        String lampSwitch = request.getParameter("lampSwitch");

        String photosensitiveStatus=controllerService.getPhotosensitiveSwitch();
        model.addAttribute("photosensitiveStatus",photosensitiveStatus);
        String photosensitiveSwitch=request.getParameter("photosensitiveSwitch");

        if(lampSwitch!=null){
//            if (lampSwitch.equals("true")){
//                lampStatus=true;
//                System.out.println(lampStatus);
//            }else {
//                lampStatus=false;
//                System.out.println("000l"+lampStatus);
//            }
//            System.out.println("给云平台发送的灯的状态lampSwitch:"+lampSwitch);
//            System.out.println("给云平台发送的灯的状态lampStatus:"+lampStatus);
            controllerService.postLampSwitch(lampSwitch);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String currentTime = df.format(new Date());
            logService.storeLampLog(currentTime,lampSwitch);
        }
        if(photosensitiveSwitch!=null){
            controllerService.postPhotosensitiveSwitch(photosensitiveSwitch);
        }

        return "lamp";
    }

    /**
     * 园内环境
     * @param model
     * @return
     */
    @RequestMapping("/campusEnvironment")
    public String toSoil(Model model){
        String[] arr = getTemperature(arr1);//温度
        String[] arr2 = getHumidity_outside(arr44);//湿度
        String[] arr3 = getMQ(arr33);//空气质量
        //横坐标
        Date date = new Date();
        int hour = date.getHours();
        String hour1 = String.valueOf(hour);
        String[] minute = {"00","03","06","09","12","15","18","21","24","27","30","33","36","39","42","45","48","51","54","57"};
        for(int i = 0; i < minute.length ;i++){
            minute[i] = hour1+":"+minute[i];
        }
        model.addAttribute("arr",arr);
        model.addAttribute("arr2",arr2);
        model.addAttribute("arr3",arr3);
        model.addAttribute("minute",minute);
        return "campusEnvironment";
    }


    /**
     * 土壤环境
     * @param model
     * @return
     */
    @RequestMapping("/soilEnvironment")
    public String toSoilEnvironment(Model model,HttpServletRequest request){
        String[] arr = getHumidity(arr2);
        //横坐标
        Date date = new Date();
        int hour = date.getHours();
        String hour1 = String.valueOf(hour);
        String[] minute = {"00","03","06","09","12","15","18","21","24","27","30","33","36","39","42","45","48","51","54","57"};
        for(int i = 0; i < minute.length ;i++){
            minute[i] = hour1+":"+minute[i];
        }

        model.addAttribute("arr",arr);
        model.addAttribute("minute",minute);

        String watering = request.getParameter("watering");
        if(watering!=null){
            controllerService.postWatering();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String currentTime = df.format(new Date());
            logService.storeWateringLog(currentTime);
        }
        return "soilEnvironment";
    }


    /**
     * 小车页面
     * @return
     */
    @RequestMapping("/car")
    public String toCar(){
        return "car";
    }


    /**
     * 门禁系统
     * @return
     */
    @RequestMapping("/accessSystem")
    public String toAccessSystem(Model model,HttpServletRequest request){
        //获得门的目前状态
        String status = controllerService.getDoorSwitch();
        model.addAttribute("status",status);

        String doorSwitch = request.getParameter("doorSwitch");
        if (doorSwitch!=null){
            boolean doorStatus = Boolean.parseBoolean(doorSwitch);
            System.out.println("给云平台发送的灯的状态:"+doorStatus);
            controllerService.postDoorSwitch(doorStatus);

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String currentTime = df.format(new Date());
            logService.storeDoorLog(currentTime,doorSwitch);
        }
        return "accessSystem";
    }

    /**
     * 日志
     * @return
     */
    @RequestMapping("/log")
    public String toLog(){
        return "log";
    }

    /**
     * 浇水日志日志
     * @return
     */
    @RequestMapping("/wateringLog")
    public String toWateringLog(Model model){
        List<WateringLog> wateringLogList = logService.findWateringLog();
        model.addAttribute("wateringLogList",wateringLogList);
        return "log/wateringLog";
    }

    /**
     * 灯开关日志日志
     * @return
     */
    @RequestMapping("/lampLog")
    public String toLampLog(Model model){
        List<LampLog> lampLogList = logService.findLampLog();
        model.addAttribute("lampLogList",lampLogList);
        return "log/lampLog";
    }

    /**
     * 门开关日志日志
     * @return
     */
    @RequestMapping("/doorLog")
    public String toDoorLog(Model model){
        List<DoorLog> doorLogList = logService.findDoorLog();
        model.addAttribute("doorLogList",doorLogList);
        return "log/doorLog";
    }



    /**
     * 得到温度
     * @param arr1
     * @return
     */
    String[] getTemperature (String[] arr1){
        Date date = new Date();
        int minutes = date.getMinutes()/3;
        for(int i = minutes;i < 20; i++){
            if(i==0){
                for(int x = 0;x<20;x++){
                    arr1[x]="";
                }
                arr1[0]=environmentService.findTemperature();
//                if(arr1[0].equals("")||arr1[0]==null){
//                    arr1[0]="31";
//                }
                break;
            }else if(arr1[i]==null){
                arr1[i]=environmentService.findTemperature();
//                if(arr1[i].equals("")||arr1[i]==null){
//                    arr1[i]="29";
//                }
                if(arr1[i].equals("1")){
                    arr1[i]=arr1[i-1];
                }
                break;
            }
            break;
        }
        return arr1;
    }

    /**
     * 得到室外湿度
     * @param arr1
     * @return
     */
    String[] getHumidity_outside (String[] arr1){
            Date date = new Date();
            int minutes = date.getMinutes()/3;
            for(int i = minutes;i < 20; i++){
                if(i==0){
                    for(int x = 0;x<20;x++){
                        arr1[x]="";
                    }
                    arr1[0]=environmentService.findHumidity_outside();
//                    if(arr1[0].equals("")||arr1[0]==null){
//                        arr1[0]="71";
//                    }
                    break;
                }else if(arr1[i]==null){
                    arr1[i]=environmentService.findHumidity_outside();
//                    if(arr1[i].equals("")||arr1[i]==null){
//                        arr1[i]="73";
//                    }
                    if(arr1[i].equals("1")){
                        arr1[i]=arr1[i-1];
                    }
                    break;
                }
                break;
            }
            return arr1;
        }

    /**
     * 获得空气质量
     * @param arr1
     * @return
     */
    String[] getMQ (String[] arr1){
            Date date = new Date();
            int minutes = date.getMinutes()/3;
            for(int i = minutes;i < 20; i++){
                if(i==0){
                    for(int x = 0;x<20;x++){
                        arr1[x]="";
                    }
                    arr1[0]=environmentService.findMQ();
                    break;
                }else if(arr1[i]==null){
                    arr1[i]=environmentService.findMQ();
//                    if(arr1[i].equals("1")){
//                        if (i%3==1){
//                            arr1[i]="61";
//                        }else if (i%3==2){
//                            arr1[i]="65";
//                        }else{
//                            arr1[i]="64";
//                        }
//
//                    }
                    break;
                }
                break;
            }
            return arr1;
        }

    /**
     * 获得土壤环境
     * @param arr
     * @return
     */
    String[] getHumidity (String[] arr){
        Date date = new Date();
        int minutes = date.getMinutes()/3;
        for(int i = minutes;i < 20; i++){
            if(i==0){
                for(int x = 0;x<20;x++){
                    arr[x]="";
                }
                arr[0]=environmentService.findHumidity();
//                if(arr[0].equals("")||arr[0]==null){
//                    arr[0]="2";
//                }
                break;
            }else if(arr[i]==null){
                arr[i]=environmentService.findHumidity();
                if(arr[i].equals("1")){
                    arr[i]=arr[i-1];
                }
//                if(arr[i].equals("")||arr[i]==null){
//                    arr[i]="2";
//                }
                break;
            }
            break;
        }
        return arr;
    }



}
