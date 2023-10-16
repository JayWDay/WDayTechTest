package com.workday.rq.ut;

import static org.junit.Assert.assertEquals;
import java.security.InvalidParameterException;
import org.junit.Before;
import org.junit.Test;
import com.workday.rq.impl.RangeContainerFactoryImpl;
import com.workday.rq.interfaces.Ids;
import com.workday.rq.interfaces.RangeContainer;
import com.workday.rq.interfaces.RangeContainerFactory;

public class RangeQueryBasicTest {
    private  RangeContainer rc;

    @Before 
    public void setUp(){  
        RangeContainerFactory rf = new RangeContainerFactoryImpl();
        rc = rf.createContainer(new long[]{10,12,17,21,2,15,16});
    }

    @Test 
    public void runARangeQuery(){ 
        Ids ids = rc.findIdsInRange(14, 17, true, true);
        assertEquals(2, ids.nextId());
        assertEquals(5, ids.nextId());
        assertEquals(6, ids.nextId());
        assertEquals(Ids.END_OF_IDS, ids.nextId());
 
        ids = rc.findIdsInRange(14, 17, true, false);
        assertEquals(5, ids.nextId());
        assertEquals(6, ids.nextId());
        assertEquals(Ids.END_OF_IDS, ids.nextId());
 
        ids = rc.findIdsInRange(20, Long.MAX_VALUE, false, true);
        assertEquals(3, ids.nextId());
        assertEquals(Ids.END_OF_IDS, ids.nextId());
    }

    @Test 
    public void runARangeQuery1(){ 
        Ids ids = rc.findIdsInRange(14, 18, true, true);
        assertEquals(2, ids.nextId());
        assertEquals(5, ids.nextId());
        assertEquals(6, ids.nextId());
        assertEquals(Ids.END_OF_IDS, ids.nextId());
    }

    @Test
    public void inclusiveOfFromAndTo(){
        Ids ids = rc.findIdsInRange(12, 17, true, true);
        assertEquals(1, ids.nextId());
        assertEquals(2, ids.nextId());
        assertEquals(5, ids.nextId());
        assertEquals(6, ids.nextId());
        assertEquals(Ids.END_OF_IDS, ids.nextId());
    }

    @Test
    public void exclusiveOfFromAndTo(){
        Ids ids = rc.findIdsInRange(12, 17, false, false);
        assertEquals(5, ids.nextId());
        assertEquals(6, ids.nextId());
        assertEquals(Ids.END_OF_IDS, ids.nextId());
    }

    @Test
    public void inclusiveFromAndExclusiveTo(){
        Ids ids = rc.findIdsInRange(12, 17, true, false);
        assertEquals(1, ids.nextId());
        assertEquals(5, ids.nextId());
        assertEquals(6, ids.nextId());
        assertEquals(Ids.END_OF_IDS, ids.nextId());
    }

    @Test
    public void exclusiveFromAndInclusiveTo(){
        Ids ids = rc.findIdsInRange(12, 17, false, true);
        assertEquals(2, ids.nextId());
        assertEquals(5, ids.nextId());
        assertEquals(6, ids.nextId());
        assertEquals(Ids.END_OF_IDS, ids.nextId());
    }

    @Test(expected = InvalidParameterException.class)
    public void fromValueGreaterThanToValue(){
        rc.findIdsInRange(18, 5, true, true);
    }

    @Test
    public void valuesDoNotExistWithinRange1(){
        Ids ids = rc.findIdsInRange(120, 170, true, true);
        assertEquals(Ids.END_OF_IDS, ids.nextId());
    }

    @Test
    public void valuesDoNotExistWithinRange2(){
        Ids ids = rc.findIdsInRange(0, 1, true, true);
        assertEquals(Ids.END_OF_IDS, ids.nextId());
    }

    @Test
    public void equalFromAndToValueInclusive(){
        Ids ids = rc.findIdsInRange(12, 12, true, true);
        assertEquals(1, ids.nextId());
        assertEquals(Ids.END_OF_IDS, ids.nextId());
    }

    @Test
    public void equalFromAndToValueExclusive(){
        Ids ids = rc.findIdsInRange(12, 12, false, false);
        assertEquals(Ids.END_OF_IDS, ids.nextId());
    }

    @Test
    public void rangeIncludesAllItems(){
        Ids ids = rc.findIdsInRange(Long.MIN_VALUE, Long.MAX_VALUE, true, true);
        assertEquals(0, ids.nextId());
        assertEquals(1, ids.nextId());
        assertEquals(2, ids.nextId());
        assertEquals(3, ids.nextId());
        assertEquals(4, ids.nextId());
        assertEquals(5, ids.nextId());
        assertEquals(6, ids.nextId());
        assertEquals(Ids.END_OF_IDS, ids.nextId());
    }
}
