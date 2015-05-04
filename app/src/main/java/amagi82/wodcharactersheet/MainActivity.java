package amagi82.wodcharactersheet;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.balysv.materialmenu.MaterialMenu;
import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.extras.toolbar.MaterialMenuIconToolbar;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity implements CharacterListFragment.OnFragmentInteractionListener{

    private Toolbar toolbar;
    private MaterialMenu materialMenu;
    FrameLayout container;
    public static ArrayList<Character> characterArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Add toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("World of Darkness");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getSupportFragmentManager().getBackStackEntryCount() != 0) {
                    onNavigateUp();
                }
            }
        });

        Vampire anstis = new Vampire();

        //int arrays are only for example data
        int[] physical = {5,3,4};
        int[] social = {2,5,1};
        int[] mental = {4,2,2};
        int[] talents = {3,3,3,4,0,0,3,1,1,1};
        int[] skills = {2,0,0,1,1,3,3,0,3,2};
        int[] knowledges = {1,0,1,0,1,0,5,1,0,1};
        anstis.setName("Thomas Anstis");
        anstis.setPlayer("Jim");
        anstis.setChronicle("");
        anstis.setNature("Rogue");
        anstis.setDemeanor("Rogue");
        anstis.setConcept("Pirate");
        anstis.setClan("Gangrel");
        anstis.setGeneration(8);
        anstis.setSire("Howell Davis");
        anstis.setDamageAgg(1);
        anstis.setDamageLethal(2);
        anstis.setDamageBashing(1);


        //TODO: possible bug - if user switches languages, hashmap may lose all values
        HashMap<String, Integer> valuesMap = new HashMap<>();

        putDataInMap(valuesMap,R.array.physical,physical);
        putDataInMap(valuesMap,R.array.social,social);
        putDataInMap(valuesMap,R.array.mental,mental);
        putDataInMap(valuesMap,R.array.talents,talents);
        putDataInMap(valuesMap,R.array.skills,skills);
        putDataInMap(valuesMap,R.array.knowledges,knowledges);

        ArrayList<String> disciplinesList = new ArrayList<>();
        disciplinesList.add(getResources().getStringArray(R.array.disciplines)[0]);
        disciplinesList.add(getResources().getStringArray(R.array.disciplines)[1]);
        disciplinesList.add(getResources().getStringArray(R.array.disciplines)[5]);
        disciplinesList.add(getResources().getStringArray(R.array.disciplines)[6]);
        disciplinesList.add(getResources().getStringArray(R.array.disciplines)[7]);
        disciplinesList.add(getResources().getStringArray(R.array.disciplines)[10]);
        disciplinesList.add(getResources().getStringArray(R.array.disciplines)[12]);
        disciplinesList.add(getResources().getStringArray(R.array.disciplines)[15]);

        valuesMap.put(disciplinesList.get(0), 2);
        valuesMap.put(disciplinesList.get(1), 1);
        valuesMap.put(disciplinesList.get(2), 2);
        valuesMap.put(disciplinesList.get(3), 3);
        valuesMap.put(disciplinesList.get(4), 4);
        valuesMap.put(disciplinesList.get(5), 3);
        valuesMap.put(disciplinesList.get(6), 4);
        valuesMap.put(disciplinesList.get(7), 3);

        ArrayList<String> necromancyPathsList = new ArrayList<>();
        necromancyPathsList.add(getResources().getStringArray(R.array.necromancyPaths)[0]);
        necromancyPathsList.add(getResources().getStringArray(R.array.necromancyPaths)[2]);
        valuesMap.put(necromancyPathsList.get(0), 4);
        valuesMap.put(necromancyPathsList.get(1), 3);

        ArrayList<String> thaumaturgyPathsList = new ArrayList<>();
        thaumaturgyPathsList.add(getResources().getStringArray(R.array.thaumaturgyPaths)[0]);
        thaumaturgyPathsList.add(getResources().getStringArray(R.array.thaumaturgyPaths)[4]);
        valuesMap.put(thaumaturgyPathsList.get(0), 3);
        valuesMap.put(thaumaturgyPathsList.get(1), 1);

        ArrayList<String> backgroundsList = new ArrayList<>();
        backgroundsList.add(getResources().getStringArray(R.array.backgrounds)[6]);
        backgroundsList.add(getResources().getStringArray(R.array.backgrounds)[5]);
        valuesMap.put(backgroundsList.get(0), 4);
        valuesMap.put(backgroundsList.get(1), 1);

        ArrayList<String> virtuesList = new ArrayList<>();
        virtuesList.add(getString(R.string.conviction));
        virtuesList.add(getString(R.string.self_control));
        virtuesList.add(getString(R.string.courage));
        valuesMap.put(virtuesList.get(0), 5);
        valuesMap.put(virtuesList.get(1), 3);
        valuesMap.put(virtuesList.get(2), 5);

        ArrayList<String> meritsList = new ArrayList<>();
        meritsList.add(getResources().getStringArray(R.array.meritsSupernatural)[11]);
        meritsList.add(getResources().getStringArray(R.array.meritsSupernatural)[10]);
        meritsList.add(getResources().getStringArray(R.array.meritsSupernatural)[5]);
        valuesMap.put(meritsList.get(0), 5);
        valuesMap.put(meritsList.get(1), 5);
        valuesMap.put(meritsList.get(2), 3);

        ArrayList<String> flawsList = new ArrayList<>();
        flawsList.add(getResources().getStringArray(R.array.flawsPhysical)[2]);
        flawsList.add(getResources().getStringArray(R.array.flawsMental)[15]);
        flawsList.add(getResources().getStringArray(R.array.flawsPhysical)[10]);
        flawsList.add(getResources().getStringArray(R.array.flawsSupernatural)[3]);
        flawsList.add(getResources().getStringArray(R.array.flawsSocial)[6]);
        valuesMap.put(flawsList.get(0), 1);
        valuesMap.put(flawsList.get(1), 2);
        valuesMap.put(flawsList.get(2), 2);
        valuesMap.put(flawsList.get(3), 1);
        valuesMap.put(flawsList.get(4), 1);

        anstis.setSystem("Vampire");
        anstis.setPhysicalBase(physical);
        anstis.setSocial(social);
        anstis.setMental(mental);
        anstis.setTalents(talents);
        anstis.setSkills(skills);
        anstis.setKnowledges(knowledges);
        anstis.setDisciplinesList(disciplinesList);
        anstis.setNecromancyPathsList(necromancyPathsList);
        anstis.setThaumaturgyPathsList(thaumaturgyPathsList);
        anstis.setBackgroundsList(backgroundsList);
        anstis.setVirtuesList(virtuesList);
        anstis.setMeritsList(meritsList);
        anstis.setFlawsList(flawsList);
        anstis.setIsPathOfHumanity(false);
        anstis.setPath("Path of the Buccaneer");
        anstis.setValuesMap(valuesMap);

        anstis.setHumanityRating(8);
        anstis.setMaxWillpower(8);
        anstis.setCurrentWillpower(6);
        anstis.setCurrentBloodpool(12);


        //Add mock character data
        characterArray.add(anstis);
        characterArray.add(new Character("Dr. Von Natsi", "Mage", "Etherite"));
        characterArray.add(new Character("Sophie", "Werewolf", "Glass Walker"));
        characterArray.add(new Character("Mab", "Changeling", "Sidhe"));

        //Set CharacterListFragment
        FrameLayout fragmentContainer = (FrameLayout) findViewById(R.id.container);
        container = new FrameLayout(this);
        container.setId(R.id.container_id);
        getSupportFragmentManager().beginTransaction().replace(container.getId(), new CharacterListFragment()).commit();
        fragmentContainer.addView(container);
        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if(getSupportFragmentManager().getBackStackEntryCount() == 0) {
                    toolbar.setTitle("World of Darkness");
                    materialMenu.animateState(MaterialMenuDrawable.IconState.BURGER);
                }else materialMenu.animateState(MaterialMenuDrawable.IconState.ARROW);
            }
        });

        materialMenu = new MaterialMenuIconToolbar(this, Color.WHITE, MaterialMenuDrawable.Stroke.THIN) {
            @Override
            public int getToolbarViewId() {
                return R.id.toolbar;
            }
        };
    }

    //convenience method for populating data
    private void putDataInMap(HashMap<String, Integer> valuesMap, int arrayId, int[] values){
        for(int i = 0; i < values.length; i++){
            valuesMap.put(getResources().getStringArray(arrayId)[i], values[i]);
        }
    }

    @Override
    public void onFragmentInteraction(int id) {
        //TODO - add other game systems

        Fragment fragment;
        Bundle bundle = new Bundle();
        bundle.putInt("index",id);
        switch(characterArray.get(id).getSystem()){
            case "Vampire":
                fragment = new VampireFragment();
                break;
            default:
                fragment = new Fragment();
        }
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(container.getId(), fragment).addToBackStack(null).commit();
        toolbar.setTitle(characterArray.get(id).getName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public boolean onNavigateUp() {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        return super.onNavigateUp();
    }

    @Override
    protected void onPause() {
        // Save data
//        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.putInt("blood", blood);
//        editor.putInt("bashing", bashing);
//        editor.putInt("lethal", lethal);
//        editor.putInt("agg", agg);
//        editor.putInt("willpower", willpower);
//        editor.putInt("xp", xp);
//        editor.putInt("xpTotal", xpTotal);
//        editor.commit();
        super.onPause();
    }

    public void healthButton(View view) {
//        newBashing = bashing;
//        newLethal = lethal;
//        newAgg = agg;
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        LayoutInflater inflater = this.getLayoutInflater();
//        builder.setTitle("Take Damage");
//        View dialogView = inflater.inflate(R.layout.damage, null);
//        RatingBar bashBar = (RatingBar) dialogView.findViewById(R.id.bashing);
//        RatingBar lethalBar = (RatingBar) dialogView.findViewById(R.id.lethal);
//        RatingBar aggBar = (RatingBar) dialogView.findViewById(R.id.aggravated);
//        bashBar.setRating(bashing);
//        lethalBar.setRating(lethal);
//        aggBar.setRating(agg);
//        bashBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//
//            @Override
//            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//                newBashing = (int) rating;
//            }
//        });
//        lethalBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//
//            @Override
//            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//                newLethal = (int) rating;
//            }
//        });
//        aggBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//
//            @Override
//            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//                newAgg = (int) rating;
//            }
//        });
//        builder.setView(dialogView);
//        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int id) {
//                if ((newBashing + newLethal + newAgg) <= 7) {
//                    bashing = newBashing;
//                    lethal = newLethal;
//                    agg = newAgg;
//                } else if ((newLethal + newAgg) <= 7) {
//                    bashing = 7 - newLethal - newAgg;
//                    lethal = newLethal;
//                    agg = newAgg;
//
//                } else {
//                    bashing = 0;
//                    lethal = 7 - newAgg;
//                    agg = newAgg;
//                }
//                healthBoxes();
//            }
//        }).setNegativeButton("Cancel", null).show();

    }

    public void heal(View view) {
//        if (blood > 0) {
//            if (bashing > 0) {
//                blood--;
//                bashing--;
//            } else if (lethal > 0) {
//                blood--;
//                lethal--;
//            } else if (agg > 0 && blood >= 5) {
//                blood = blood - 5;
//                agg--;
//            }
//            currentBlood.setText(blood + "/15");
//            healthBoxes();
//        }
    }

    public void healthBoxes() {
//        for (int i = 6; i >= (bashing + lethal + agg); i--) {
//            //healthBox[i] = R.drawable.square_empty;
//        }
//        for (int i = (bashing + lethal + agg - 1); i >= (lethal + agg); i--) {
//            //healthBox[i] = R.drawable.square_bashing;
//        }
//        for (int i = (lethal + agg - 1); i >= agg; i--) {
//            //healthBox[i] = R.drawable.square_lethal;
//        }
//        for (int i = agg - 1; i >= 0; i--) {
//            //healthBox[i] = R.drawable.square_agg;
//        }
//        healthBox1.setImageDrawable(getResources().getDrawable(healthBox[0]));
//        healthBox2.setImageDrawable(getResources().getDrawable(healthBox[1]));
//        healthBox3.setImageDrawable(getResources().getDrawable(healthBox[2]));
//        healthBox4.setImageDrawable(getResources().getDrawable(healthBox[3]));
//        healthBox5.setImageDrawable(getResources().getDrawable(healthBox[4]));
//        healthBox6.setImageDrawable(getResources().getDrawable(healthBox[5]));
//        healthBox7.setImageDrawable(getResources().getDrawable(healthBox[6]));
    }

    public void chooseForm(View view) {

//        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Choose Form").setItems(R.array.forms, new DialogInterface.OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                if (which != 0 && blood > 0) {
//                    blood--;
//                    currentBlood.setText(blood + "/15");
//                    if (which == 1) {
//                        //pirateForm.setImageResource(R.drawable.parrot_form);
//                        attributes[0] = 4;
//                        if (attributes1.getRating() > 4)
//                            attributes1.setRating(attributes1.getRating() - 1);
//                        form.setText(R.string.parrot_form);
//                    } else {
//                        attributes[0] = 5;
//                        if (attributes1.getRating() < 5)
//                            attributes1.setRating(attributes[0]);
//                        //pirateForm.setImageResource(R.drawable.octopus_form);
//                        form.setText(R.string.octopus_form);
//                    }
//                } else if (which == 0) {
//                    attributes[0] = 5;
//                    //pirateForm.setImageResource(R.drawable.pirate_form);
//                    if (attributes1.getRating() < 5)
//                        attributes1.setRating(attributes[0]);
//                    form.setText(R.string.human_form);
//                }
//
//            }
//        }).setNeutralButton("Cancel", null).show();

    }

//    public void moreRemainingXP(View view) {
//        xp++;
//        remainingXP.setText("" + xp);
//    }
//
//    public void lessRemainingXP(View view) {
//        xp--;
//        remainingXP.setText("" + xp);
//    }
//
//    public void moreTotalXP(View view) {
//        xpTotal++;
//        totalXP.setText("" + xpTotal);
//    }
//
//    public void lessTotalXP(View view) {
//        xpTotal--;
//        totalXP.setText("" + xpTotal);
//    }
//
//    public void moreBlood(View view) {
//        if (blood < 15)
//            blood++;
//        currentBlood.setText(blood + "/15");
//    }
//
//    public void lessBlood(View view) {
//        if (blood > 0)
//            blood--;
//        currentBlood.setText(blood + "/15");
//    }

    public void animalism(View view) {
        int[] messages = { R.string.feral_whispers, R.string.beckoning, R.string.quell_the_beast, R.string.subsume_the_spirit,
                R.string.drawing_out_the_beast };
        message("Animalism", R.array.animalism_array, messages);
    }

    public void auspex(View view) {
        int[] messages = { R.string.heightened_senses, R.string.aura_perception, R.string.the_spirits_touch, R.string.telepathy,
                R.string.psychic_projection };
        message("Auspex", R.array.auspex_array, messages);
    }

    public void dominate(View view) {
        int[] messages = { R.string.dominate, R.string.command, R.string.mesmerize, R.string.the_forgetful_mind, R.string.conditioning,
                R.string.possession };
        message("Dominate", R.array.dominate_array, messages);
    }

    public void fortitude(View view) {
        message("Fortitude", R.string.fortitude);
    }

    public void necromancySepulchre(View view) {
        int[] messages = { R.string.necromancy, R.string.witness_of_death, R.string.summon_soul, R.string.compel_soul, R.string.haunting,
                R.string.torment };
        message("Necromancy", R.array.sepulchre_array, messages);
    }

    public void necromancyBone(View view) {
        int[] messages = { R.string.zombies, R.string.tremens, R.string.apprentices_brooms, R.string.shambling_hordes,
                R.string.soul_stealing, R.string.daemonic_possession };
        message("Necromancy", R.array.bone_array, messages);
    }

    public void potence(View view) {
        message("Potence", R.string.potence);
    }

    public void protean(View view) {
        int[] messages = { R.string.eyes_of_the_beast, R.string.feral_claws, R.string.earth_meld, R.string.shape_of_the_beast,
                R.string.mist_form };
        message("Protean", R.array.protean_array, messages);
    }

    public void thaumaturgyBlood(View view) {
        int[] messages = { R.string.thaumaturgy, R.string.a_taste_for_blood, R.string.blood_rage, R.string.blood_of_potency,
                R.string.theft_of_vitae, R.string.cauldron_of_blood };

        message("Thaumaturgy", R.array.blood_array, messages);
    }

    public void thaumaturgyFlames(View view) {
        int[] messages = { R.string.thaumaturgy, R.string.lure_of_flames };
        message("Thaumaturgy", R.array.flames_array, messages);
    }

    public void thaumaturgyRituals(View view) {
        int[] messages = { R.string.thaum_rituals, R.string.knowing_stone_transport };
        message("Thaumaturgy Rituals", R.array.thaum_ritual_array, messages);
    }

    public void necromancyRituals(View view) {
        int[] messages = { R.string.necro_rituals, R.string.knowing_stone };
        message("Necromancy Rituals", R.array.necro_ritual_array, messages);
    }

    public void path(View view) {

        message("Path of the Buccaneer", R.string.path_of_the_buccaneer);
    }

    public void message(String title, int message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title).setMessage(message).setNeutralButton("Dismiss", null).show();
    }

    // fix inputs and set up onClick
    public void message(final String title, final int itemsId, final int[] messages) {
        final String[] items = getResources().getStringArray(itemsId);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title).setItems(itemsId, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                builder.setTitle(items[which]).setMessage(messages[which]).setNeutralButton("Dismiss", null)
                        .setNegativeButton("Back", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                message(title, itemsId, messages);
                            }
                        }).show();
            }
        }).setNeutralButton("Dismiss", null).show();
    }
}
