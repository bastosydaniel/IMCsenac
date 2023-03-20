package co.tiagoaguiar.codelab.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	private View btnImc;
	private RecyclerView rvMain;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		rvMain = findViewById(R.id.main_rb);

	List<MainItem> mainItems = new ArrayList<>();
	mainItems.add(new MainItem(1,R.drawable.ic_baseline_whatshot_24,R.string.label_imc,Color.YELLOW));
	mainItems.add(new MainItem(2,R.drawable.ic_baseline_child_care_24,R.string.label_tmb,Color.MAGENTA));



		rvMain.setLayoutManager(new GridLayoutManager(this , 2));
		MainAdapter adapter = new MainAdapter(mainItems);
		adapter.setListener(new OnItenClickListener() {
			@Override
			public void onClick(int id) {
				switch (id){
					case 1:
						startActivity(new Intent(MainActivity.this, ImcActivity.class));
						break;
					case 2:

						startActivity( new Intent(MainActivity.this, TmbActivity.class));
						break;
				}

			}


		});
		rvMain.setAdapter(adapter);
	}

	private class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder>{
		private List<MainItem> mainItems;
		private OnItenClickListener listener;

		public MainAdapter(List<MainItem> mainItems){
			this.mainItems = mainItems;
		}
		public void setListener(OnItenClickListener listener){
			this.listener = listener;
		}

		@NonNull
		@Override
		public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			return new MainViewHolder(getLayoutInflater().inflate(R.layout.main_item, parent, false));
		}

		@Override
		public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
			MainItem mainItemCurrent = mainItems.get(position);
			holder.bind(mainItemCurrent);
		}

		@Override
		public int getItemCount() {
			return mainItems.size();
		}
		private class MainViewHolder extends RecyclerView.ViewHolder{

			public MainViewHolder(@NonNull View itemView) {
				super(itemView);
			}

			public void bind(final MainItem item) {
				TextView textName = itemView.findViewById(R.id.item_txt_name);
				ImageView imgIcon = itemView.findViewById(R.id.item_img_icon);
				LinearLayout btnImc = (LinearLayout) itemView.findViewById(R.id.btn_imc);

				btnImc.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						listener.onClick(item.getId());

					}
				});

				textName.setText(item.getTextStringID());
				imgIcon.setImageResource(item.getDrawableId());
				btnImc.setBackgroundColor(item.getColor());
			}
		}
	}

}