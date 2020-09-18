package com.my.springcloud.common.utils;

import java.util.*;

public class ListUtil {
    public static Map<String, List<String>> compareTwoList(List<String> newList,List<String> oldList){

        Map<String,List<String>> map = new HashMap<>();

        List<String> addIdList = new ArrayList<>();
        List<String> deleteIdList = new ArrayList<>();

        for (String new_id:newList) {
            if(!oldList.contains(new_id)){
                addIdList.add(new_id);
            }
        }
        for (String old_id:oldList) {
            if(!newList.contains(old_id)){
                deleteIdList.add(old_id);
            }
        }
        newList.retainAll(oldList);

        map.put("addIdList",addIdList);
        map.put("deleteIdList",deleteIdList);
        map.put("updateIdList",newList);

        return map;
    }

}
