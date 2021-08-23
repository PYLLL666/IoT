package com.llp.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description:
 * create by Administrator
 * create on 2021/07/11/18:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LampLog {

    private Integer id;
    private String time;
    private String status;

}
