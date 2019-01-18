package com.ezbuy.eznetlog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ListHelper {

    public static <T> List<T> of(T... array) {
        List<T> list = new ArrayList<>();
        for (T t : array) {
            list.add(t);
        }
        return list;
    }

    public static <T> ArrayList<T> toArrayList(List<T> list) {
        ArrayList<T> arrayList = new ArrayList<>();
        if (list == null) {
            return arrayList;
        }
        arrayList.addAll(list);
        return arrayList;
    }

    public static <T> HashSet<T> toHashSet(List<T> list) {
        HashSet<T> set = new HashSet<>();
        if (list == null) {
            return set;
        }
        set.addAll(list);
        return set;
    }
}
