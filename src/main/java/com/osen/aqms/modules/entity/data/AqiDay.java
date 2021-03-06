package com.osen.aqms.modules.entity.data;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * User: PangYi
 * Date: 2019-11-29
 * Time: 14:59
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("aqi_day")
public class AqiDay extends Model<AqiDay> implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String deviceNo;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;

    private int aqi;

    private String quality;

    private int level;

    private String pollute;

    private BigDecimal data;

    private String tips;

    private BigDecimal pm25 = new BigDecimal(0);

    private BigDecimal pm10= new BigDecimal(0);

    private BigDecimal so2= new BigDecimal(0);

    private BigDecimal no2= new BigDecimal(0);

    private BigDecimal co= new BigDecimal(0);

    private BigDecimal o3= new BigDecimal(0);

    private BigDecimal voc= new BigDecimal(0);

    private int pm25Index;

    private int pm10Index;

    private int so2Index;

    private int no2Index;

    private int coIndex;

    private int o3Index;
 
}
