package org.jcommit.util;

import java.util.List;

public final class ListUtil {

    public static String[] listToArray(List<String> list) {
        final String[] array = new String[list.size()];
        int index = 0;

        for (String string : list) {
            array[index++] = string;
        }

        return array;
    }
}
