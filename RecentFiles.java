package br.ufpa.refactoring.q4;

import java.util.ArrayList;

public class RecentFiles extends ArrayList<String> {

    private final int limit;

    public RecentFiles(int limit) {
        this.limit = limit;
    }

    public void addFile(String path) {
        remove(path);
        add(0, path);
        while (size() > limit) remove(size() - 1);
    }
}
