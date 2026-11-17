package br.ufpa.refactoring.q4;

public class ArrayList<String> {
    
    public void size() {
        return ArrayList.size();
    }

    public void remove() {
        // Logica de remoção
    } 


    public void add() {
        // Logica de adição
    }
}

public class RecentFiles{

    private ArrayList<String> _array = new ArrayList<String>();

    private final int limit;

    public RecentFiles(int limit,_array) {
        this.limit = limit;
        this._array = _array;
    }

    public void addFile(String path) {
        this._array.remove(path);
        this._array.add(0, path);
        while (this._array.size() > limit) this._array.remove(this._array.size() - 1);
    }
}
