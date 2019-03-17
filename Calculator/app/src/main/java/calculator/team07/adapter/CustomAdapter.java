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

    private Context context;
    private int resource;
    private List<Result> objects;
    private String tag = getClass().getSimpleName();
    //nhan cac doi tuong de xac dinh view cha va
    public CustomAdapter(Context context, int resource, List<Result> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    //
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //khoi tao lan dau
        ViewHolder viewHolder;
        if (convertView==null)
        {
            convertView = LayoutInflater.from(this.context).inflate(R.layout.row_item,parent,false);
            viewHolder= new ViewHolder();
            // xac dinh view de lay doi tuong
            viewHolder.mTvAnswer = (TextView) convertView.findViewById(R.id.text_view_expression);
            viewHolder.mTvExpression = (TextView)convertView.findViewById(R.id.text_view_answer);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder =(ViewHolder)convertView.getTag();
        }
        //Button btndelet =  (Button) convertView.findViewById(R.id.btn_delete);
        //lay doi tuong theo position
        Result result = this.objects.get(position);
        viewHolder.mTvExpression.setText(result.ScreenMath);
        viewHolder.mTvAnswer.setText(result.Answer);
        return convertView;
    }
    //viewholder
    public class ViewHolder
    {
        private TextView mTvAnswer;
        private TextView mTvExpression;
    }
}
