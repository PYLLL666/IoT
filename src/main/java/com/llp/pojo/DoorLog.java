package com.llp.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description:门日志实体
 * create by Administrator
 * create on 2021/07/11/18:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoorLog {

    private Integer id;
    private String time;
    private String status;

}
