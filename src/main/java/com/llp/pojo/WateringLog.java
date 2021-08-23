package com.llp.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description:浇水日志实体
 * create by Administrator
 * create on 2021/07/11/18:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WateringLog {
    private Integer id;
    private String time;
}
