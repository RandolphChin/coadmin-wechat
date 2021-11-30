package com.gitee.coadmin.modules.mq.service;

import cn.hutool.core.util.ObjectUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gitee.coadmin.enums.DeviceRespEnum;
import com.gitee.coadmin.enums.DeviceStatusEnum;
import com.gitee.coadmin.modules.equip.domain.Device;
import com.gitee.coadmin.modules.equip.service.DeviceService;
import com.gitee.coadmin.modules.equip.service.dto.DeviceDTO;
import com.gitee.coadmin.modules.equip.service.dto.DeviceCreateReceiveDTO;
import com.gitee.coadmin.modules.equip.service.dto.DeviceRespDTO;
import com.gitee.coadmin.modules.equip.service.dto.DeviceStatusReceiveDTO;
import com.gitee.coadmin.modules.mq.config.MQConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;

import org.bouncycastle.util.Times;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;

@Service
@Slf4j
public class MQReceiver {


    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private MQSender mqSender;

    // 设备创建
    @RabbitListener(queues = MQConfig.DEVICE_CREATE_QUEUE)
    public void deviceCreate(Message message, Channel channel) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        String msg = new String(message.getBody(),"gbk");
        log.info("****rabbit****receive create****** {}",msg);
        DeviceCreateReceiveDTO deviceCreateReceiveDTO =objectMapper.readValue(msg, DeviceCreateReceiveDTO.class);
        this.insertDevice(deviceCreateReceiveDTO);
    }

    private void insertDevice(DeviceCreateReceiveDTO deviceCreateReceiveDTO) throws JsonProcessingException {
        DeviceDTO deviceDTO = new DeviceDTO();
        deviceDTO.setCreateTime(new Timestamp(System.currentTimeMillis()));
        deviceDTO.setDeviceId(deviceCreateReceiveDTO.getDeviceId());
        deviceDTO.setOpenid(deviceCreateReceiveDTO.getDeviceOwner());
        deviceDTO.setType(deviceCreateReceiveDTO.getDeviceType());
        deviceDTO.setStatus(DeviceStatusEnum.findBoolean(DeviceStatusEnum.OFF_LINE.ordinal()));
        Boolean pass =this.checkParams(deviceCreateReceiveDTO);
        this.notifyClient(pass);
        if(pass){
            deviceService.insert(deviceDTO);
        }
    }

    private Boolean checkParams(DeviceCreateReceiveDTO deviceCreateReceiveDTO){
        Device device =deviceService.getByDeviceId(deviceCreateReceiveDTO.getDeviceId());
        if(ObjectUtil.isNull(device)){
            return true;
        }
        return false;
    }

    private void notifyClient(Boolean pass) throws JsonProcessingException {
        DeviceRespDTO deviceRespDTO = null;
        if(pass){
            deviceRespDTO = DeviceRespDTO.build(DeviceRespEnum.SUCCESS.name(),"");
        }else{
            deviceRespDTO = DeviceRespDTO.build(DeviceRespEnum.ERROR.name(),"设备重复注册");

        }
        mqSender.deviceResponse(objectMapper.writeValueAsString(deviceRespDTO));
    }

    // 设备状态上报
    @RabbitListener(queues = MQConfig.DEVICE_STATUS_QUEUE)
    public void devicesStatus(Message message, Channel channel) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        String msg = new String(message.getBody(),"gbk");
        log.info("****rabbit****receive status****** {}",msg);
        DeviceStatusReceiveDTO deviceStatusReceiveDTO =objectMapper.readValue(msg, DeviceStatusReceiveDTO.class);
        Device device =deviceService.getByDeviceId(deviceStatusReceiveDTO.getDeviceId());
        device.setStatus(DeviceStatusEnum.findBoolean(deviceStatusReceiveDTO.getDeviceStatus()));
        device.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        this.notifyClient(true);
        deviceService.saveOrUpdate(device);
    }
}
