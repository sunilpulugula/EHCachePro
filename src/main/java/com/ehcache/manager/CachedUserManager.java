package com.ehcache.manager;

import com.ehcache.model.User;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

/**
 * @author <a href="mailto:psunil1278@gmail.com">Sunil Kumar</a>
 * @since 16/11/15
 */
public class CachedUserManager<K extends Integer, V extends User> {

    private final Cache cache;

    public CachedUserManager(Cache cache) {
        this.cache = cache;
    }

    public void add(K k, V v) {
        cache.put(new Element(k, v));
    }

    public V getValue(K k) {
        return (V) cache.get(k).getObjectValue();
    }

    public boolean keyExist(K k) {
        return cache.get(k) == null ? false : true;
    }

    public void deleteElement(K k) {
        if (cache.get(k) != null) {
            cache.remove(k);
        }
    }

    public Cache getCache() {
        return cache;
    }
}
