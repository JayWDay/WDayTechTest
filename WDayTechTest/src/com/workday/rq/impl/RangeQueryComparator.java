package com.workday.rq.impl;

import java.util.Comparator;

class RangeQueryComparator implements Comparator<DataNode> {

    @Override
    public int compare(DataNode o1, DataNode o2) {
        if(o1.value.compareTo(o2.value) == 0){
            return o1.id.compareTo(o2.id);
        };
        return o1.value.compareTo(o2.value);
    }
    
}
