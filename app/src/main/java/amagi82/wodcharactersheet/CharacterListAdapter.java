package amagi82.wodcharactersheet;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CharacterListAdapter extends ArrayAdapter<Character>{

    private final LayoutInflater inflater;
    private ArrayList<Character> arrayList;

    public CharacterListAdapter(Context context, ArrayList<Character> arrayList) {
        super(context, android.R.layout.simple_list_item_1, arrayList);
        this.inflater = LayoutInflater.from(context);
        this.arrayList = arrayList;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder viewHolder;

        // Reuse views
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_character, parent, false);

            // Configure ViewHolder
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.system = (TextView) convertView.findViewById(R.id.system);
            viewHolder.clan = (TextView) convertView.findViewById(R.id.clan);
            viewHolder.name.setTextColor(Color.BLACK);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        switch (arrayList.get(position).getSystem()){
            case "Vampire":
                viewHolder.system.setTextColor(getContext().getResources().getColor(R.color.darkRed));
                break;
            default:
                viewHolder.system.setTextColor(Color.BLACK);
        }
        viewHolder.name.setText(arrayList.get(position).getName());
        viewHolder.system.setText(arrayList.get(position).getSystem());
        viewHolder.clan.setText(arrayList.get(position).getClan());

        return convertView;
    }

    // ViewHolder increases speed and efficiency by recycling views rather than doing many findViewByIds, which are expensive.
    static class ViewHolder {
        TextView system, name, clan;
    }



}
