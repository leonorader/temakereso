package temakereso.helper;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TopicType {

    BSC_THESIS("Bsc. szakdolgozat"),
    MSC_THESIS("Msc. diplomamunka"),
    PHD_THESIS("PhD. disszertáció"),
    RESEARCH_TOPIC("Kutatási téma, TDK");

    private String name;

    TopicType(String name) {
        this.name = name;
    }

    public String getId() {
        return name();
    }

    public String getName() {
        return name;
    }


}
