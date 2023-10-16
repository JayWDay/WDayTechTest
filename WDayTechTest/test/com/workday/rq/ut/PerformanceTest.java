package com.workday.rq.ut;

import static org.junit.Assert.assertEquals;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.LongStream;
import org.junit.Before;
import org.junit.Test;
import com.workday.rq.impl.RangeContainerFactoryImpl;
import com.workday.rq.interfaces.Ids;
import com.workday.rq.interfaces.RangeContainer;
import com.workday.rq.interfaces.RangeContainerFactory;

public class PerformanceTest {
    private  RangeContainer rc;

    @Before 
    public void setUp(){  
        RangeContainerFactory rf = new RangeContainerFactoryImpl();

        long[] randomArr = LongStream.generate(() -> ThreadLocalRandom.current().nextLong(3459, 19870)).limit(32000).toArray();

        rc = rf.createContainer(randomArr);
    }

    @Test 
    public void runARangeQuery(){

        short counter = 0;
        //Instant start = Instant.now();

        Ids ids = rc.findIdsInRange(Long.MIN_VALUE, Long.MAX_VALUE, true, true);
        short nextId = ids.nextId();
        while(nextId != Ids.END_OF_IDS){
            counter++;
            nextId = ids.nextId();
        }

        //Instant end = Instant.now();

        assertEquals("Counter should be 32000", 32000, counter);
        //long elapsedTime = Duration.between(start, end).toMillis();
    }
}
