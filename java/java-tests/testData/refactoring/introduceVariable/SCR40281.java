class Set<T> {}

class Map <K,V> {
    class Entry<K,V> {
    }
    Set<Entry<K,V>> entries () { return null; }
}

class C {
    void foo (Map<?, String> s) {
        <selection>s.entries()</selection>;
    }
}