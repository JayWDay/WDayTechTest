package com.workday.rq.interfaces;

/** 
 * a specialized container of records optimized for efficient range queries  
 * on an attribute of the data. 
 */ 
public interface RangeContainer { 
   
  /** 
   * @return the Ids of all instances found in the container that 
   * have data value between fromValue and toValue with optional 
   * inclusivity.  
   */ 
  public Ids findIdsInRange(long fromValue,  
                     long toValue,  
                     boolean fromInclusive,  
                     boolean toInclusive);
}