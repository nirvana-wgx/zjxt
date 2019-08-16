package com.ctevs.api.controller;

import com.ctevs.common.api.DemoApi;
import com.ctevs.common.vo.DemoVo;
import com.ctevs.po.DemoPo;
import com.ctevs.service.DemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hu_qzh
 * @version : 1.0
 * @Date Created on 2019/8/16 14:28
 * @Description : DemoController
 */
@Api(tags = "Demo信息管理")
@RequestMapping("/v1/demo")
@RestController
public class DemoController {

    @Autowired
    DemoService demoService;

    @GetMapping(DemoApi.DEMO_QUERY)
    @ApiOperation(value = "信息查询",produces = "application/json")
    @ApiResponse(code = 200, message = "信息查询", response = DemoVo.class)
    public DemoVo getInformationDemo(DemoVo demoVo){
        DemoPo demoPo = new DemoPo();

        //输入赋值
        demoPo.setUserId(demoVo.getUserId());

        //查询
        demoPo = demoService.queryDemo(demoPo);

        //输出赋值
        demoVo.setUserId(demoPo.getUserId());
        demoVo.setUserName(demoPo.getUserName());
        demoVo.setAge(demoPo.getAge());

        return demoVo;
    }

    @PostMapping(DemoApi.DEMO_INSERT)
    @ApiOperation(value = "信息插入",produces = "application/json")
    @ApiResponse(code = 200,message = "信息插入")
    public void insertInformationDemo(DemoVo demoVo){
        DemoPo demoPo = new DemoPo();

        demoPo.setUserId(demoVo.getUserId());
        demoPo.setUserName(demoVo.getUserName());
        demoPo.setAge(demoVo.getAge());

        try {
            demoService.insertDemo(demoPo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
