package org.example.spi;

import org.junit.Test;

import java.sql.Driver;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author anyuan
 * @since 2021-04-16 16:26
 */
public class TestSPI {
    @Test
    public void testLoad() {
        ServiceLoader<Driver> loadedDrivers = ServiceLoader.load(Driver.class);
//        final Iterator<Driver> iterator = loadedDrivers.iterator();
//        while (iterator.hasNext()) {
//            final Driver next = iterator.next();
        for (Driver next : loadedDrivers) {
            System.out.println("-----");
            System.out.println(next);
            System.out.println(next.getClass());
            System.out.println(next.getMajorVersion());
            System.out.println(next.getMinorVersion());
        }
    }
}
