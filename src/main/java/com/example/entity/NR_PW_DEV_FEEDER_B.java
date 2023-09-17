package com.example.entity;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

/**
 * 对应1.1.1	馈线
 * 配网馈线
 * NR_PW_DEV_FEEDER_B
 */
@Node("NR_PW_DEV_FEEDER_B")
@Data
public class NR_PW_DEV_FEEDER_B {
    /**
     * OBJ_ID
     */
    private String OBJ_ID;
    /**
     * ID
     */
    private String ID;
    /**
     * 线路名称
     */
    private String NAME;
    /**
     * 电压等级
     */
    private Integer VOLTAGE_TYPE;

}
