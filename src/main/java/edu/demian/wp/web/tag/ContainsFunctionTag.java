package edu.demian.wp.web.tag;

import java.util.Collection;

public class ContainsFunctionTag {
    public static boolean contains(Collection collection, Object o) {
        return collection.contains(o);
    }
}
