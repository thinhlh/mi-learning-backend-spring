package com.thinhlh.mi_learning_backend.helper;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public abstract class ListHelper {

    public static <T> List<T> toList(Iterable<T> iterator) {
        return StreamSupport
                .stream(iterator.spliterator(), false)
                .collect(Collectors.toList());
    }


    public static <S, D> List<D> mapTo(Collection<S> source, Function<S, D> mapper) {
        return
                source
                        .stream()
                        .map(mapper)
                        .collect(Collectors.toList());
    }
}
