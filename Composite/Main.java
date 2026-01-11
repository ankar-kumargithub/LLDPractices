package Composite;

import java.util.ArrayList;
import java.util.List;

interface FileType {
    void printStructure();
}

class File implements FileType {
    private String name;

    public File(String name) {
        this.name = name;
    }

    @Override
    public void printStructure() {
        System.out.println(this.name);
    }
}

class Directory implements FileType {
    private List<FileType> files = new ArrayList<>();
    private String directoryName;

    public Directory(String directoryName) {
        this.directoryName = directoryName;
    }

    @Override
    public void printStructure() {
        System.out.println(this.directoryName);
        for (FileType f : files) {
            f.printStructure();
        }
    }

    public void add(FileType file) {
        files.add(file);
    }
}

public class Main {
    public static void main(String[] args) {
        Directory dir1 = new Directory("Dir1");
        Directory dir2 = new Directory("Dir2");
        Directory dir3 = new Directory("Dir3");
        File file1 = new File("File1");
        File file2 = new File("File2");
        File file3 = new File("File3");
        dir1.add(file3);
        dir2.add(dir3);
        dir2.add(file1);
        dir3.add(file2);
        dir1.add(dir2);
        dir1.printStructure();
    }
}
