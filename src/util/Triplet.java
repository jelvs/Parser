package util;

public class Triplet<F, S, T> {

    private F first;
    private S second;
    private T third;

    public Triplet(F first, S second, T third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public F getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }

    public T getThird() {
        return third;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Triplet<?, ?, ?> triplet = (Triplet<?, ?, ?>) o;

        if (first != null ? !first.equals(triplet.first) : triplet.first != null) {
            return false;
        }

        if (second != null ? !second.equals(triplet.second) : triplet.second != null) {
            return false;
        }

        if (third != null ? !third.equals(triplet.third) : triplet.third != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = 31 * (first != null ? first.hashCode() : 0);
        result = 31 * result + (second != null ? second.hashCode() : 0);
        result = 31 * result + (third != null ? third.hashCode() : 0);
        return result;
    }
}
