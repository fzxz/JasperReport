package ua.sevenLevelLabs.model;

/**
 * @author Tatiana Marchuk
 *         17.03.16 : 18:10
 */
public class Template {
    private long length;
    private String name;
    private String lastModifiedDate;

    public Template(String name, String lastModifiedDate, long length) {
        this.length = length;
        this.name = name;
        this.lastModifiedDate = lastModifiedDate;
    }

    public Template() {
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public String toString() {
        return "Template{" +
                "length=" + length +
                ", name='" + name + '\'' +
                ", lastModifiedDate='" + lastModifiedDate + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Template)) return false;

        Template template = (Template) o;

        if (getLength() != template.getLength()) return false;
        if (getName() != null ? !getName().equals(template.getName()) : template.getName() != null) return false;
        return getLastModifiedDate() != null ? getLastModifiedDate().equals(template.getLastModifiedDate()) : template.getLastModifiedDate() == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (getLength() ^ (getLength() >>> 32));
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getLastModifiedDate() != null ? getLastModifiedDate().hashCode() : 0);
        return result;
    }
}
