package ir.solid.reports;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private List<Details> detailsList;

    public CustomAdapter(Activity activity, Context context, List<Details> detailsList) {
        this.activity = activity;
        this.context = context;
        this.detailsList = detailsList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_show_activity, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {

        Details details = detailsList.get(position);

        holder.id_txt.setText(details.getId());
        holder.date_txt.setText(details.getJStyleDate());
        holder.lecture_txt.setText(details.getLecture());
        holder.section_txt.setText(details.getSection());
        holder.source_txt.setText(details.getSource());
        holder.duration_txt.setText(details.getDuration());
        holder.pages_txt.setText(details.getPages());
        holder.function_txt.setText(details.getFunction());

        boolean isExpandable = detailsList.get(position).isExpandable();
        holder.expandableLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);

        holder.edit_btn.setOnClickListener(view -> {
            Intent intent = new Intent(context, UpdateActivity.class);
            intent.putExtra("id", details.getId());
            intent.putExtra("date", details.getDate());
            intent.putExtra("lecture", details.getLecture());
            intent.putExtra("section", details.getSection());
            intent.putExtra("source", details.getSource());
            intent.putExtra("duration", details.getDuration());
            intent.putExtra("pages", details.getPages());
            intent.putExtra("function", details.getFunction());
            activity.startActivityForResult(intent, 1);
        });

        holder.delete_btn.setOnClickListener(view -> {
            MyDatabaseHelper myDB = new MyDatabaseHelper(context);
            myDB.deleteOneRow(details.getId());
        });
    }

    @Override
    public int getItemCount() {
        return detailsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id_txt, date_txt, lecture_txt, section_txt, source_txt,
                duration_txt, pages_txt, function_txt;

        Button edit_btn, delete_btn;

        ConstraintLayout outer, expandableLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_txt = itemView.findViewById(R.id.id_txt);
            date_txt = itemView.findViewById(R.id.date_txt);
            lecture_txt = itemView.findViewById(R.id.lecture_txt);
            section_txt = itemView.findViewById(R.id.section_txt);
            source_txt = itemView.findViewById(R.id.source_txt);
            duration_txt = itemView.findViewById(R.id.duration_txt);
            pages_txt = itemView.findViewById(R.id.pages_txt);
            function_txt = itemView.findViewById(R.id.function_txt);

            edit_btn = itemView.findViewById(R.id.edit_btn);
            delete_btn = itemView.findViewById(R.id.delete_btn);

            outer = itemView.findViewById(R.id.outer);
            expandableLayout = itemView.findViewById(R.id.expandable_layout);

            outer.setOnClickListener(view -> {
                Details details = detailsList.get(getAdapterPosition());
                details.setExpandable(!details.isExpandable());
                notifyItemChanged(getAdapterPosition());
            });
        }
    }
}