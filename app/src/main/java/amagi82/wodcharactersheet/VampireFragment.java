package amagi82.wodcharactersheet;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import me.grantland.widget.AutofitTextView;


public class VampireFragment extends Fragment {

    Vampire vampire;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_vampire, container, false);

        vampire = (Vampire) MainActivity.characterArray.get(getArguments().getInt("index"));

        long time = SystemClock.elapsedRealtime();
        setPlayerData(rootView);
        Log.i("elapsed time player = ", "" + (SystemClock.elapsedRealtime() - time));

        time = SystemClock.elapsedRealtime();
        setAttributesBlock(inflater, rootView);
        Log.i("elapsed time attribu = ", "" + (SystemClock.elapsedRealtime() - time));

        time = SystemClock.elapsedRealtime();
        setAbilitiesBlock(inflater, rootView);
        Log.i("elapsed time ability = ", "" + (SystemClock.elapsedRealtime() - time));

        time = SystemClock.elapsedRealtime();
        setAdvantagesBlock(inflater, rootView);
        Log.i("elapsed time advanta = ", "" + (SystemClock.elapsedRealtime() - time));

        time = SystemClock.elapsedRealtime();
        setBottomBlock(inflater, rootView);
        Log.i("elapsed time bottom = ", "" + (SystemClock.elapsedRealtime() - time));

        return rootView;
    }

    private void setBottomBlock(LayoutInflater inflater, View rootView) {
        ViewGroup group = (ViewGroup) rootView.findViewById(R.id.bottomBlock);

        TextView tvTitleCenter = (TextView) group.findViewById(R.id.title_center);
        tvTitleCenter.setText(vampire.isPathOfHumanity()? getString(R.string.humanity) : getString(R.string.path));

        LinearLayout contentLeft = (LinearLayout) group.findViewById(R.id.content_left);
        LinearLayout contentCenter = (LinearLayout) group.findViewById(R.id.content_center);
        LinearLayout contentRight = (LinearLayout) group.findViewById(R.id.content_right);

        setMeritFlawBlock(inflater, contentLeft);

        AutofitTextView tvAutofitPath = (AutofitTextView) contentCenter.findViewById(R.id.tvAutofitPath);
        StatRatingBar statRatingBarPath = (StatRatingBar) contentCenter.findViewById(R.id.statRatingBarPath);
        StatRatingBar statRatingBarMaxWillpower = (StatRatingBar) contentCenter.findViewById(R.id.statRatingBarMaxWillpower);
        StatRatingBar statRatingBarCurrentWillpower = (StatRatingBar) contentCenter.findViewById(R.id.statRatingBarCurrentWillpower);
        StatRatingBar statRatingBarBloodPool1 = (StatRatingBar) contentCenter.findViewById(R.id.statRatingBarBloodPool1);
        StatRatingBar statRatingBarBloodPool2 = (StatRatingBar) contentCenter.findViewById(R.id.statRatingBarBloodPool2);
        TextView tvBloodPerTurn = (TextView) contentCenter.findViewById(R.id.tvBloodPerTurn);

        tvAutofitPath.setText(vampire.getPath());
        statRatingBarPath.setRating(vampire.getHumanityRating());
        statRatingBarMaxWillpower.setRating(vampire.getMaxWillpower());
        statRatingBarCurrentWillpower.setMaxRating(vampire.getMaxWillpower());
        statRatingBarCurrentWillpower.setRating(vampire.getCurrentWillpower());
        statRatingBarBloodPool1.setRating(vampire.getCurrentBloodpool());
        statRatingBarBloodPool2.setMaxRating(vampire.getMaxBloodpool() - 10);
        statRatingBarBloodPool2.setRating(vampire.getCurrentBloodpool() - 10);
        tvBloodPerTurn.setText(getString(R.string.blood_per_turn) + ": " + vampire.getBloodPerTurn());

        StatRatingBar statRatingBarHealth = (StatRatingBar) contentRight.findViewById(R.id.statRatingBarHealthLevel);
        statRatingBarHealth.setHealthAgg(vampire.getDamageAgg());
        statRatingBarHealth.setHealthLethal(vampire.getDamageLethal());
        statRatingBarHealth.setHealthBashing(vampire.getDamageBashing());

        AutofitTextView tvAutofitWeaknesses = (AutofitTextView) contentRight.findViewById(R.id.tvAutofitWeaknesses);
        TextView tvExperience = (TextView) contentRight.findViewById(R.id.tvExperience);

        //tvAutofitWeaknesses.setText(vampire.getWeaknessesList());
        tvExperience.setText(vampire.getCurrentXP() + " / " + vampire.getTotalXP());
    }

    private void setAdvantagesBlock(LayoutInflater inflater, View rootView) {

        TextView tvHeader = (TextView) rootView.findViewById(R.id.headerAdvantages);
        tvHeader.setText(getArray(R.array.headers)[2]);

        ViewGroup group = (ViewGroup) rootView.findViewById(R.id.advantagesBlock);
        TextView tvTitleLeft = (TextView) group.findViewById(R.id.title_left);
        TextView tvTitleCenter = (TextView) group.findViewById(R.id.title_center);
        TextView tvTitleRight = (TextView) group.findViewById(R.id.title_right);

        tvTitleLeft.setText(getArray(R.array.advantagesTitles)[0]);
        tvTitleCenter.setText(getArray(R.array.advantagesTitles)[1]);
        tvTitleRight.setText(getArray(R.array.advantagesTitles)[2]);

        LinearLayout contentLeft = (LinearLayout) group.findViewById(R.id.content_left);
        LinearLayout contentCenter = (LinearLayout) group.findViewById(R.id.content_center);
        LinearLayout contentRight = (LinearLayout) group.findViewById(R.id.content_right);

        setDisciplinesStatBlock(inflater, contentLeft);
        setStatBlock(vampire.getBackgroundsList(), inflater, contentCenter);
        setStatBlock(vampire.getVirtuesList(), inflater, contentRight);
    }

    private void setAttributesBlock(LayoutInflater inflater, View rootView) {

        TextView tvHeader = (TextView) rootView.findViewById(R.id.headerAttributes);
        tvHeader.setText(getArray(R.array.headers)[0]);

        ViewGroup group = (ViewGroup) rootView.findViewById(R.id.attributesBlock);
        TextView tvTitleLeft = (TextView) group.findViewById(R.id.title_left);
        TextView tvTitleCenter = (TextView) group.findViewById(R.id.title_center);
        TextView tvTitleRight = (TextView) group.findViewById(R.id.title_right);

        tvTitleLeft.setText(getArray(R.array.attributesTitles)[0]);
        tvTitleCenter.setText(getArray(R.array.attributesTitles)[1]);
        tvTitleRight.setText(getArray(R.array.attributesTitles)[2]);

        LinearLayout contentLeft = (LinearLayout) group.findViewById(R.id.content_left);
        LinearLayout contentCenter = (LinearLayout) group.findViewById(R.id.content_center);
        LinearLayout contentRight = (LinearLayout) group.findViewById(R.id.content_right);

        setStatBlock(R.array.physical, inflater, contentLeft);
        setStatBlock(R.array.social, inflater, contentCenter);
        setStatBlock(R.array.mental, inflater, contentRight);
    }

    private void setAbilitiesBlock(LayoutInflater inflater, View rootView) {

        TextView tvHeader = (TextView) rootView.findViewById(R.id.headerAbilities);
        tvHeader.setText(getArray(R.array.headers)[1]);

        ViewGroup group = (ViewGroup) rootView.findViewById(R.id.abilitiesBlock);
        TextView tvTitleLeft = (TextView) group.findViewById(R.id.title_left);
        TextView tvTitleCenter = (TextView) group.findViewById(R.id.title_center);
        TextView tvTitleRight = (TextView) group.findViewById(R.id.title_right);

        tvTitleLeft.setText(getArray(R.array.abilitiesTitles)[0]);
        tvTitleCenter.setText(getArray(R.array.abilitiesTitles)[1]);
        tvTitleRight.setText(getArray(R.array.abilitiesTitles)[2]);

        LinearLayout contentLeft = (LinearLayout) group.findViewById(R.id.content_left);
        LinearLayout contentCenter = (LinearLayout) group.findViewById(R.id.content_center);
        LinearLayout contentRight = (LinearLayout) group.findViewById(R.id.content_right);

        setStatBlock(R.array.talents, inflater, contentLeft);
        setStatBlock(R.array.skills, inflater, contentCenter);
        setStatBlock(R.array.knowledges, inflater, contentRight);
    }

    private void setStatBlock(int arrayId, LayoutInflater inflater, LinearLayout content) {

        for (int i = 0; i < getArray(arrayId).length; i++) {
            LinearLayout stat = (LinearLayout) inflater.inflate(R.layout.stat, content);
            AutofitTextView tvAutofitStat = (AutofitTextView) stat.findViewById(R.id.tvAutofitStat);
            tvAutofitStat.setId(i + 1);
            tvAutofitStat.setText(getArray(arrayId)[i]);
            StatRatingBar statRatingBar = (StatRatingBar) stat.findViewById(R.id.statRatingBar);
            statRatingBar.setId(-i);
            statRatingBar.setRating(vampire.getValuesMap().get(getArray(arrayId)[i]));
        }
    }

    private void setStatBlock(ArrayList<String> list, LayoutInflater inflater, LinearLayout content) {

        for (int i = 0; i < list.size(); i++) {
            LinearLayout stat = (LinearLayout) inflater.inflate(R.layout.stat, content);
            AutofitTextView tvAutofitStat = (AutofitTextView) stat.findViewById(R.id.tvAutofitStat);
            tvAutofitStat.setId(i + 1);
            tvAutofitStat.setText(list.get(i));
            StatRatingBar statRatingBar = (StatRatingBar) stat.findViewById(R.id.statRatingBar);
            statRatingBar.setId(-i);
            statRatingBar.setRating(vampire.getValuesMap().get(list.get(i)));
        }
    }

    /*
        This method sets the disciplines by running through the discipline list for the character. If it detects Necromancy or Thaumaturgy, it adds
        all the respective Necromancy or Thaumaturgy Paths before continuing, indented them with padding, and reduces the text size so they fit.
        The variable 'content' is the linear layout container all the individual stat layouts are added to.
        TODO: test how this looks on different screen sizes and configurations
     */
    private void setDisciplinesStatBlock(LayoutInflater inflater, LinearLayout content) {
        ArrayList<String> list = vampire.getDisciplinesList();

        String necromancy = getArray(R.array.disciplines)[7];
        String thaumaturgy = getArray(R.array.disciplines)[15];

        for (int i = 0; i < list.size(); i++) {
            content.addView(getStatLayout(inflater, content, list, i, false));

            if(list.get(i).equals(necromancy)){
                for(int j = 0; j < vampire.getNecromancyPathsList().size(); j++){
                    content.addView(getStatLayout(inflater, content, vampire.getNecromancyPathsList(), j, true));
                }
            }
            if(list.get(i).equals(thaumaturgy)){
                for (int j = 0; j < vampire.getThaumaturgyPathsList().size(); j++){
                    content.addView(getStatLayout(inflater, content, vampire.getThaumaturgyPathsList(), j, true));
                }
            }
        }
    }

    //Convenience method for setDisciplineStatBlock()
    private LinearLayout getStatLayout(LayoutInflater inflater, LinearLayout content, ArrayList<String> list, int i, boolean isPath) {
        LinearLayout stat = (LinearLayout) inflater.inflate(R.layout.stat, content, false);
        AutofitTextView tvAutofitStat = (AutofitTextView) stat.findViewById(R.id.tvAutofitStat);
        tvAutofitStat.setId(i + 1);
        StatRatingBar statRatingBar = (StatRatingBar) stat.findViewById(R.id.statRatingBar);
        statRatingBar.setId(-i);
        statRatingBar.setRating(vampire.getValuesMap().get(list.get(i)));
        if(isPath){
            tvAutofitStat.setText("  -" + list.get(i));
            tvAutofitStat.setGravity(Gravity.CENTER_VERTICAL);
            tvAutofitStat.setMaxTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
            tvAutofitStat.setMinTextSize(TypedValue.COMPLEX_UNIT_SP, 5);
        }else{
            tvAutofitStat.setText(list.get(i));
        }
        return stat;
    }

    private void setMeritFlawBlock(LayoutInflater inflater, LinearLayout content) {

        String category;
        int meritsSize = vampire.getMeritsList().size();
        int flawsSize = vampire.getFlawsList().size();
        if(meritsSize+flawsSize == 0) return;

        for (int i = 0; i < (meritsSize + flawsSize + 1); i++) {
            LinearLayout meritFlaw = (LinearLayout) inflater.inflate(R.layout.merit_flaw, content);
            AutofitTextView tvAutofitMeritFlaw = (AutofitTextView) meritFlaw.findViewById(R.id.tvAutofitMeritFlaw);
            tvAutofitMeritFlaw.setId(i + 1);
            category = i == 0 ? getString(R.string.merit) : i <= meritsSize ? vampire.getMeritsList().get(i-1) : i == meritsSize + 1 ?
                    getString(R.string.flaw) : vampire.getFlawsList().get(i-meritsSize-1);
            tvAutofitMeritFlaw.setText(category);
            TextView tvCost = (TextView) meritFlaw.findViewById(R.id.tvCost);
            tvCost.setId(-i);
            tvCost.setText(i == 0 || i == meritsSize + 1? getString(R.string.cost) : ""+vampire.getValuesMap().get(category));
            if(tvCost.getText().equals(getString(R.string.cost))){
                tvAutofitMeritFlaw.setTypeface(null, Typeface.BOLD);
                tvAutofitMeritFlaw.setTextColor(getResources().getColor(R.color.textDark));
                tvCost.setTypeface(null, Typeface.BOLD);
                if(tvAutofitMeritFlaw.getText().equals(getString(R.string.flaw))) tvCost.setPadding(0,(int) (4*getResources().getDisplayMetrics().density + 0.5f),0,0);
            }
        }
    }

    private void setPlayerData(View rootView) {
        TextView tvRowLeft1 = (TextView) rootView.findViewById(R.id.text_left1);
        TextView tvRowLeft2 = (TextView) rootView.findViewById(R.id.text_left2);
        TextView tvRowLeft3 = (TextView) rootView.findViewById(R.id.text_left3);
        TextView tvRowCenter1 = (TextView) rootView.findViewById(R.id.text_center1);
        TextView tvRowCenter2 = (TextView) rootView.findViewById(R.id.text_center2);
        TextView tvRowCenter3 = (TextView) rootView.findViewById(R.id.text_center3);
        TextView tvRowRight1 = (TextView) rootView.findViewById(R.id.text_right1);
        TextView tvRowRight2 = (TextView) rootView.findViewById(R.id.text_right2);
        TextView tvRowRight3 = (TextView) rootView.findViewById(R.id.text_right3);

        int gen = vampire.getGeneration();

        tvRowLeft1.setText(buildString(getArray(R.array.playerData)[0], vampire.getName()));
        tvRowLeft2.setText(buildString(getArray(R.array.playerData)[1], vampire.getPlayer()));
        tvRowLeft3.setText(buildString(getArray(R.array.playerData)[2], vampire.getChronicle()));
        tvRowCenter1.setText(buildString(getArray(R.array.playerData)[3], vampire.getNature()));
        tvRowCenter2.setText(buildString(getArray(R.array.playerData)[4], vampire.getDemeanor()));
        tvRowCenter3.setText(buildString(getArray(R.array.playerData)[5], vampire.getConcept()));
        tvRowRight1.setText(buildString(getArray(R.array.playerData)[6], vampire.getClan()));
        tvRowRight2.setText(buildString(getArray(R.array.playerData)[7], gen+ (gen>3? "th": gen == 3? "rd" : gen == 2? "nd" : "st")));
        tvRowRight3.setText(buildString(getArray(R.array.playerData)[8], vampire.getSire()));
    }

    private SpannableStringBuilder buildString(String start, String end) {
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder();
        stringBuilder.append(start);
        stringBuilder.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.textDark)), 0, stringBuilder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        stringBuilder.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, stringBuilder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        stringBuilder.append(" ");
        stringBuilder.append(end);
        return stringBuilder;
    }

    private String[] getArray(int id) {
        return getResources().getStringArray(id);
    }
}
