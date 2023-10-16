package com.workday.rq.impl;

import com.workday.rq.interfaces.RangeContainer;
import com.workday.rq.interfaces.RangeContainerFactory;

public class RangeContainerFactoryImpl implements RangeContainerFactory {

    @Override
    public RangeContainer createContainer(long[] data) {
        return new RangeContainerImpl(data);
    }
}
