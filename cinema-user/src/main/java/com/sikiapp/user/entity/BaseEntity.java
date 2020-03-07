/**
 * projectName: redis
 * fileName: BaseEntity.java
 * packageName: com.sikiapp.redis.entity
 * date: 2019-06-03 下午4:40
 * copyright(c) 2018-2028 深圳识迹科技有限公司
 */
package com.sikiapp.user.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @className: BaseEntity
 * @packageName: com.sikiapp.redis.entity
 * @description: 基础父类
 * @author: Robert
 * @data: 2019-06-03 下午4:40
 * @version: V1.0
 **/
@Data
@Accessors(chain = true)
public class BaseEntity<T> {
//    @TableId(value = "ID", type = IdType.AUTO)
//    protected Long id;

    protected Serializable pkVal() {
        return null;
    }
}