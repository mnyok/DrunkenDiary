package ms.drunkdiary.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import ms.drunkdiary.Activity.MainActivity;
import ms.drunkdiary.DiaryAdapter;
import ms.drunkdiary.DiaryData;
import ms.drunkdiary.R;

/**
 * Created by orc12 on 2015-12-05.
 */
public class DiaryFragment extends Fragment {
    TextView text_title;
    ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diary, container, false);

        text_title = (TextView) getActivity().findViewById(R.id.actionbar_title);
        text_title.setText("DRUNKEN DIARY");

        listView = (ListView) view.findViewById(R.id.diary_list);

        DiaryAdapter adapter = new DiaryAdapter(getActivity());
        listView.setAdapter(adapter);

        refreshDiary(adapter);
        return view;
    }

    public void refreshDiary(DiaryAdapter adapter){
        ArrayList<DiaryData> diaryList = MainActivity.dbHelper.getItemList();

        adapter.addAll(diaryList);
        adapter.notifyDataSetChanged();
        }
}
