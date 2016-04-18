package comet.hackathonfoodapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Zehcnas on 2/24/2016.
 */
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder>{



    ArrayList<Note> notes;

    public NoteAdapter(ArrayList<Note> notes){

        this.notes = notes;
    }

    public class NoteHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        ImageView ivGenre;
        View container;
        public NoteHolder(View itemView) {
            super(itemView);

            //tvTitle = (TextView) itemView.findViewById(R.id.tv_songtitle);
           // ivGenre = (ImageView) itemView.findViewById(R.id.iv_genre);
            //container = itemView.findViewById(R.id.container);
        }
    }

    @Override
    public NoteHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.note_item, null);

        return new NoteHolder(v);
    }

    @Override
    public void onBindViewHolder(NoteHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
