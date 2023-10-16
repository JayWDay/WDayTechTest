package com.workday.rq.ut;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.workday.rq.impl.RangeContainerFactoryImpl;
import com.workday.rq.interfaces.Ids;
import com.workday.rq.interfaces.RangeContainer;
import com.workday.rq.interfaces.RangeContainerFactory;

public class RangeQueryWithDuplicateValuesTest {
    private  RangeContainer rc;

    @Before 
    public void setUp(){  
        RangeContainerFactory rf = new RangeContainerFactoryImpl();
        rc = rf.createContainer(new long[]{10,12,17,21,2,15,16,21,23,2,12});
    }

    @Test 
    public void rangeBoundariesExistInSet(){ 
        Ids ids = rc.findIdsInRange(12, 21, true, true);
        assertEquals(1, ids.nextId());
        assertEquals(2, ids.nextId());
        assertEquals(3, ids.nextId());
        assertEquals(5, ids.nextId());
        assertEquals(6, ids.nextId());
        assertEquals(7, ids.nextId());
        assertEquals(10, ids.nextId());
        assertEquals(Ids.END_OF_IDS, ids.nextId());
    }

    @Test 
    public void rangeBoundariesDoNotExistInSet(){ 
        Ids ids = rc.findIdsInRange(11, 22, true, true);
        assertEquals(1, ids.nextId());
        assertEquals(2, ids.nextId());
        assertEquals(3, ids.nextId());
        assertEquals(5, ids.nextId());
        assertEquals(6, ids.nextId());
        assertEquals(7, ids.nextId());
        assertEquals(10, ids.nextId());
        assertEquals(Ids.END_OF_IDS, ids.nextId());
    }

    @Test 
    public void maxToMinRange(){ 
        Ids ids = rc.findIdsInRange(Long.MIN_VALUE, Long.MAX_VALUE, true, true);
        assertEquals(0, ids.nextId());
        assertEquals(1, ids.nextId());
        assertEquals(2, ids.nextId());
        assertEquals(3, ids.nextId());
        assertEquals(4, ids.nextId());
        assertEquals(5, ids.nextId());
        assertEquals(6, ids.nextId());
        assertEquals(7, ids.nextId());
        assertEquals(8, ids.nextId());
        assertEquals(9, ids.nextId());
        assertEquals(10, ids.nextId());
        assertEquals(Ids.END_OF_IDS, ids.nextId());
    }
}
