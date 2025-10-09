import java.util.ArrayList;
import java.util.List;

public class Chapter implements Element {
    private String title;
    private List<Element> elements;

    public Chapter(String title) {
        this.title = title;
        this.elements = new ArrayList<>();
    }

    public void addElement(Element element) {
        elements.add(element);
    }

    @Override
    public void print() {
        System.out.println("Chapter: " + title);
        for (Element e : elements) {
            e.print();
        }
    }
}