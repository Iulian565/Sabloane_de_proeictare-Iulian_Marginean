public class Image implements Element {
    private String name;

    public Image(String name) {
        this.name = name;
    }

    @Override
    public void print() {
        System.out.println("Image with name:" + name);
    }
}
