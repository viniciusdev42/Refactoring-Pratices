package br.ufpa.refactoring.q4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RecentFiles implements Iterable<String> {

    private final int limit;
    private final List<String> files; // Delegação

    public RecentFiles(int limit) {
        this.limit = limit;
        this.files = new ArrayList<>();
    }

    public void addFile(String path) {
        files.remove(path);
        files.add(0, path);
        while (files.size() > limit) {
            files.remove(files.size() - 1);
        }
    }

    // Métodos delegados essenciais para uso externo da classe
    
    public String get(int index) {
        return files.get(index);
    }

    public int size() {
        return files.size();
    }

    public boolean isEmpty() {
        return files.isEmpty();
    }

    public void clear() {
        files.clear();
    }

    @Override
    public Iterator<String> iterator() {
        return files.iterator();
    }
}