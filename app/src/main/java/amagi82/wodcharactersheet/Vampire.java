package amagi82.wodcharactersheet;


import java.util.ArrayList;
import java.util.HashMap;

public class Vampire extends Character {

    private int[] physicalBase = {1, 1, 1};
    private int[] physicalCurrent = {1, 1, 1};
    private int[] social = {1, 1, 1};
    private int[] mental = {1, 1, 1};
    private int[] talents = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int[] skills = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int[] knowledges = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int humanityRating = 1;
    private int maxWillpower = 1;
    private int currentWillpower = 0;
    private int currentBloodpool = 0;
    private int damageAgg = 0;
    private int damageLethal = 0;
    private int damageBashing = 0;
    private int currentXP = 0;
    private int totalXP = 0;
    private int generation = 13;
    private String player = "";
    private String chronicle = "";
    private String nature = "";
    private String demeanor = "";
    private String concept = "";
    private String sire = "";
    private ArrayList<String> disciplinesList = new ArrayList<>();
    private ArrayList<String> necromancyPathsList = new ArrayList<>();
    private ArrayList<String> thaumaturgyPathsList = new ArrayList<>();
    private ArrayList<String> backgroundsList = new ArrayList<>();
    private ArrayList<String> virtuesList = new ArrayList<>();
    private ArrayList<String> meritsList = new ArrayList<>();
    private ArrayList<String> flawsList = new ArrayList<>();
    private ArrayList<String> weaknessesList = new ArrayList<>();
    private HashMap<String, Integer> valuesMap = new HashMap<>();
    private HashMap<String, String> specialitiesMap = new HashMap<>();
    private String path = "Path of Humanity";
    private boolean isPathOfHumanity = true;

    public Vampire() {
        super();
    }

    public Vampire(String name, String system, String clan) {
        super(name, system, clan);
    }

    public int[] getPhysicalBase() {
        return physicalBase;
    }

    public void setPhysicalBase(int[] physicalBase) {
        this.physicalBase = physicalBase;
        for (int i = 0; i < physicalBase.length; i++) {
            physicalCurrent[i] = Math.max(physicalBase[i], physicalCurrent[i]);
        }
    }

    public int[] getPhysicalCurrent() {
        return physicalCurrent;
    }

    public void setPhysicalCurrent(int[] physicalCurrent) {
        this.physicalCurrent = physicalCurrent;
    }

    public int[] getSocial() {
        return social;
    }

    public void setSocial(int[] social) {
        this.social = social;
    }

    public int[] getMental() {
        return mental;
    }

    public void setMental(int[] mental) {
        this.mental = mental;
    }

    public int[] getTalents() {
        return talents;
    }

    public void setTalents(int[] talents) {
        this.talents = talents;
    }

    public int[] getSkills() {
        return skills;
    }

    public void setSkills(int[] skills) {
        this.skills = skills;
    }

    public int[] getKnowledges() {
        return knowledges;
    }

    public void setKnowledges(int[] knowledges) {
        this.knowledges = knowledges;
    }

    public int getHumanityRating() {
        return humanityRating;
    }

    public void setHumanityRating(int humanityRating) {
        this.humanityRating = humanityRating;
    }

    public int getMaxWillpower() {
        return maxWillpower;
    }

    public void setMaxWillpower(int maxWillpower) {
        this.maxWillpower = maxWillpower;
    }

    public int getCurrentWillpower() {
        return currentWillpower;
    }

    public void setCurrentWillpower(int currentWillpower) {
        this.currentWillpower = currentWillpower;
    }

    //This is no longer accurate if the character reaches 3rd generation
    public int getMaxBloodpool() {
        if (generation >= 13) return 10;
        if (generation >=8) return 23-generation;
        return 10*(9-generation);
    }

    public int getCurrentBloodpool() {
        return currentBloodpool;
    }

    public void setCurrentBloodpool(int currentBloodpool) {
        this.currentBloodpool = currentBloodpool;
    }

