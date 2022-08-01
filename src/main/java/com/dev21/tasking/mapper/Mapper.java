package com.dev21.tasking.mapper;

public interface Mapper<I, O> {

    O map(I in);
}
