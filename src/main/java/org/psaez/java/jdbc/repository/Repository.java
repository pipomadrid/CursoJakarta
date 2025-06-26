package org.psaez.java.jdbc.repository;

import java.util.List;

public interface Repository<T> {
        List<T> findAll();

        T findById(Long id);

        void insert(T t);

        void delete(Long id);


}
