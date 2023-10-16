package com.workday.rq.impl;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

import com.workday.rq.interfaces.Ids;
import com.workday.rq.interfaces.RangeContainer;

public class RangeContainerImpl implements RangeContainer {

    ArrayList<DataNode> dataStore;

    RangeContainerImpl(long[] data){
        dataStore = new ArrayList<>();
        buildRangeContainerStore(data);
    }
    
    @Override
    public Ids findIdsInRange(long fromValue,  
                     long toValue,  
                     boolean fromInclusive,  
                     boolean toInclusive){

        if(fromValue > toValue){
            throw new InvalidParameterException();
        }
        
        short fromNode = CustomBinarySearch.searchFromValue(dataStore, fromValue, fromInclusive);
        short toNode = CustomBinarySearch.searchToValue(dataStore, toValue, toInclusive);
        
        Ids ids = new IdsImpl(dataStore, fromNode, toNode);

        return ids;
    }

    private void buildRangeContainerStore(long[] data){

        SortedSet<DataNode> sortedDataSet = new TreeSet<>(new RangeQueryComparator());

        for(short i = 0; i<data.length; i++){
            sortedDataSet.add(new DataNode(i, data[i]));
        }

        for(DataNode node : sortedDataSet){

            dataStore.add(node);
        }
    }
}