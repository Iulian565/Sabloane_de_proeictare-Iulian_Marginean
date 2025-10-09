public class Table implements Element {
    private String[][] data;

    public Table(String[][] data) { this.data = data; }

    @Override
    public void print() {
        System.out.println("[Table with " + data.length + " rows]");
    }
}
