package com.workday.rq.interfaces;

/** 
 * an iterator of Ids 
 */ 
public interface Ids { 
     /** 
   * return the next id in sequence, ­-1 if at end of data. 
   * The ids should be in sorted order (from lower to higher) to facilitate 
   * the query distribution into multiple containers. 
   */ 
 
  public static final short END_OF_IDS = -1;
 
  public short nextId();
}
