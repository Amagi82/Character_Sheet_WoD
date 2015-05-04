package amagi82.wodcharactersheet;


public class Character {

    private String name = "";
    private String clan = "";
    private String system = "";

    public Character() {
    }

    public String getName() {
        return name;
    }

    public Character(String name, String system, String clan) {
        this.name = name;
        this.clan = clan;
        this.system = system;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClan() {
        return clan;
    }

    public void setClan(String clan) {
        this.clan = clan;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }
}
