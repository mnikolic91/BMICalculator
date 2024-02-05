package BMILogic;


import java.util.EventObject;

public class FormEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public FormEvent(Object source) {
        super(source);
    }

    private float height;
    private float weight;
    private String category;

    private Person person;

    public FormEvent(Object source, float height, float weight, String category) {
        super(source);
        this.height = height;
        this.weight = weight;
        this.category = category;
    }

    public FormEvent(Object source, Person person) {
        super(source);
        this.person = person;
    }

    public float getHeight() {
        return height;
    }

    public float getWeight() {
        return weight;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "FormEvent{" +
                "height=" + height +
                ", weight=" + weight +
                ", category='" + category + '\'' +
                '}';
    }

    public Person getPerson() {
        return person;
    }
}
