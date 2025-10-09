import java.util.ArrayList;
import java.util.List;

public class Section implements Element {
    private String title;
    private List<Element> elements;

    public Section(String title) {
        this.title = title;
        this.elements = new ArrayList<>();
    }

    public void add(Element element) {
        elements.add(element);
    }

    @Override
    public void print() {
        System.out.println(title);
        for (Element e : elements) {
            e.print();
        }
    }
}