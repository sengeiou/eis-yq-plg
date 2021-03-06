package com.prolog.eis.model.repair;

import java.util.Date;
import java.io.Serializable;
import com.prolog.framework.core.annotation.Column;
import com.prolog.framework.core.annotation.Id;
import com.prolog.framework.core.annotation.Table;
import io.swagger.annotations.ApiModelProperty;

/**
 * 补货计划(RepairPlan)实体类
 *
 * @author panteng
 * @since 2020-04-25 11:01:43
 */
@Table("repair_plan_his")
public class RepairPlanHis implements Serializable {
    private static final long serialVersionUID = 967379061136634694L;

    @Id
    @ApiModelProperty("id")
    private Integer id;
    
    @Column("goods_id")
    @ApiModelProperty("商品id")
    private Integer goodsId;
    
    @Column("operator")
    @ApiModelProperty("操作人")
    private String operator;
    
    @Column("create_time")
    @ApiModelProperty("创建时间（YYYY-MM-DD HH:MM:SS）")
    private Date createTime;
    


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}