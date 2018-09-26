package org.kingsski.database.dao.caching;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Base abstraction for creating caching implementation of DAOs
 *
 * Not the best approach but has small footprint and is easy to manage for limited DAOs
 */
public abstract class AbstractCachingDao<T> {

    private long interval = 60000;
    private Map<String, ServiceCache<T>> cachedItems = new ConcurrentHashMap<>();

    /**
     * Default constructor
     */
    public AbstractCachingDao() {
    }

    /**
     * Set the minimum interval (in ms) which must elapse before a call is made to the dao to update
     * a cached set of items of type {@code T}
     *
     * @param interval the interval in ms
     */
    public void setInterval(long interval) {
        this.interval = interval;
    }

    /**
     * Converts a type and an array of string arguments to a string key by joining them
     * with "-" as a delimiter
     *
     * @param type the type for this key
     * @param args the arguments for this key
     * @return a key built from the type and args
     */
    private String toKey(String type, String... args) {
        return type + "-" + String.join("-", args);
    }

    /**
     * Retrieves a list of items of type {@code T} from the cache. If the items are null, empty or have not been updated for
     * the required interval they are retrieved from the {@code dao} associated with this class.
     *
     * @param type the type that these items are for
     * @param args the arguments required to retrieve these races
     * @return a list of items of type {@code T}
     */
    protected List<T> getFromCache(String type, String... args) {
        String key = toKey(type, args);
        ServiceCache<T> cache = cachedItems.get(key);

        if (cache == null) {
            cachedItems.putIfAbsent(key, new ServiceCache<>());
            cache = cachedItems.get(key);
        }

        List<T> items = cache.getItems();

        if ((cache.getUpdateTime() + interval) < System.currentTimeMillis() || items == null || items.isEmpty()) {
            items = updateCacheFromService(type, args);
        }

        return items;
    }

    /**
     * If applicable retrieve the list of items of type {@code T} from the dao and update the caching with them.
     * These items of type {@code T} are then returned to the calling method. If an update via the dao was not
     * required, the original list of items of type {@code T} associated with the cache is returned.
     * <p>
     * The cache is updated if the items of type {@code T} list is empty, null or has not been updated for the interval
     * assoicated with this instance.
     *
     * @param type the type that these items of type {@code T} are for
     * @param args the arguments required to retrieve these items of type {@code T}
     * @return a list of items of type {@code T}
     */
    private List<T> updateCacheFromService(String type, String... args) {
        final ServiceCache<T> cache = cachedItems.get(toKey(type, args));

        synchronized (cache) {
            List<T> items = cache.getItems();
            long now = System.currentTimeMillis();

            if ((cache.getUpdateTime() + interval) < now || items == null || items.isEmpty()) {
                items = itemsFromService(type, args);
                cache.setItems(items);
                cache.setUpdateTime(now);
            }

            return items;
        }
    }

    protected abstract List<T> itemsFromService(String type, String... args);

    /**
     * Holder for a list of items associated with a last updated time
     */
    private class ServiceCache<T> {
        private List<T> items;
        private long updateTime;

        /**
         * @return the list of cached items
         */
        public List<T> getItems() {
            return items;
        }

        /**
         * @param items the list of cached items to set
         */
        public void setItems(List<T> items) {
            this.items = items;
        }

        /**
         * @return the last update time
         */
        public long getUpdateTime() {
            return updateTime;
        }

        /**
         * @param updateTime the last update time to set
         */
        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }
    }
}