    public int getDamageAgg() {
        return damageAgg;
    }

    public void setDamageAgg(int damageAgg) {
        this.damageAgg = damageAgg;
    }

    public int getDamageLethal() {
        return damageLethal;
    }

    public void setDamageLethal(int damageLethal) {
        this.damageLethal = damageLethal;
    }

    public int getDamageBashing() {
        return damageBashing;
    }

    public void setDamageBashing(int damageBashing) {
        this.damageBashing = damageBashing;
    }

    public int getCurrentXP() {
        return currentXP;
    }

    public void setCurrentXP(int currentXP) {
        this.currentXP = currentXP;
    }

    public int getTotalXP() {
        return totalXP;
    }

    public void setTotalXP(int totalXP) {
        this.totalXP = totalXP;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getChronicle() {
        return chronicle;
    }

    public void setChronicle(String chronicle) {
        this.chronicle = chronicle;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getDemeanor() {
        return demeanor;
    }

    public void setDemeanor(String demeanor) {
        this.demeanor = demeanor;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public String getSire() {
        return sire;
    }

    public void setSire(String sire) {
        this.sire = sire;
    }

    public ArrayList<String> getDisciplinesList() {
        return disciplinesList;
    }

    public void setDisciplinesList(ArrayList<String> disciplinesList) {
        this.disciplinesList = disciplinesList;
    }

    public ArrayList<String> getThaumaturgyPathsList() {
        return thaumaturgyPathsList;
    }

    public void setThaumaturgyPathsList(ArrayList<String> thaumaturgyPathsList) {
        this.thaumaturgyPathsList = thaumaturgyPathsList;
    }

    public ArrayList<String> getNecromancyPathsList() {
        return necromancyPathsList;
    }

    public void setNecromancyPathsList(ArrayList<String> necromancyPathsList) {
        this.necromancyPathsList = necromancyPathsList;
    }

    public ArrayList<String> getBackgroundsList() {
        return backgroundsList;
    }

    public void setBackgroundsList(ArrayList<String> backgroundsList) {
        this.backgroundsList = backgroundsList;
    }

    public ArrayList<String> getVirtuesList() {
        return virtuesList;
    }

    public void setVirtuesList(ArrayList<String> virtuesList) {
        this.virtuesList = virtuesList;
    }

    public ArrayList<String> getMeritsList() {
        return meritsList;
    }

    public void setMeritsList(ArrayList<String> meritsList) {
        this.meritsList = meritsList;
    }

    public ArrayList<String> getFlawsList() {
        return flawsList;
    }

    public void setFlawsList(ArrayList<String> flawsList) {
        this.flawsList = flawsList;
    }

    public ArrayList<String> getWeaknessesList() {
        return weaknessesList;
    }

    public void setWeaknessesList(ArrayList<String> weaknessesList) {
        this.weaknessesList = weaknessesList;
    }

    public HashMap<String, Integer> getValuesMap() {
        return valuesMap;
    }

    public void setValuesMap(HashMap<String, Integer> valuesMap) {
        this.valuesMap = valuesMap;
    }

    public HashMap<String, String> getSpecialitiesMap() {
        return specialitiesMap;
    }

    public void setSpecialitiesMap(HashMap<String, String> specialitiesMap) {
        this.specialitiesMap = specialitiesMap;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isPathOfHumanity() {
        return isPathOfHumanity;
    }

    public void setIsPathOfHumanity(boolean isPathOfHumanity) {
        this.isPathOfHumanity = isPathOfHumanity;
    }

    public String getBloodPerTurn() {
        if(generation >=10) return "1";
        if(generation >=7) return "" + (11-generation);
        if(generation >=4) return "" + (9-generation)*2;
        return "???";
    }

    public int getMaxTraitRating(){
        if(generation >= 8) return 5;
        return 13-generation;
    }

    public int getMaxDisciplineRating(){
        if(generation > 13) return 5-(13-generation);
        return getMaxTraitRating();
    }
}
