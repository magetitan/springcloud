package com.my.springcloud.common.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2018-2019, 烟台欣和企业食品有限公司
 * fileName CommonListener
 *
 * @author 18040062
 * @date 2019/9/23 14:18
 * description
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者名称          修改时间           版本号             描述
 */
@Data
public class CommonListener<T> extends AnalysisEventListener<T> {

    private List<T> data = new ArrayList<>();

    @Override
    public void invoke(T object, AnalysisContext context) {
        data.add(object);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
    }



}
