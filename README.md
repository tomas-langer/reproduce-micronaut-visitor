Reproducer
---

This reproducer shows the following:

1. There is a `Context` annotation in `legacy-annotations`
2. There is a visitor in `di-processors` that `@ContextBindable` on parameters
    for each parameter and method annotated with `@Context`
3. There is a small executable method processor in `reproducer` module in
    `impl` package that prepares the executable method 
4. The `Main` tries to call executable methods from `PathBeanDefinition`     


What should happen:

- the execution should succeed

What happens:

- there is no `ContextBindable` annotation available on the method parameters
    (verified in debug mode on `ExecMethodProcessor`)
- the execution fails with:
```
Caused by: io.micronaut.core.bind.exceptions.UnsatisfiedArgumentException: Required argument [FirstBean paramInjection] not specified
	at io.micronaut.core.bind.DefaultExecutableBinder.bind(DefaultExecutableBinder.java:96)
	at io.reproducers.reproducer.impl.ExecMethodProcessor.process(ExecMethodProcessor.java:37)
	at io.reproducers.reproducer.impl.ExecMethodProcessor.process(ExecMethodProcessor.java:17)
	at io.micronaut.context.AnnotationProcessorListener.onCreated(AnnotationProcessorListener.java:89)
	... 10 more
```