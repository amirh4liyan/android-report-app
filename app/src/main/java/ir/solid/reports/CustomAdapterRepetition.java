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


public class CustomAdapterRepetition extends RecyclerView.Adapter<CustomAdapterRepetition.MyViewHolder> {

    private Context context;
    private Activity activity;
    private List<DetailsRepetition> detailsList;

    public CustomAdapterRepetition(Activity activity, Context context, List<DetailsRepetition> detailsList) {
        this.activity = activity;
        this.context = context;
        this.detailsList = detailsList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_today_activity, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterRepetition.MyViewHolder holder, int position) {

        DetailsRepetition details = detailsList.get(position);

        holder.date_txt.setText(details.getJStyleDate());
        holder.repetition_txt.setText(details.getRepetition());
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

        TextView date_txt, repetition_txt, section_txt, source_txt,
                duration_txt, pages_txt, function_txt;

        Button edit_btn, delete_btn;

        ConstraintLayout outer, expandableLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date_txt = itemView.findViewById(R.id.date_lbl2);
            repetition_txt = itemView.findViewById(R.id.repetition_lbl);
            section_txt = itemView.findViewById(R.id.section_lbl2);
            source_txt = itemView.findViewById(R.id.source_lbl2);
            duration_txt = itemView.findViewById(R.id.duration_lbl2);
            pages_txt = itemView.findViewById(R.id.pages_lbl2);
            function_txt = itemView.findViewById(R.id.function_lbl2);

            edit_btn = itemView.findViewById(R.id.edit_btn2);
            delete_btn = itemView.findViewById(R.id.delete_btn2);

            outer = itemView.findViewById(R.id.outer2);
            expandableLayout = itemView.findViewById(R.id.expandable_layout2);

            outer.setOnClickListener(view -> {
                DetailsRepetition details = detailsList.get(getAdapterPosition());
                details.setExpandable(!details.isExpandable());
                notifyItemChanged(getAdapterPosition());
            });
        }
    }
}

