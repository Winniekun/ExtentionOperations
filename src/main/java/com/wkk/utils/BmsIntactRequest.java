package com.wkk.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * TODO 类描述
 *
 * @author weikunkun
 * @since 2022/3/8
 */
@Data
public class BmsIntactRequest {
    /**
     * 运营商ID
     */
    @JsonProperty("OperatorID")
    private String operatorId;

    /**
     * 充电站ID
     */
    @JsonProperty("StationID")
    private String stationId;

    /**
     * 充电设备接口ID
     */
    @JsonProperty("ConnectorID")
    private String connectorId;

    /**
     * 设备编码
     */
    @JsonProperty("EquipmentID")
    private String equipmentId;

    /**
     * 数据缺失时间
     */
    @JsonProperty("MissingTime")
    private String missingTime;
}
