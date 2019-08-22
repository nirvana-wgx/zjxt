package com.ctevs.dao.shard;

import java.util.Calendar;

import org.springframework.stereotype.Component;

import com.ctevs.common.DateUtil;
import com.ctevs.common.beans.Vo;
import com.ctevs.common.query.QueryBean;
import com.ctevs.po.Entity;
import com.google.code.shardbatis.strategy.ShardStrategy;

@Component
public class MonthStrategy implements ShardStrategy {

    @Override
    public String getTargetTableName(String baseTableName, Object params, String mapperId) {
        
        // 空参数,不进行路由
        if (params == null) {
            return baseTableName;
        }
        boolean isShard = false;
        if(params instanceof String){
            isShard=Boolean.valueOf((String)params);
            
            if(isShard){
                baseTableName=baseTableName+"_"+DateUtil.format(Calendar.getInstance().getTime(),
                        DateUtil.FORMAT_SHORT2).substring(0, 6);
            }
            
        }
        else if (params instanceof QueryBean) {
            QueryBean queryBean = (QueryBean) params;
            isShard =  queryBean.isShard();
            if(isShard){
                String suffix =queryBean.getParaValue("endDate").substring(0,7).replaceAll("-", "");
                baseTableName=baseTableName+"_"+suffix;
                
            }
        }else if (params instanceof Entity) {
            Entity entity =((Entity) params);
            isShard = entity.isShard();
            if(isShard){
                String suffix =entity.getCollectime().substring(0,7).replaceAll("-", "");
                baseTableName=baseTableName+"_"+suffix;
            }
        }
        else if (params instanceof Vo) {
        	Vo po =((Vo) params);
            isShard = po.isShard();
            if(isShard){
                String suffix =po.getCollectime().substring(0,7).replaceAll("-", "");
                baseTableName=baseTableName+"_"+suffix;
            }
        }
        
        

        return baseTableName;
    }

}
