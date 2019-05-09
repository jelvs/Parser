package environment;

import exception.DuplicateIdentifierException;
import exception.UndeclaredIdentifierException;

public interface IEnvironment<T> {

    IEnvironment<T> beginScope();

    void bind(String id, T value) throws DuplicateIdentifierException;

    T find(String id) throws UndeclaredIdentifierException;
}
