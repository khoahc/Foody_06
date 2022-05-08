package hcmute.edu.vn.nhom6.foody_06.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hcmute.edu.vn.nhom6.foody_06.Data.DatabaseAccess;
import hcmute.edu.vn.nhom6.foody_06.Modal.Comment;
import hcmute.edu.vn.nhom6.foody_06.R;

public class CommentAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<Comment> commentList;

    public CommentAdapter(Context context, int layout, List<Comment> commentList) {
        this.context = context;
        this.layout = layout;
        this.commentList = commentList;
    }

    @Override
    public int getCount() {
        return commentList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(inflater.getContext());
        databaseAccess.open();

        TextView textViewUser = (TextView) view.findViewById(R.id.textViewUser);
        String fullNameUser = databaseAccess.getFullNameUserById(commentList.get(i).getIdUser());
        textViewUser.setText(fullNameUser);

        TextView textViewComment = (TextView) view.findViewById(R.id.textViewComment);
        textViewComment.setText(commentList.get(i).getContent());

        TextView textViewTime = (TextView) view.findViewById(R.id.textViewTime);
        textViewTime.setText(commentList.get(i).getTimeCreate());

        return view;
    }
}
