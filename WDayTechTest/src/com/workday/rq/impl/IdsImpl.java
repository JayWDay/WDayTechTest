package com.workday.rq.impl;

import java.util.ArrayList;
import java.util.Arrays;
import com.workday.rq.interfaces.Ids;

public class IdsImpl implements Ids {

    short[] queryResult;
    short current;

    IdsImpl(ArrayList<DataNode> dataStore, short start, short end){

        if(start == -1 || end == -1 || start > end){
            return;
        }

        queryResult = new short[end-start+1];
        buildQueryResult(dataStore, start, end);
    }

    @Override
    public short nextId() {

        if(queryResult == null || queryResult.length == 0 || current > queryResult.length - 1){
            return -1;
        }

        short currentId = queryResult[current];
        current++;

        return currentId;
    }

    private void buildQueryResult(ArrayList<DataNode> dataStore, short start, short end){

        for(short i = start; i <= end; i++){
            queryResult[i-start] = dataStore.get(i).id;
        }

        Arrays.sort(queryResult);
    }
}
