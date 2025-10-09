import java.util.ArrayList;
import java.util.List;

public class SubChapter implements Element {
    private String title;
    private List<Element> elements;

    public SubChapter(String title) {
        this.title = title;
        this.elements = new ArrayList<>();
    }

    public void addElement(Element element) {
        elements.add(element);
    }

    @Override
    public void print() {
        System.out.println("SubChapter: " + title);
        for (Element e : elements) {
            e.print();
        }
    }
}