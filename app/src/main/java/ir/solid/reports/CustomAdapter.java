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

        holder.id_lbl.setText(details.getId());
        holder.date_lbl.setText(details.getJStyleDate());
        holder.lecture_lbl.setText(details.getLecture());
        holder.section_lbl.setText(details.getSection());
        holder.source_lbl.setText(details.getSource());
        holder.duration_lbl.setText(details.getDuration());
        holder.pages_lbl.setText(details.getPages());
        holder.function_lbl.setText(details.getFunction());

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

        TextView id_lbl, date_lbl, lecture_lbl, section_lbl, source_lbl,
                duration_lbl, pages_lbl, function_lbl;

        Button edit_btn, delete_btn;

        ConstraintLayout outer, expandableLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_lbl = itemView.findViewById(R.id.id_lbl);
            date_lbl = itemView.findViewById(R.id.date_lbl);
            lecture_lbl = itemView.findViewById(R.id.lecture_lbl);
            section_lbl = itemView.findViewById(R.id.section_lbl);
            source_lbl = itemView.findViewById(R.id.source_lbl);
            duration_lbl = itemView.findViewById(R.id.duration_lbl);
            pages_lbl = itemView.findViewById(R.id.pages_lbl);
            function_lbl = itemView.findViewById(R.id.function_lbl);

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