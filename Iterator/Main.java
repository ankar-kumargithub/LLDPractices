package Iterator;

import java.util.ArrayList;
import java.util.List;

// Iterator interface

// IterableCollection Interface
// Concrete Iterator
// Concrete IterableCollection

interface Iterator<T> {
    boolean hasNext();
    T next();
}

interface IterableCollection<T> {
    Iterator<T> createIterator();
}

class Playlist implements IterableCollection<String> {
    List<String> songs = new ArrayList<>();
    public void addSong(String song) {
        songs.add(song);
    }

    public String getSongAt(int index) {
        return songs.get(index);
    }

    public int getSize() {
        return songs.size();
    }

    @Override
    public Iterator<String> createIterator() {
        return new PlaylistIterator(this);
    }
}

class PlaylistIterator implements Iterator<String>{
    private final Playlist list;
    private int idx = 0;
    public PlaylistIterator(Playlist list) {
        this.list = list;
    }
    
    @Override
    public boolean hasNext(){
        return idx < list.getSize();
    }

    @Override
    public String next(){
        String name = list.getSongAt(idx);
        idx++;
        return name;
    }
}

public class Main {
    public static void main(String[] args) {
        Playlist playlist = new Playlist();
        playlist.addSong("a");
        playlist.addSong("b");
        playlist.addSong("c");

        PlaylistIterator itr = new PlaylistIterator(playlist);
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
    }
}
