package Proxy;

interface Image {
    void display();

    String getFileName();
}

class HighResolutionImage implements Image {
    private String fileName;
    private byte[] imageData;

    public HighResolutionImage(String name) {
        this.fileName = name;
        loadImageFromDisk();
    }

    private void loadImageFromDisk() {
        System.out.println("Loading image: " + fileName + " from Disk.");
        try {
            Thread.sleep(2000);
            this.imageData = new byte[1024 * 1024 * 10];
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Image Laoded successfully from disk!");
    }

    @Override
    public void display() {
        System.out.println("Displaying image: " + fileName);
    }

    @Override
    public String getFileName() {
        return fileName;
    }
}

class ImageProxy implements Image{
    private final String fileName;
    private HighResolutionImage image;

    public ImageProxy(String fileName) {
        this.fileName = fileName;
        System.out.println("ImageProxy: Created for " + fileName + ". Real image not loaded yet.");
    }

    @Override
    public void display() {
       if(this.image == null){
            System.out.println("ImageProxy: display() requested for " + fileName + ". Loading high-resolution image...");
            image = new HighResolutionImage(this.fileName);
       }
       image.display();
    }

    @Override
    public String getFileName(){
        return this.fileName;
    }
    
}

public class Main {
    public static void main(String[] args) {
        System.out.println("Application Started. Initializing image proxies for gallery...");
        Image image1 = new ImageProxy("File1.jpeg");
        Image image2 = new ImageProxy("File2.jpeg");
        image1.getFileName();
        image2.getFileName();
        image1.display();
        image1.display();
        image2.display();
    }
}
