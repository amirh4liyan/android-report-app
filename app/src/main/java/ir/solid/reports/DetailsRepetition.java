package ir.solid.reports;

public class DetailsRepetition {


    private String repetition, id, date, lecture, section, source, duration, pages, function;
    private boolean expandable;
    private JCal jcal;

    public DetailsRepetition(String repetition, String id, String date,
                             String lecture, String section, String source,
                             String duration, String pages, String function) {
        jcal = new JCal();

        this.repetition = repetition;
        this.id = String.valueOf(id);
        this.date = String.valueOf(date);
        this.lecture = String.valueOf(lecture);
        this.section = String.valueOf(section);
        this.source = String.valueOf(source);
        this.duration = String.valueOf(duration);
        this.pages = String.valueOf(pages);
        this.function = String.valueOf(function);
        this.expandable = false;
    }

    public String getId() {
        return id;
    }

    public String getRepetition() {
        return repetition;
    }

    public String getJStyleDate() {
        return jcal.convert_DB_ToJDate(String.valueOf(date));
    }

    public String getDate() {
        return date;
    }

    public String getLecture() {
        return lecture;
    }

    public String getSection() {
        return section;
    }

    public String getSource() {
        return source;
    }

    public String getDuration() {
        return duration;
    }

    public String getPages() {
        return pages;
    }

    public String getFunction() {
        return function;
    }

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }

    @Override
    public String toString() {
        return "Details{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", lecture='" + lecture + '\'' +
                ", section='" + section + '\'' +
                ", source='" + source + '\'' +
                ", duration='" + duration + '\'' +
                ", pages='" + pages + '\'' +
                ", function='" + function + '\'' +
                '}';
    }
}