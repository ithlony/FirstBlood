package com.example.tasklist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.tasklist.core.Task;
import com.example.tasklist.core.TaskStorage;
import com.example.tasklist.dummy.DummyContent;

/**
 * A fragment representing a single Item detail screen. This fragment is either
 * contained in a {@link ItemListActivity} in two-pane mode (on tablets) or a
 * {@link ItemDetailActivity} on handsets.
 */
public class ItemDetailFragment extends Fragment {
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	/**
	 * The dummy content this fragment is presenting.
	 */
	private DummyContent.DummyItem mItem;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public ItemDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		
		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			mItem = DummyContent.ITEM_MAP.get(getArguments().getString(
					ARG_ITEM_ID));
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_item_detail,
				container, false);
		Context context = inflater.getContext();
		
		
		// Show the dummy content as text in a TextView.
		if (mItem != null) {
			String x;
			if (mItem.id.equals("1")) {
				rootView = inflater.inflate(R.layout.new_task_detail,
							container, false);
				create_new_task_view(rootView);
				/*
				TextView tv = ((TextView) rootView
						.findViewById(R.id.item_detail));
				tv.setText("Please add a new task!");
				*/
				
			} else {
				rootView = inflater.inflate(R.layout.list_of_tasks, container,
						false);
				// tv.setText("Show tasks!");
				ListView listView = (ListView) rootView
						.findViewById(R.id.TaskListView);
				
				TaskStorage t = new TaskStorage(context);
				List<Task> task_list = t.get_tasks();


				if (task_list == null || task_list.isEmpty()) {
					listView.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_expandable_list_item_1, new String[] {"No Task!"}));
				} else {
					
					SimpleAdapter adapter = new SimpleAdapter(
							context,
							task_list_for_view(task_list),// t.get_tasks(),
							R.layout.task_list_item, new String[] {
									"TaskTitle", "TaskInfo" }, new int[] {
									R.id.TaskTitle, R.id.TaskInfo });
					listView.setAdapter(adapter);
					
				}
				
			}
		}

		return rootView;
	}

	ArrayList<HashMap<String, String>> task_list_for_view(List<Task> task_list) {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		for (Task task : task_list) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("TaskTitle", task.title);
			map.put("TaskInfo", task.detail);
			//map.put("TaskType", String.valueOf(task.frequency));
			list.add(map);
		}

		return list;
	}
	
	void create_new_task_view(View view)
	{
		Button button = (Button)view.findViewById(R.id.BTN_add_new_task);
		final EditText task_title_view = (EditText) view.findViewById(R.id.TXT_task_title);
		final EditText task_detail_view = (EditText) view.findViewById(R.id.TXT_task_detail);
		
		
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				
				TaskStorage t = new TaskStorage(view.getContext());
				Task task = new Task();
				
				task.title = task_title_view.getText().toString();
				task.detail = task_detail_view.getText().toString();
				
				if (task.title.length() == 0)
				{
					Toast.makeText(view.getContext(), "Empty task title!", Toast.LENGTH_SHORT).show();
				} else {
					t.add_task(task);
					Toast.makeText(view.getContext(), "New task added!", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
}
