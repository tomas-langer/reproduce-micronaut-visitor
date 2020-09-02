package io.reproducers.processors;

import java.util.List;

import io.micronaut.core.annotation.AnnotationValue;
import io.micronaut.inject.annotation.TypedAnnotationMapper;
import io.micronaut.inject.visitor.VisitorContext;
import io.reproducers.legacy.Path;

public class PathMapper implements TypedAnnotationMapper<Path> {
    @Override
    public List<AnnotationValue<?>> map(AnnotationValue<Path> annotation, VisitorContext visitorContext) {
        return List.of(AnnotationValue.builder("io.reproducers.reproducer.impl.Path")
                               .build());
    }

    @Override
    public Class<Path> annotationType() {
        return Path.class;
    }
}
