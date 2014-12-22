package samaritano.inject;

public final class DependencyInjection {

	private static final Injector injector = new DefaultInjector(new DefaultBinder());

	private DependencyInjection() {
	}

	public static Injector createInjector(Module... modules) {
		for (Module module : modules)
			injector.binder().install(module);
		return injector;
	}

}