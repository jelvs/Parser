package environment;

import exception.DuplicateIdentifierException;
import exception.UndeclaredIdentifierException;

import java.util.ArrayList;
import java.util.List;

public class Environment<T> implements IEnvironment<T> {

    private Environment<T> previous;
    private List<Binding<T>> bindings;

    public Environment() {
        this.previous = null;
        this.bindings = new ArrayList<Binding<T>>();
    }

    private Environment(Environment<T> env) {
        this();
        this.previous = env;
    }

    @Override
    public IEnvironment<T> beginScope() {
        return new Environment<T>(this);
    }

    @Override
    public void bind(String id, T value) throws DuplicateIdentifierException {
        for (Binding<T> binding : this.bindings) {
            if (binding.equals(id)) {
                throw new DuplicateIdentifierException(String.format("Duplicate identifier: %s!", id));
            }
        }
        bindings.add(new Binding<T>(id, value));
    }

    @Override
    public T find(String id) throws UndeclaredIdentifierException {
        Environment<T> env = this;
        while (env != null) {
            for (Binding<T> binding : env.bindings) {
                if (binding.equals(id)) {
                    return binding.getValue();
                }
            }
            env = env.previous;
        }
        throw new UndeclaredIdentifierException(String.format("Undeclared identifier: %s!", id));
    }

    private class Binding<T> {

        private final String id;
        private final T value;

        public Binding(String id, T value) {
            this.id = id;
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public boolean equals(String str) {
            return id.equals(str);
        }
    }
}
