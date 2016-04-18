package comet.hackathonfoodapp;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Zehcnas on 2/24/2016.
 */
public class NoteCursorAdapter extends CursorRecyclerViewAdapter<NoteCursorAdapter.NoteViewHolder> {

    OnItemClickListener mOnItemClickListener;

    public NoteCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, null);
        return new NoteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder viewHolder, Cursor cursor) {
        String title = cursor.getString(cursor.getColumnIndex(Note.COLUMN_TITLE));
        viewHolder.tvTitle.setText(title);
        viewHolder.tvTitle.setTag(cursor.getInt(cursor.getColumnIndex(Note.COLUMN_ID)));
        viewHolder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dbid = Integer.parseInt(v.getTag().toString());
                mOnItemClickListener.onItemClick(dbid);
            }
        });
    }

    public void setmOnItemClickListener(OnItemClickListener m){

        this.mOnItemClickListener = m;

    }

    public interface OnItemClickListener{
        public void onItemClick(int id);
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;

        public NoteViewHolder(View itemView) {
            super(itemView);
            //findViewById
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);

        }
    }






}
