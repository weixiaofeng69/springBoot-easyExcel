package cn.com.easyExcel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface BaseDaoMapper<T> extends BaseMapper<T> {
    void batchInsert(List<T> list);
}
