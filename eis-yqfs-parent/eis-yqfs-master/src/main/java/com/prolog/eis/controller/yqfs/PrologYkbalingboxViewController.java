package com.prolog.eis.controller.yqfs;

import com.prolog.eis.dto.base.BasePagerDto;
import com.prolog.eis.dto.base.DataEntity;
import com.prolog.eis.dto.base.DataEntityCollectionDto;
import com.prolog.eis.service.masterbase.BasePagerService;
import com.prolog.eis.util.PrologApiJsonHelper;
import com.prolog.framework.common.message.RestMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author LuoLi
 * @version 1.0
 * @date 2020/8/21 11:28
 */
@RestController
@Api(tags = "YkbalingboxView管理")
@RequestMapping("/api/v1/master/ykbalingboxview")
public class PrologYkbalingboxViewController{
    @Autowired
    private BasePagerService basePagerService;

    @ApiOperation(value = "加载分页", notes = "加载分页")
    @PostMapping("/loadPager")
    public RestMessage<DataEntityCollectionDto> loadPager(@RequestBody String json) throws Exception{

        String columns = this.getColumns();
        String tableName = this.getTableName();
        PrologApiJsonHelper helper = PrologApiJsonHelper.createHelper(json);
        Map<String, Object> reStr = helper.getPagerDto(columns, tableName, "condition", BasePagerDto.class);
        // 获得拼装后的JSON对象
        int startRowNum = (int)reStr.get("startRowNum");
        int endRowNum = (int)reStr.get("endRowNum");
        String condition = reStr.get("conditions").toString();
        String orders = reStr.get("orders").toString();
        List<DataEntity> rows = basePagerService.getPagers(columns, tableName, condition, orders, startRowNum, endRowNum);

        DataEntityCollectionDto collectionDto = new DataEntityCollectionDto();
        collectionDto.setRows(rows);
        return RestMessage.newInstance(true, "查询成功", collectionDto);
    }

    @ApiOperation(value = "第一次加载分页", notes = "第一次加载分页")
    @PostMapping("/firstLoadPager")
    public RestMessage<DataEntityCollectionDto> firstLoadPager(@RequestBody String json) throws Exception{

        String columns = this.getColumns();
        String tableName = this.getTableName();
        PrologApiJsonHelper helper = PrologApiJsonHelper.createHelper(json);
        Map<String, Object> reStr = helper.getPagerDto(columns, tableName, "condition", BasePagerDto.class);
        // 获得拼装后的JSON对象
        int startRowNum = (int)reStr.get("startRowNum");
        int endRowNum = (int)reStr.get("endRowNum");
        String condition = reStr.get("conditions").toString();
        String orders = reStr.get("orders").toString();
        // 第一次加载需要获得总数量
        int totalCount = basePagerService.getTotalCount(tableName, condition);
        List<DataEntity> rows = basePagerService.getPagers(columns, tableName, condition, orders, startRowNum, endRowNum);
        DataEntityCollectionDto collectionDto = new DataEntityCollectionDto();
        collectionDto.setRows(rows);
        collectionDto.setTotalCount(totalCount);
        return RestMessage.newInstance(true, "查询成功", collectionDto);
    }

    private String getColumns()
    {
        return "billNo,goodsName,goodsNo,commodityNum,orderBoxNo,balingBoxNo,createTime";
    }

    private String getTableName()
    {
        return "ykbalingboxview";
    }
}
