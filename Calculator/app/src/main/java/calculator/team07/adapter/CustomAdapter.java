package calculator.team07.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import calculator.team07.R;
import calculator.team07.model.Entity.Result;

public class CustomAdapter<puclic> extends ArrayAdapter<Result> {

    private Context mContext;
    private int mResource;
    private List<Result> mObjects;
    private String mTag = getClass().getSimpleName();

    public CustomAdapter(Context context, int resource, List<Result> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
        this.mObjects = objects;
    }

    //
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(this.mContext).inflate(R.layout.row_item, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.mTvExpression = (TextView) convertView.findViewById(R.id.text_view_expression);
            viewHolder.mTvAnswer = (TextView) convertView.findViewById(R.id.text_view_answer);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Result result = this.mObjects.get(position);
        viewHolder.mTvExpression.setText(result.ScreenMath);
        viewHolder.mTvAnswer.setText(result.Answer);
        return convertView;
    }

    public class ViewHolder {
        private TextView mTvAnswer;
        private TextView mTvExpression;
    }
}
