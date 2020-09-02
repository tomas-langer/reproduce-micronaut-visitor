package io.reproducers.processors;

import io.micronaut.inject.ast.FieldElement;
import io.micronaut.inject.ast.MethodElement;
import io.micronaut.inject.ast.ParameterElement;
import io.micronaut.inject.visitor.TypeElementVisitor;
import io.micronaut.inject.visitor.VisitorContext;
import io.reproducers.legacy.Context;

public class ContextVisitor implements TypeElementVisitor<Object, Object> {
    private static final String CONTEXT = "io.reproducers.reproducer.impl.ContextBindable";
    private static final String INJECT = "javax.inject.Inject";

    @Override
    public void visitField(FieldElement element, VisitorContext context) {
        // add @Inject to fields so they get injected
        if (element.hasAnnotation(Context.class)) {
            element.annotate(INJECT);
        }
    }

    @Override
    public void visitMethod(MethodElement element, VisitorContext context) {
        if (element.hasAnnotation(Context.class)) {
            // add context bindable to each parameter if method annotated
            for (ParameterElement parameter : element.getParameters()) {
                // this is ignored and not visible in executable method
                context.warn("Adding " + CONTEXT + " annotation", element);
                parameter.annotate(CONTEXT);
            }
        } else {
            // check parameteres and add context bindable to each annotated with @Context
            for (ParameterElement parameter : element.getParameters()) {
                if (parameter.hasAnnotation(Context.class)) {
                    // this is ignored and not visible in executable method
                    context.warn("Adding " + CONTEXT + " annotation", element);
                    parameter.annotate(CONTEXT);
                }
            }
        }
    }
}
