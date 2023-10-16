package com.workday.rq.impl;

import java.util.ArrayList;

class CustomBinarySearch {
    
    static short searchFromValue(ArrayList<DataNode> dataStore, long value, boolean inclusive){

        short start = 0;
        short end = (short)(dataStore.size() - 1);
        short mid = -1;

        if(dataStore.get(end).value < value){
            return -1;
        }

        if(!inclusive && value != Long.MAX_VALUE){
            value = value + 1;
        }
        else if(!inclusive && value == Long.MAX_VALUE){
            return -1;
        }

        //Try finding first occurrence of search value
        while(start <= end){

            mid = (short)(start + ((end-start)/2));

            long midNodeValue = dataStore.get(mid).value;

            if(midNodeValue < value){
                start = (short)(mid + 1);
            }
            else if(midNodeValue >= value){
                end = (short)(mid - 1);
            }
        }

        if(mid >= 0 && dataStore.get(mid).value >= value){
            return mid;
        }

        return start;
    }

    static short searchToValue(ArrayList<DataNode> dataStore, long value, boolean inclusive){

        short start = 0;
        short end = (short)(dataStore.size() - 1);
        short mid = -1;

        if(dataStore.get(start).value > value){
            return -1;
        }

        if(!inclusive && value != Long.MIN_VALUE){
            value = value - 1;
        }
        else if(!inclusive && value == Long.MIN_VALUE){
            return -1;
        }

        //Try finding last occurrence of search value
        while(start <= end){

            mid = (short)(start + ((end - start)/2));

            Long midNodeValue = dataStore.get(mid).value;

            if(midNodeValue <= value){
                start = (short)(mid + 1);
            }
            else if(midNodeValue > value){
                end = (short)(mid - 1);
            }
        }

        if(mid < dataStore.size() && dataStore.get(mid).value <= value){
            return mid;
        }

        return end;
    }
}
