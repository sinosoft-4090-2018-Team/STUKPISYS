package com.sinosoft.stukpisys.dao;

public interface BaseDao<T> {
    int insert(T t);
    T select(T t);
}
